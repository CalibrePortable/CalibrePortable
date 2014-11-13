package org.geeklub.smartlib;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.services.NormalUserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/13.
 */
public class ModifyPasswordFragment extends BaseFragment {

  public static final String MODIFY_PASSWORD_FRAGMENT = "modify_password_fragment";

  private RestAdapter mRestAdapter;

  private NormalUserService mService;

  @InjectView(R.id.et_new_password) EditText mNewPassword;
  @InjectView(R.id.et_new_re_password) EditText mNewRePassword;
  @InjectView(R.id.btn_ok) Button mConfirm;

  public static Fragment newInstance() {
    Fragment modifyPasswordFragment = new ModifyPasswordFragment();
    Bundle args = new Bundle();
    modifyPasswordFragment.setArguments(args);
    return modifyPasswordFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRestAdapter =
        new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_modify_password, null);
    ButterKnife.inject(this, view);

    mConfirm.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        sendNewPasswordToServer(mNewPassword.getText().toString(),
            mNewRePassword.getText().toString());
      }
    });

    return view;
  }

  private void sendNewPasswordToServer(String newPassword, String reNewPassword) {
    if (TextUtils.isEmpty(newPassword)) {
      mNewPassword.setError("empty new password...");
    }

    if (TextUtils.isEmpty(reNewPassword)) {
      mNewPassword.setError("empty new repassword...");
    }

    if (!newPassword.equals(reNewPassword)) {
      mService.modifyPassword("", "", newPassword, reNewPassword, new Callback<ServerResponse>() {
        @Override public void success(ServerResponse serverResponse, Response response) {

        }

        @Override public void failure(RetrofitError error) {

        }
      });
    }
  }
}
