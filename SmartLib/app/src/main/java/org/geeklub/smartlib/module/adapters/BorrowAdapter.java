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
import java.util.ArrayList;
import java.util.List;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.SummaryBook;

/**
 * Created by Vass on 2014/11/8.
 */
public class BorrowAdapter extends BaseRecyclerAdapter<SummaryBook, BorrowAdapter.ViewHolder> {

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.iv_book_icon) ImageView mBookIcon;

    @InjectView(R.id.tv_book_name) TextView mBookName;

    @InjectView(R.id.tv_book_description) TextView mBookDescription;

    @InjectView(R.id.tv_book_favour) TextView mBookFavour;

    @InjectView(R.id.tv_borrow_at) TextView mBorrowAt;

    @InjectView(R.id.tv_return_at) TextView mReturnAt;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }

  public static interface OnItemClickListener {
    void onItemClick(SummaryBook book);
  }

  private List<SummaryBook> mData;

  private Context mContext;

  private OnItemClickListener onItemClickListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    this.onItemClickListener = listener;
  }

  public BorrowAdapter(Context context) {
    mData = new ArrayList<SummaryBook>();
    mContext = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.cardview_borrow, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int position) {
    final SummaryBook book = mData.get(position);

    viewHolder.mBookName.setText(book.book_name);
    viewHolder.mBookDescription.setText(book.book_author);
    viewHolder.mBookFavour.setText(book.favour);
    viewHolder.mBorrowAt.setText("FROM:" + book.created_at);

    if (Integer.valueOf(book.created_at) > 0) {
      viewHolder.mReturnAt.setText("剩余:" + book.return_at);
    } else {
      viewHolder.mReturnAt.setText("超期:" + Math.abs(Integer.valueOf(book.return_at)));
    }

    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onItemClickListener != null) {
          onItemClickListener.onItemClick(book);
        }
      }
    });
  }
}
