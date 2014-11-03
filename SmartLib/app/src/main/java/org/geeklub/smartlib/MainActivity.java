package org.geeklub.smartlib;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.geeklub.smartlib.drawer.DrawerFragment;
import org.geeklub.smartlib.type.Category;

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

        break;

      case borrow:
        break;

      default:
        break;
    }
    setTitle(mCategory.getDisplayName());
    //getFragmentManager().beginTransaction().replace(R.id.content_frame, mContentFragment).commit();
  }

  @Override public void setTitle(CharSequence title) {
    super.setTitle(title);
    mTitle = title;
    getSupportActionBar().setTitle(mTitle);
  }
}
