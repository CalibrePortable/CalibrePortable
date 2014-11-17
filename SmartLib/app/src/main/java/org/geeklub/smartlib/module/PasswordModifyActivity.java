package org.geeklub.smartlib.module;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import butterknife.InjectView;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.beans.ServerResponse;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.api.NormalUserService;
import org.geeklub.smartlib.utils.LogUtil;
import org.geeklub.smartlib.utils.SharedPreferencesUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vass on 2014/11/13.
 */
public class PasswordModifyActivity extends BaseActivity {

  @InjectView(R.id.et_new_password) EditText mNewPassword;
  @InjectView(R.id.et_new_re_password) EditText mNewRePassword;
  @InjectView(R.id.btn_commit) Button mConfirm;

  private RestAdapter mRestAdapter;

  private NormalUserService mService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRestAdapter =
        new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build();
    mService = mRestAdapter.create(NormalUserService.class);

    initViews();
  }

  private void initViews() {
    setTitle("Modify Your Password");
    //mConfirm.setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View v) {
    //    sendNewPasswordToServer(mNewPassword.getText().toString(),
    //        mNewRePassword.getText().toString());
    //  }
    //});
  }

  @Override protected void initActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setHomeButtonEnabled(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        LogUtil.i("back to setting activity");
        NavUtils.navigateUpFromSameTask(this);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_modify_password;
  }

  private void sendNewPasswordToServer(String newPassword, String reNewPassword) {
    if (TextUtils.isEmpty(newPassword)) {
      mNewPassword.setError("empty new password...");
    }

    if (TextUtils.isEmpty(reNewPassword)) {
      mNewPassword.setError("empty new repassword...");
    }

    SharedPreferencesUtil preferencesUtil = new SharedPreferencesUtil(this);

    if (newPassword.equals(reNewPassword)) {
      mService.modifyPassword(preferencesUtil.getUser().getUserName(),
          preferencesUtil.getUser().getPassword(), newPassword, reNewPassword,
          new Callback<ServerResponse>() {
            @Override public void success(ServerResponse serverResponse, Response response) {

              LogUtil.i(serverResponse.getInfo());
            }

            @Override public void failure(RetrofitError error) {
              LogUtil.i(error.getMessage());
            }
          });
    }
  }
}
