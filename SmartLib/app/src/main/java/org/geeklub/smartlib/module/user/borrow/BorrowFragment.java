package org.geeklub.smartlib.module.user.borrow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.InjectView;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.adapters.BorrowAdapter;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.module.base.BasePageListFragment;
import org.geeklub.smartlib.module.user.detail.BookDetailActivity;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/8.
 */
public class BorrowFragment extends BasePageListFragment<NormalUserService> {

  @InjectView(R.id.swipe_layout) SwipeRefreshLayout mSwipeRefreshLayout;

  @InjectView(R.id.recycle_view) RecyclerView mRecycleView;

  //private RestAdapter mRestAdapter;

  //private NormalUserService mService;

  //private BorrowAdapter mAdapter;

  public static Fragment newInstance() {

    Fragment borrowFragment = new BorrowFragment();

    return borrowFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //mAdapter = new BorrowAdapter(mActivity);

    //mRestAdapter =
    //    new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();

    //mService = mRestAdapter.create(NormalUserService.class);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    //View view = inflater.inflate(R.layout.fragment_library, null);

    //ButterKnife.inject(this, view);

    //mSwipeRefreshLayout.setOnRefreshListener(this);

    //mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));

    //mRecycleView.setItemAnimator(new DefaultItemAnimator());

    //mRecycleView.setHasFixedSize(true);

    //mRecycleView.setAdapter(mAdapter);

    ((BorrowAdapter) mAdapter).setOnItemClickListener(new BorrowAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {
        Intent intent = new Intent(mActivity, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRAS_BOOK, book);
        startActivity(intent);
      }
    });

    //loadFirstPage();

    return view;
  }

  //private void loadFirstPage() {
  //  loadData(1);
  //}

  //private void loadData(int page) {
  //
  //  final boolean isRefreshFromTop = (page == 1);
  //  if (!mSwipeRefreshLayout.isRefreshing() && isRefreshFromTop) {
  //    mSwipeRefreshLayout.setRefreshing(true);
  //  }
  //
  //  SharedPreferencesUtil preferencesUtil = new SharedPreferencesUtil(mActivity);
  //
  //  mService.haveBorrowed(preferencesUtil.getUser().getUserName(),
  //      preferencesUtil.getUser().getPassword(), new Callback<List<Book>>() {
  //    @Override public void success(List<Book> bookList, Response response) {
  //      mSwipeRefreshLayout.setRefreshing(false);
  //
  //      LogUtil.i(bookList.toString());
  //
  //      if (isRefreshFromTop) {
  //        mAdapter.refresh(bookList);
  //      } else {
  //        mAdapter.addItems(bookList);
  //      }
  //    }
  //
  //    @Override public void failure(RetrofitError error) {
  //      mSwipeRefreshLayout.setRefreshing(false);
  //
  //      LogUtil.i(error.getMessage());
  //    }
  //  });
  //}

  //@Override public void onRefresh() {
  //  loadFirstPage();
  //}

  @Override protected int getLayoutResources() {
    return R.layout.fragment_borrow;
  }

  @Override protected RecyclerView.Adapter newAdapter() {
    return new BorrowAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class getServiceClass() {
    return NormalUserService.class;
  }

  @Override protected void executeRequest(int page) {

    mService.haveBorrowed(mPreferencesUtil.getUser().getUserName(),
        mPreferencesUtil.getUser().getPassword(), new Callback<List<Book>>() {
          @Override public void success(List<Book> bookList, Response response) {

            mSwipeRefreshLayout.setRefreshing(false);

            if (mIsRefreshFromTop) {
              ((BorrowAdapter) mAdapter).refresh(bookList);
            } else {
              ((BorrowAdapter) mAdapter).addItems(bookList);
            }
          }

          @Override public void failure(RetrofitError error) {

            LogUtil.i(error.getMessage());
            mSwipeRefreshLayout.setRefreshing(false);
          }
        });
  }
}
