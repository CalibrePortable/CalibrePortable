package org.geeklub.smartlib4admin.module.library;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.module.adapters.LibraryAdapter;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.base.BasePageListFragment;
import org.geeklub.smartlib4admin.utils.LogUtil;
import org.geeklub.smartlib4admin.utils.SmartLibraryUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/25.
 */
public class LibraryFragment extends BasePageListFragment<AdministratorService> {

  public static Fragment newInstance() {

    Fragment libraryFragment = new LibraryFragment();

    return libraryFragment;
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_library;
  }

  @Override protected RecyclerView.Adapter newAdapter() {
    return new LibraryAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class getServiceClass() {
    return AdministratorService.class;
  }

  @Override protected void executeRequest(int page) {
    LogUtil.i("下载数据");

    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();

    mService.search("12108238", "5", page, "all", new Callback<List<Book>>() {
      @Override public void success(List<Book> bookList, Response response) {
        LogUtil.i("下载了" + bookList.size() + "本书");

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

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    ((LibraryAdapter) mAdapter).setOnItemClickListener(new LibraryAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {

      }
    });

    ((LibraryAdapter) mAdapter).setOnFavourClickListener(
        new LibraryAdapter.OnFavourClickListener() {
          @Override public void onFavourClick(Book book) {
            book.setIsLike(1);
            book.setFavour(Integer.valueOf(book.getFavour()) + 1 + "");
          }
        });

    return view;
  }
}
