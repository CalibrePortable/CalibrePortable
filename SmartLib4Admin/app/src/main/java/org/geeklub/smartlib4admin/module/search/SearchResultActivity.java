package org.geeklub.smartlib4admin.module.search;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;


import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.module.base.BaseActivity;

import butterknife.InjectView;

/**
 * Created by Vass on 2014/11/17.
 */
public class SearchResultActivity extends BaseActivity {

    @InjectView(R.id.pager)
    ViewPager mViewPager;

    @InjectView(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    private SearchPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolBar();
        initTabs();


        mAdapter = new SearchPagerAdapter(getFragmentManager());

        mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);

        mTabs.setViewPager(mViewPager);

        handleIntent(getIntent());
    }

    private void initToolBar() {
        mToolBar.setTitle("搜索");
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search;
    }


    private void initTabs() {
        // 底部游标颜色
        mTabs.setIndicatorColor(Color.parseColor("#e74c3c"));
        // tab的分割线颜色
        mTabs.setDividerColor(Color.TRANSPARENT);
        // tab背景
        mTabs.setBackgroundColor(Color.parseColor("#3498db"));
        // tab底线高度
        mTabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, getResources().getDisplayMetrics()));
        // 游标高度
        mTabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics()));

        // 正常文字颜色
        mTabs.setTextColor(Color.parseColor("#34495e"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String queryWord = intent.getStringExtra(SearchManager.QUERY);
            mAdapter.setKeyWord(queryWord);
            mAdapter.refresh();
        }
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
                //我也不知道这么做对不对
                NavUtils.navigateUpTo(this, getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    private class SearchPagerAdapter extends FragmentStatePagerAdapter {
        private final String[] TITLES = {"书名", "出版社", "作者", "种类"};

        private String mKeyWord;

        public SearchPagerAdapter(FragmentManager fm) {
            super(fm);
            mKeyWord = "";
        }

        public void setKeyWord(String keyWord) {
            mKeyWord = keyWord;
        }

        @Override
        public Fragment getItem(int position) {
            return SearchFragment.newInstance(position + 1 + "", mKeyWord);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        public void refresh() {
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
