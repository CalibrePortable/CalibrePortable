package org.geeklub.smartlib4admin.module.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.module.adapters.BookDetailAdapter;
import org.geeklub.smartlib4admin.module.base.BaseActivity;
import org.geeklub.smartlib4admin.utils.LogUtil;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by Vass on 2014/12/10.
 */
public class BookDetailActivity extends BaseActivity {


    private int mHeaderHeight;

    private int mMinHeaderTranslation;

    private LinearLayoutManager linearLayoutManager;

    private BookDetailAdapter mAdapter;

    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.toolbar)
    Toolbar mToolBar;

    @InjectView(R.id.header)
    View mHeader;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_book_detail;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getToolBarHeight();

        setUpToolBar();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        ArrayList<String> FAKES = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            FAKES.add("entry " + i);
        }
        mAdapter = new BookDetailAdapter();
        mAdapter.addItems(FAKES);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int scrollY = getScrollY();
                LogUtil.i("mMinHeaderTranslation ===>>>" + mMinHeaderTranslation);
                //sticky actionbar
                mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
            }
        });

    }

    private int getScrollY() {
        View c = linearLayoutManager.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    private void setUpToolBar() {
        mToolBar.setBackgroundColor(getResources().getColor(R.color.TRANSPARRENT));
        setSupportActionBar(mToolBar);
    }

    private int getToolBarHeight() {
        return getResources().getDimensionPixelOffset(R.dimen.toolbar_size);
    }
}
