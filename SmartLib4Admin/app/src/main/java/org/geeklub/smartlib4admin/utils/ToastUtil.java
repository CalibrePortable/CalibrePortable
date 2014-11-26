package org.geeklub.smartlib4admin.utils;

import android.content.Context;
import android.widget.Toast;
import org.geeklub.smartlib4admin.GlobalContext;

/**
 * Created by Vass on 2014/10/7.
 */

public class ToastUtil {
  private ToastUtil() {
  }

  private static void show(Context context, int resId, int duration) {
    Toast.makeText(context, resId, duration).show();
  }

  private static void show(Context context, String message, int duration) {
    Toast.makeText(context, message, duration).show();
  }

  public static void showShort(int resId) {
    Toast.makeText(GlobalContext.getInstance(), resId, Toast.LENGTH_SHORT).show();
  }

  public static void showShort(String message) {
    Toast.makeText(GlobalContext.getInstance(), message, Toast.LENGTH_SHORT).show();
  }

  public static void showLong(int resId) {
    Toast.makeText(GlobalContext.getInstance(), resId, Toast.LENGTH_LONG).show();
  }

  public static void showLong(String message) {
    Toast.makeText(GlobalContext.getInstance(), message, Toast.LENGTH_LONG).show();
  }
}

