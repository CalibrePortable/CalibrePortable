package org.geeklub.smartlib.adapters;

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
import org.geeklub.smartlib.beans.Book;

/**
 * Created by Vass on 2014/11/3.
 */
public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

  private List<Book> mData;

  private Context mContext;

  public LibraryAdapter(Context context) {
    mData = new ArrayList<Book>();
    mContext = context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view =
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_library, null);

    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
    //Book book = mData.get(i);
    //viewHolder.mBookName.setText();
    //viewHolder.mBookDescripion.setText();

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
}
