package org.geeklub.smartlib.module.search;

import android.support.v4.view.ViewPager;
import android.view.View;

@Deprecated
public class StackTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
    }
}
