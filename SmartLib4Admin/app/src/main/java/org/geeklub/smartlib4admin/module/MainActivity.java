package org.geeklub.smartlib4admin.module;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.InjectView;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.geeklub.smartlib4admin.GlobalContext;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.QRCodeInfo;
import org.geeklub.smartlib4admin.beans.ServerResponse;
import org.geeklub.smartlib4admin.module.api.AdministratorService;
import org.geeklub.smartlib4admin.module.base.BaseActivity;
import org.geeklub.smartlib4admin.module.lend.LendFragment;
import org.geeklub.smartlib4admin.module.library.LibraryFragment;
import org.geeklub.smartlib4admin.module.library.SelectBookTypeDialogFragment;
import org.geeklub.smartlib4admin.module.type.Category;
import org.geeklub.smartlib4admin.utils.LogUtil;
import org.geeklub.smartlib4admin.utils.ToastUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/19.
 */
public class MainActivity extends BaseActivity
        implements LibraryFragment.OnAddBookButtonClickListener,
        SelectBookTypeDialogFragment.OnDialogButtonClickListener {

    public
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    public ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;

    private CharSequence mTitle;

    private CharSequence mBookType;

    private Fragment mContentFragment;

    private Fragment mDrawerFragment;

    private Fragment mLibraryFragment;

    private Fragment mLendFragment;

    private Category mCategory;

    private FragmentManager fragmentManager;

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        fragmentManager = getFragmentManager();

        initDrawer();
        initLendFragment();
        initLibraryFragment();

        setContentFragment(mLibraryFragment);
    }

    public void setContentFragment(Fragment contentFragment) {
        this.mContentFragment = contentFragment;
    }

    private void initLendFragment() {
        mLendFragment = LendFragment.newInstance();
    }

    private void initLibraryFragment() {
        mLibraryFragment = LibraryFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.content_frame, mLibraryFragment, LibraryFragment.TAG)
                .commit();
    }

    public void setCategory(Category category) {
        mDrawerLayout.closeDrawer(Gravity.START);

        if (mCategory == category) {
            return;
        }

        mCategory = category;

        switch (mCategory) {

            case library:
                switchContent(mContentFragment, mLibraryFragment, LibraryFragment.TAG);
                break;

            case lend:
                switchContent(mContentFragment, mLendFragment, LendFragment.TAG);
                break;

            default:
                break;
        }

        setTitle(mCategory.getDisplayName());
    }

    private void switchContent(Fragment from, Fragment to, String tag) {
        if (mContentFragment != to) {
            LogUtil.i("mContentFragment != to");
            mContentFragment = to;
            if (!to.isAdded()) {
                fragmentManager.beginTransaction().hide(from).add(R.id.content_frame, to, tag).commit();
            } else {
                fragmentManager.beginTransaction().hide(from).show(to).commit();
            }
        }
    }

    private void initDrawer() {
        mDrawerFragment = DrawerFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.left_drawer, mDrawerFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_qr_code:
                new IntentIntegrator(this).initiateScan();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(Gravity.START);
        menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onAddBookButtonClick() {
        showDialog();
    }

    private void showDialog() {
        SelectBookTypeDialogFragment fragment =
                (SelectBookTypeDialogFragment) SelectBookTypeDialogFragment.newInstance("请选择书本的类型");
        fragment.show(fragmentManager, SelectBookTypeDialogFragment.TAG);
    }

    @Override
    public void onPositiveButtonClick(CharSequence bookType) {
        LogUtil.i("书本的类型 ===>>>" + bookType);
        mBookType = bookType;
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.i("requestCode ===>>> " + requestCode + ",resultCode ===>>> " + resultCode);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                ToastUtil.showShort("取消扫描");
            } else {
                LogUtil.i("mBookType == null?" + (mBookType == null ? true : false));

                if (mBookType == null) {
                    QRCodeInfo qrCodeInfo = new Gson().fromJson(result.getContents(), QRCodeInfo.class);
                    notifyServerReturnBook(qrCodeInfo.book_id);
                } else {
                    notifyServerBookPlusOne(result.getContents());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void notifyServerReturnBook(String bookId) {
        AdministratorService service =
                GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);

        service.returnBook(bookId, "12108238", "12108238", new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {

                ToastUtil.showShort("还书成功...");
            }

            @Override
            public void failure(RetrofitError error) {
                ToastUtil.showShort("还书失败...");
            }
        });
    }

    private void notifyServerBookPlusOne(String ISBN) {
        AdministratorService service =
                GlobalContext.getApiDispencer().getRestApi(AdministratorService.class);

        service.addBook(ISBN, mBookType.toString(), "12108238", "12108238",
                new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {
                        mBookType = null;
                        ToastUtil.showShort("新增图书成功...");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mBookType = null;
                        ToastUtil.showShort("新增图书失败...");
                    }
                });
    }
}
