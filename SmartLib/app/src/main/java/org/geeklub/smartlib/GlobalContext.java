package org.geeklub.smartlib;

import android.app.Application;
import android.content.Context;
import com.avos.avoscloud.AVOSCloud;

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
    AVOSCloud.initialize(sContext, "skr3lpa65qd2maqwvl6b00k9ra1ecvc6usloso5in3kw8e9s",
        "2t8luwbujuvpfwv4xjswips2y4fqim56yeptjtm5lclzv6fb");
  }
}
