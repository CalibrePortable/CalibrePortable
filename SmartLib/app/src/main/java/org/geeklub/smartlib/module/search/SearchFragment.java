package org.geeklub.smartlib.module.search;

import android.app.Fragment;
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
import java.util.List;
import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.SearchAdapter;
import org.geeklub.smartlib.module.base.BaseFragment;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import org.geeklub.smartlib.utils.ToastUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/17.
 */
public class SearchFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

  private static final String ARGS_KEYWORD = "args_query_word";

  private static final String ARGS_TYPE = "args_type";

  private String mQueryWord;

  private int mType;

  private SearchAdapter mAdapter;

  @InjectView(R.id.swipe_layout) SwipeRefreshLayout mRefreshLayout;

  @InjectView(R.id.recycle_view) RecyclerView mRecyclerView;

  private int mPage = 1;

  public static Fragment newInstance(int type, String keyWord) {
    Fragment searchFragment = new SearchFragment();
    Bundle args = new Bundle();
    args.putString(ARGS_KEYWORD, keyWord);
    args.putInt(ARGS_TYPE, type);
    searchFragment.setArguments(args);
    return searchFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new SearchAdapter(mActivity);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    parseArgument();
    View view = super.onCreateView(inflater, container, savedInstanceState);

    return view;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    mRefreshLayout.setOnRefreshListener(this);
    mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setAdapter(mAdapter);

    mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
  }

  private void loadFirstPage() {
    mPage = 1;
    loadData(mPage);
  }

  private void loadData(int page) {

    final boolean isRefreshFromTop = (page == 1);

    if (!mRefreshLayout.isRefreshing() && isRefreshFromTop) {
      mRefreshLayout.setRefreshing(true);
    }

    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
    NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

    service.search(user.getUserId(), mType, page, mQueryWord, new Callback<List<SummaryBook>>() {
      @Override public void success(List<SummaryBook> bookList, Response response) {
        //LogUtil.i(bookList.toString());
        mRefreshLayout.setRefreshing(false);
        if (isRefreshFromTop) {
          LogUtil.i("isRefreshFromTop ===>>>"+isRefreshFromTop);
          if (mAdapter.equals(bookList)) {
            ToastUtil.showShort("没有新的书本...");
          } else {
            mAdapter.replaceWith(bookList);
          }
        } else {
          mAdapter.addAll(bookList);
        }
        mPage++;
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i(error.getMessage());
        mRefreshLayout.setRefreshing(false);
      }
    });
  }

  private void loadNextPage() {
    loadData(mPage);
  }

  private void parseArgument() {
    Bundle args = getArguments();
    mQueryWord = args.getString(ARGS_KEYWORD);
    mType = args.getInt(ARGS_TYPE);
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_search;
  }

  @Override public void onRefresh() {
    loadFirstPage();
  }
}
