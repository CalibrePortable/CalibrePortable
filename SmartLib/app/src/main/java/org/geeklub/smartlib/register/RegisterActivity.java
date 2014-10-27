package org.geeklub.smartlib.register;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.geeklub.smartlib.R;
import org.geeklub.smartlib.register.presenter.RegisterPresenter;
import org.geeklub.smartlib.register.presenter.RegisterPresenterImpl;
import org.geeklub.smartlib.register.view.RegisterView;
import org.geeklub.smartlib.utils.ToastUtil;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterActivity extends Activity
    implements RegisterView, View.OnClickListener, TextWatcher {

  @InjectView(R.id.et_account) EditText mEditTextAccount;

  @InjectView(R.id.et_password) EditText mEditTextPassword;

  @InjectView(R.id.et_rePassword) EditText mEditTextRePassword;

  @InjectView(R.id.progressBar) ProgressBar mProgressBar;

  @InjectView(R.id.btn_register) Button mBtnRegister;

  private RegisterPresenter mRegisterPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_register);

    ButterKnife.inject(this);

    mRegisterPresenter = new RegisterPresenterImpl(this);

    initCallBacks();
  }

  private void initCallBacks() {
    mBtnRegister.setOnClickListener(this);
    mEditTextRePassword.addTextChangedListener(this);
  }

  @Override public void showProgressbar() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void setAccountError() {
    mEditTextAccount.setError(getString(R.string.account_not_empty));
  }

  @Override public void setPasswordError() {
    mEditTextPassword.setError(getString(R.string.password_not_empty));
  }

  @Override public void setRepasswordError() {
    mEditTextRePassword.setError(getString(R.string.re_password_error));
  }

  @Override public void showMessage(String msg) {
    ToastUtil.showShort(msg);
  }

  @Override public void onClick(View v) {

    switch (v.getId()) {

      case R.id.btn_register:
        mRegisterPresenter.validateCredentials(mEditTextAccount.getText().toString(),
            mEditTextPassword.getText().toString(), mEditTextRePassword.getText().toString());
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
    mRegisterPresenter.validatePassWord(mEditTextPassword.getText().toString(),
        mEditTextRePassword.getText().toString());
  }
}
