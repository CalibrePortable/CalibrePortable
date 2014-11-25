package org.geeklub.smartlib4admin.module.lend;

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
import org.geeklub.smartlib4admin.module.adapters.LendAdapter;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.base.BasePageListFragment;
import org.geeklub.smartlib4admin.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/16.
 */
public class LendFragment extends BasePageListFragment<AdministratorService> {

  public static Fragment newInstance() {

    Fragment lendFragment = new LendFragment();

    return lendFragment;
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_lend;
  }

  @Override protected RecyclerView.Adapter newAdapter() {
    return new LendAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class<AdministratorService> getServiceClass() {
    return AdministratorService.class;
  }

  @Override protected void executeRequest(int page) {
    mService.haveLended("12108238", "123", page, new Callback<List<Book>>() {
      @Override public void success(List<Book> bookList, Response response) {
        LogUtil.i("下载了" + bookList.size() + "本书");
        mSwipeRefreshLayout.setRefreshing(false);
        if (mIsRefreshFromTop) {
          ((LendAdapter) mAdapter).refresh(bookList);
        } else {
          ((LendAdapter) mAdapter).addItems(bookList);
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

    ((LendAdapter) mAdapter).setOnItemClickListener(new LendAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {

      }
    });

    ((LendAdapter) mAdapter).setOnFavourClickListener(new LendAdapter.OnFavourClickListener() {
      @Override public void onFavourClick(Book book) {
        book.setIsLike(1);
        book.setFavour(Integer.valueOf(book.getFavour()) + 1 + "");
      }
    });

    return view;
  }
}
