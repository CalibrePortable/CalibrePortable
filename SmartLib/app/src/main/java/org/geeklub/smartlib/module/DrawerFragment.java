package org.geeklub.smartlib.module;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.InjectView;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseFragment;
import org.geeklub.smartlib.module.type.Category;

/**
 * Created by Vass on 2014/11/15.
 */
public class DrawerFragment extends BaseFragment {

  protected ArrayAdapter<String> mAdapter;

  public static Fragment newInstance() {
    Fragment drawerFragment = new DrawerFragment();
    return drawerFragment;
  }

  public @InjectView(R.id.listview) ListView mDrawerList;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_activated_1,
        mActivity.getResources().getStringArray(R.array.normal_user_functions));
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = super.onCreateView(inflater, container, savedInstanceState);

    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((MainActivity) mActivity).setCategory(Category.values()[position]);
      }
    });

    mDrawerList.setAdapter(mAdapter);
    mDrawerList.setItemChecked(0, true);

    return view;
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_drawer;
  }
}
