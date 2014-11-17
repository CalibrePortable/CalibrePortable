package org.geeklub.smartlib.module.login.model;

import org.geeklub.smartlib.module.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.module.login.presenter.OnUserInputListener;

/**
 * Created by Vass on 2014/10/26.
 */
public interface LoginModel {
  void login(String userId, String password, OnUserInputListener userInputListener,
      OnLoginFinishedListener loginFinishedListener);

  boolean ifLoginBefore();
}
