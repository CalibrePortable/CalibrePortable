package org.geeklub.smartlib.module.search;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.module.adapters.LibraryAdapter;
import org.geeklub.smartlib.module.base.BasePageListFragment;
import org.geeklub.smartlib.utils.LogUtil;
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

    Fragment userSearchFragment = new SearchFragment();
    Bundle args = new Bundle();
    args.putString(ARGS_KEYWORD, keyWord);
    userSearchFragment.setArguments(args);
    return userSearchFragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    parseArgument();
    View view = super.onCreateView(inflater, container, savedInstanceState);

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
    return new LibraryAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class<NormalUserService> getServiceClass() {
    return NormalUserService.class;
  }

  @Override protected void executeRequest(int page) {

    mService.search(mPreferencesUtil.getUser().getUserName(), 5, page, mQueryWord,
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
