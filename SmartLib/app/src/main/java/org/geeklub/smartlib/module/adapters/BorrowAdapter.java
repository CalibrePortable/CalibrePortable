package org.geeklub.smartlib.module.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.Book;

/**
 * Created by Vass on 2014/11/8.
 */
public class BorrowAdapter extends BaseRecyclerAdapter<Book, BorrowAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_book_icon)
        ImageView mBookIcon;

        @InjectView(R.id.tv_book_name)
        TextView mBookName;

        @InjectView(R.id.tv_book_description)
        TextView mBookDescription;

        @InjectView(R.id.tv_book_favour)
        TextView mBookFavour;

        @InjectView(R.id.tv_borrow_at)
        TextView mBorrowAt;

        @InjectView(R.id.tv_return_at)
        TextView mReturnAt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(Book book);
    }


    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public BorrowAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_borrow, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Book book = get(position);

        viewHolder.mBookName.setText(book.book_name);
        viewHolder.mBookDescription.setText(book.book_author);
        viewHolder.mBookFavour.setText(book.favour);
        viewHolder.mBorrowAt.setText("FROM:" + book.created_at);


        if ("已还".equals(book.book_status)) {
            viewHolder.mReturnAt.setTextColor(Color.GREEN);
            viewHolder.mReturnAt.setText("已还");
        } else {
            viewHolder.mReturnAt.setTextColor(Color.RED);
            if (Integer.valueOf(book.return_at) > 0) {
                viewHolder.mReturnAt.setText("剩余:" + book.return_at + "天");
            } else {
                viewHolder.mReturnAt.setText("超期:" + Math.abs(Integer.valueOf(book.return_at)) + "天");
            }
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(book);
                }
            }
        });

        Picasso.with(mContext)
                .load(book.book_pic)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(viewHolder.mBookIcon);
    }
}
