package org.geeklub.smartlib.module.detail;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.InjectView;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.R;

/**
 * Created by Vass on 2014/11/6.
 */
public class BookDetailActivity extends BaseActivity {

  @InjectView(R.id.tv_book_author) TextView mBookAuthor;

  @InjectView(R.id.tv_book_price) TextView mBookPrice;

  @InjectView(R.id.tv_book_status) TextView mBookStatus;

  private SummaryBook mBook;



  public static final String EXTRAS_BOOK = "extras_book";

  @Override protected int getLayoutResource() {
    return R.layout.activity_detail_book;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mBook = getIntent().getParcelableExtra(EXTRAS_BOOK);

    initViews();
  }

  private void initViews() {
    setTitle(mBook.book_name);
    mBookAuthor.setText("作者:" + mBook.book_author);
    //mBookPrice.setText("价格:" + mBook.());
    mBookStatus.setText("当前状态:" + mBook.book_status);
  }

  @Override protected void initActionBar() {
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
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
