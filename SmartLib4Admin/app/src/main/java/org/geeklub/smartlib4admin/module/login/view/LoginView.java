package org.geeklub.smartlib4admin.module.login.view;

/**
 * Created by Vass on 2014/10/26.
 */
public interface LoginView {

  void setProgressBarHide(boolean ifHide);

  void showMessage(String msg);

  void setAccountError();

  void setPassWordError();

  void navigateToMain();

}
