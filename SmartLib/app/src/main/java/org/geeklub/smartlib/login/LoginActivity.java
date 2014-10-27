package org.geeklub.smartlib.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.geeklub.smartlib.R;
import org.geeklub.smartlib.login.presenter.LoginPresenter;
import org.geeklub.smartlib.login.presenter.LoginPresenterImpl;
import org.geeklub.smartlib.login.view.LoginView;
import org.geeklub.smartlib.register.RegisterActivity;
import org.geeklub.smartlib.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Vass on 2014/10/26.
 */
public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

  private LoginPresenter mLoginPresenter;

  @InjectView(R.id.et_account) EditText mEditTextAccount;

  @InjectView(R.id.et_password) EditText mEditTextPassWord;

  @InjectView(R.id.progressBar) ProgressBar mProgressBar;

  @InjectView(R.id.btn_login) Button mBtnLogin;

  @InjectView(R.id.btn_register) Button mBtnRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);

    ButterKnife.inject(this);

    mLoginPresenter = new LoginPresenterImpl(this);

    initCallBacks();
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
    mEditTextAccount.setError(getString(R.string.account_not_empty));
  }

  @Override
  public void setPassWordError() {
    mEditTextPassWord.setError(getString(R.string.password_not_empty));
  }

  @Override
  public void navigateToMain() {

  }

  @Override
  public void navigateToRegister() {
    startActivity(new Intent(this, RegisterActivity.class));

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_login:
        mLoginPresenter.validateCredentials(mEditTextAccount.getText().toString(),
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
