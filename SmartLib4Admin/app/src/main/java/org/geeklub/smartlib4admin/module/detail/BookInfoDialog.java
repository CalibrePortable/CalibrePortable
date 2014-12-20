package org.geeklub.smartlib4admin.module.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.geeklub.smartlib4admin.R;
import org.geeklub.smartlib4admin.beans.BookDetailInfo;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vass on 2014/12/20.
 */
public class BookInfoDialog extends DialogFragment {

    public static final String TAG = BookInfoDialog.class.getSimpleName();

    private static final String ARGS_TITLE = "args_title";

    private static final String ARGS_CONTENT = "args_content";

    private String mTitle;

    private String mContent;

    private Activity mActivity;

    @InjectView(R.id.tv_book_info)
    TextView mBookInfo;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


    public static Fragment newInstance(String title, String content) {
        Fragment fragment = new BookInfoDialog();
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        args.putString(ARGS_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }


    public static Fragment newInstance() {
        Fragment fragment = new BookInfoDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setDialogContent(BookDetailInfo detailInfo){
        Bundle args = getArguments();
        args.putString(BookInfoDialog.ARGS_TITLE, detailInfo.book_name);
        args.putString(BookInfoDialog.ARGS_CONTENT, detailInfo.book_info);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        parseArguments();

        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_book_info, null);
        ButterKnife.inject(this, view);

        mBookInfo.setText(mContent);

        AlertDialog dialog = new AlertDialog.Builder(mActivity).setTitle(mTitle).setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        dialog.setCanceledOnTouchOutside(true);

        return dialog;

    }

    private void parseArguments() {
        Bundle args = getArguments();
        mTitle = args.getString(ARGS_TITLE);
        mContent = args.getString(ARGS_CONTENT);
    }


}
