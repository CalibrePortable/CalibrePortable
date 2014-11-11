package org.geeklub.smartlib.register.view;

/**
 * Created by Vass on 2014/10/26.
 */
public interface RegisterView {

  void showProgressbar();

  void hideProgressBar();

  void setUserIdError();

  void setUserNameError();

  void setPasswordError();

  void setRePasswordError();

  void showMessage(String msg);

  void moveToMain();


}
