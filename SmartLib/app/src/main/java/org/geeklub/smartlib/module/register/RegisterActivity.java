package org.geeklub.smartlib.module.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.InjectView;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.module.register.presenter.RegisterPresenter;
import org.geeklub.smartlib.module.register.presenter.RegisterPresenterImpl;
import org.geeklub.smartlib.module.register.view.RegisterView;
import org.geeklub.smartlib.utils.ToastUtil;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterActivity extends BaseActivity
    implements RegisterView, View.OnClickListener, TextWatcher {

  @InjectView(R.id.et_user_id) EditText mUserId;

  @InjectView(R.id.et_user_name) EditText mUserName;

  @InjectView(R.id.et_password) EditText mPassword;

  @InjectView(R.id.et_re_password) EditText mRePassword;

  @InjectView(R.id.progressBar) ProgressBar mProgressBar;

  @InjectView(R.id.btn_register) Button mBtnRegister;

  private RegisterPresenter mRegisterPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRegisterPresenter = new RegisterPresenterImpl(this);

    initCallBacks();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_register;
  }

  private void initCallBacks() {
    mBtnRegister.setOnClickListener(this);
    mRePassword.addTextChangedListener(this);
  }

  @Override public void showProgressbar() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void setUserIdError() {
    mUserId.setError(getString(R.string.user_id_not_empty));
  }

  @Override public void setUserNameError() {
    mUserName.setError(getString(R.string.user_name_not_empty));
  }

  @Override public void setPasswordError() {
    mPassword.setError(getString(R.string.password_not_empty));
  }

  @Override public void setRePasswordError() {
    mRePassword.setError(getString(R.string.re_password_error));
  }

  @Override public void showMessage(String msg) {
    ToastUtil.showShort(msg);
  }

  @Override public void moveToMain() {
    finish();
  }

  @Override public void onClick(View v) {

    switch (v.getId()) {

      case R.id.btn_register:
        mRegisterPresenter.validateCredentials(mUserId.getText().toString(),
            mUserName.getText().toString(), mPassword.getText().toString(),
            mRePassword.getText().toString());
        break;

      default:
        break;
    }
  }

  @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override public void afterTextChanged(Editable s) {
    mRegisterPresenter.validatePassWord(mPassword.getText().toString(),
        mRePassword.getText().toString());
  }
}
