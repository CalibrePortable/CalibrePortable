package org.geeklub.smartlib.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.SummaryBook;
import org.geeklub.smartlib.utils.LogUtil;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryAdapter extends BaseRecyclerAdapter<SummaryBook, LibraryAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_book_icon)
        ImageView mBookIcon;
        @InjectView(R.id.tv_book_name)
        TextView mBookName;
        @InjectView(R.id.tv_book_description)
        TextView mBookDescription;
        @InjectView(R.id.tv_book_favour)
        TextView mBookFavour;
        @InjectView(R.id.tv_add_one)
        TextView mAddOneTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    private Context mContext;

    private OnItemClickListener onItemClickListener;

    private OnFavourClickListener onFavourClickListener;

    private Animation mAnimation;

    public static interface OnItemClickListener {
        void onItemClick(SummaryBook book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static interface OnFavourClickListener {
        void onFavourClick(SummaryBook book);
    }

    public void setOnFavourClickListener(OnFavourClickListener listener) {
        this.onFavourClickListener = listener;
    }

    public LibraryAdapter(Context context) {
        mContext = context;
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_library, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        final SummaryBook summaryBook = get(position);

        viewHolder.mBookName.setText(summaryBook.book_name);

        StringBuilder description = new StringBuilder();
        description.append("作者:")
                .append(summaryBook.book_author)
                .append("\n\n当前状态:")
                .append(summaryBook.book_status);
        viewHolder.mBookDescription.setText(description);

        LogUtil.i("点赞数 ===>>>" + summaryBook.favour);
        viewHolder.mBookFavour.setText(summaryBook.favour);
        viewHolder.mAddOneTextView.setVisibility(View.GONE);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(summaryBook);
                }
            }
        });

        if ("1".equals(summaryBook.isLike)) {
            viewHolder.mBookFavour.setEnabled(false);
        } else {
            viewHolder.mBookFavour.setEnabled(true);
        }

        viewHolder.mBookFavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFavourClickListener != null) {
                    //禁用点赞按钮，否则会出现疯狂点赞情况
                    v.setEnabled(false);
                    //更新adapter中的数据
                    onFavourClickListener.onFavourClick(summaryBook);
                    //载入动画
                    mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.dianzan_anim);
                    //设置动画监听器
                    mAnimation.setAnimationListener(new DianZanAnimation(position));
                    //开启动画
                    startDianZanAnimation(viewHolder.mAddOneTextView);
                }
            }
        });
    }

    public void startDianZanAnimation(final View addOne) {

        if (addOne.getVisibility() == View.GONE) {
            addOne.setVisibility(View.VISIBLE);
            addOne.startAnimation(mAnimation);
        }
    }

    private class DianZanAnimation implements Animation.AnimationListener {
        private int mPosition;

        public DianZanAnimation(int position) {
            this.mPosition = position;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //动画结束后更新视图
            updateItem(mPosition);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
