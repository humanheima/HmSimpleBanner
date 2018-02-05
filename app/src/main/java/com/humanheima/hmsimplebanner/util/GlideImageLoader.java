package com.humanheima.hmsimplebanner.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.hm.banner.impl.ImageLoader;
import com.bumptech.glide.Glide;
import com.humanheima.hmsimplebanner.R;

/**
 * Created by dumingwei on 2017/7/13.
 */
public class GlideImageLoader extends ImageLoader {

    private RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.img_bg)
            .error(R.drawable.img_bg);

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .apply(options)
                .into(imageView);
    }
}
