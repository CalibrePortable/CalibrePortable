package org.geeklub.smartlib.module.library;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.adapters.LibraryAdapter;
import org.geeklub.smartlib.beans.Book;
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

    ((LibraryAdapter) mAdapter).setOnFavourClickListener(
        new LibraryAdapter.OnFavourClickListener() {
          @Override public void onFavourClick(Book book, final View view, final int position) {
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.dianzan_anim);
            animation.setAnimationListener(new Animation.AnimationListener() {
              @Override public void onAnimationStart(Animation animation) {

              }

              @Override public void onAnimationEnd(Animation animation) {

                ((LibraryAdapter) mAdapter).updateItem(position);
              }

              @Override public void onAnimationRepeat(Animation animation) {

              }
            });
            if (view.getVisibility() == View.GONE) {
              view.setVisibility(View.VISIBLE);
              book.setLike(true);
              book.setFavour(Integer.valueOf(book.getFavour()) + 1 + "");
              view.startAnimation(animation);
            }
          }
        });

    return view;
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

    mService.search(user.getPassWord(), 5, page, "all", new Callback<List<Book>>() {
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
}
