package org.geeklub.smartlib.borrow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.List;
import org.geeklub.smartlib.BaseFragment;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.adapters.BorrowAdapter;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.detail.BookDetailActivity;
import org.geeklub.smartlib.services.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/8.
 */
public class BorrowFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

  @InjectView(R.id.swipe_layout) SwipeRefreshLayout mSwipeRefreshLayout;

  @InjectView(R.id.recycle_view) RecyclerView mRecycleView;

  private RestAdapter mRestAdapter;

  private NormalUserService mService;

  private BorrowAdapter mAdapter;

  public static Fragment newInstance() {

    Fragment borrowFragment = new BorrowFragment();

    return borrowFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new BorrowAdapter(mActivity);

    mRestAdapter =
        new RestAdapter.Builder().setEndpoint("http://book.duanpengfei.com/API.php").build();

    mService = mRestAdapter.create(NormalUserService.class);
  }

  @Nullable @Override public View onCreateView(final LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_library, null);

    ButterKnife.inject(this, view);

    mSwipeRefreshLayout.setOnRefreshListener(this);

    mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));

    mRecycleView.setItemAnimator(new DefaultItemAnimator());

    mRecycleView.setHasFixedSize(true);

    mAdapter.setOnItemClickListener(new BorrowAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {
        Intent intent = new Intent(mActivity, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRAS_BOOK, book);
        startActivity(intent);
      }
    });

    mRecycleView.setAdapter(mAdapter);

    loadFirstPage();

    return view;
  }

  private void loadFirstPage() {
    loadData(1);
  }

  private void loadData(int page) {

    final boolean isRefreshFromTop = (page == 1);
    if (!mSwipeRefreshLayout.isRefreshing() && isRefreshFromTop) {
      mSwipeRefreshLayout.setRefreshing(true);
    }

    mService.haveBorrowed("12108413", "12345", new Callback<List<Book>>() {
      @Override public void success(List<Book> bookList, Response response) {
        mSwipeRefreshLayout.setRefreshing(false);

        LogUtil.i(bookList.toString());

        if (isRefreshFromTop) {
          mAdapter.refresh(bookList);
        } else {
          mAdapter.addItems(bookList);
        }
      }

      @Override public void failure(RetrofitError error) {
        mSwipeRefreshLayout.setRefreshing(false);

        LogUtil.i(error.getMessage());
      }
    });
  }

  @Override public void onRefresh() {
    loadFirstPage();
  }
}
