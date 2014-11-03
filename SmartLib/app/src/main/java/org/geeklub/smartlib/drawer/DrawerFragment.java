package org.geeklub.smartlib.drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.geeklub.smartlib.BaseFragment;
import org.geeklub.smartlib.MainActivity;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.type.Category;

/**
 * Created by Vass on 2014/10/30.
 */
public class DrawerFragment extends BaseFragment {

  private ArrayAdapter<String> mAdapter;

  @InjectView(R.id.listview) ListView mDrawerList;

  public static Fragment newInstance() {
    Fragment drawerFragment = new DrawerFragment();
    return drawerFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_activated_1,
        mActivity.getResources().getStringArray(R.array.categorys_array));
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_drawer, null);
    ButterKnife.inject(this, view);

    mDrawerList.setAdapter(mAdapter);
    mDrawerList.setItemChecked(0, true);
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    return view;
  }

  @Override public void onResume() {
    super.onResume();
  }

  private class DrawerItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      ((MainActivity) mActivity).setCategory(Category.values()[position]);
    }
  }
}
