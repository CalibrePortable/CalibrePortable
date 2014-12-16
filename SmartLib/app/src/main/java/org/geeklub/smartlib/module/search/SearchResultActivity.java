package org.geeklub.smartlib.module.search;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import butterknife.InjectView;

import com.astuetz.PagerSlidingTabStrip;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.VersionUtil;

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
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setPageTransformer(true, new StackTransformer());
        mTabs.setViewPager(mViewPager);
        mTabs.setOnPageChangeListener(new SearchOnPageChangeListener());

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
        mTabs.setBackgroundColor(Color.parseColor("#f1c40f"));
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
            LogUtil.i("搜索的关键字是 ===>>>" + queryWord);
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
            return SearchFragment.newInstance(position + 1, mKeyWord, position);
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

    private class SearchOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int position) {
            LogUtil.i("onPageSelected ===>>>position" + position);
            changeColor(position);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    private void changeColor(int position) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), SearchFragment.getBackgroundBitmapPosition(position));

        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                  /* 界面颜色UI统一性处理,看起来更Material一些 */
                mTabs.setBackgroundColor(vibrant.getRgb());
                mTabs.setTextColor(vibrant.getTitleTextColor());
                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加
                mTabs.setIndicatorColor(colorBurn(vibrant.getRgb()));

                mToolBar.setBackgroundColor(vibrant.getRgb());

                if (VersionUtil.IS_LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }

            }
        });
    }


    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
