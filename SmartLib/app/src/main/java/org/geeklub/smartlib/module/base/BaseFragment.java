package org.geeklub.smartlib.module.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

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


  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResource(), null);
    ButterKnife.inject(this, view);
    return view;
  }

  protected abstract int getLayoutResource();
}
