package org.geeklub.smartlib4admin.module.adapters;

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
import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.Book;

/**
 * Created by Vass on 2014/11/8.
 */
public class LendAdapter extends RecyclerView.Adapter<LendAdapter.ViewHolder> {

  private List<Book> mData;

  private Context mContext;

  private OnItemClickListener onItemClickListener;

  private OnFavourClickListener onFavourClickListener;

  private Animation mAnimation;

  public LendAdapter(Context context) {
    mData = new ArrayList<Book>();
    mContext = context;
  }

  public void updateItem(int position) {
    notifyItemChanged(position);
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
        .inflate(R.layout.cardview_lend, viewGroup, false);

    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
    final Book book = mData.get(position);

    viewHolder.mBookName.setText(book.getBook_name());

    StringBuilder description = new StringBuilder();
    description.append(book.getBook_author())
        .append("/")
        .append(book.getBook_type())
        .append("/")
        .append(book.getBook_info());
    viewHolder.mBookDescription.setText(description);

    viewHolder.mBookFavour.setText(book.getFavour());
    viewHolder.mPlusOne.setVisibility(View.GONE);
    viewHolder.mBorrowAt.setText("FROM:"+book.getCreated_at());
    viewHolder.mReturnAt.setText("TO:"+book.getReturn_at());

    Picasso.with(mContext)
        .load(book.getBook_pic())
        .placeholder(R.drawable.ic_launcher)
        .error(R.drawable.ic_launcher)
        .into(viewHolder.mBookIcon);


    if (book.getIsLike() == 1) {
      //LogUtil.i("已经点赞过了");
      viewHolder.mBookFavour.setEnabled(false);
    } else {
      //LogUtil.i("还没有点赞过");
      viewHolder.mBookFavour.setEnabled(true);
    }

    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onItemClickListener != null) {
          onItemClickListener.onItemClick(book);
        }
      }
    });

    viewHolder.mBookFavour.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onFavourClickListener != null) {
          onFavourClickListener.onFavourClick(book);

          v.setEnabled(false);
          mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.dianzan_anim);
          mAnimation.setAnimationListener(new DianZanAnimationListener(position));
          startDianZanAnimation(viewHolder.mPlusOne);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return mData.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.tv_borrow_at) TextView mBorrowAt;

    @InjectView(R.id.tv_return_at) TextView mReturnAt;

    @InjectView(R.id.iv_book_icon) ImageView mBookIcon;

    @InjectView(R.id.tv_book_name) TextView mBookName;

    @InjectView(R.id.tv_add_one) TextView mPlusOne;

    @InjectView(R.id.tv_book_description) TextView mBookDescription;

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

  public static interface OnFavourClickListener {
    void onFavourClick(Book book);
  }

  public void setOnFavourClickListener(OnFavourClickListener listener) {
    this.onFavourClickListener = listener;
  }

  private void startDianZanAnimation(final View plusOne) {

    if (plusOne.getVisibility() == View.GONE) {
      plusOne.setVisibility(View.VISIBLE);
      plusOne.startAnimation(mAnimation);
    }
  }

  private class DianZanAnimationListener implements Animation.AnimationListener {
    private int mPosition;

    public DianZanAnimationListener(int position) {
      this.mPosition = position;
    }

    @Override public void onAnimationStart(Animation animation) {

    }

    @Override public void onAnimationEnd(Animation animation) {
      updateItem(mPosition);
    }

    @Override public void onAnimationRepeat(Animation animation) {

    }
  }
}
