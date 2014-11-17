package org.geeklub.smartlib.module.administrator.lend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.api.AdministratorService;
import org.geeklub.smartlib.module.adapters.BorrowAdapter;
import org.geeklub.smartlib.module.base.BasePageListFragment;
import retrofit.RestAdapter;

/**
 * Created by Vass on 2014/11/16.
 */
public class LendFragment extends BasePageListFragment<AdministratorService> {

  public static Fragment newInstance() {

    Fragment lendFragment = new LendFragment();

    return lendFragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    return view;
  }

  @Override protected int getLayoutResources() {
    return R.layout.fragment_lend;
  }

  @Override protected RecyclerView.Adapter newAdapter() {
    return new BorrowAdapter(mActivity);
  }

  @Override protected RestAdapter newRestAdapter() {
    return new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Override protected Class getServiceClass() {
    return AdministratorService.class;
  }

  @Override protected void executeRequest(int page) {

  }
}
