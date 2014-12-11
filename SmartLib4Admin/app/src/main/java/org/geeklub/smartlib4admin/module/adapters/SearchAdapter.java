package org.geeklub.smartlib4admin.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.SummaryBook;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vass on 2014/11/7.
 */
public class SearchAdapter extends BaseRecyclerAdapter<SummaryBook, SearchAdapter.ViewHolder> {

    private Context mContext;

    private OnItemClickListener onItemClickListener;


    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
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

        if ("1".equals(book.isLike)) {
            viewHolder.mBookFavour.setEnabled(false);
        } else {
            viewHolder.mBookFavour.setEnabled(true);
        }

        StringBuilder description = new StringBuilder();
        description.append("作者:").append(book.book_author).append("\n\n当前状态:").append(book.book_status);
        viewHolder.mBookDescription.setText(description);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(book);
                }
            }
        });


    }

    public static interface OnItemClickListener {
        void onItemClick(SummaryBook book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


}
