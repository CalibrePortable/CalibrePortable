package org.geeklub.smartlib4admin.module.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.geeklub.smartlib4admin.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vass on 2014/12/1.
 */
public class SelectBookTypeDialogFragment extends DialogFragment {

  public static final String TAG = SelectBookTypeDialogFragment.class.getSimpleName();

  private static final String ARGS_TITLE = "args_title";

  private String mTitle;

  private Activity mActivity;

  private CharSequence mSelectedBookType;

  @InjectView(R.id.spinner_book_type) Spinner mBookTypeSpinner;

  public static interface OnDialogButtonClickListener {
    void onPositiveButtonClick(CharSequence bookType);
  }

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

    View view =
        LayoutInflater.from(mActivity).inflate(R.layout.dialog_fragment_selected_book_type, null);
    ButterKnife.inject(this, view);

    final ArrayAdapter<CharSequence> bookTypeAdapter =
        ArrayAdapter.createFromResource(mActivity, R.array.book_type,
            android.R.layout.simple_spinner_item);
    mBookTypeSpinner.setAdapter(bookTypeAdapter);
    mBookTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSelectedBookType = bookTypeAdapter.getItem(position);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    return new AlertDialog.Builder(getActivity()).setTitle(mTitle)
        .setView(view)
        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            ((OnDialogButtonClickListener) mActivity).onPositiveButtonClick(mSelectedBookType);
          }
        })
        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {

          }
        })
        .create();
  }
}
