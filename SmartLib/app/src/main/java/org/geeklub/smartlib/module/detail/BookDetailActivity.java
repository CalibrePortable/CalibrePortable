package org.geeklub.smartlib.module.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.InjectView;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.BookDetailInfo;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.DetailAdapter;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.utils.LogUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/6.
 */
public class BookDetailActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    private DetailAdapter mAdapter;

    private SummaryBook mBook;

    private int[] mPrimary = new int[3];

    private View mFirstItemView;

    private float mTransHeight;

    public static final String EXTRAS_BOOK = "extras_book";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail_book;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBook = getIntent().getParcelableExtra(EXTRAS_BOOK);

        int color = getResources().getColor(R.color.SUN_FLOWER);
        mPrimary[0] = Color.red(color);
        mPrimary[1] = Color.green(color);
        mPrimary[2] = Color.blue(color);

        initToolBar();

        mAdapter = new DetailAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                handleScroll();
            }
        });

        loadData();
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
            mToolBar.setTitleTextColor(Color.BLACK);
        }
    }

    private void initScroll() {
        DetailAdapter.ViewHolderHeader holder =
                (DetailAdapter.ViewHolderHeader) mRecyclerView.findViewHolderForPosition(0);

        if (holder == null) {
            return;
        }

        mFirstItemView = holder.itemView;

        View title = holder.mBookName;
        mTransHeight = title.getTop() + title.getHeight();

    }

    private void loadData() {
        progressWheel.setVisibility(View.VISIBLE);
        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);
        service.bookDetail(mBook.book_kind, new Callback<BookDetailInfo>() {
            @Override
            public void success(BookDetailInfo bookDetailInfo, Response response) {
                progressWheel.setVisibility(View.GONE);
                mToolBar.setTitle(bookDetailInfo.book_detail.book_name);
                mToolBar.setTitleTextColor(Color.TRANSPARENT);
                mAdapter.setBookDetailInfo(bookDetailInfo);

            }

            @Override
            public void failure(RetrofitError error) {
                progressWheel.setVisibility(View.GONE);
                LogUtil.i("failure ===>>>" + error.getMessage());
            }
        });
    }

    private void initToolBar() {
        mToolBar.setBackgroundColor(Color.TRANSPARENT);
        mToolBar.setTitle("");
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

    private int color(float alpha) {
        return Color.argb((int) (alpha * 0xff), mPrimary[0], mPrimary[1], mPrimary[2]);
    }
}
