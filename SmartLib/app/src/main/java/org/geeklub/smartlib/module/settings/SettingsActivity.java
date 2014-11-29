package org.geeklub.smartlib.module.settings;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.module.base.BaseActivity;

/**
 * Created by Vass on 2014/11/12.
 */
public class SettingsActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initActionBar();

    setTitle(getString(R.string.action_settings));

    getFragmentManager().beginTransaction()
        .replace(R.id.content_frame, new SettingsFragment())
        .commit();
  }

  private void initActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_settings;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        LogUtil.i("back to setting main activity");
        NavUtils.navigateUpTo(this, getIntent());
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
