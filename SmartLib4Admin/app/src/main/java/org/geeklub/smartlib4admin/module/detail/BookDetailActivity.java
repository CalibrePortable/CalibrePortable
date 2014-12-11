package org.geeklub.smartlib4admin.module.detail;

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
import org.geeklub.smartlib4admin.beans.BookDetailInfo;
import org.geeklub.smartlib4admin.module.adapters.BookDetailAdapter;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.base.BaseActivity;

import butterknife.InjectView;
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

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.header)
    View mHeader;

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

        mBookKind = getIntent().getStringExtra(EXTRAS_BOOK_KIND);

        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getToolBarHeight();

        setUpToolBar();
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        progressWheel.setVisibility(View.VISIBLE);
        AdministratorService service = GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);

        service.bookDetail(mBookKind, new Callback<BookDetailInfo>() {
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
}
