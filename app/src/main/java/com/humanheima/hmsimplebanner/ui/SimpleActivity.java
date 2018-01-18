package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.brotherd.bannerlibrary.SimpleBanner;
import com.brotherd.bannerlibrary.inter.OnBannerClickListener;
import com.brotherd.bannerlibrary.transformer.TransitionEffect;
import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.util.GlideImageLoader;
import com.humanheima.hmsimplebanner.util.Images;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleActivity extends AppCompatActivity {

    @BindView(R.id.simple_single_banner)
    SimpleBanner simpleSingleBanner;
    @BindView(R.id.simple_multi_banner)
    SimpleBanner simpleMultiBanner;
    private List<String> singleImgs;

    private List<String> multiTitles;
    private List<String> multiImgs;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SimpleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        initSingleBanner();
        initMultiBanner();
    }

    private void initSingleBanner() {
        singleImgs = new ArrayList<>();
        singleImgs.add("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");

        simpleSingleBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        simpleSingleBanner.setImages(singleImgs)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Flip)
                .isAutoPlay(false)
                .start();

    }

    private void initMultiBanner() {
        multiTitles = new ArrayList<>();
        multiImgs = new ArrayList<>();
        multiTitles.add("当春乃发生");
        multiTitles.add("随风潜入夜");
        multiTitles.add("润物细无声");
        multiImgs.add(Images.imageThumbUrls[0]);
        multiImgs.add(Images.imageThumbUrls[1]);
        multiImgs.add(Images.imageThumbUrls[2]);
        simpleMultiBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        simpleMultiBanner.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setTitles(multiTitles)
                .isAutoPlay(true)
                .start();
    }
}
