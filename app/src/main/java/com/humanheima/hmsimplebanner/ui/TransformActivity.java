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
import com.humanheima.hmsimplebanner.databinding.ActivityTransformBinding;
import com.humanheima.hmsimplebanner.util.GlideImageLoader;
import com.humanheima.hmsimplebanner.util.Images;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TransformActivity extends AppCompatActivity {

    private ActivityTransformBinding binding;
    private List<String> multiTitles;
    private List<String> multiImgs;

    public static void launch(Context context) {
        Intent intent = new Intent(context, TransformActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transform);
        ButterKnife.bind(this);
        initial();
    }

    private void initial() {
        multiTitles = new ArrayList<>();
        multiImgs = new ArrayList<>();
        multiTitles.add("当春乃发生");
        multiTitles.add("随风潜入夜");
        multiTitles.add("润物细无声");
        multiImgs.add(Images.imageUrls[4]);
        multiImgs.add(Images.imageUrls[11]);
        multiImgs.add(Images.imageUrls[12]);

        binding.sbTransformDefault
                .setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(TransformActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
                    }
                });

        binding.sbTransformDefault.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Default)
                .isAutoPlay(true)
                .start();

        binding.sbTransformAlpha.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setTitles(multiTitles)
                .setTransitionEffect(TransitionEffect.Alpha)
                .isAutoPlay(true)
                .start();

        binding.sbTransformRotate.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Rotate)
                .isAutoPlay(true)
                .start();

        binding.sbTransformCube.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Cube)
                .isAutoPlay(true)
                .start();

        binding.sbTransformFlip.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Flip)
                .isAutoPlay(true)
                .start();

        binding.sbTransformAccordion.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Accordion)
                .isAutoPlay(true)
                .start();

        binding.sbTransformZoomFade.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.ZoomFade)
                .isAutoPlay(true)
                .start();

        binding.sbTransformFade.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Fade)
                .isAutoPlay(true)
                .start();

        binding.sbTransformZoomCenter.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.ZoomCenter)
                .isAutoPlay(true)
                .start();

        binding.sbTransformZoomStack.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.ZoomStack)
                .isAutoPlay(true)
                .start();

        binding.sbTransformStack.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Stack)
                .isAutoPlay(true)
                .start();

        binding.sbTransformDepth.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Depth)
                .isAutoPlay(true)
                .start();

        binding.sbTransformZoom.setImages(multiImgs)
                .setTitles(multiTitles)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Zoom)
                .isAutoPlay(true)
                .start();
    }
}
