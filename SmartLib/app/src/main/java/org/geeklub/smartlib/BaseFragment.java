package org.geeklub.smartlib;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by Vass on 2014/10/30.
 */
public abstract class BaseFragment extends Fragment {

  protected Activity mActivity;

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);

    mActivity = activity;
  }

  @Override public void onDetach() {
    super.onDetach();

    mActivity = null;
  }





}
