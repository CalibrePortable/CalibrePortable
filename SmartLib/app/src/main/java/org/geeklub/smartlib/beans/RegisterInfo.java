package org.geeklub.smartlib.beans;

/**
 * Created by Vass on 2014/11/28.
 */
public class RegisterInfo {
  public String userId;
  public String userName;
  public String password;
  public String rePassword;

  public RegisterInfo(String userId, String userName, String password, String rePassword) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.rePassword = rePassword;
  }
}
