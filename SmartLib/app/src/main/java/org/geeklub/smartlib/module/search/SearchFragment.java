package org.geeklub.smartlib.module.search;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.InjectView;

import java.util.List;

import org.geeklub.smartlib.GlobalContext;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.module.adapters.SearchAdapter;
import org.geeklub.smartlib.module.base.BaseFragment;
import org.geeklub.smartlib.module.detail.BookDetailActivity;
import org.geeklub.smartlib.utils.BitmapUtil;
import org.geeklub.smartlib.utils.DisplayParams;
import org.geeklub.smartlib.utils.DisplayUtil;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SmartLibraryUser;
import org.geeklub.smartlib.utils.ToastUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/17.
 */
public class SearchFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARGS_KEYWORD = "args_query_word";

    private static final String ARGS_TYPE = "args_type";

    private static final String ARG_POSITION = "args_position";

    private int mPosition;


    private static final int[] drawables = {R.drawable.search_fragment_bg_1, R.drawable.search_fragment_bg_2, R.drawable.search_fragment_bg_3, R.drawable.search_fragment_bg_4};

    private String mQueryWord;

    private int mType;

    private SearchAdapter mAdapter;

    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout mRefreshLayout;

    @InjectView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.iv_background)
    ImageView mBackground;


    private int mPage = 1;

    public static Fragment newInstance(int type, String keyWord, int position) {
        Fragment searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_KEYWORD, keyWord);
        args.putInt(ARGS_TYPE, type);
        args.putInt(ARG_POSITION, position);
        searchFragment.setArguments(args);
        return searchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new SearchAdapter(mActivity);

        mAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SummaryBook book) {
                Intent intent = new Intent(mActivity, BookDetailActivity.class);
                intent.putExtra(BookDetailActivity.EXTRAS_BOOK_DETAIL_URL, book.book_kind);
                startActivity(intent);
            }
        });

        mAdapter.setOnFavourClickListener(new SearchAdapter.OnFavourClickListener() {
            @Override
            public void onFavourClick(SummaryBook book) {
                book.isLike = "1";
                book.favour = Integer.valueOf(book.favour) + 1 + "";
                sendDianZanMsg(book);
            }
        });
    }

    private void sendDianZanMsg(SummaryBook book) {
        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        service.likePlusOne(book.book_kind, user.getUserId(), user.getPassWord(),
                new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {
                        LogUtil.i("点赞成功 ...");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        LogUtil.i("点赞失败 ===>>>" + error.getMessage());
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parseArgument();
        View view = super.onCreateView(inflater, container, savedInstanceState);

        int height = DisplayParams.getInstance(mActivity).screenHeight;
        int width = DisplayParams.getInstance(mActivity).screenWidth;



        mBackground.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(mActivity.getResources(), drawables[mPosition], width, height));


        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        loadNextPage();
                        break;

                    default:
                        break;
                }
            }
        });

        loadFirstPage();

        return view;
    }

    private void loadFirstPage() {
        mPage = 1;
        loadData(mPage);
    }

    private void loadData(int page) {

        final boolean isRefreshFromTop = (page == 1);

        if (!mRefreshLayout.isRefreshing() && isRefreshFromTop) {
            mRefreshLayout.setRefreshing(true);
        }

        SmartLibraryUser user = SmartLibraryUser.getCurrentUser();
        NormalUserService service = GlobalContext.getApiDispencer().getRestApi(NormalUserService.class);

        service.search(user.getUserId(), mType, page, mQueryWord, new Callback<List<SummaryBook>>() {
            @Override
            public void success(List<SummaryBook> bookList, Response response) {
                mRefreshLayout.setRefreshing(false);
                if (isRefreshFromTop) {
                    mAdapter.replaceWith(bookList);
                } else {
                    mAdapter.addAll(bookList);
                }
                mPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtil.i(error.getMessage());
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadNextPage() {
        loadData(mPage);
    }

    private void parseArgument() {
        Bundle args = getArguments();
        mQueryWord = args.getString(ARGS_KEYWORD);
        mType = args.getInt(ARGS_TYPE);
        mPosition = args.getInt(ARG_POSITION);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void onRefresh() {
        loadFirstPage();
    }

    public static int getBackgroundBitmapPosition(int selectViewPagerItem) {
        return drawables[selectViewPagerItem];
    }


}
