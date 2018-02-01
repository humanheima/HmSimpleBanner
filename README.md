####HmBanner 的实现参考了以下参考链接
[BGABanner-Android ](https://github.com/bingoogolapple/BGABanner-Android)

[shucc](https://github.com/shucc/Carousel)

[Rolyyu](https://github.com/Rolyyu/banner)

[使用RecyclerView + ViewPager 的两个大坑！](http://blog.csdn.net/u011002668/article/details/72884893)

HmBanner 部分实现细节请参看[ViewPager实现无限循环轮播图控件](http://blog.csdn.net/leilifengxingmw/article/details/53364392)

1. 使用,首先实现ImageLoader重写展示图片的逻辑
```java
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .placeholder(R.drawable.img_bg)
                .error(R.drawable.img_bg)
                .into(imageView);
    }
}
```
2. xml布局文件
````xml
        <RelativeLayout
               android:id="@+id/activity_simple"
               android:layout_width="match_parent"
               android:layout_height="match_parent">
       
               <TextView
                   android:id="@+id/text_single"
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content"
                   android:gravity="center"
                   android:text="一张图片"
                   android:textColor="#ff3344"
                   android:textSize="16sp" />
       
               <!--一张图片-->
               <com.hm.banner.HmBanner
                   android:id="@+id/simple_single_banner"
                   android:layout_width="match_parent"
                   android:layout_height="200dp"
                   android:layout_below="@+id/text_single"
                   app:num_indicator_bg="@drawable/shape_number_indicator_background" />
       
               <TextView
                   android:id="@+id/text_multi"
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content"
                   android:layout_below="@+id/simple_single_banner"
                   android:layout_marginTop="8dp"
                   android:gravity="center"
                   android:text="多张图片"
                   android:textColor="#ff3344"
                   android:textSize="16sp" />
               
               <!--多张图片-->
               <com.hm.banner.HmBanner
                   android:id="@+id/simple_multi_banner"
                   android:layout_width="match_parent"
                   android:layout_height="200dp"
                   android:layout_below="@+id/text_multi" />
       
           </RelativeLayout>
            
````
3. 代码逻辑
````
 /**
     * 一张轮播图
     */
    private void initSingleBanner() {
        List<String> singleImgs = new ArrayList<>();
        singleImgs.add("http://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png");

        binding.simpleSingleBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        binding.simpleSingleBanner.setImages(singleImgs)
                .setImageLoader(new GlideImageLoader())
                .setTransitionEffect(TransitionEffect.Flip)
                .isAutoPlay(false)
                .start();

    }
    

    /**
     * 多张轮播图
     */
    private void initMultiBanner() {
        List<String> multiTitles = new ArrayList<>();
        List<String> multiImgs = new ArrayList<>();
        multiTitles.add("当春乃发生");
        multiTitles.add("随风潜入夜");
        multiTitles.add("润物细无声");
        multiImgs.add(Images.imageThumbUrls[0]);
        multiImgs.add(Images.imageThumbUrls[1]);
        multiImgs.add(Images.imageThumbUrls[2]);
        binding.simpleMultiBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(SimpleActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        binding.simpleMultiBanner.setImages(multiImgs)
                .setImageLoader(new GlideImageLoader())
                .setTitles(multiTitles)
                .isAutoPlay(true)
                .start();
    }

````


* 关于Banner作为HeadView在RecyclerView中的使用请参看：https://github.com/humanheima/RecyclerViewDemo


