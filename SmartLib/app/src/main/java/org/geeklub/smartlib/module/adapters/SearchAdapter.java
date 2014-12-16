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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.SummaryBook;

/**
 * Created by Vass on 2014/11/7.
 */
public class SearchAdapter extends BaseRecyclerAdapter<SummaryBook, SearchAdapter.ViewHolder> {

    private Context mContext;

    private OnItemClickListener onItemClickListener;

    private OnFavourClickListener onFavourClickListener;

    private Animation mAnimation;

    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
    }

    public static interface OnItemClickListener {
        void onItemClick(SummaryBook book);
    }

    public static interface OnFavourClickListener {
        void onFavourClick(SummaryBook book);
    }

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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_search, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final SummaryBook book = get(position);

        viewHolder.mBookName.setText(book.book_name);
        viewHolder.mBookDescription.setText(book.book_author);
        viewHolder.mBookFavour.setText(book.favour);
        viewHolder.mAddOneTextView.setVisibility(View.GONE);

        Picasso.with(mContext)
                .load(book.book_pic)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(viewHolder.mBookIcon);

        if ("1".equals(book.isLike)) {
            viewHolder.mBookFavour.setEnabled(false);
        } else {
            viewHolder.mBookFavour.setEnabled(true);
        }

        StringBuilder description = new StringBuilder();
        description.append(book.book_author).append("\n\n").append(book.book_status);
        viewHolder.mBookDescription.setText(description);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(book);
                }
            }
        });

        viewHolder.mBookFavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFavourClickListener != null) {
                    //禁用点赞按钮，否则会出现疯狂点赞情况
                    v.setEnabled(false);
                    //更新adapter中的数据
                    onFavourClickListener.onFavourClick(book);
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnFavourClickListener(OnFavourClickListener listener) {
        this.onFavourClickListener = listener;
    }

    public void startDianZanAnimation(final View plusOne) {

        if (plusOne.getVisibility() == View.GONE) {
            plusOne.setVisibility(View.VISIBLE);
            plusOne.startAnimation(mAnimation);
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
