package org.geeklub.smartlib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import org.geeklub.smartlib.beans.SLUser;

/**
 * Created by Vass on 2014/11/10.
 */
public class SharedPreferencesUtil {

  private final static String SMART_LIB_USER = "smart_lib_user";

  private SharedPreferences mSharedPreferences;

  //private SLUser mUser;

  public SharedPreferencesUtil(Context context) {
    mSharedPreferences = context.getSharedPreferences(SMART_LIB_USER, Context.MODE_PRIVATE);
  }

  public void saveUser(SLUser user) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putInt(SLUser.USER_NAME, user.getUserName());
    editor.putString(SLUser.PASS_WORD, user.getPassword());
    editor.apply();
  }

  public SLUser getUser() {
    String password = mSharedPreferences.getString(SLUser.PASS_WORD, "");
    int userName = mSharedPreferences.getInt(SLUser.USER_NAME, -1);
    //mUser = new SLUser(userName,password);
    return new SLUser(userName, password);
  }
}
