package org.geeklub.smartlib;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

/**
 * Created by Vass on 2014/11/13.
 */
public class ModifyPasswordActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(getString(R.string.modify_password));

    getFragmentManager().beginTransaction()
        .replace(R.id.content_frame, ModifyPasswordFragment.newInstance())
        .commit();
  }

  @Override protected void initActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setHomeButtonEnabled(true);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_modify_password;
  }


}
