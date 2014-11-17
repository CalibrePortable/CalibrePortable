package org.geeklub.smartlib.module.user.library;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.adapters.LibraryAdapter;
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
 * Created by Vass on 2014/11/3.
 */
public class LibraryFragment extends BasePageListFragment<NormalUserService> {

  public static Fragment newInstance() {

    Fragment libraryFragmen = new LibraryFragment();

    return libraryFragmen;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    ((LibraryAdapter) mAdapter).setOnItemClickListener(new LibraryAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {
        Intent intent = new Intent(mActivity, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRAS_BOOK, book);
        startActivity(intent);
      }
    });

    return view;
  }

  @Override protected int getLayoutResources() {
    return R.layout.fragment_library;
  }

  @Override protected RecyclerView.Adapter newAdapter() {
    return new LibraryAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class getServiceClass() {
    return NormalUserService.class;
  }

  @Override protected void executeRequest(int page) {
    mService.search(mPreferencesUtil.getUser().getUserName(), 5, page, "all",
        new Callback<List<Book>>() {
          @Override public void success(List<Book> bookList, Response response) {

            LogUtil.i(bookList.toString());

            mSwipeRefreshLayout.setRefreshing(false);

            if (mIsRefreshFromTop) {
              ((LibraryAdapter) mAdapter).refresh(bookList);
            } else {
              ((LibraryAdapter) mAdapter).addItems(bookList);
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
