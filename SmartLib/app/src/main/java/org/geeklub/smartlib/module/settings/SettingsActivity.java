package org.geeklub.smartlib.module.settings;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.InjectView;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.module.search.SearchFragment;

/**
 * Created by Vass on 2014/11/12.
 */
public class SettingsActivity extends BaseActivity {

  @InjectView(R.id.toolbar) Toolbar mToolBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initToolBar();

    getFragmentManager().beginTransaction()
        .replace(R.id.content_frame, new SettingsFragment())
        .commit();

  }

  private void initToolBar() {
    mToolBar.setTitle("设置");
    mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    setSupportActionBar(mToolBar);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_settings;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpTo(this, getIntent());
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
