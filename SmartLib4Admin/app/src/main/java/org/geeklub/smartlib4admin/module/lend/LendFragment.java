package org.geeklub.smartlib4admin.module.lend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import org.geeklub.smartlib4admin.module.adapters.LendAdapter;
import org.geeklub.smartlib4admin.module.base.BasePageListFragment;
import org.geeklub.smartlib4admin.utils.LogUtil;
import org.geeklub.smartlib4admin.utils.SmartLibraryUser;

import cn.pedant.SweetAlert.SweetAlertDialog;
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

        mService.haveLended("12108238", "12108238", page, new Callback<List<Book>>() {
            @Override
            public void success(List<Book> bookList, Response response) {

                mSwipeRefreshLayout.setRefreshing(false);

                if (mIsRefreshFromTop) {
                    if (((LendAdapter) mAdapter).equals(bookList)) {

                    } else {
                        ((LendAdapter) mAdapter).replaceWith(bookList);
                    }
                } else {
                    ((LendAdapter) mAdapter).addAll(bookList);
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

        ((LendAdapter) mAdapter).setOnItemClickListener(new LendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {

            }
        });

        ((LendAdapter) mAdapter).setOnItemRemoveListener(new LendAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(final Book book) {
                new SweetAlertDialog(mActivity, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("是否要删除？")
                        .setContentText("不能恢复删除的图书！")
                        .setConfirmText("确认删除")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {
                                mService.deleteBook(book.book_id, "12108238", "12108238", new Callback<ServerResponse>() {
                                    @Override
                                    public void success(ServerResponse serverResponse, Response response) {
                                        sDialog.setTitleText("Deleted!")
                                                .setContentText("图书已经被删除d!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        ((LendAdapter) mAdapter).remove(book);
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        LogUtil.i("删除图书失败");
                                        sDialog.dismissWithAnimation();
                                    }
                                });

                            }
                        })
                        .show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }
}
