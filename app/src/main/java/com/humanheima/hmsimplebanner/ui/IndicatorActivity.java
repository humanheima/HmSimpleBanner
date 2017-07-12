package com.humanheima.hmsimplebanner.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.listener.OnBannerClickListener;
import com.humanheima.hmsimplebanner.transformer.TransitionEffect;
import com.humanheima.hmsimplebanner.util.Images;
import com.humanheima.hmsimplebanner.widget.SuperCopyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 使用SuperCopyBanner
 */
public class IndicatorActivity extends AppCompatActivity {

    @BindView(R.id.banner_num_indicator)
    SuperCopyBanner bannerNumIndicator;
    @BindView(R.id.banner_circle_indicator)
    SuperCopyBanner bannerCircleIndicator;
    @BindView(R.id.banner_single)
    SuperCopyBanner bannerSingle;
    @BindView(R.id.banner_num)
    SuperCopyBanner bannerNum;
    private List<String> singleTitles;
    private List<String> singleImgs;
    private List<String> multiTitles;
    private List<String> multiImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        ButterKnife.bind(this);
        initSingleBanner();
        initNumBanner();
        initCircleBanner();
    }

    private void initSingleBanner() {
        List<String> imglist = new ArrayList<>();
        List<String> titlelist = new ArrayList<>();
        imglist.add(Images.imageThumbUrls[19]);
        titlelist.add("只有一个");
        bannerSingle.setmOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(IndicatorActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        bannerSingle.setImages(imglist)
                .setBannerTitles(titlelist)
                .isAutoPlay(false)
                .start();
    }

    private void initNumBanner() {
        singleTitles = new ArrayList<>();
        singleImgs = new ArrayList<>();
        singleTitles.add("好雨知时节");
        singleTitles.add("当春乃发生");
        singleTitles.add("随风潜入夜");
        singleTitles.add("润物细无声");
        singleImgs.add(Images.imageThumbUrls[4]);
        singleImgs.add(Images.imageThumbUrls[5]);
        singleImgs.add(Images.imageThumbUrls[6]);
        singleImgs.add(Images.imageThumbUrls[7]);
        bannerNumIndicator.setmOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(IndicatorActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        bannerNumIndicator.setImages(singleImgs)
                .setBannerTitles(singleTitles)
                .isAutoPlay(true)
                .start();

        bannerNum.setImages(singleImgs)
                .setBannerTitles(singleTitles)
                .isAutoPlay(true)
                .start();
    }

    private void initCircleBanner() {
        multiTitles = new ArrayList<>();
        multiImgs = new ArrayList<>();
        multiTitles.add("当春乃发生");
        multiTitles.add("随风潜入夜");
        multiTitles.add("润物细无声");
        multiImgs.add("http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg");
        multiImgs.add("http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg");
        multiImgs.add("http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg");
        bannerCircleIndicator.setmOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(IndicatorActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        bannerCircleIndicator.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Cube)
                .isAutoPlay(true)
                .start();
    }
}
