package org.geeklub.smartlib.module.detail;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.InjectView;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.BookDetailInfo;
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

    private String mBookDetailUrl;

    private int[] mPrimary = new int[3];

    private View mFirstItemView;

    private float mTransHeight;

    private String mBookLink;

    private ShareActionProvider mShareActionProvider;

    private Intent mShareInetnt;

    public static final String EXTRAS_BOOK_DETAIL_URL = "extras_book_detail_url";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail_book;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBookDetailUrl = getIntent().getStringExtra(EXTRAS_BOOK_DETAIL_URL);

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
        service.bookDetail(mBookDetailUrl, new Callback<BookDetailInfo>() {
            @Override
            public void success(BookDetailInfo bookDetailInfo, Response response) {
                progressWheel.setVisibility(View.GONE);
                mToolBar.setTitle(bookDetailInfo.book_name);
                mToolBar.setTitleTextColor(Color.TRANSPARENT);
                mAdapter.setBookDetailInfo(bookDetailInfo);
                mBookLink = bookDetailInfo.book_link;
                setShareIntent(getUpSendIntent(bookDetailInfo.book_link));
            }

            @Override
            public void failure(RetrofitError error) {
                progressWheel.setVisibility(View.GONE);
            }
        });
    }

    private Intent getUpSendIntent(String bookLink) {
        mShareInetnt = new Intent();
        mShareInetnt.setAction(Intent.ACTION_SEND);
        mShareInetnt.putExtra(Intent.EXTRA_TEXT, bookLink);
        mShareInetnt.setType("text/plain");
        return mShareInetnt;
    }


    private void initToolBar() {
        mToolBar.setBackgroundColor(Color.TRANSPARENT);
        mToolBar.setTitle("");
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;
    }


    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, getIntent());
                return true;

            case R.id.action_open:
                openWithBrowser(mBookLink);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openWithBrowser(String mBookLink) {
        if (TextUtils.isEmpty(mBookLink)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mBookLink));
        startActivity(intent);
    }

    private int color(float alpha) {
        return Color.argb((int) (alpha * 0xff), mPrimary[0], mPrimary[1], mPrimary[2]);
    }
}
