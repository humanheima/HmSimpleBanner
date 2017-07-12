package com.humanheima.hmsimplebanner.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.listener.OnBannerClickListener;
import com.humanheima.hmsimplebanner.transformer.developer.ZoomOutPageTransformer;
import com.humanheima.hmsimplebanner.util.Images;
import com.humanheima.hmsimplebanner.widget.SuperBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleActivity extends AppCompatActivity {

    @BindView(R.id.simple_single_banner)
    SuperBanner simpleSingleBanner;
    @BindView(R.id.simple_multi_banner)
    SuperBanner simpleMultiBanner;
    private List<String> singleTitles;
    private List<String> singleImgs;

    private List<String> multiTitles;
    private List<String> multiImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        initSingleBanner();
        initMultiBanner();
    }

    private void initSingleBanner() {
        singleTitles = new ArrayList<>();
        singleImgs = new ArrayList<>();
        singleTitles.add("好雨知时节");
        singleTitles.add("好雨知时节");
        singleTitles.add("好雨知时节");
        singleImgs.add("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");
        singleImgs.add("http://img.zcool.cn/community/01fca557a7f5f90000012e7e9feea8.jpg");
        singleImgs.add("http://img.zcool.cn/community/01996b57a7f6020000018c1bedef97.jpg");
        simpleSingleBanner.setmOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        simpleSingleBanner.setImages(singleImgs)
                .setBannerTitles(singleTitles)
                .setPageTransformer(new ZoomOutPageTransformer())
                .isNumberIndicator(true)
                .isAutoPlay(true)
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
        simpleMultiBanner.setmOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        simpleMultiBanner.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .isAutoPlay(true)
                .start();
    }
}
