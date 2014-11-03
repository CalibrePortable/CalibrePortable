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
import org.geeklub.smartlib.BaseFragment;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.adapters.LibraryAdapter;
import org.geeklub.smartlib.beans.WebAppsReponse;
import org.geeklub.smartlib.services.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryFragment extends BaseFragment {
  private int mPage = 1;

  @InjectView(R.id.recycler_view) RecyclerView mRecycleView;

  private LibraryAdapter mAdapter;

  public static Fragment newInstance() {

    Fragment libraryFragmen = new LibraryFragment();

    return libraryFragmen;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new LibraryAdapter(mActivity);
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
    RestAdapter restAdapter =
        new RestAdapter.Builder().setEndpoint("http://s0.lydiabox.com:18080/apps").build();

    NormalUserService service = restAdapter.create(NormalUserService.class);

    service.getResult(String.valueOf(page), new Callback<WebAppsReponse>() {
      @Override public void success(WebAppsReponse reponse, Response response) {

        LogUtil.i("reponse ===>>>"+reponse.getApps().toString());
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i("error");

      }
    });
  }
}
