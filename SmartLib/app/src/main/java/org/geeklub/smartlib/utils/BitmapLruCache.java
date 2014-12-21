package org.geeklub.smartlib.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Vass on 2014/12/21.
 */
public class BitmapLruCache extends LruCache<Integer, Bitmap> {


    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }


    @Override
    protected int sizeOf(Integer key, Bitmap value) {
        return value.getByteCount() / 1024;
    }

    public void addBitmapToMemoryCache(int key, Bitmap bitmap) {

        if (getBitmapFromMemoryCache(key) == null) {
            put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(int key) {
        return get(key);
    }


}
