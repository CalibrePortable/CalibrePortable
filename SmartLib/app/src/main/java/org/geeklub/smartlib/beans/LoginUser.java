package org.geeklub.smartlib.beans;

import android.support.v4.view.ViewPager;

/**
 * Created by Vass on 2014/11/13.
 */
public class LoginUser {
  public String userId;

  public String password;

  public LoginUser(String userId, String password) {
    this.userId = userId;
    this.password = password;

  }
}
