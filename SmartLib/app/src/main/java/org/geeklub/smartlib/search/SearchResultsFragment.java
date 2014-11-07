package org.geeklub.smartlib.search;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.List;
import org.geeklub.smartlib.BaseFragment;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.adapters.SearchAdapter;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.detail.BookDetailActivity;
import org.geeklub.smartlib.services.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/7.
 */
public class SearchResultsFragment extends BaseFragment
    implements SwipeRefreshLayout.OnRefreshListener {

  private static final String ARGS_KEYWORD = "args_keyword";

  private String mKeyword;

  @InjectView(R.id.swipe_layout) SwipeRefreshLayout mSwipeRefreshLayout;

  @InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

  private SearchAdapter mAdapter;

  private NormalUserService mService;

  private RestAdapter mRestAdapter;

  private int mPage;

  public static Fragment newInstance(String keyWord) {
    Fragment searchFragment = new SearchResultsFragment();
    Bundle args = new Bundle();
    args.putString(ARGS_KEYWORD, keyWord);
    searchFragment.setArguments(args);
    return searchFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new SearchAdapter(mActivity);
    mRestAdapter =
        new RestAdapter.Builder().setEndpoint("http://book.duanpengfei.com/API.php").build();

    mService = mRestAdapter.create(NormalUserService.class);
  }

  @Nullable @Override public View onCreateView(final LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search, null);

    parseArgument();

    ButterKnife.inject(this, view);

    mAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {
        Intent intent = new Intent(mActivity, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRAS_BOOK, book);
        startActivity(intent);
      }
    });

    mSwipeRefreshLayout.setOnRefreshListener(this);

    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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

    return view;
  }

  @Override public void onResume() {
    super.onResume();
  }

  private void parseArgument() {
    Bundle args = getArguments();
    mKeyword = args.getString(ARGS_KEYWORD);
  }

  @Override public void onRefresh() {
    loadFirstPage();
  }

  private void loadNextPage() {
    loadData(mPage);
  }

  private void loadFirstPage() {
    mPage = 1;
    loadData(mPage);
  }

  private void loadData(int page) {

    final boolean isRefreshFromTop = (page == 1);
    if (!mSwipeRefreshLayout.isRefreshing() && isRefreshFromTop) {
      mSwipeRefreshLayout.setRefreshing(true);
    }

    mService.search(5, page, mKeyword, new Callback<List<Book>>() {
      @Override public void success(List<Book> bookList, Response response) {
        LogUtil.i(bookList.toString());

        mSwipeRefreshLayout.setRefreshing(false);

        LogUtil.i(bookList.toString());

        if (isRefreshFromTop) {
          mAdapter.refresh(bookList);
        } else {
          mAdapter.addItems(bookList);
        }
        mPage++;
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i(error.getMessage());
        mSwipeRefreshLayout.setRefreshing(false);
      }
    });
  }
}
