package org.geeklub.smartlib.register.view;

/**
 * Created by Vass on 2014/10/26.
 */
public interface RegisterView {

  void showProgressbar();

  void hideProgressBar();

  void setAccountError();

  void setPasswordError();

  void setRepasswordError();

  void showMessage(String msg);


}
