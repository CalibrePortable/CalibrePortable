package org.geeklub.smartlib.module.adapters;

import android.content.Context;
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
import org.geeklub.smartlib.beans.BookDetailInfo;

/**
 * Created by Vass on 2014/12/1.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_book_icon)
        ImageView mBookIcon;
        public
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
                viewHolderHeader.mBookName.setText("书名:" + bookDetailInfo.book_name);
                viewHolderHeader.mBookAuthor.setText("作者:" + bookDetailInfo.book_author);
                viewHolderHeader.mBookType.setText("类别:" + bookDetailInfo.book_type);
                viewHolderHeader.mBookEdit.setText("出版社:" + bookDetailInfo.book_edit);
                viewHolderHeader.mBookPub.setText("版次:" + bookDetailInfo.book_pub);
                viewHolderHeader.mBookPrice.setText("价格:" + bookDetailInfo.book_price + "￥");
                viewHolderHeader.mBookInfo.setText("介绍:" + bookDetailInfo.book_info);

                Picasso.with(mContext)
                        .load(bookDetailInfo.book_pic)
                        .placeholder(R.drawable.ic_launcher)
                        .error(R.drawable.ic_launcher)
                        .into(viewHolderHeader.mBookIcon);

                break;

            case VIEW_TYPE_BOOK:
                Book book = bookDetailInfo.book_list.get(position - 1);
                ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
                viewHolderItem.mBookId.setText("ID:" + book.book_id);
                viewHolderItem.mCreatedAt.setText("借书日期:" + book.created_at);
                viewHolderItem.mUserName.setText("借书人:" + book.user_name);

                if (book.return_at == null) {
                    viewHolderItem.mReturnAt.setTextColor(mContext.getResources().getColor(R.color.GREEN_SEA));
                    viewHolderItem.mBookStatus.setText("状态:" + "未被借");
                    viewHolderItem.mUserName.setText("");
                    viewHolderItem.mCreatedAt.setText("");
                    viewHolderItem.mReturnAt.setText("");
                } else if (Integer.valueOf(book.return_at) > 0) {
                    viewHolderItem.mReturnAt.setTextColor(mContext.getResources().getColor(R.color.GREEN_SEA));
                    viewHolderItem.mReturnAt.setText("剩余:" + book.return_at + "天");
                } else {
                    viewHolderItem.mReturnAt.setTextColor(mContext.getResources().getColor(R.color.ALIZARIN));
                    viewHolderItem.mReturnAt.setText("超期:" + Math.abs(Integer.valueOf(book.return_at)) + "天");
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return bookDetailInfo == null ? 0 : 1 + bookDetailInfo.book_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_BOOK;
    }
}
