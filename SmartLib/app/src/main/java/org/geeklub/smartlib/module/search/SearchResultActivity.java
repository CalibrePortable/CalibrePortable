package org.geeklub.smartlib.module.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.SearchAdapter;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.module.detail.BookDetailActivity;
import org.geeklub.smartlib.utils.DisplayParams;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;

import java.util.List;

/**
 * Created by Vass on 2014/11/17.
 */
public class SearchResultActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SearchAdapter searchAdapter;

    private int mPage = 1;

    private String mQueryWord;

//    private static final int mBackgroundDrawable = R.drawable.search_fragment_bg_1;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.filter)
    Spinner mFilter;

    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout mRefreshLayout;


    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @InjectView(R.id.iv_background)
    ImageView mBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolBar();

        setUpBackground();
        setUpFilter();
        setUpRecyclerView();
        setUpSwipingLayout();


        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            mQueryWord = getIntent().getStringExtra(SearchManager.QUERY);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    //处理新的请求，表示在同一个Activity内
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mQueryWord = intent.getStringExtra(SearchManager.QUERY);
            onRefresh();
        }
    }

    private void setUpBackground() {
        int height = DisplayParams.getInstance(this).screenHeight;
        int width = DisplayParams.getInstance(this).screenWidth;

        Picasso.with(this).load(R.drawable.search_fragment_bg_1).resize(width / 2, height / 2).centerCrop().into(mBackground);

//        mBackground.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(getResources(), mBackgroundDrawable, width / 2, height / 2));
    }


    private void setUpSwipingLayout() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


    private void setUpRecyclerView() {
        searchAdapter = new SearchAdapter(this);
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SummaryBook book) {
                Intent intent = new Intent(SearchResultActivity.this, BookDetailActivity.class);
                intent.putExtra(BookDetailActivity.EXTRAS_BOOK_DETAIL_URL, book.book_kind);
                startActivity(intent);
            }
        });

        searchAdapter.setOnFavourClickListener(new SearchAdapter.OnFavourClickListener() {
            @Override
            public void onFavourClick(SummaryBook book) {
                book.isLike = "1";
                book.favour = Integer.valueOf(book.favour) + 1 + "";
                sendDianZanMsg(book);
            }
        });

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(searchAdapter);

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


    private void loadData(int page) {
        int type = 1;
        switch (mFilter.getSelectedItemPosition()) {
            case 0:
                type = 1;
                break;
            case 1:
                type = 2;
                break;
            case 2:
                type = 3;
                break;
            case 3:
                type = 4;
                break;
            default:
                break;
        }

        final boolean isRefreshFromTop = (page == 1);

        if (!mRefreshLayout.isRefreshing() && isRefreshFromTop) {
            mRefreshLayout.setRefreshing(true);
        }

        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        final NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        service.search(user.getUserId(), type, page, mQueryWord, new Callback<List<SummaryBook>>() {
            @Override
            public void success(List<SummaryBook> bookList, Response response) {
                mRefreshLayout.setRefreshing(false);
                if (isRefreshFromTop) {
                    searchAdapter.replaceWith(bookList);
                } else {
                    searchAdapter.addAll(bookList);
                }
                mPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtil.i(error.getMessage());
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadFirstPage() {
        mPage = 1;
        loadData(mPage);
    }

    private void loadNextPage() {
        loadData(mPage);
    }

    private void setUpFilter() {

        mFilter.setAdapter(ArrayAdapter.createFromResource(this, R.array.search_tabs,
                android.R.layout.simple_spinner_dropdown_item));


        mFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //          onItemSelected在init之后会自动被调用一次
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onRefresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initToolBar() {
        mToolBar.setTitle("");
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //TODO 我也不知道这么做对不对
                NavUtils.navigateUpTo(this, getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        LogUtil.i("onRefresh");
        loadFirstPage();
    }


}
