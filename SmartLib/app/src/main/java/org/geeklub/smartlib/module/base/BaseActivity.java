package org.geeklub.smartlib.module.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.geeklub.smartlib.R;

/**
 * Created by Vass on 2014/11/6.
 */
public abstract class BaseActivity extends ActionBarActivity {

  public @InjectView(R.id.toolbar) Toolbar mToolBar;

  public @InjectView(R.id.toolbar_title) TextView mToolBarTitle;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutResource());

    ButterKnife.inject(this);

    if (mToolBar != null) {
      setSupportActionBar(mToolBar);
      initActionBar();
    }
  }

  protected abstract void initActionBar();

  protected abstract int getLayoutResource();

  @Override public void setTitle(CharSequence title) {
    mToolBarTitle.setText(title);
  }
}
