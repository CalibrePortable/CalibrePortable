package org.geeklub.smartlib.module.search;

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
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.adapters.SearchAdapter;
import org.geeklub.smartlib.module.base.BasePageListFragment;
import org.geeklub.smartlib.module.detail.BookDetailActivity;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/17.
 */
public class SearchFragment extends BasePageListFragment<NormalUserService> {

  private static final String ARGS_KEYWORD = "args_query_word";

  private String mQueryWord;

  public static Fragment newInstance(String keyWord) {

    Fragment searchFragment = new SearchFragment();
    Bundle args = new Bundle();
    args.putString(ARGS_KEYWORD, keyWord);
    searchFragment.setArguments(args);
    return searchFragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    parseArgument();
    View view = super.onCreateView(inflater, container, savedInstanceState);

    ((SearchAdapter) mAdapter).setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
      @Override public void onItemClick(Book book) {
        Intent intent = new Intent(mActivity, BookDetailActivity.class);
        intent.putExtra(BookDetailActivity.EXTRAS_BOOK, book);
        startActivity(intent);
      }
    });

    ((SearchAdapter) mAdapter).setOnFavourClickListener(new SearchAdapter.OnFavourClickListener() {
      @Override public void onFavourClick(Book book) {
        book.setIsLike(1);
        book.setFavour(Integer.valueOf(book.getFavour()) + 1 + "");
        sendDianZanMsg(book);
      }
    });

    return view;
  }

  private void parseArgument() {
    Bundle args = getArguments();
    mQueryWord = args.getString(ARGS_KEYWORD);
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_search;
  }

  @Override protected RecyclerView.Adapter newAdapter() {
    return new SearchAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class<NormalUserService> getServiceClass() {
    return NormalUserService.class;
  }

  @Override protected void executeRequest(int page) {

    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();

    mService.search(user.getUserId(), 1, page, mQueryWord, new Callback<List<Book>>() {
      @Override public void success(List<Book> bookList, Response response) {
        LogUtil.i(bookList.toString());

        mSwipeRefreshLayout.setRefreshing(false);

        if (mIsRefreshFromTop) {
          ((SearchAdapter) mAdapter).refresh(bookList);
        } else {
          ((SearchAdapter) mAdapter).addItems(bookList);
        }
        mPage++;
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i(error.getMessage());
        mSwipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  private void sendDianZanMsg(Book book) {
    SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
    mService.likePlusOne(book.getBook_id(), user.getUserId(), user.getPassWord(),
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
}
