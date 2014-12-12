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

import java.util.List;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.SummaryBook;
import org.geeklub.smartlib4admin.module.adapters.LibraryAdapter;
import org.geeklub.smartlib4admin.module.base.BasePageListFragment;
import org.geeklub.smartlib4admin.module.detail.BookDetailActivity;
import org.geeklub.smartlib4admin.utils.LogUtil;
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


        mService.search("12108238", "5", page, "all", new Callback<List<SummaryBook>>() {
            @Override
            public void success(List<SummaryBook> bookList, Response response) {

                mSwipeRefreshLayout.setRefreshing(false);

                if (mIsRefreshFromTop) {
                    if (((LibraryAdapter) mAdapter).equals(bookList)) {

                    } else {
                        ((LibraryAdapter) mAdapter).replaceWith(bookList);
                    }
                } else {
                    ((LibraryAdapter) mAdapter).addAll(bookList);
                }
                mPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtil.i(error.getMessage());
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
}
