package org.geeklub.smartlib;

import android.app.Application;
import android.content.Context;
import com.avos.avoscloud.AVOSCloud;
import org.geeklub.smartlib.api.RestApiDispencer;
import retrofit.RestAdapter;

/**
 * Created by Vass on 2014/10/7.
 */
public class GlobalContext extends Application {

  private static Context sContext;

  private static RestApiDispencer sApiDispencer;

  public static Context getInstance() {
    return sContext;
  }

  public static RestApiDispencer getApiDispencer() {
    return sApiDispencer;
  }

  @Override public void onCreate() {
    super.onCreate();

    sContext = getApplicationContext();
    sApiDispencer = new RestApiDispencer(
        new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build());

    AVOSCloud.initialize(sContext, "skr3lpa65qd2maqwvl6b00k9ra1ecvc6usloso5in3kw8e9s",
        "2t8luwbujuvpfwv4xjswips2y4fqim56yeptjtm5lclzv6fb");
  }
}
