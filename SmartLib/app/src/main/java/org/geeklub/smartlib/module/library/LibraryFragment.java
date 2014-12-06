package org.geeklub.smartlib.module.library;

import android.app.Fragment;
import android.content.Intent;
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

import java.util.List;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.LibraryAdapter;
import org.geeklub.smartlib.module.base.BaseFragment;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.module.detail.BookDetailActivity;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import org.geeklub.smartlib.utils.ToastUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public final static String TAG  = LibraryFragment.class.getSimpleName();

    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout mRefreshLayout;
    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private LibraryAdapter mAdapter;
    private int mPage = 1;

    public static Fragment newInstance() {
        Fragment libraryFragment = new LibraryFragment();
        return libraryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new LibraryAdapter(mActivity);

        mAdapter.setOnItemClickListener(new LibraryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SummaryBook book) {
                Intent intent = new Intent(mActivity, BookDetailActivity.class);
                intent.putExtra(BookDetailActivity.EXTRAS_BOOK, book);
                startActivity(intent);
            }
        });

        mAdapter.setOnFavourClickListener(new LibraryAdapter.OnFavourClickListener() {
            @Override
            public void onFavourClick(SummaryBook book) {
                book.isLike = "1";
                book.favour = Integer.valueOf(book.favour) + 1 + "";
                sendDianZanMsg(book);
            }
        });
    }

    private void sendDianZanMsg(SummaryBook book) {
        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        service.likePlusOne(book.book_kind, user.getUserId(), user.getPassWord(),
                new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {
                        LogUtil.i("点赞成功 ...");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        LogUtil.i("点赞失败 ===>>>" + error.getMessage());
                    }
                });
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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

    private void loadNextPage() {
        loadData(mPage);
    }

    private void loadFirstPage() {
        mPage = 1;
        loadData(mPage);
    }

    private void loadData(int page) {
        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        final boolean isRefreshFromTop = (page == 1);
        if (!mRefreshLayout.isRefreshing() && isRefreshFromTop) {
            mRefreshLayout.setRefreshing(true);
        }

        service.search(user.getUserId(), 5, page, "all", new Callback<List<SummaryBook>>() {
            @Override
            public void success(List<SummaryBook> summaryBooks, Response response) {
                mRefreshLayout.setRefreshing(false);
                if (isRefreshFromTop) {
                    LogUtil.i("isRefreshFromTop ===>>>" + isRefreshFromTop);
                    if (mAdapter.equals(summaryBooks)) {
                        ToastUtil.showShort("没有新的书本...");
                    } else {
                        mAdapter.replaceWith(summaryBooks);
                    }
                } else {
                    mAdapter.addAll(summaryBooks);
                }
                mPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                mRefreshLayout.setRefreshing(false);
                ToastUtil.showShort("Failed to load. Try again later...");
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_library;
    }

    @Override
    public void onRefresh() {
        loadFirstPage();
    }
}
