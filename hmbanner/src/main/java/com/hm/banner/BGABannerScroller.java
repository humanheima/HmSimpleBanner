package com.hm.banner;

import android.content.Context;
import android.widget.Scroller;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/6/19 下午11:59
 * 描述:
 */
class BGABannerScroller extends Scroller {

    //控制ViewPager的切换时间
    private int mDuration = 1000;

    public BGABannerScroller(Context context, int duration) {
        super(context);
        mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}