package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hm.banner.inter.OnBannerClickListener;
import com.hm.banner.transformer.TransitionEffect;
import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.databinding.ActivitySimpleBinding;
import com.humanheima.hmsimplebanner.util.GlideImageLoader;
import com.humanheima.hmsimplebanner.util.Images;

import java.util.ArrayList;
import java.util.List;

public class SimpleActivity extends AppCompatActivity {

    private ActivitySimpleBinding binding;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SimpleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple);
        initSingleBanner();
        initMultiBanner();
    }

    /**
     * 一张轮播图
     */
    private void initSingleBanner() {
        List<String> singleImgs = new ArrayList<>();
        singleImgs.add("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");

        binding.simpleSingleBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        binding.simpleSingleBanner.setImages(singleImgs)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Flip)
                .isAutoPlay(false)
                .start();

    }

    /**
     * 多张轮播图
     */
    private void initMultiBanner() {
        List<String> multiTitles = new ArrayList<>();
        List<String> multiImgs = new ArrayList<>();
        multiTitles.add("当春乃发生");
        multiTitles.add("随风潜入夜");
        multiTitles.add("润物细无声");
        multiImgs.add(Images.imageUrls[0]);
        multiImgs.add(Images.imageUrls[1]);
        multiImgs.add(Images.imageUrls[2]);
        binding.simpleMultiBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        binding.simpleMultiBanner.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setTitles(multiTitles)
                .isAutoPlay(true)
                .start();
    }
}
