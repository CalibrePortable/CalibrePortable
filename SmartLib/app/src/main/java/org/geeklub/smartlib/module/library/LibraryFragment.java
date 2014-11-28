package org.geeklub.smartlib.module.library;

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
import org.geeklub.smartlib.api.Constant;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.LibraryAdapter;
import org.geeklub.smartlib.module.base.BasePageListFragment;
import org.geeklub.smartlib.module.detail.BookDetailActivity;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryFragment extends BasePageListFragment<NormalUserService> {

  public static Fragment newInstance() {
    Fragment libraryFragment = new LibraryFragment();
    return libraryFragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    ((LibraryAdapter) mAdapter).setOnItemClickListener(new LibraryAdapter.OnItemClickListener() {
      @Override public void onItemClick(SummaryBook summaryBook) {
        Intent intent = new Intent(mActivity, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRAS_BOOK, summaryBook);
        startActivity(intent);
      }
    });

    ((LibraryAdapter) mAdapter).setOnFavourClickListener(
        new LibraryAdapter.OnFavourClickListener() {
          @Override public void onFavourClick(SummaryBook summaryBook) {
            summaryBook.isLike = "1";
            summaryBook.favour = Integer.valueOf(summaryBook.favour) + 1 + "";
            sendDianZanMsg(summaryBook);
          }
        });

    return view;
  }

  private void sendDianZanMsg(SummaryBook summaryBook) {
    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
    mService.likePlusOne(summaryBook.book_kind, user.getUserId(), user.getPassWord(),
        new Callback<ServerResponse>() {
          @Override public void success(ServerResponse serverResponse, Response response) {
            if (serverResponse.getStatus() == Constant.RESULT_SUCCESS) {
              LogUtil.i("点赞成功");
            } else {
              LogUtil.i("点赞失败");
            }
          }

          @Override public void failure(RetrofitError error) {

            LogUtil.i(error.getMessage());
          }
        });
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
    return NormalUserService.class;
  }

  @Override protected void executeRequest(int page) {

    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();

    mService.search(user.getUserId(), 5, page, "all", new Callback<List<SummaryBook>>() {
      @Override public void success(List<SummaryBook> bookList, Response response) {

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
}
