package org.geeklub.smartlib4admin.beans;

/**
 * Created by Vass on 2014/11/13.
 */
public class LoginInfo {
  public String userId;

  public String password;

  public LoginInfo(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }
}
