package org.geeklub.smartlib.module.administrator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseDrawerFragment;

/**
 * Created by Vass on 2014/11/15.
 */
public class DrawerFragment extends BaseDrawerFragment {

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //((NormalUserMainActivity) mActivity).setCategory(AdministratorFunctions.values()[position]);
      }
    });

    return view;
  }

  @Override protected ArrayAdapter<String> newAdapter() {
    return new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_activated_1,
        mActivity.getResources().getStringArray(R.array.administrators_functions));
  }
}
