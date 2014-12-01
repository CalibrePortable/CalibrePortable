package org.geeklub.smartlib.module.detail;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.InjectView;
import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.BookDetailInfo;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.DetailAdapter;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.utils.LogUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/6.
 */
public class BookDetailActivity extends BaseActivity {

  @InjectView(R.id.toolbar) Toolbar mToolBar;

  @InjectView(R.id.recycle_view) RecyclerView mRecyclerView;

  private DetailAdapter mAdapter;

  private SummaryBook mBook;

  public static final String EXTRAS_BOOK = "extras_book";

  @Override protected int getLayoutResource() {
    return R.layout.activity_detail_book;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBook = getIntent().getParcelableExtra(EXTRAS_BOOK);
    initToolBar();

    mAdapter = new DetailAdapter(this);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(mAdapter);

    loadData();
  }

  private void loadData() {
    NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);
    service.bookDetail(mBook.book_kind, new Callback<BookDetailInfo>() {
      @Override public void success(BookDetailInfo bookDetailInfo, Response response) {
        LogUtil.i("bookDetailInfo ===>>>" + bookDetailInfo.toString());
        mAdapter.setBookDetailInfo(bookDetailInfo);
      }

      @Override public void failure(RetrofitError error) {
        LogUtil.i("failure ===>>>" + error.getMessage());
      }
    });
  }

  private void initToolBar() {
    mToolBar.setTitle("图书详情");
    mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    setSupportActionBar(mToolBar);
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
