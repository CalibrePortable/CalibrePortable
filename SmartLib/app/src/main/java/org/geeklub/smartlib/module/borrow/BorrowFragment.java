package org.geeklub.smartlib.module.borrow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;

import java.util.List;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.module.adapters.BorrowAdapter;
import org.geeklub.smartlib.module.base.BaseFragment;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.module.event.BookBorrowEvent;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import org.geeklub.smartlib.utils.ToastUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/8.
 */
public class BorrowFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = BorrowFragment.class.getSimpleName();

    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout mRefreshLayout;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private BorrowAdapter mAdapter;

    private int mPage = 1;

    private LinearLayoutManager linearLayoutManager;

    private boolean isLoading = true;

    private int firstVisibleItem;

    private int visibleItemCount;

    private int totalItemCount;

    public static Fragment newInstance() {
        Fragment borrowFragment = new BorrowFragment();
        return borrowFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new BorrowAdapter(mActivity);

        linearLayoutManager = new LinearLayoutManager(mActivity);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && (firstVisibleItem + visibleItemCount >= totalItemCount)) {
                    if (!isLoading) {
                        isLoading = true;
                        loadNextPage();
                    }
                }
            }
        });


        loadFirstPage();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalContext.getBusInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        GlobalContext.getBusInstance().unregister(this);
    }

    private void loadFirstPage() {
        mPage = 1;
        loadData(mPage);
    }

    private void loadNextPage() {
        loadData(mPage);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_borrow;
    }

    @Override
    public void onRefresh() {
        loadFirstPage();
    }

    private void loadData(int page) {

        final boolean isRefreshFromTop = (page == 1);
        if (!mRefreshLayout.isRefreshing() && isRefreshFromTop) {
            mRefreshLayout.setRefreshing(true);
        }

        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        service.haveBorrowed(user.getUserId(), user.getPassWord(), page,
                new Callback<List<Book>>() {
                    @Override
                    public void success(List<Book> bookList, Response response) {

                        if (isRefreshFromTop) {
                            mRefreshLayout.setRefreshing(false);
                            mAdapter.replaceWith(bookList);
                        } else {
                            mAdapter.addAll(bookList);
                        }
                        mPage++;
                        isLoading = false;
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mRefreshLayout.setRefreshing(false);
                        isLoading = false;
                        ToastUtil.showShort("Failed to load. Try again later...");
                    }
                });
    }

    @Subscribe
    public void onBookBorrow(BookBorrowEvent event) {
        onRefresh();
    }


}
