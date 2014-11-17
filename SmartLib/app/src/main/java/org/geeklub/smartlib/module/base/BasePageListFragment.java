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
import retrofit.RestAdapter;

/**
 * Created by Vass on 2014/11/15.
 */
public abstract class BasePageListFragment<T> extends BaseFragment
    implements SwipeRefreshLayout.OnRefreshListener {

  public @InjectView(R.id.swipe_layout) SwipeRefreshLayout mSwipeRefreshLayout;

  public @InjectView(R.id.recycle_view) RecyclerView mRecycleView;

  protected int mPage = 1;

  protected boolean mIsRefreshFromTop;

  private RestAdapter mRestAdapter;

  protected T mService;

  protected RecyclerView.Adapter mAdapter;


  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    mAdapter = newAdapter();

    mRestAdapter = newRestAdapter();

    mService = mRestAdapter.create(getServiceClass());
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    mSwipeRefreshLayout.setOnRefreshListener(this);

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
    loadData(mPage);
  }

  private void loadData(int page) {

    mIsRefreshFromTop = (page == 1);

    if (!mSwipeRefreshLayout.isRefreshing() && mIsRefreshFromTop) {
      mSwipeRefreshLayout.setRefreshing(true);
    }

    executeRequest(page);
  }

  @Override public void onRefresh() {
    loadFirstPage();
  }

  protected abstract RecyclerView.Adapter newAdapter();

  protected abstract RestAdapter newRestAdapter();

  protected abstract Class<T> getServiceClass();

  protected abstract void executeRequest(int page);
}
