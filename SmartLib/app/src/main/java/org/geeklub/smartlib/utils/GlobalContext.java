package org.geeklub.smartlib.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vass on 2014/10/7.
 */
public class GlobalContext extends Application {

  private static Context sContext;

  public static Context getInstance() {
    return sContext;
  }

  @Override public void onCreate() {
    super.onCreate();

    sContext = getApplicationContext();
  }
}
