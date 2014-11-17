package org.geeklub.smartlib.module.login.presenter;

import org.geeklub.smartlib.module.login.model.LoginModel;
import org.geeklub.smartlib.module.login.model.LoginModelImpl;
import org.geeklub.smartlib.module.login.view.LoginView;

/**
 * Created by Vass on 2014/10/26.
 */
public class LoginPresenterImpl
    implements LoginPresenter, OnLoginFinishedListener, OnUserInputListener {

  private LoginModel mLoginModel;

  private LoginView mLoginView;

  public LoginPresenterImpl(LoginView loginView) {
    mLoginView = loginView;
    mLoginModel = new LoginModelImpl();
  }

  @Override
  public void validateCredentials(String username, String password) {
    mLoginView.setProgressBarHide(false);
    mLoginModel.login(username, password, this, this);
  }

  @Override
  public void registerAccount() {
    mLoginView.navigateToRegister();
  }

  @Override
  public void onSuccess(String msg) {
    mLoginView.setProgressBarHide(true);
    mLoginView.showMessage(msg);
    mLoginView.navigateToMain();
  }

  @Override
  public void onFail(String msg) {
    mLoginView.setProgressBarHide(true);
    mLoginView.showMessage(msg);
  }

  @Override
  public void onAccountError() {
    mLoginView.setProgressBarHide(true);
    mLoginView.setAccountError();
  }

  @Override
  public void onPasswordError() {
    mLoginView.setProgressBarHide(true);
    mLoginView.setPassWordError();
  }
}
