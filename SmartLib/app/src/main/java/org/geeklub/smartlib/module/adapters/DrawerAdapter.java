package org.geeklub.smartlib.module.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.geeklub.smartlib.R;
import org.geeklub.smartlib.utils.SmartLibraryUser;

import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;


@Deprecated
public class DrawerAdapter extends BaseRecyclerAdapter<String, RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;

    public static final int VIEW_TYPE_FUNCTION = 1;

    private OnItemClickListener onItemClickListener;

    private Context mContext;


    public DrawerAdapter(Context context) {
        this.mContext = context;
    }


    public static class ViewHolderHeader extends RecyclerView.ViewHolder {

        @InjectView(R.id.ci_user_picture)
        CircleImageView mUserPicture;

        @InjectView(R.id.tv_user_name)
        TextView mUserName;


        public ViewHolderHeader(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    @Override
    public boolean addAll(Collection<? extends String> collection) {
        synchronized (lock) {
            int lastIndex = list.size();
            if (list.addAll(collection)) {
                notifyItemRangeInserted(lastIndex + 1, collection.size());
                return true;
            } else {
                return false;
            }
        }
    }

    public static class ViewHolderFunction extends RecyclerView.ViewHolder {

        @InjectView(android.R.id.text1)
        TextView mFunctionName;

        public ViewHolderFunction(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return new ViewHolderHeader(
                        LayoutInflater.from(mContext).inflate(R.layout.header_drawer, viewGroup, false));

            case VIEW_TYPE_FUNCTION:
                return new ViewHolderFunction(
                        LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_activated_1, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        switch (getItemViewType(position)) {

            case VIEW_TYPE_HEADER:
                ViewHolderHeader header = (ViewHolderHeader) viewHolder;
                header.mUserPicture.setImageResource(R.drawable.drawer_header);
                header.mUserName.setText(SmartLibraryUser.getCurrentUser().getUserId());
                break;

            case VIEW_TYPE_FUNCTION:
                final ViewHolderFunction function = (ViewHolderFunction) viewHolder;
                String functionName = get(position - 1);
                function.mFunctionName.setText(functionName);

                function.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position - 1);
                        }
                    }
                });


                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_FUNCTION;
    }

    @Override
    public int getItemCount() {
        return size() + 1;
    }


    public static interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
