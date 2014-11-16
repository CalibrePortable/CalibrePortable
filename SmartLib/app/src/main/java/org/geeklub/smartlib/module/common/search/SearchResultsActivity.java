package org.geeklub.smartlib.module.common.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import org.geeklub.smartlib.R;

/**
 * Created by Vass on 2014/11/3.
 */
public class SearchResultsActivity extends ActionBarActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initActionBar();

    handleIntent(getIntent());
  }

  private void initActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);

    handleIntent(intent);
  }

  private void handleIntent(Intent intent) {

    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      //LogUtil.i("query ===>>>" + query);

      getFragmentManager().beginTransaction()
          .replace(android.R.id.content, SearchResultsFragment.newInstance(query))
          .commit();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.search, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView =
        (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        //我也不知道这么做对不对
        NavUtils.navigateUpTo(this, getIntent());
    }
    return super.onOptionsItemSelected(item);
  }
}
