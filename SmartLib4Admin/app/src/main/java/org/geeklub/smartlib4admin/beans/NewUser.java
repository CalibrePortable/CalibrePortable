package org.geeklub.smartlib4admin.beans;

/**
 * Created by Vass on 2014/11/13.
 */
public class NewUser {
  public String userId;
  public String userName;
  public String password;
  public String rePassword;

  public NewUser(String userId, String userName, String password, String rePassword) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.rePassword = rePassword;
  }
}
