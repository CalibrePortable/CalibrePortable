package org.geeklub.smartlib4admin.module.login.model;


import org.geeklub.smartlib4admin.module.login.presenter.OnLoginFinishedListener;
import org.geeklub.smartlib4admin.module.login.presenter.OnUserInputListener;

/**
 * Created by Vass on 2014/10/26.
 */
public interface LoginModel {
  void login(String userId, String password, OnUserInputListener userInputListener,
             OnLoginFinishedListener loginFinishedListener);

}
