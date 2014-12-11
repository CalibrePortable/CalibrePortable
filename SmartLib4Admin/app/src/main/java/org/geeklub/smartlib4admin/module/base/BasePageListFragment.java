package org.geeklub.smartlib4admin.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.InjectView;

import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.module.api.AdministratorService;

/**
 * Created by Vass on 2014/11/15.
 */
public abstract class BasePageListFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener {

    public
    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public
    @InjectView(R.id.recycle_view)
    RecyclerView mRecycleView;

    protected int mPage = 1;

    protected boolean mIsRefreshFromTop = false;

    protected AdministratorService mService;

    protected RecyclerView.Adapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = newAdapter();
        mService = GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        loadNextPage();
                        break;

                    default:
                        break;
                }
            }
        });

        loadFirstPage();
        return view;
    }


    private void loadFirstPage() {
        mPage = 1;
        loadData(mPage);
    }

    private void loadNextPage() {
        loadData(mPage);
    }

    private void loadData(int page) {

        mIsRefreshFromTop = (page == 1);

        if (!mSwipeRefreshLayout.isRefreshing() && mIsRefreshFromTop) {
            mSwipeRefreshLayout.setRefreshing(true);
        }

        executeRequest(page);
    }

    @Override
    public void onRefresh() {
        loadFirstPage();
    }

    protected abstract RecyclerView.Adapter newAdapter();

    protected abstract void executeRequest(int page);
}
