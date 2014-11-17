package org.geeklub.smartlib.module.login.presenter;

/**
 * Created by Vass on 2014/10/26.
 */
public interface LoginPresenter {
  void validateCredentials(String username, String password);

  void registerAccount();

  void haveLoginBefore();
}
