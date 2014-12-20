package org.geeklub.smartlib.module;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.InjectView;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseFragment;
import org.geeklub.smartlib.module.type.Category;


/**
 * Created by Vass on 2014/11/15.
 */
public class DrawerFragment extends BaseFragment {


    private ArrayAdapter<String> drawerAdapter;

    @InjectView(R.id.listview)
    ListView mDrawerList;

    public static Fragment newInstance() {
        Fragment drawerFragment = new DrawerFragment();
        return drawerFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        drawerAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_activated_1,
                mActivity.getResources().getStringArray(R.array.normal_user_functions));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        View headerView = LayoutInflater.from(mActivity).inflate(R.layout.header_drawer, null);

        mDrawerList.addHeaderView(headerView);
        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setItemChecked(1, true);


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)
                    return;

                ((MainActivity) mActivity).setCategory(Category.values()[position - 1]);
            }
        });


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_drawer;
    }
}
