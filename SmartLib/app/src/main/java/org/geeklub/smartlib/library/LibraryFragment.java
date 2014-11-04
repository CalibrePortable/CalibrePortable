package org.geeklub.smartlib.library;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import org.geeklub.smartlib.adapters.LibraryAdapter;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.services.NormalUserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryFragment extends BaseFragment {

  @InjectView(R.id.recycler_view) RecyclerView mRecycleView;

  private RestAdapter mRestAdapter;

  private NormalUserService mService;

  private LibraryAdapter mAdapter;

  private int mPage = 1;

  public static Fragment newInstance() {

    Fragment libraryFragmen = new LibraryFragment();

    return libraryFragmen;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new LibraryAdapter(mActivity);

    mRestAdapter =
        new RestAdapter.Builder().setEndpoint("http://115.29.206.171/webservice/book/API.php")
            .build();

    mService = mRestAdapter.create(NormalUserService.class);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_library, null);

    ButterKnife.inject(this, view);

    mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));

    mRecycleView.setItemAnimator(new DefaultItemAnimator());

    mRecycleView.setHasFixedSize(false);

    mRecycleView.setAdapter(mAdapter);

    mRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        switch (newState) {
          case RecyclerView.SCROLL_STATE_IDLE:
            loadNextPage();

            break;
        }
      }
    });

    loadFirstPage();

    return view;
  }

  private void loadNextPage() {
    loadData(mPage + 1);
  }

  private void loadFirstPage() {
    loadData(1);
  }

  private void loadData(int page) {
    mService.search(5, page, "all", new Callback<List<Book>>() {
      @Override public void success(List<Book> bookList, Response response) {

      }

      @Override public void failure(RetrofitError error) {

      }
    });
  }
}
