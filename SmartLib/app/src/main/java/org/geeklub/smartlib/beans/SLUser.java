package org.geeklub.smartlib.beans;

/**
 * Created by Vass on 2014/11/10.
 */
public class SLUser {

  public final static String SMART_LIB_USER_NAME = "user_name";

  public final static String SMART_LIB_PASS_WORD = "pass_word";

  private int userName;

  private String password;

  public SLUser(int userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public int getUserName() {
    return userName;
  }

  public void setUserName(int userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override public String toString() {
    return "SLUser{" +
        "userName=" + userName +
        ", password='" + password + '\'' +
        '}';
  }
}
