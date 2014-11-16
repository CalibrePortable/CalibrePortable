package org.geeklub.smartlib.module.common.login.model;

import org.geeklub.smartlib.module.common.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib.module.common.login.presenter.OnUserInputListener;

/**
 * Created by Vass on 2014/10/26.
 */
public interface LoginModel {
  void login(String username, String password, OnUserInputListener userInputListener,
      OnLoginFinishedListener loginFinishedListener);
}
