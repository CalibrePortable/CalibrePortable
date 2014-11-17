package org.geeklub.smartlib.module.register.presenter;

import org.geeklub.smartlib.module.register.model.RegisterModel;
import org.geeklub.smartlib.module.register.model.RegisterModelImpl;
import org.geeklub.smartlib.module.register.view.RegisterView;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterPresenterImpl
    implements RegisterPresenter, OnUserInputListener, OnPassWordMatchListener,
    OnRegisterFinishedListener {

  private RegisterModel mRegisterModel;

  private RegisterView mRegisterView;

  public RegisterPresenterImpl(RegisterView registerView) {
    mRegisterView = registerView;
    mRegisterModel = new RegisterModelImpl();
  }

  @Override public void userIdError() {
    mRegisterView.hideProgressBar();
    mRegisterView.setUserIdError();
  }

  @Override public void userNameError() {
    mRegisterView.hideProgressBar();
    mRegisterView.setUserNameError();
  }

  @Override public void passwordError() {
    mRegisterView.hideProgressBar();
    mRegisterView.setPasswordError();
  }

  @Override public void onPassWordNotMatchError() {
    mRegisterView.hideProgressBar();
    mRegisterView.setRePasswordError();
  }

  @Override public void validateCredentials(String account, String userName, String passWord,
      String rePassword) {
    mRegisterView.showProgressbar();
    mRegisterModel.register(account, userName, passWord, rePassword, this, this, this);
  }

  @Override public void validatePassWord(String firstPassWord, String secondPassWord) {
    mRegisterModel.verifyPassWord(firstPassWord, secondPassWord, this);
  }

  @Override public void onFail(String msg) {
    mRegisterView.hideProgressBar();
    mRegisterView.showMessage(msg);
  }

  @Override public void onSuccess(String msg) {
    mRegisterView.hideProgressBar();
    mRegisterView.showMessage(msg);
    mRegisterView.moveToMain();
  }
}
