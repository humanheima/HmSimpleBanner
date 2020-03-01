package com.hm.banner;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hm.banner.impl.ImageLoader;

import java.util.List;

/**
 * Created by dumingwei on 2020-02-25.
 * Desc:
 */
public class ViewPageAdapter extends PagerAdapter {

    private static final String TAG = "ViewPageAdapter";

    //轮播图片链接
    private List imgUrls;
    //图片数量
    private int count;
    private Context context;
    //用来加载图片
    private ImageLoader imageLoader;

    public ViewPageAdapter(Context context, ImageLoader imageLoader, List imgUrls) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.imgUrls = imgUrls;
        count = imgUrls.size();
    }

    @Override
    public int getCount() {
        //注释1处，返回Integer.MAX_VALUE
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem: position = " + position);
        //2. 取余获取正确的图片链接
        position %= count;
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //使用Glide加载图片
        imageLoader.displayImage(context, imgUrls.get(position), imageView);
        //这里的container就是ViewPager，调用ViewPager的addView方法，添加子View
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
