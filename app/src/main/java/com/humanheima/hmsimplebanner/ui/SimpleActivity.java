package com.humanheima.hmsimplebanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.hm.banner.ViewPageAdapter;
import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.databinding.ActivitySimpleBinding;
import com.humanheima.hmsimplebanner.util.GlideImageLoader;
import com.humanheima.hmsimplebanner.util.Images;

import java.util.ArrayList;
import java.util.List;

/**
 * Crete by dumingwei on 2020-02-25
 * Desc:
 */
public class SimpleActivity extends AppCompatActivity {

    private static final String TAG = "SimpleActivity";
    private ActivitySimpleBinding binding;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SimpleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple);
        initMultiBanner();

        binding.btnChangeCurrentItem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        binding.viewPager.setCurrentItem(0);
                        //binding.viewPager.setCurrentItem(Integer.MAX_VALUE - 1);
                    }
                }
        );
    }

    /**
     * 多张轮播图
     */
    private void initMultiBanner() {
        List<String> multiImgs = new ArrayList<>();
        multiImgs.add(Images.imageUrls[0]);
        multiImgs.add(Images.imageUrls[1]);
        multiImgs.add(Images.imageUrls[2]);
        ViewPageAdapter adapter = new ViewPageAdapter(this, new GlideImageLoader(), multiImgs);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.viewPager.setAdapter(adapter);
        int middle = Integer.MAX_VALUE >> 1;
        //取divisiblePosition为可以整除multiImgs的size的值
        int divisiblePosition = middle - (middle % multiImgs.size());
        binding.viewPager.setCurrentItem(divisiblePosition);
    }

}
