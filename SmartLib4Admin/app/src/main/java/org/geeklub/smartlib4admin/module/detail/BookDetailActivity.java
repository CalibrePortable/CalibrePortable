package org.geeklub.smartlib4admin.module.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.otto.Produce;

import org.geeklub.smartlib4admin.BusProvider;
import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.BookDetailInfo;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import org.geeklub.smartlib4admin.module.adapters.BookDetailAdapter;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.base.BaseActivity;
import org.geeklub.smartlib4admin.module.event.BookDeleteEvent;
import org.geeklub.smartlib4admin.utils.LogUtil;

import butterknife.InjectView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/12/10.
 */
public class BookDetailActivity extends BaseActivity {

    public static final String EXTRAS_BOOK_KIND = "extras_book_kind";

    private String mBookKind;

    private BookDetailAdapter bookDetailAdapter;

    private AdministratorService mService;

    private View mFirstItemView;

    private float mTransHeight;

    private int[] mPrimary = new int[3];

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_book_detail;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int color = getResources().getColor(R.color.PETER_RIVER);
        mPrimary[0] = Color.red(color);
        mPrimary[1] = Color.green(color);
        mPrimary[2] = Color.blue(color);


        mService = GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);

        mBookKind = getIntent().getStringExtra(EXTRAS_BOOK_KIND);


        setUpToolBar();
        setUpRecyclerView();

        initCallBacks();

        loadData();
    }


    private void initCallBacks() {
        bookDetailAdapter.setOnItemRemoveListener(new BookDetailAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(final Book book) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(BookDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("你确定?")
                        .setContentText("删除后不能恢复")
                        .setConfirmText("是的，删除!")
                        .setCancelClickListener(null)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                sDialog.setTitleText("删除");
                                sDialog.setCancelable(false);
                                sDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);

                                mService.deleteBook(book.book_id, "12108238", "12108238", new DeleteBookCallBack(sDialog, book));


                            }
                        });
                sweetAlertDialog.setCanceledOnTouchOutside(true);
                sweetAlertDialog.show();

            }
        });
    }


    private void loadData() {
        progressWheel.setVisibility(View.VISIBLE);

        mService.bookDetail(mBookKind, new Callback<BookDetailInfo>() {
            @Override
            public void success(BookDetailInfo bookDetailInfo, Response response) {
                progressWheel.setVisibility(View.GONE);
                mToolBar.setTitle(bookDetailInfo.book_detail.book_name);
                mToolBar.setTitleTextColor(Color.TRANSPARENT);
                bookDetailAdapter.setBookDetailInfo(bookDetailInfo);
            }

            @Override
            public void failure(RetrofitError error) {
                progressWheel.setVisibility(View.GONE);
            }
        });

    }


    private void setUpRecyclerView() {

        bookDetailAdapter = new BookDetailAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(bookDetailAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                handleScroll();
            }
        });

    }

    private int color(float alpha) {
        return Color.argb((int) (alpha * 0xff), mPrimary[0], mPrimary[1], mPrimary[2]);
    }

    private void handleScroll() {
        if (mFirstItemView == null || mTransHeight == 0) {
            initScroll();
            return;
        }

        float scrolled = mFirstItemView.getTop() * -1;


        if (scrolled < mTransHeight) {
            mToolBar.setBackgroundColor(color(scrolled / mTransHeight));
        } else {
            mToolBar.setBackgroundColor(color(1));
        }

        if (scrolled < mTransHeight / 2) {
            mToolBar.setTitleTextColor(Color.TRANSPARENT);
        } else if (scrolled < mTransHeight) {
            float alpha = (scrolled - mTransHeight) / (mTransHeight / 2);
            mToolBar.setTitleTextColor(Color.argb((int) (alpha * 0xff), 0, 0, 0));
        } else {
            mToolBar.setTitleTextColor(Color.WHITE);
        }
    }

    private void initScroll() {
        BookDetailAdapter.ViewHolderHeader header =
                (BookDetailAdapter.ViewHolderHeader) mRecyclerView.findViewHolderForPosition(0);

        if (header == null) {
            return;
        }

        mFirstItemView = header.itemView;

        View title = header.mBookName;
        mTransHeight = title.getTop() + title.getHeight();

    }


    private void setUpToolBar() {
        mToolBar.setTitle("");
        mToolBar.setBackgroundColor(Color.TRANSPARENT);
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class DeleteBookCallBack implements Callback<ServerResponse> {
        private SweetAlertDialog mDialog;
        private Book mBook;


        public DeleteBookCallBack(SweetAlertDialog dialog, Book book) {
            this.mDialog = dialog;
            this.mBook = book;
        }


        @Override
        public void success(ServerResponse serverResponse, Response response) {
            mDialog.setTitleText("删除成功!")
                    .setContentText("书已经被删除")
                    .setConfirmText("OK")
                    .setCancelClickListener(null)
                    .setConfirmClickListener(null)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

            bookDetailAdapter.deleteBook(mBook);

        }

        @Override
        public void failure(RetrofitError error) {
            LogUtil.i("失败");
            mDialog.setTitleText("删除出错")
                    .setConfirmText("Cancel")
                    .setCancelClickListener(null)
                    .setConfirmClickListener(null)
                    .setContentText("Something went wrong!")
                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
        }

    }

}
