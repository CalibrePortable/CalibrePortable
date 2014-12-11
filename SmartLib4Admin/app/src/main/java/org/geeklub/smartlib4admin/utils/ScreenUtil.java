package org.geeklub.smartlib4admin.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Vass on 2014/12/11.
 */
public class ScreenUtil {

    public static int getScreenHeight(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }


    public static int getScreenWidth(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
