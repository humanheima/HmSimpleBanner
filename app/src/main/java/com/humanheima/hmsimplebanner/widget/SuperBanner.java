package com.humanheima.hmsimplebanner.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.humanheima.hmsimplebanner.R;
import com.humanheima.hmsimplebanner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.humanheima.hmsimplebanner.R.styleable.Banner;

/**
 * Created by dumingwei on 2016/10/22.
 * 简单的图片轮播
 */

public class SuperBanner extends FrameLayout implements ViewPager.OnPageChangeListener {

    private String tag = SuperBanner.class.getSimpleName();
    private int count;//图片轮播的数量
    private boolean isNumIndicator = false;//标志是否是数字指示
    private List imageUrls;//轮播的图片的加载地址
    private List<String> titles;//轮播的标题
    private List<ImageView> indicatorImages;//多个轮播图片时，底部的小圆点
    private BannerViewPager viewPager;
    private int duration = 800;//viewpager 切换页面的时间
    private BannerPagerAdapter adapter;
    private LinearLayout llIndicator;//使用线性布局放置轮播小圆点
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private OnBannerClickListener mOnBannerClickListener;
    //之前显示的图片
    private int preSelect = -1;
    //当前显示图片
    private int nowSelect = 0;
    private TextView textNumIndicator;

    private Context context;
    private Handler handler = new Handler();

    //attrs 属性
    private int delayTime = 4000;//默认轮播时间3000毫秒
    private boolean isAutoPlay = false;//默认自动轮播为false
    private int mIndicatorMargin;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int numIndicatorTextColor;//数字指示器的文字的颜色
    private Drawable numIndicatorBackground;//数字指示器的背景
    private int mIndicatorSelectedResId = R.drawable.selected_radius;//选中的时候小圆点
    private int mIndicatorUnselectedResId = R.drawable.unselected_radius;//未选中时的小圆点

    public void setmOnBannerClickListener(OnBannerClickListener mOnBannerClickListener) {
        this.mOnBannerClickListener = mOnBannerClickListener;
    }

    public SuperBanner(Context context) {
        this(context, null);
    }

    public SuperBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        titles = new ArrayList<>();
        imageUrls = new ArrayList<>();
        indicatorImages = new ArrayList<>();
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.simple_banner, this, true);
        viewPager = (BannerViewPager) view.findViewById(R.id.banner_viewpager);
        llIndicator = (LinearLayout) view.findViewById(R.id.ll_indicator);
        //初始化属性
        initTypedArray(context, attrs);
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, Banner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, 16);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, 16);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, 8);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.selected_radius);
        mIndicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.unselected_radius);
        delayTime = typedArray.getInt(R.styleable.Banner_delay_time, delayTime);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, isAutoPlay);
        numIndicatorTextColor = typedArray.getColor(R.styleable.Banner_num_indicator_text_color, Color.WHITE);
        numIndicatorBackground = typedArray.getDrawable(R.styleable.Banner_num_indicator_bg);
        typedArray.recycle();
    }

    public SuperBanner isNumberIndicator(boolean isNumIndicator) {
        this.isNumIndicator = isNumIndicator;
        return this;
    }

    public SuperBanner isAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    public SuperBanner setDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public SuperBanner setBannerTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public SuperBanner setImages(List<?> imagesUrl) {
        this.imageUrls = imagesUrl;
        return this;
    }

    public SuperBanner setPageTransformer(ViewPager.PageTransformer transformer) {
        viewPager.setPageTransformer(true, transformer);
        return this;
    }

    /**
     * 设置页码切换过程的时间长度
     *
     * @param duration 页码切换过程的时间长度
     */
    public void setPageChangeDuration(int duration) {
        if (duration >= 0 && duration <= 2000) {
            if (viewPager != null) {
                viewPager.setPageChangeDuration(duration);
            }
        }
    }


    public void start() {
        setPageChangeDuration(duration);
        initPlay(titles, imageUrls);
        if (isAutoPlay) {
            startAutoPlay();
        }
    }

    /**
     * @param titles
     * @param imageUrls
     */
    private void initPlay(List<String> titles, List imageUrls) {
        if ((titles == null || titles.size() <= 0) || (titles == null || titles.size() <= 0)) {
            throw new IllegalStateException("when initPlay(List<String> titles, List<?> imageUrls) 标题和图片地址不能为空!");
        }
        count = imageUrls.size();
        //轮播图片大于一个,并且不是数字指示，才显示底部的小圆点
        if (count > 1) {
            initIndicator();
            changeLoopPoint(nowSelect);
        }
        setAdapter();
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new BannerPagerAdapter(imageUrls, titles);
            viewPager.setAdapter(adapter);
            viewPager.setFocusable(true);
            viewPager.setCurrentItem(0);
            if (count > 1) {
                viewPager.addOnPageChangeListener(this);
                viewPager.setScrollable(true);
            } else {
                viewPager.setScrollable(false);
            }
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 添加轮播图片底部的小圆点
     */
    private void initIndicator() {
        if (isNumIndicator) {
            llIndicator.removeAllViews();
            textNumIndicator = new TextView(getContext());
            textNumIndicator.setId(R.id.banner_num_indicator_id);
            textNumIndicator.setGravity(Gravity.CENTER);
            textNumIndicator.setSingleLine();
            textNumIndicator.setEllipsize(TextUtils.TruncateAt.END);
            textNumIndicator.setTextColor(numIndicatorTextColor);
            if (numIndicatorBackground != null) {
                textNumIndicator.setBackgroundDrawable(numIndicatorBackground);
            }
            textNumIndicator.setVisibility(VISIBLE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llIndicator.addView(textNumIndicator, layoutParams);
        } else {
            indicatorImages.clear();
            llIndicator.removeAllViews();
            for (int i = 0; i < count; i++) {
                ImageView imageView = new ImageView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
                params.leftMargin = mIndicatorMargin;
                params.rightMargin = mIndicatorMargin;
                imageView.setLayoutParams(params);
                if (i == 0) {
                    imageView.setImageResource(mIndicatorSelectedResId);
                } else {
                    imageView.setImageResource(mIndicatorUnselectedResId);
                }
                indicatorImages.add(imageView);
                llIndicator.addView(imageView);
            }
        }
    }

    public void startAutoPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    public void stopAutoPlay() {
        handler.removeCallbacks(task);
    }

    /**
     * 处理触摸事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAutoPlay) {
            int action = ev.getAction();
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay();
            } else if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.e(tag, "onPageSelected position=" + position);
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        position %= count;
        //改变底部的小圆点
        if (count > 1) {
            changeLoopPoint(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeLoopPoint(int position) {
        preSelect = nowSelect;
        nowSelect = position;
        if (isNumIndicator) {
            textNumIndicator.setText((nowSelect + 1) + "/" + count);
        } else {
            if (preSelect != -1) {
                indicatorImages.get(preSelect).setImageResource(mIndicatorUnselectedResId);
            }
            if (nowSelect != -1) {
                indicatorImages.get(nowSelect).setImageResource(mIndicatorSelectedResId);
            }
        }
    }

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            int num = adapter.getCount();
            if (num > 2) {
                int index = viewPager.getCurrentItem();
                index = index % (num - 2) + 1;
                viewPager.setCurrentItem(index);
            }
            handler.postDelayed(task, delayTime);
        }
    };

    /**
     * ViewPager 的适配器
     */
    class BannerPagerAdapter extends PagerAdapter {

        private final int FAKE_BANNER_SIZE = count*30;

        private List imgUrls;
        private List<String> titles;

        public BannerPagerAdapter(List imgUrls, List<String> titles) {
            this.imgUrls = imgUrls;
            this.titles = titles;
        }

        @Override
        public int getCount() {
            if (count == 1) {
                return 1;
            }
            return FAKE_BANNER_SIZE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= count;
            final int pos = position;
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_banner);
            TextView textView = (TextView) view.findViewById(R.id.text_banner_title);
            textView.setText(titles.get(position));
            Glide.with(context).load(imgUrls.get(position)).placeholder(R.drawable.img_bg).error(R.drawable.img_bg).into(imageView);
            if (mOnBannerClickListener != null) {
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnBannerClickListener.OnBannerClick(pos);
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            if (count > 1) {
                int position = viewPager.getCurrentItem();
                Log.e(tag, "finishUpdate" + position);
                if (position == 0) {
                    position = count;
                    viewPager.setCurrentItem(position, false);
                } else if (position == FAKE_BANNER_SIZE - 1) {
                    position = count - 1;
                    viewPager.setCurrentItem(position, false);
                }
            }
        }
    }
}
