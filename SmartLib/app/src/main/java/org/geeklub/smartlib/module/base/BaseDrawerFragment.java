package org.geeklub.smartlib.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.geeklub.smartlib.R;

/**
 * Created by Vass on 2014/10/30.
 */
public abstract class BaseDrawerFragment extends BaseFragment {

  protected ArrayAdapter<String> mAdapter;

  public @InjectView(R.id.listview) ListView mDrawerList;

  //public static Fragment newInstance() {
  //  Fragment drawerFragment = new BaseDrawerFragment();
  //  return drawerFragment;
  //}

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = newAdapter();
    //mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_activated_1,
    //    mActivity.getResources().getStringArray(R.array.categorys_array));
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_drawer_base, null);
    ButterKnife.inject(this, view);

    mDrawerList.setAdapter(mAdapter);
    mDrawerList.setItemChecked(0, true);
    //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    return view;
  }

  //@Override public void onResume() {
  //  super.onResume();
  //}

  //private class DrawerItemClickListener implements ListView.OnItemClickListener {
  //  @Override
  //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
  //    ((BaseMainActivity) mActivity).setCategory(Category.values()[position]);
  //  }
  //}

  protected abstract ArrayAdapter<String> newAdapter();
}
