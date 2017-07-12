package com.humanheima.hmsimplebanner.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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

public class TransformActivity extends AppCompatActivity {

    @BindView(R.id.sb_transform_default)
    SuperCopyBanner sbTransformDefault;
    @BindView(R.id.sb_transform_alpha)
    SuperCopyBanner sbTransformAlpha;
    @BindView(R.id.sb_transform_rotate)
    SuperCopyBanner sbTransformRotate;
    @BindView(R.id.sb_transform_cube)
    SuperCopyBanner sbTransformCube;
    @BindView(R.id.sb_transform_flip)
    SuperCopyBanner sbTransformFlip;
    @BindView(R.id.sb_transform_accordion)
    SuperCopyBanner sbTransformAccordion;
    @BindView(R.id.sb_transform_ZoomFade)
    SuperCopyBanner sbTransformZoomFade;
    @BindView(R.id.sb_transform_Fade)
    SuperCopyBanner sbTransformFade;
    @BindView(R.id.sb_transform_ZoomCenter)
    SuperCopyBanner sbTransformZoomCenter;
    @BindView(R.id.sb_transform_ZoomStack)
    SuperCopyBanner sbTransformZoomStack;
    @BindView(R.id.sb_transform_Stack)
    SuperCopyBanner sbTransformStack;
    @BindView(R.id.sb_transform_Depth)
    SuperCopyBanner sbTransformDepth;
    @BindView(R.id.sb_transform_zoom)
    SuperCopyBanner sbTransformZoom;
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
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Default)
                .isAutoPlay(true)
                .start();
        sbTransformAlpha.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Alpha)
                .isAutoPlay(true)
                .start();
        sbTransformRotate.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Rotate)
                .isAutoPlay(true)
                .start();
        sbTransformCube.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Cube)
                .isAutoPlay(true)
                .start();
        sbTransformFlip.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Flip)
                .isAutoPlay(true)
                .start();
        sbTransformAccordion.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Accordion)
                .isAutoPlay(true)
                .start();
        sbTransformZoomFade.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.ZoomFade)
                .isAutoPlay(true)
                .start();
        sbTransformFade.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Fade)
                .isAutoPlay(true)
                .start();
        sbTransformZoomCenter.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.ZoomCenter)
                .isAutoPlay(true)
                .start();
        sbTransformZoomStack.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.ZoomStack)
                .isAutoPlay(true)
                .start();
        sbTransformStack.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Stack)
                .isAutoPlay(true)
                .start();
        sbTransformDepth.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Depth)
                .isAutoPlay(true)
                .start();
        sbTransformZoom.setImages(multiImgs)
                .setBannerTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Zoom)
                .isAutoPlay(true)
                .start();
    }
}
