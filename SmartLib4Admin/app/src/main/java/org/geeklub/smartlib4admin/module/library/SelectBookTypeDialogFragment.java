package org.geeklub.smartlib4admin.module.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import org.geeklub.smartlib4admin.R;


/**
 * Created by Vass on 2014/12/1.
 */
public class SelectBookTypeDialogFragment extends DialogFragment {

    private static final String ARGS_TITLE = "args_title";

    private String mTitle;

    private Activity mActivity;


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

    public static Fragment newInstance(String title) {
        Fragment fragment = new SelectBookTypeDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }


    private void parseArguments() {
        Bundle args = getArguments();
        mTitle = args.getString(ARGS_TITLE);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        parseArguments();

        return new AlertDialog.Builder(getActivity())
                .setTitle(mTitle)
                .setView(LayoutInflater.from(mActivity).inflate(R.layout.dialog_fragment_selected_book_type, null))
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }
}
