package org.geeklub.smartlib.module.search;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Vass on 2014/12/16.
 */
public class StackTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
    }
}
