package org.geeklub.smartlib.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.Book;
import org.geeklub.smartlib.utils.LogUtil;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

  private List<Book> mData;

  private Context mContext;

  private OnItemClickListener onItemClickListener;

  private OnFavourClickListener onFavourClickListener;

  public LibraryAdapter(Context context) {
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

  public void updateItem(int position) {
    LogUtil.i("position ===>>>" + position);
    notifyItemChanged(position);
  }

  public void addItems(List<Book> bookList) {
    if (!mData.containsAll(bookList)) {
      mData.addAll(bookList);
      notifyItemRangeInserted(getItemCount(), bookList.size());
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    //LogUtil.i("创建第" + position + "个ViewHolder");
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.cardview_library, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
    //LogUtil.i("绑定第" + position + "个ViewHolder");

    final Book book = mData.get(position);
    LogUtil.i("Book Info ===>>>" + book.toString());

    viewHolder.mBookName.setText(book.getBook_name());
    viewHolder.mBookDescription.setText(book.getBook_author());
    viewHolder.mBookFavour.setText(book.getFavour());
    viewHolder.mAddOneTextView.setVisibility(View.GONE);

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

    if (book.isLike()) {
      viewHolder.mBookFavour.setEnabled(false);
    } else {
      viewHolder.mBookFavour.setEnabled(true);
    }

    viewHolder.mBookFavour.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onFavourClickListener != null) {
          onFavourClickListener.onFavourClick(book, viewHolder.mAddOneTextView, position);
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

    @InjectView(R.id.tv_book_description) TextView mBookDescription;

    @InjectView(R.id.tv_book_favour) TextView mBookFavour;

    @InjectView(R.id.tv_add_one) TextView mAddOneTextView;

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

  public static interface OnFavourClickListener {
    void onFavourClick(Book book, View addOneTextView, int position);
  }

  public void setOnFavourClickListener(OnFavourClickListener listener) {
    this.onFavourClickListener = listener;
  }
}
