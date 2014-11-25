package org.geeklub.smartlib4admin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import org.geeklub.smartlib4admin.GlobalContext;

/**
 * Created by Vass on 2014/11/17.
 */
public class SmartLibraryUser {

  private final static String SMART_LIB_USER = "smart_lib_user";

  private final static String USER_ID = "user_id";

  private final static String USER_PASSWORD = "user_pass_word";

  private String userId;
  private String passWord;

  public SmartLibraryUser(String userId, String passWord) {
    this.userId = userId;
    this.passWord = passWord;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public static SmartLibraryUser getCurrentUser() {

    SharedPreferences preferences =
        GlobalContext.getInstance().getSharedPreferences(SMART_LIB_USER, Context.MODE_PRIVATE);

    String userId = preferences.getString(USER_ID, "");
    String password = preferences.getString(USER_PASSWORD, "");

    if (userId == null || "".equals(userId)) {
      return null;
    }
    return new SmartLibraryUser(userId, password);
  }

  public static void saveUser(String userId, String passWord) {
    SharedPreferences preferences =
        GlobalContext.getInstance().getSharedPreferences(SMART_LIB_USER, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(USER_ID, userId);
    editor.putString(USER_PASSWORD, passWord);
    editor.apply();
  }

  public static void clear() {
    SharedPreferences preferences =
        GlobalContext.getInstance().getSharedPreferences(SMART_LIB_USER, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.clear();
  }
}
