package org.geeklub.smartlib4admin.module.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.geeklub.smartlib4admin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vass on 2014/12/10.
 */
public class BookDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mListData;

    public static final int VIEW_TYPE_PLACE_HOLDER = 0;
    public static final int VIEW_TYPE_ITEM = 1;


    public BookDetailAdapter() {
        mListData = new ArrayList<String>();
    }


    public void addItems(List<String> list) {
        mListData.addAll(list);
        notifyDataSetChanged();

    }

    public static class ViewHolderItem extends RecyclerView.ViewHolder {

        public
        @InjectView(android.R.id.text1)
        TextView mBookId;


        public ViewHolderItem(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    public static class PlaceHolder extends RecyclerView.ViewHolder {


        public PlaceHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


        switch (viewType) {
            case VIEW_TYPE_PLACE_HOLDER:
                return new PlaceHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_header_placeholder, viewGroup, false));

            case VIEW_TYPE_ITEM:
                return new ViewHolderItem(LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false));

            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_PLACE_HOLDER:
                break;
            case VIEW_TYPE_ITEM:
                ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
                viewHolderItem.mBookId.setText(mListData.get(position));
                break;
        }

    }


    @Override
    public int getItemCount() {
        return mListData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_PLACE_HOLDER : VIEW_TYPE_ITEM;
    }
}
