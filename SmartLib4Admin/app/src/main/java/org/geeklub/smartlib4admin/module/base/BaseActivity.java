package org.geeklub.smartlib4admin.module.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;

/**
 * Created by Vass on 2014/11/6.
 */
public abstract class BaseActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutResource());




    ButterKnife.inject(this);
  }

  protected abstract void initActionBar();

  protected abstract int getLayoutResource();
}
