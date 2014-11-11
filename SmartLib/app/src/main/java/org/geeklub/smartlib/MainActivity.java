package org.geeklub.smartlib;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.geeklub.smartlib.beans.SLUser;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.borrow.BorrowFragment;
import org.geeklub.smartlib.drawer.DrawerFragment;
import org.geeklub.smartlib.library.LibraryFragment;
import org.geeklub.smartlib.services.NormalUserService;
import org.geeklub.smartlib.type.Category;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SharedPreferencesUtil;
import org.geeklub.smartlib.utils.ToastUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity {

  @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  private ActionBarDrawerToggle mDrawerToggle;

  private Category mCategory;

  private CharSequence mDrawerTitle;

  private CharSequence mTitle;

  private Fragment mDrawerFragment;

  private Fragment mContentFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mTitle = mDrawerTitle = getTitle();

    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    initActionBar();

    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
        R.string.drawer_close) {

      @Override public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        getSupportActionBar().setTitle(mTitle);
        invalidateOptionsMenu();
      }

      @Override public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        getSupportActionBar().setTitle(mDrawerTitle);
        invalidateOptionsMenu();
      }
    };

    mDrawerLayout.setDrawerListener(mDrawerToggle);
    iniDrawer();
    setCategory(Category.library);
  }

  private void iniDrawer() {
    mDrawerFragment = DrawerFragment.newInstance();
    getFragmentManager().beginTransaction().replace(R.id.left_drawer, mDrawerFragment).commit();
  }

  private void initActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView =
        (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

    return true;
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    boolean drawerOpen = mDrawerLayout.isDrawerOpen(Gravity.START);
    menu.findItem(R.id.action_search).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
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

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerToggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  public void setCategory(Category category) {
    mDrawerLayout.closeDrawer(Gravity.START);

    if (mCategory == category) {
      return;
    }

    mCategory = category;

    switch (category) {

      case library:
        mContentFragment = LibraryFragment.newInstance();

        break;

      case borrow:
        mContentFragment = BorrowFragment.newInstance();
        break;

      default:
        break;
    }
    setTitle(mCategory.getDisplayName());
    getFragmentManager().beginTransaction().replace(R.id.content_frame, mContentFragment).commit();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if (result != null) {
      if (result.getContents() == null) {
        ToastUtil.showShort("取消...");
      } else {

        RestAdapter restAdapter =
            new RestAdapter.Builder().setEndpoint("http://book.duanpengfei.com/API.php").build();

        NormalUserService service = restAdapter.create(NormalUserService.class);

        SharedPreferencesUtil preferencesUtil = new SharedPreferencesUtil(this);
        SLUser user = preferencesUtil.getUser();

        service.borrow(result.getContents(), user.getUserName(),
            user.getPassword(), new Callback<ServerResponse>() {
              @Override public void success(ServerResponse serverResponse, Response response) {
                LogUtil.i(serverResponse.getInfo());
              }

              @Override public void failure(RetrofitError error) {
                LogUtil.i(error.getMessage());
              }
            });
      }
    } else {
      // This is important, otherwise the result will not be passed to the fragment
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override public void setTitle(CharSequence title) {
    super.setTitle(title);
    mTitle = title;
    getSupportActionBar().setTitle(mTitle);
  }
}
