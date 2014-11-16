package org.geeklub.smartlib.module.user;

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
import com.google.zxing.integration.android.IntentResult;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.SLUser;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.base.BaseMainActivity;
import org.geeklub.smartlib.module.common.settings.SettingsActivity;
import org.geeklub.smartlib.module.type.NormalUserFunctions;
import org.geeklub.smartlib.module.user.borrow.BorrowFragment;
import org.geeklub.smartlib.module.user.library.LibraryFragment;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SharedPreferencesUtil;
import org.geeklub.smartlib.utils.ToastUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/15.
 */
public class NormalUserMainActivity extends BaseMainActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setCategory(NormalUserFunctions.library);
  }

  public void iniDrawer() {
    mDrawerFragment = DrawerFragment.newInstance();
    getFragmentManager().beginTransaction().replace(R.id.left_drawer, mDrawerFragment).commit();
  }

  public void setCategory(NormalUserFunctions normalUserFunctions) {
    mDrawerLayout.closeDrawer(Gravity.START);

    if (mNormalUserFunctions == normalUserFunctions) {
      return;
    }

    mNormalUserFunctions = normalUserFunctions;

    switch (normalUserFunctions) {

      case library:
        mContentFragment = LibraryFragment.newInstance();

        break;

      case borrow:
        mContentFragment = BorrowFragment.newInstance();
        break;

      default:
        break;
    }
    setTitle(mNormalUserFunctions.getDisplayName());
    getFragmentManager().beginTransaction().replace(R.id.content_frame, mContentFragment).commit();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.normal_user_main, menu);

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

        service.borrow(result.getContents(), user.getUserName(), user.getPassword(),
            new Callback<ServerResponse>() {
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
}
