package org.geeklub.smartlib4admin.module.lend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.otto.Subscribe;

import java.util.Comparator;
import java.util.List;

import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.module.adapters.LendAdapter;
import org.geeklub.smartlib4admin.module.base.BasePageListFragment;
import org.geeklub.smartlib4admin.module.event.BookReturnEvent;
import org.geeklub.smartlib4admin.utils.SmartLibraryUser;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/16.
 */
public class LendFragment extends BasePageListFragment {
    public static final String TAG = LendFragment.class.getSimpleName();

    public static Fragment newInstance() {
        Fragment lendFragment = new LendFragment();
        return lendFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_lend;
    }

    @Override
    protected RecyclerView.Adapter newAdapter() {
        return new LendAdapter(mActivity);
    }

    @Override
    protected void executeRequest(int page) {

        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();


        mService.haveLended(user.getUserId(), user.getPassWord(), page, new Callback<List<Book>>() {
            @Override
            public void success(List<Book> bookList, Response response) {

                mSwipeRefreshLayout.setRefreshing(false);
                ((LendAdapter) mAdapter).sort(bookList, new OverDueComparator());

                if (mIsRefreshFromTop) {
                    ((LendAdapter) mAdapter).replaceWith(bookList);
                } else {
                    ((LendAdapter) mAdapter).addAll(bookList);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    private static class OverDueComparator implements Comparator<Book> {
        @Override
        public int compare(Book lhs, Book rhs) {
            Integer return_at_1 = Integer.valueOf(lhs.return_at);
            Integer return_at_2 = Integer.valueOf(rhs.return_at);
            return return_at_1.compareTo(return_at_2);
        }
    }

    @Subscribe
    public void onBookReturn(BookReturnEvent event) {
        onRefresh();
    }


}
