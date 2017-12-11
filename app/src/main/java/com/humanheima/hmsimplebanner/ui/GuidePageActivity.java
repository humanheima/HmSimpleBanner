package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.databinding.ActivityGuidePageBinding;
import com.humanheima.hmsimplebanner.util.GlideImageLoader;
import com.humanheima.hmsimplebanner.util.Images;

import java.util.ArrayList;
import java.util.List;

public class GuidePageActivity extends AppCompatActivity {

    private ActivityGuidePageBinding binding;
    private List<String> multiTitles;
    private List<String> multiImgs;

    public static void launch(Context context) {
        Intent intent = new Intent(context, GuidePageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guide_page);
        binding.textJumpOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(GuidePageActivity.this);
                finish();
            }
        });
        binding.textEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(GuidePageActivity.this);
                finish();
            }
        });
        initBanner();
    }

    private void initBanner() {
        multiTitles = new ArrayList<>();
        multiImgs = new ArrayList<>();
        multiTitles.add("");
        multiTitles.add("");
        multiTitles.add("");
        multiImgs.add(Images.imageThumbUrls[0]);
        multiImgs.add(Images.imageThumbUrls[1]);
        multiImgs.add(Images.imageThumbUrls[2]);
        binding.simpleMultiBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == multiImgs.size() - 1) {
                    binding.textEnter.setVisibility(View.VISIBLE);
                    binding.textJumpOver.setVisibility(View.INVISIBLE);
                } else {
                    binding.textEnter.setVisibility(View.INVISIBLE);
                    binding.textJumpOver.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.simpleMultiBanner.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setBannerTitles(multiTitles)
                .start();
    }

}
