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
 * Created by Vass on 2014/11/7.
 */
public class SearchAdapter extends BaseRecyclerAdapter<Book, SearchAdapter.ViewHolder> {


    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_search, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Book book = get(position);
        viewHolder.mBookName.setText(book.book_name);
        viewHolder.mBookDescription.setText(book.book_status);
        viewHolder.mBookFavour.setText(book.favour);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(book);
                }
            }
        });
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
}
