package org.geeklub.smartlib.module.administrator;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.zxing.integration.android.IntentIntegrator;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseMainActivity;
import org.geeklub.smartlib.module.common.settings.SettingsActivity;
import org.geeklub.smartlib.module.type.AdministratorFunctions;
import org.geeklub.smartlib.module.user.borrow.BorrowFragment;
import org.geeklub.smartlib.module.user.library.LibraryFragment;

/**
 * Created by Vass on 2014/11/15.
 */
public class AdministratorMainActivity extends BaseMainActivity {

  private AdministratorFunctions mCategory;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setCategory(AdministratorFunctions.library);
  }

  public void iniDrawer() {
    mDrawerFragment = new DrawerFragment();
    getFragmentManager().beginTransaction().replace(R.id.left_drawer, mDrawerFragment).commit();
  }

  public void setCategory(AdministratorFunctions functions) {
    mDrawerLayout.closeDrawer(Gravity.START);

    if (mCategory == functions) {
      return;
    }

    mCategory = functions;

    switch (mCategory) {

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

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.admin_main, menu);

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
        startActivity(new Intent(this, SettingsActivity.class));
        return true;

      case R.id.action_qr_code:
        new IntentIntegrator(this).initiateScan();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
