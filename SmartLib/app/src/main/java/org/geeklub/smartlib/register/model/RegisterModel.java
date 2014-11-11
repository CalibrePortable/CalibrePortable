package org.geeklub.smartlib.register.model;

import org.geeklub.smartlib.register.presenter.OnPassWordMatchListener;
import org.geeklub.smartlib.register.presenter.OnRegisterFinishedListener;
import org.geeklub.smartlib.register.presenter.OnUserInputListener;

/**
 * Created by Vass on 2014/10/26.
 */
public interface RegisterModel {
  /**
   * 验证注册的逻辑
   */
  void register(String account,String userName, String passWord, String passWordConfirm,
      OnUserInputListener userInputListener, OnPassWordMatchListener matchListener,
      OnRegisterFinishedListener finishedListener);

  /**
   * 验证用户两次输入的密码是否一致
   */
  void verifyPassWord(String firstPassWord, String secondPassWord,
      OnPassWordMatchListener listener);
}
