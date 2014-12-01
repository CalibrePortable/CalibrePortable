package org.geeklub.smartlib.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.beans.BookDetailInfo;

/**
 * Created by Vass on 2014/12/1.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_book_icon)
        ImageView mBookIcon;
        @InjectView(R.id.tv_book_name)
        TextView mBookName;
        @InjectView(R.id.tv_book_author)
        TextView mBookAuthor;
        @InjectView(R.id.tv_book_type)
        TextView mBookType;
        @InjectView(R.id.tv_book_edit)
        TextView mBookEdit;
        @InjectView(R.id.tv_book_pub)
        TextView mBookPub;
        @InjectView(R.id.tv_book_price)
        TextView mBookPrice;
        @InjectView(R.id.tv_book_info)
        TextView mBookInfo;

        public ViewHolderHeader(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static class ViewHolderItem extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_book_id)
        TextView mBookId;
        @InjectView(R.id.tv_created_at)
        TextView mCreatedAt;
        @InjectView(R.id.tv_return_at)
        TextView mReturnAt;
        @InjectView(R.id.tv_user_name)
        TextView mUserName;
        @InjectView(R.id.tv_book_status)
        TextView mBookStatus;

        public ViewHolderItem(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_BOOK = 1;

    private Context mContext;

    private BookDetailInfo bookDetailInfo;

    public DetailAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return new ViewHolderHeader(
                        LayoutInflater.from(mContext).inflate(R.layout.header_detail, viewGroup, false));

            case VIEW_TYPE_BOOK:
                return new ViewHolderItem(
                        LayoutInflater.from(mContext).inflate(R.layout.item_book, viewGroup, false));
            default:
                return null;
        }
    }

    public void setBookDetailInfo(BookDetailInfo info) {
        this.bookDetailInfo = info;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_HEADER:
                ViewHolderHeader viewHolderHeader = (ViewHolderHeader) viewHolder;
                viewHolderHeader.mBookName.setText("书名:" + bookDetailInfo.book_detail.book_name);
                viewHolderHeader.mBookAuthor.setText("作者:" + bookDetailInfo.book_detail.book_author);
                viewHolderHeader.mBookType.setText("类别:" + bookDetailInfo.book_detail.book_type);
                viewHolderHeader.mBookEdit.setText("出版社:" + bookDetailInfo.book_detail.book_edit);
                viewHolderHeader.mBookPub.setText("版次:" + bookDetailInfo.book_detail.book_pub);
                viewHolderHeader.mBookPrice.setText("价格:" + bookDetailInfo.book_detail.book_price + "￥");
                viewHolderHeader.mBookInfo.setText("介绍:" + bookDetailInfo.book_detail.book_info);
                break;

            case VIEW_TYPE_BOOK:
                Book book = bookDetailInfo.book_list.get(position - 1);
                ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
                viewHolderItem.mBookId.setText("书本ID:" + book.book_id);
                viewHolderItem.mCreatedAt.setText("借书日期:" + book.created_at);
                viewHolderItem.mReturnAt.setText("归还日期:" + book.return_at);
                viewHolderItem.mUserName.setText("借书人:" + book.user_name);
                viewHolderItem.mBookStatus.setText("书本状态:" + book.book_status);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return bookDetailInfo == null ? 0 : bookDetailInfo.book_list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_BOOK;
    }
}
