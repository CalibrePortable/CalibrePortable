package org.geeklub.smartlib;

import android.app.Application;
import android.content.Context;
import android.util.LruCache;

import com.avos.avoscloud.AVOSCloud;
import com.squareup.otto.Bus;

import org.geeklub.smartlib.api.RestApiDispencer;
import org.geeklub.smartlib.utils.BitmapLruCache;

import retrofit.RestAdapter;

/**
 * Created by Vass on 2014/10/7.
 */
public class GlobalContext extends Application {

    // 取运行内存阈值的1/8作为图片缓存
    private static final int MEMORY_CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);

    private static BitmapLruCache sBitmapLruCache;

    private static Bus sBus;

    private static Context sContext;

    private static RestApiDispencer sApiDispencer;

    public static Context getInstance() {
        return sContext;
    }

    public static Bus getBusInstance() {
        return sBus;
    }

    public static RestApiDispencer getApiDispencer() {
        return sApiDispencer;
    }

    public static BitmapLruCache getBitmapLruCacheInstance() {
        return sBitmapLruCache;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sBitmapLruCache = new BitmapLruCache(MEMORY_CACHE_SIZE);
        sBus = new Bus();
        sContext = getApplicationContext();
        sApiDispencer = new RestApiDispencer(
                new RestAdapter.Builder().setEndpoint("http://www.flappyant.com/book/API.php").build());

        AVOSCloud.initialize(sContext, "skr3lpa65qd2maqwvl6b00k9ra1ecvc6usloso5in3kw8e9s",
                "2t8luwbujuvpfwv4xjswips2y4fqim56yeptjtm5lclzv6fb");
    }
}
