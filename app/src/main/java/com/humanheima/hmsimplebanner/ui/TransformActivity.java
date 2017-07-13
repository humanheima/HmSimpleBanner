package com.humanheima.hmsimplebanner.ui;

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

public class TransformActivity extends AppCompatActivity {

    @BindView(R.id.sb_transform_default)
    SimpleBanner sbTransformDefault;
    @BindView(R.id.sb_transform_alpha)
    SimpleBanner sbTransformAlpha;
    @BindView(R.id.sb_transform_rotate)
    SimpleBanner sbTransformRotate;
    @BindView(R.id.sb_transform_cube)
    SimpleBanner sbTransformCube;
    @BindView(R.id.sb_transform_flip)
    SimpleBanner sbTransformFlip;
    @BindView(R.id.sb_transform_accordion)
    SimpleBanner sbTransformAccordion;
    @BindView(R.id.sb_transform_ZoomFade)
    SimpleBanner sbTransformZoomFade;
    @BindView(R.id.sb_transform_Fade)
    SimpleBanner sbTransformFade;
    @BindView(R.id.sb_transform_ZoomCenter)
    SimpleBanner sbTransformZoomCenter;
    @BindView(R.id.sb_transform_ZoomStack)
    SimpleBanner sbTransformZoomStack;
    @BindView(R.id.sb_transform_Stack)
    SimpleBanner sbTransformStack;
    @BindView(R.id.sb_transform_Depth)
    SimpleBanner sbTransformDepth;
    @BindView(R.id.sb_transform_zoom)
    SimpleBanner sbTransformZoom;
    private List<String> multiTitles;
    private List<String> multiImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        ButterKnife.bind(this);
        initial();
    }

    private void initial() {
        multiTitles = new ArrayList<>();
        multiImgs = new ArrayList<>();
        multiTitles.add("当春乃发生");
        multiTitles.add("随风潜入夜");
        multiTitles.add("润物细无声");
        multiImgs.add(Images.imageThumbUrls[4]);
        multiImgs.add(Images.imageThumbUrls[11]);
        multiImgs.add(Images.imageThumbUrls[12]);

        sbTransformDefault
                .setmOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(TransformActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
                    }
                });

        sbTransformDefault.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Default)
                .isAutoPlay(true)
                .start();

        sbTransformAlpha.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Alpha)
                .isAutoPlay(true)
                .start();

        sbTransformRotate.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Rotate)
                .isAutoPlay(true)
                .start();

        sbTransformCube.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Cube)
                .isAutoPlay(true)
                .start();

        sbTransformFlip.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Flip)
                .isAutoPlay(true)
                .start();

        sbTransformAccordion.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Accordion)
                .isAutoPlay(true)
                .start();

        sbTransformZoomFade.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.ZoomFade)
                .isAutoPlay(true)
                .start();

        sbTransformFade.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Fade)
                .isAutoPlay(true)
                .start();

        sbTransformZoomCenter.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.ZoomCenter)
                .isAutoPlay(true)
                .start();

        sbTransformZoomStack.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.ZoomStack)
                .isAutoPlay(true)
                .start();

        sbTransformStack.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Stack)
                .isAutoPlay(true)
                .start();

        sbTransformDepth.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Depth)
                .isAutoPlay(true)
                .start();

        sbTransformZoom.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Zoom)
                .isAutoPlay(true)
                .start();
    }
}
