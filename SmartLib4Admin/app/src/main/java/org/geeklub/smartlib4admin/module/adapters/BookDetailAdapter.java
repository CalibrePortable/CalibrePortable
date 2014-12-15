package org.geeklub.smartlib4admin.module.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;
import org.geeklub.smartlib4admin.beans.BookDetailInfo;
import org.geeklub.smartlib4admin.utils.Blur;
import org.geeklub.smartlib4admin.utils.ScreenUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Vass on 2014/12/10.
 */
public class BookDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;

    public static final int VIEW_TYPE_BOOK_ITEM = 1;

    private Context mContext;

    private BookDetailInfo bookDetailInfo;

    private OnItemRemoveListener onItemRemoveListener;


    public BookDetailAdapter(Context context) {
        this.mContext = context;
    }


    public static class ViewHolderHeader extends RecyclerView.ViewHolder {


        @InjectView(R.id.blurred_image)
        ImageView mBlurredImage;

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


    public void setBookDetailInfo(BookDetailInfo info) {
        this.bookDetailInfo = info;
        notifyDataSetChanged();
    }

    public void deleteBook(Book book) {
        int index = bookDetailInfo.book_list.indexOf(book);
        bookDetailInfo.book_list.remove(book);
        notifyItemRemoved(index + 1);

    }


    public static class ViewHolderBookItem extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_book_id)
        TextView mBookId;

        @InjectView(R.id.tv_book_status)
        TextView mBookStatus;

        @InjectView(R.id.tv_book_delete)
        TextView mBookDelete;

        public ViewHolderBookItem(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return new ViewHolderHeader(LayoutInflater.from(mContext).inflate(R.layout.book_detail_header, viewGroup, false));

            case VIEW_TYPE_BOOK_ITEM:
                return new ViewHolderBookItem(LayoutInflater.from(mContext).inflate(R.layout.book_detail_item, viewGroup, false));

            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)) {

            case VIEW_TYPE_HEADER:
                ViewHolderHeader header = (ViewHolderHeader) viewHolder;

                setUpHeaderBackground(header.mBlurredImage);
                header.mBookName.setText("书名:" + bookDetailInfo.book_detail.book_name);
                header.mBookAuthor.setText("作者:" + bookDetailInfo.book_detail.book_author);
                header.mBookType.setText("类别:" + bookDetailInfo.book_detail.book_type);
                header.mBookEdit.setText("出版社:" + bookDetailInfo.book_detail.book_edit);
                header.mBookPub.setText("版次:" + bookDetailInfo.book_detail.book_pub);
                header.mBookPrice.setText("价格:" + bookDetailInfo.book_detail.book_price + "￥");
                header.mBookInfo.setText("介绍:" + bookDetailInfo.book_detail.book_info);

                Picasso.with(mContext)
                        .load(bookDetailInfo.book_detail.book_pic)
                        .placeholder(R.drawable.ic_launcher)
                        .error(R.drawable.ic_launcher)
                        .into(header.mBookIcon);

                break;

            case VIEW_TYPE_BOOK_ITEM:
                final Book book = bookDetailInfo.book_list.get(position - 1);
                ViewHolderBookItem bookItem = (ViewHolderBookItem) viewHolder;
                bookItem.mBookId.setText("书本ID:" + book.book_id);
                bookItem.mBookStatus.setText("书本状态:" + book.book_status);

                bookItem.mBookDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemRemoveListener != null) {
                            onItemRemoveListener.onItemRemove(book);
                        }
                    }
                });


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
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_BOOK_ITEM;
    }

    public static interface OnItemRemoveListener {
        void onItemRemove(Book book);
    }

    public void setOnItemRemoveListener(OnItemRemoveListener listener) {
        this.onItemRemoveListener = listener;
    }


    private void setUpHeaderBackground(ImageView blurredImage) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap image = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.picture0);
        Bitmap newImg = Blur.fastblur(mContext, image, 12);
        Bitmap bmpBlurred = Bitmap.createScaledBitmap(newImg, ScreenUtil.getScreenWidth((android.app.Activity) mContext), mContext.getResources().getDimensionPixelSize(R.dimen.header_height), false);
        blurredImage.setImageBitmap(bmpBlurred);
    }
}
