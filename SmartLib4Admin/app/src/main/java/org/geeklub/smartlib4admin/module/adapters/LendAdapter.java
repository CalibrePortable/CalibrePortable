package org.geeklub.smartlib4admin.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;

/**
 * Created by Vass on 2014/11/8.
 */
public class LendAdapter extends BaseRecyclerAdapter<Book, LendAdapter.ViewHolder> {

    private Context mContext;

    private OnItemClickListener onItemClickListener;

    private OnItemRemoveListener onItemRemoveListener;

    public LendAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_lend, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Book book = get(position);

        viewHolder.mBookName.setText(book.book_name);
        viewHolder.mBookDescription.setText(book.book_status);
        viewHolder.mBorrowAt.setText("FROM:" + book.created_at);

        if (Integer.valueOf(book.return_at) > 0) {
            viewHolder.mReturnAt.setText("剩余:" + book.return_at);
        } else {
            viewHolder.mReturnAt.setText("超期:" + Math.abs(Integer.valueOf(book.return_at)));
        }
        viewHolder.mBookFavour.setText(book.favour);
        viewHolder.mUserName.setText(book.user_name);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(book);
                }
            }
        });

        viewHolder.mBookDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemRemoveListener != null) {
                    onItemRemoveListener.onItemRemove(book);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_borrow_at)
        TextView mBorrowAt;

        @InjectView(R.id.tv_return_at)
        TextView mReturnAt;

        @InjectView(R.id.iv_book_icon)
        ImageView mBookIcon;

        @InjectView(R.id.tv_book_name)
        TextView mBookName;


        @InjectView(R.id.tv_book_description)
        TextView mBookDescription;

        @InjectView(R.id.tv_book_favour)
        TextView mBookFavour;

        @InjectView(R.id.tv_user_name)
        TextView mUserName;

        @InjectView(R.id.tv_delete)
        TextView mBookDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static interface OnItemRemoveListener {
        void onItemRemove(Book book);
    }

    public void setOnItemRemoveListener(OnItemRemoveListener listener) {
        this.onItemRemoveListener = listener;
    }
}
