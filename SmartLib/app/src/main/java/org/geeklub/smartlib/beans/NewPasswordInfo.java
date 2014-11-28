package org.geeklub.smartlib.beans;

/**
 * Created by Vass on 2014/11/13.
 */
public class NewPasswordInfo {
  private String userId;
  private String oldPass;
  private String newPass;
  private String renewPass;

  public NewPasswordInfo(String userId, String oldPass, String newPass, String renewPass) {
    this.userId = userId;
    this.oldPass = oldPass;
    this.newPass = newPass;
    this.renewPass = renewPass;
  }
}
