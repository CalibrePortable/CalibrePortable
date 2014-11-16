package org.geeklub.smartlib.module.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseDrawerFragment;
import org.geeklub.smartlib.module.type.NormalUserFunctions;

/**
 * Created by Vass on 2014/11/15.
 */
public class DrawerFragment extends BaseDrawerFragment {

  public static Fragment newInstance() {
    Fragment drawerFragment = new DrawerFragment();
    return drawerFragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((NormalUserMainActivity) mActivity).setCategory(NormalUserFunctions.values()[position]);
      }
    });

    return view;
  }

  @Override protected ArrayAdapter<String> newAdapter() {
    return new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_activated_1,
        mActivity.getResources().getStringArray(R.array.normal_user_functions));
  }
}
