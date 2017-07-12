package com.humanheima.hmsimplebanner.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
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

/**
 * Created by dumingwei on 2016/10/22.
 * 简单的图片轮播,这个控件还是存在问题的不推荐使用
 */

public class SimpleBanner extends FrameLayout implements ViewPager.OnPageChangeListener {

    private String tag = SimpleBanner.class.getSimpleName();
    private int count;//图片轮播的数量
    private int currentItem;//ViewPager当前的item
    private List imageUrls;//轮播的图片的加载地址
    private List finalImageUrls;//无限循环轮播的图片的加载地址
    private List<String> titles;//轮播的标题
    private List<String> finalTitles;//无限循环轮播的标题
    private List<ImageView> indicatorImages;//多个轮播图片时，底部的小圆点
    private BannerViewPager viewPager;
    private BannerPagerAdapter adapter;
    private LinearLayout llIndicator;//使用线性布局放置轮播小圆点
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private OnBannerClickListener mOnBannerClickListener;


    public void setmOnBannerClickListener(OnBannerClickListener mOnBannerClickListener) {
        this.mOnBannerClickListener = mOnBannerClickListener;
    }

    private Context context;
    private Handler handler = new Handler();

    //attrs 属性
    private int delayTime = 3000;//默认轮播时间3000毫秒
    private boolean isAutoPlay = true;//默认自动轮播
    private int mIndicatorMargin;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int mIndicatorSelectedResId = R.drawable.selected_radius;//选中的时候小圆点
    private int mIndicatorUnselectedResId = R.drawable.unselected_radius;//未选中时的小圆点
    private int lastPosition = 1;

    public SimpleBanner(Context context) {
        this(context, null);
    }

    public SimpleBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        titles = new ArrayList<>();
        finalTitles = new ArrayList<>();
        imageUrls = new ArrayList<>();
        finalImageUrls = new ArrayList<>();
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
        //initViewPagerScroll();
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_width, 16);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_height, 16);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.Banner_indicator_margin, 8);
        mIndicatorSelectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.selected_radius);
        mIndicatorUnselectedResId = typedArray.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.unselected_radius);
        delayTime = typedArray.getInt(R.styleable.Banner_delay_time, delayTime);
        isAutoPlay = typedArray.getBoolean(R.styleable.Banner_is_auto_play, isAutoPlay);
        typedArray.recycle();
    }

    public SimpleBanner isAutoPlay(boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
        return this;
    }

    public SimpleBanner setDelayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    public SimpleBanner setBannerTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }

    public SimpleBanner setImages(List<?> imagesUrl) {
        this.imageUrls = imagesUrl;
        return this;
    }

    public SimpleBanner start() {
        initPlay(titles, imageUrls);
        if (isAutoPlay) {
            startAutoPlay();
        }
        return this;
    }

    /**
     * 为了实现无限循环轮播，titles 和 imageUrls 的长度都会增加两个
     * 比如说现在的titles 里面有三个title 分别是 [“刘备”，“关羽”，“张飞”]，有三个图片url[“刘备url”，“关羽url”，“张飞url”]
     * 经过这个方法以后变成 [“张飞”，“刘备”，“关羽”，“张飞”，刘备”]，url[张飞url”，“刘备url”，“关羽url”，“张飞url”，“刘备url”]
     *
     * @param titles
     * @param imageUrls
     */
    private void initPlay(List<String> titles, List imageUrls) {
        if ((titles == null || titles.size() <= 0) || (titles == null || titles.size() <= 0)) {
            throw new IllegalStateException("when SimpleBanner initPlay(List<String> titles, List<?> imageUrls) 标题和图片地址不能为空!");
        }
        count = imageUrls.size();
        //轮播图片大于一个才显示底部的小圆点
        if (count > 1) {
            initIndicator();
        }
        for (int i = 0; i <= count + 1; i++) {
            Object imgUrl;
            String title;
            if (i == 0) {
                imgUrl = imageUrls.get(count - 1);
                title = titles.get(count - 1);

            } else if (i == count + 1) {
                imgUrl = imageUrls.get(0);
                title = titles.get(0);
            } else {
                imgUrl = imageUrls.get(i - 1);
                title = titles.get(i - 1);
            }
            finalImageUrls.add(imgUrl);
            finalTitles.add(title);
        }
        setAdapter();
    }

    private void setAdapter() {
        currentItem = 1;
        if (adapter == null) {
            adapter = new BannerPagerAdapter(finalImageUrls, finalTitles);
            viewPager.setAdapter(adapter);
            viewPager.setFocusable(true);
            viewPager.addOnPageChangeListener(this);
        } else {
            adapter.notifyDataSetChanged();
        }
        viewPager.setCurrentItem(1);
        if (count <= 1) {
            viewPager.setScrollable(false);
        } else {
            viewPager.setScrollable(true);
        }
    }

    /**
     * 添加轮播图片底部的小圆点
     */
    private void initIndicator() {
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
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.e(tag, "onPageSelected position=" + position);
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        if (count > 1) {
            indicatorImages.get((lastPosition - 1 + count) % count).setImageResource(mIndicatorUnselectedResId);
            indicatorImages.get((position - 1 + count) % count).setImageResource(mIndicatorSelectedResId);
            lastPosition = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
        currentItem = viewPager.getCurrentItem();
        switch (state) {
            case 0:
                if (currentItem == 0) {
                    viewPager.setCurrentItem(count, false);
                } else if (currentItem == count + 1) {
                    viewPager.setCurrentItem(1, false);
                }
                break;
        }
    }

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (count > 1 && isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
                if (currentItem == 1) {
                    viewPager.setCurrentItem(currentItem, false);
                    handler.postDelayed(task, delayTime);
                } else if (currentItem == count + 1) {
                    viewPager.setCurrentItem(currentItem);
                    /**
                     * 这里为什么要比delayTime短的原因，因为当currentItem == count + 1的时候，
                     * 在 onPageScrollStateChanged 方法中执行了，下面两句话，程序悄悄的把第currentItem == count + 1个item换成了第1个item
                     *
                     * else if (currentItem == count + 1) {
                     viewPager.setCurrentItem(1, false);
                     }
                     这个时候currentItem=currentItem == count + 1,500毫秒后，在 run()方法中 会执行第一个if语句

                     currentItem = currentItem % (count + 1) + 1;
                     if (currentItem == 1) {
                     viewPager.setCurrentItem(currentItem, false);
                     handler.postDelayed(task, delayTime);
                     }
                     这个时候会重复设置ViewPager的item为第一个，所以 第一个item的停留时间，要比别的item多出500毫秒
                     */
                    handler.postDelayed(task, 500);
                } else {
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            }
        }
    };

    /**
     * ViewPager 的适配器
     */
    class BannerPagerAdapter extends PagerAdapter {

        private List imgUrls;
        private List<String> titles;

        public BannerPagerAdapter(List imgUrls, List<String> titles) {
            this.imgUrls = imgUrls;
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_banner);
            TextView textView = (TextView) view.findViewById(R.id.text_banner_title);
            textView.setText(titles.get(position));
            Glide.with(context).load(imgUrls.get(position)).placeholder(R.drawable.img_bg).error(R.drawable.img_bg).into(imageView);
            if (mOnBannerClickListener != null) {
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnBannerClickListener.OnBannerClick(position);
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
    }
}
