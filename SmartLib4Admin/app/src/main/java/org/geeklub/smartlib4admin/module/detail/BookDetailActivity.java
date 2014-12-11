package org.geeklub.smartlib4admin.module.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.BookDetailInfo;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import org.geeklub.smartlib4admin.module.adapters.BookDetailAdapter;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.base.BaseActivity;
import org.geeklub.smartlib4admin.utils.Blur;
import org.geeklub.smartlib4admin.utils.LogUtil;
import org.geeklub.smartlib4admin.utils.ScreenUtil;

import butterknife.InjectView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/12/10.
 */
public class BookDetailActivity extends BaseActivity {


    private int mHeaderHeight;

    private int mMinHeaderTranslation;

    private LinearLayoutManager linearLayoutManager;

    private BookDetailAdapter bookDetailAdapter;


    public static final String EXTRAS_BOOK_KIND = "extras_book_kind";

    private String mBookKind;

    private AdministratorService mService;

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.header)
    View mHeader;

    @InjectView(R.id.blurred_image)
    ImageView mBlurredImage;

    @InjectView(R.id.iv_book_icon)
    ImageView mBookIcon;

    @InjectView(R.id.tv_book_name)
    TextView mBookName;

    @InjectView(R.id.tv_book_author)
    TextView mBookAuthor;

    @InjectView(R.id.tv_book_type)
    TextView mBookType;

    @InjectView(R.id.tv_book_edit)
    TextView mBookEdit;

    @InjectView(R.id.tv_book_pub)
    TextView mBookPub;

    @InjectView(R.id.tv_book_price)
    TextView mBookPrice;

    @InjectView(R.id.tv_book_info)
    TextView mBookInfo;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_book_detail;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mService = GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);

        mBookKind = getIntent().getStringExtra(EXTRAS_BOOK_KIND);

        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getToolBarHeight();

        setUpToolBar();
        setUpRecyclerView();
        setUpHeaderBackground();

        initCallBacks();
    }

    private void setUpHeaderBackground() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.picture0);
        Bitmap newImg = Blur.fastblur(this, image, 12);
        Bitmap bmpBlurred = Bitmap.createScaledBitmap(newImg, ScreenUtil.getScreenWidth(this), getResources().getDimensionPixelSize(R.dimen.header_height), false);
        mBlurredImage.setImageBitmap(bmpBlurred);
    }

    private void initCallBacks() {
        bookDetailAdapter.setOnItemRemoveListener(new BookDetailAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(final Book book) {
                new SweetAlertDialog(BookDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
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
                        })
                        .show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        progressWheel.setVisibility(View.VISIBLE);

        mService.bookDetail(mBookKind, new Callback<BookDetailInfo>() {
            @Override
            public void success(BookDetailInfo bookDetailInfo, Response response) {
                progressWheel.setVisibility(View.GONE);
                bookDetailAdapter.setBookDetailInfo(bookDetailInfo);
                updateHeaderView(bookDetailInfo);
            }

            @Override
            public void failure(RetrofitError error) {
                progressWheel.setVisibility(View.GONE);
            }
        });

    }

    private void updateHeaderView(BookDetailInfo bookDetailInfo) {

        mBookName.setText("书名:" + bookDetailInfo.book_detail.book_name);
        mBookAuthor.setText("作者:" + bookDetailInfo.book_detail.book_author);
        mBookType.setText("类别:" + bookDetailInfo.book_detail.book_type);
        mBookEdit.setText("出版社:" + bookDetailInfo.book_detail.book_edit);
        mBookPub.setText("版次:" + bookDetailInfo.book_detail.book_pub);
        mBookPrice.setText("价格:" + bookDetailInfo.book_detail.book_price + "￥");
        mBookInfo.setText("介绍:" + bookDetailInfo.book_detail.book_info);
    }

    private void setUpRecyclerView() {

        bookDetailAdapter = new BookDetailAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(bookDetailAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int scrollY = getScrollY();
                //sticky actionbar
                mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
            }
        });

    }

    private int getScrollY() {
        View c = linearLayoutManager.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    private void setUpToolBar() {
        mToolBar.setTitle("");
        mToolBar.setBackgroundColor(Color.TRANSPARENT);
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
    }

    private int getToolBarHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.toolbar_size);
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
