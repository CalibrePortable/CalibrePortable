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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.Book;

/**
 * Created by Vass on 2014/11/8.
 */
public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.ViewHolder> {

  private List<Book> mData;

  private Context mContext;

  private OnItemClickListener onItemClickListener;

  public BorrowAdapter(Context context) {
    mData = new ArrayList<Book>();
    mContext = context;
  }

  public void refresh(List<Book> bookList) {
    if (!mData.isEmpty()) {
      mData.clear();
    }
    mData.addAll(bookList);
    notifyDataSetChanged();
  }

  public void addItems(List<Book> bookList) {
    if (!mData.containsAll(bookList)) {
      mData.addAll(bookList);
      notifyItemRangeInserted(getItemCount(), bookList.size());
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.cardview_borrow, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int position) {
    final Book book = mData.get(position);
    viewHolder.mBookName.setText(book.getBook_name());
    viewHolder.mBookDescripion.setText(book.getBook_author());
    viewHolder.mBookFavour.setText(book.getFavour());

    Picasso.with(mContext)
        .load(book.getBook_pic())
        .placeholder(R.drawable.ic_launcher)
        .error(R.drawable.ic_launcher)
        .into(viewHolder.mBookIcon);

    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onItemClickListener != null) {
          onItemClickListener.onItemClick(book);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return mData.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.iv_book_icon) ImageView mBookIcon;

    @InjectView(R.id.tv_book_name) TextView mBookName;

    @InjectView(R.id.tv_book_description) TextView mBookDescripion;

    @InjectView(R.id.tv_book_favour) TextView mBookFavour;

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
