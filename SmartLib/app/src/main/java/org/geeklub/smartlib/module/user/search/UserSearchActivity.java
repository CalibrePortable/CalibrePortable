package org.geeklub.smartlib.module.user.search;

import android.app.SearchManager;
import android.content.Intent;
import org.geeklub.smartlib.module.base.BaseSearchResultsActivity;
import org.geeklub.smartlib.utils.LogUtil;

/**
 * Created by Vass on 2014/11/17.
 */
public class UserSearchActivity extends BaseSearchResultsActivity {

  @Override protected void handleIntent(Intent intent) {
    super.handleIntent(intent);

    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);

      LogUtil.i("query_word ===>>>"+query);

      getFragmentManager().beginTransaction()
          .replace(android.R.id.content, UserSearchFragment.newInstance(query))
          .commit();
    }
  }
}
