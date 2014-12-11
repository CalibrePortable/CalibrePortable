package org.geeklub.smartlib4admin.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.BookDetailInfo;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Vass on 2014/12/10.
 */
public class BookDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_PLACE_HOLDER = 0;

    public static final int VIEW_TYPE_BOOK_ITEM = 1;


    private Context mContext;

    private BookDetailInfo bookDetailInfo;


    public BookDetailAdapter(Context context) {
        this.mContext = context;
    }


    public static class ViewPlaceHolder extends RecyclerView.ViewHolder {
        public ViewPlaceHolder(View itemView) {
            super(itemView);
        }
    }


    public void setBookDetailInfo(BookDetailInfo info) {
        this.bookDetailInfo = info;
        notifyDataSetChanged();
    }


    public static class ViewHolderBookItem extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_book_id)
        TextView mBookId;

        @InjectView(R.id.tv_book_status)
        TextView mBookStatus;

        public ViewHolderBookItem(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PLACE_HOLDER:
                return new ViewPlaceHolder(LayoutInflater.from(mContext).inflate(R.layout.view_header_placeholder, viewGroup, false));

            case VIEW_TYPE_BOOK_ITEM:
                return new ViewHolderBookItem(LayoutInflater.from(mContext).inflate(R.layout.item_book, viewGroup, false));

            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)) {

            case VIEW_TYPE_PLACE_HOLDER:
                break;

            case VIEW_TYPE_BOOK_ITEM:
                Book book = bookDetailInfo.book_list.get(position - 1);
                ViewHolderBookItem holderBookItem = (ViewHolderBookItem) viewHolder;
                holderBookItem.mBookId.setText("书本ID:" + book.book_id);
                holderBookItem.mBookStatus.setText("书本状态:" + book.book_status);
                break;
            default:
                break;
        }

    }


    @Override
    public int getItemCount() {
        return bookDetailInfo == null ? 0 : bookDetailInfo.book_list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_PLACE_HOLDER : VIEW_TYPE_BOOK_ITEM;
    }
}
