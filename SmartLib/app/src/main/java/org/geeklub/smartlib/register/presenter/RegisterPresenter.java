package org.geeklub.smartlib.register.presenter;

/**
 * Created by Vass on 2014/10/26.
 */
public interface RegisterPresenter {

  /**
   * 注册验证逻辑
   */
  void validateCredentials(String account,String userName, String passWord, String rePassword);

  /**
   * 验证两次输入的密码是否一致
   * @param firstPassWord
   * @param secondPassWord
   */

  void validatePassWord(String firstPassWord, String secondPassWord);
}
