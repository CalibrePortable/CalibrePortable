package org.geeklub.smartlib4admin.module.library;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.squareup.otto.Subscribe;

import java.util.List;

import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.SummaryBook;
import org.geeklub.smartlib4admin.module.adapters.LibraryAdapter;
import org.geeklub.smartlib4admin.module.base.BasePageListFragment;
import org.geeklub.smartlib4admin.module.detail.BookDetailActivity;
import org.geeklub.smartlib4admin.module.event.BookDeleteEvent;
import org.geeklub.smartlib4admin.module.event.BookPlusEvent;
import org.geeklub.smartlib4admin.module.event.BookReturnEvent;
import org.geeklub.smartlib4admin.utils.SmartLibraryUser;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/25.
 */
public class LibraryFragment extends BasePageListFragment {
    public static final String TAG = LibraryFragment.class.getSimpleName();

    @InjectView(R.id.fab)
    FloatingActionButton mFab;


    public static interface OnAddBookButtonClickListener {
        void onAddBookButtonClick();
    }

    public static Fragment newInstance() {
        Fragment libraryFragment = new LibraryFragment();
        return libraryFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_library;
    }

    @Override
    protected RecyclerView.Adapter newAdapter() {
        return new LibraryAdapter(mActivity);
    }

    @Override
    protected void executeRequest(int page) {


        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();

        mService.search(user.getUserId(), "5", page, "all", new Callback<List<SummaryBook>>() {
            @Override
            public void success(List<SummaryBook> bookList, Response response) {

                mSwipeRefreshLayout.setRefreshing(false);

                if (mIsRefreshFromTop) {
                    ((LibraryAdapter) mAdapter).replaceWith(bookList);
                } else {
                    ((LibraryAdapter) mAdapter).addAll(bookList);
                }
                mPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((LibraryAdapter) mAdapter).setOnItemClickListener(new LibraryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SummaryBook book) {
                Intent intent = new Intent(mActivity, BookDetailActivity.class);
                intent.putExtra(BookDetailActivity.EXTRAS_BOOK_KIND, book.book_kind);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalContext.getBusInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GlobalContext.getBusInstance().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        //TODO FloatActionBar 在recylerview上还有bug
        //mFab.attachToRecyclerView(mRecycleView);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnAddBookButtonClickListener) mActivity).onAddBookButtonClick();
            }
        });


        return view;
    }

    @Subscribe
    public void onBookReturn(BookReturnEvent event) {
        onRefresh();
    }

    @Subscribe
    public void onBookPlus(BookPlusEvent event) {
        onRefresh();
    }

    @Subscribe
    public void onBookDelete(BookDeleteEvent event) {
        onRefresh();
    }


}
