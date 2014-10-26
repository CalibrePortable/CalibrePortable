package org.geeklub.smartlib.login.model;

import org.geeklub.smartlib.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.login.presenter.OnUserInputListener;

/**
 * Created by Vass on 2014/10/26.
 */
public interface LoginModel {
  void login(String username, String password, OnUserInputListener userInputListener,
      OnLoginFinishedListener loginFinishedListener);
}
