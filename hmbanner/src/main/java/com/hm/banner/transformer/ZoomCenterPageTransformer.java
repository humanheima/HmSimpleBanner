package com.hm.banner.transformer;

import android.view.View;

import static androidx.core.view.ViewCompat.setAlpha;
import static androidx.core.view.ViewCompat.setPivotX;
import static androidx.core.view.ViewCompat.setPivotY;
import static androidx.core.view.ViewCompat.setScaleX;
import static androidx.core.view.ViewCompat.setScaleY;
import static androidx.core.view.ViewCompat.setTranslationX;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/6/19 上午8:41
 * 描述:
 */
public class ZoomCenterPageTransformer extends BGAPageTransformer {

    @Override
    public void handleInvisiblePage(View view, float position) {
    }

    @Override
    public void handleLeftPage(View view, float position) {
        setTranslationX(view, -view.getWidth() * position);

        setPivotX(view, view.getWidth() * 0.5f);
        setPivotY(view, view.getHeight() * 0.5f);
        setScaleX(view, 1 + position);
        setScaleY(view, 1 + position);

        if (position < -0.95f) {
            setAlpha(view, 0);
        } else {
            setAlpha(view, 1);
        }
    }

    @Override
    public void handleRightPage(View view, float position) {
        setTranslationX(view, -view.getWidth() * position);

        setPivotX(view, view.getWidth() * 0.5f);
        setPivotY(view, view.getHeight() * 0.5f);
        setScaleX(view, 1 - position);
        setScaleY(view, 1 - position);

        if (position > 0.95f) {
            setAlpha(view, 0);
        } else {
            setAlpha(view, 1);
        }
    }

}