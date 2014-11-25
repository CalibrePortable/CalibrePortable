package org.geeklub.smartlib4admin.module.search;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.View;
import butterknife.InjectView;
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.module.base.BaseActivity;

/**
 * Created by Vass on 2014/11/25.
 */
public class SearchActivity extends BaseActivity {

  @InjectView(R.id.sliding_tabs) SlidingPaneLayout mSlidingPaneLayout;

  @InjectView(R.id.viewpager) ViewPager mViewPager;

  private SearchPagerAdapter mAdapter;

  @Override protected void initActionBar() {


  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAdapter = new SearchPagerAdapter(getFragmentManager());

  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_search;
  }

  @Override public View onCreateView(View parent, String name, Context context,
      AttributeSet attrs) {
    View view =  super.onCreateView(parent, name, context, attrs);

    mViewPager.setAdapter(mAdapter);





    return view;
  }



  private class SearchPagerAdapter extends FragmentStatePagerAdapter {

    public SearchPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      return null;
    }

    @Override public int getCount() {
      return 0;
    }
  }
}
