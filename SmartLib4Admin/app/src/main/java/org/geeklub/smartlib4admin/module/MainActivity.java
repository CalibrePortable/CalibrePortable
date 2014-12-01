package org.geeklub.smartlib4admin.module;

import android.app.Fragment;
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
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.module.base.BaseActivity;
import org.geeklub.smartlib4admin.module.lend.LendFragment;
import org.geeklub.smartlib4admin.module.library.LibraryFragment;
import org.geeklub.smartlib4admin.module.type.Category;

/**
 * Created by Vass on 2014/11/19.
 */
public class MainActivity extends BaseActivity {

  public @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  public ActionBarDrawerToggle mDrawerToggle;

  private CharSequence mDrawerTitle;

  private CharSequence mTitle;

  private Fragment mDrawerFragment;

  private Fragment mContentFragment;

  private Category mCategory;

  private void initActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initActionBar();

    mTitle = mDrawerTitle = getTitle();

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

  public void setCategory(Category category) {
    mDrawerLayout.closeDrawer(Gravity.START);

    if (mCategory == category) {
      return;
    }

    mCategory = category;

    switch (mCategory) {

      case library:
        mContentFragment = LibraryFragment.newInstance();
        break;

      case lend:
        mContentFragment = LendFragment.newInstance();
        break;

      default:
        break;
    }
    setTitle(mCategory.getDisplayName());
    getFragmentManager().beginTransaction().replace(R.id.content_frame, mContentFragment).commit();
  }

  private void iniDrawer() {
    mDrawerFragment = DrawerFragment.newInstance();
    getFragmentManager().beginTransaction().replace(R.id.left_drawer, mDrawerFragment).commit();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
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
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    boolean drawerOpen = mDrawerLayout.isDrawerOpen(Gravity.START);
    menu.findItem(R.id.action_search).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mDrawerToggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    mDrawerToggle.onConfigurationChanged(newConfig);
  }

  @Override public void setTitle(CharSequence title) {
    super.setTitle(title);
    mTitle = title;
    getSupportActionBar().setTitle(mTitle);
  }
}
