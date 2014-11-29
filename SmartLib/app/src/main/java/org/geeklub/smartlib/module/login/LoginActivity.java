package org.geeklub.smartlib.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.module.MainActivity;
import org.geeklub.smartlib.module.base.BaseActivity;
import org.geeklub.smartlib.module.login.presenter.LoginPresenter;
import org.geeklub.smartlib.module.login.presenter.LoginPresenterImpl;
import org.geeklub.smartlib.module.login.view.LoginView;
import org.geeklub.smartlib.module.register.RegisterActivity;
import org.geeklub.smartlib.utils.ToastUtil;

import butterknife.InjectView;

/**
 * Created by Vass on 2014/10/26.
 */
public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

  private LoginPresenter mLoginPresenter;

  @InjectView(R.id.et_user_id) EditText mEditTextUserId;

  @InjectView(R.id.et_password) EditText mEditTextPassWord;

  @InjectView(R.id.progressBar) ProgressBar mProgressBar;

  @InjectView(R.id.btn_login) Button mBtnLogin;

  @InjectView(R.id.btn_register) Button mBtnRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mLoginPresenter = new LoginPresenterImpl(this);
    mLoginPresenter.haveLoginBefore();
    initCallBacks();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_login;
  }

  private void initCallBacks() {
    mBtnLogin.setOnClickListener(this);
    mBtnRegister.setOnClickListener(this);
  }

  @Override
  public void setProgressBarHide(boolean ifHide) {
    if (ifHide) {
      mProgressBar.setVisibility(View.GONE);
    } else {
      mProgressBar.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void showMessage(String msg) {
    ToastUtil.showShort(msg);
  }

  @Override
  public void setAccountError() {
    mEditTextUserId.setError(getString(R.string.user_id_not_empty));
  }

  @Override
  public void setPassWordError() {
    mEditTextPassWord.setError(getString(R.string.password_not_empty));
  }

  @Override
  public void navigateToMain() {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }

  @Override
  public void navigateToRegister() {
    startActivity(new Intent(this, RegisterActivity.class));
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        mLoginPresenter.validateCredentials(mEditTextUserId.getText().toString(),
            mEditTextPassWord.getText().toString());
        break;
      case R.id.btn_register:
        mLoginPresenter.registerAccount();
        break;
      default:
        break;
    }
  }
}
