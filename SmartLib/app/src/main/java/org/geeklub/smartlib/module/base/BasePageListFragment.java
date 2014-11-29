package org.geeklub.smartlib.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.InjectView;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.utils.LogUtil;

@Deprecated
public abstract class BasePageListFragment<T> extends BaseFragment
    implements SwipeRefreshLayout.OnRefreshListener {

  public @InjectView(R.id.swipe_layout) SwipeRefreshLayout mSwipeRefreshLayout;

  public @InjectView(R.id.recycle_view) RecyclerView mRecycleView;

  protected int mPage = 1;

  protected boolean mIsRefreshFromTop = false;

  protected RecyclerView.Adapter mAdapter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = newAdapter();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    mSwipeRefreshLayout.setOnRefreshListener(this);

    mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);

    mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
    mRecycleView.setItemAnimator(new DefaultItemAnimator());
    mRecycleView.setHasFixedSize(true);
    mRecycleView.setAdapter(mAdapter);

    mRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        switch (newState) {
          case RecyclerView.SCROLL_STATE_IDLE:
            loadNextPage();
            break;

          default:
            break;
        }
      }
    });

    loadFirstPage();

    return view;
  }

  private void loadFirstPage() {
    mPage = 1;
    loadData(mPage);
  }

  private void loadNextPage() {
    LogUtil.i("加载下一页");
    loadData(mPage);

   
  }

  private void loadData(int page) {
    mIsRefreshFromTop = (page == 1);
    if (!mSwipeRefreshLayout.isRefreshing() && mIsRefreshFromTop) {
      mSwipeRefreshLayout.setRefreshing(true);
    }
  }

  @Override public void onRefresh() {
    loadFirstPage();
  }

  protected abstract RecyclerView.Adapter newAdapter();
}
