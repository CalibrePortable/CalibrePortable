package org.geeklub.smartlib.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.ArrayList;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.Book;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

  private List<Book> mData;

  private Context mContext;

  private OnItemClickListener onItemClickListener;

  public LibraryAdapter(Context context) {
    mData = new ArrayList<Book>();
    mContext = context;
  }

  public void refresh(List<Book> bookList) {
    if (!mData.isEmpty()) {
      mData.clear();
      mData.addAll(bookList);
      notifyDataSetChanged();
    }
  }

  public void addItems(List<Book> bookList) {
    if (!mData.containsAll(bookList)) {
      mData.addAll(bookList);
      notifyItemRangeInserted(getItemCount(), bookList.size());
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.cardview_library, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int position) {
    final Book book = mData.get(position);
    viewHolder.mBookName.setText(book.getBook_name());
    viewHolder.mBookDescripion.setText(book.getBook_author());

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
