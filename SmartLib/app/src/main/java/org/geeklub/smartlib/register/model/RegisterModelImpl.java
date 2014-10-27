package org.geeklub.smartlib.register.model;

import android.text.TextUtils;
import org.geeklub.smartlib.register.presenter.OnPassWordMatchListener;
import org.geeklub.smartlib.register.presenter.OnRegisterFinishedListener;
import org.geeklub.smartlib.register.presenter.OnUserInputListener;

/**
 * Created by Vass on 2014/10/26.
 */
public class RegisterModelImpl implements RegisterModel {

  @Override public void register(String userName, String passWord, String passWordConfirm,
      OnUserInputListener userInputListener, OnPassWordMatchListener matchListener,
      OnRegisterFinishedListener finishedListener) {

    if (TextUtils.isEmpty(userName)) {
      userInputListener.accountError();
      return;
    }

    if (TextUtils.isEmpty(passWord)) {
      userInputListener.passwordError();
      return;
    }

    if (!isPasswordSame(passWord, passWordConfirm)) {
      matchListener.onPassWordNotMatchError();
      return;
    }
  }

  @Override public void verifyPassWord(String firstPassWord, String secondPassWord,
      OnPassWordMatchListener listener) {

    if (!isPasswordSame(firstPassWord, secondPassWord)) {
      listener.onPassWordNotMatchError();
      return;
    }
  }

  private boolean isPasswordSame(String firstPassWord, String secondPassWord) {
    if (firstPassWord.equals(secondPassWord)) {
      return true;
    }
    return false;
  }
}
