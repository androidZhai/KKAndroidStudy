package com.kkandroidstudy.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kkandroidstudy.R;
import com.kkandroidstudy.transformer.ScaleInTransformer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiyan on 2016/10/14.
 */
public class ScaleViewPager extends RelativeLayout {
    /**
     * 点击显示图片回调接口
     */
    public interface OnScaleViewPagerDisplayClickListener {
        public void clickImage(int postion, String url);

        public void displayImage(ImageView imageView, String imgUrl);
    }

    OnScaleViewPagerDisplayClickListener onScaleViewPagerDisplayClickListener;

    public void setOnScaleViewPagerDisplayClickListener(OnScaleViewPagerDisplayClickListener onScaleViewPagerDisplayClickListener) {
        this.onScaleViewPagerDisplayClickListener = onScaleViewPagerDisplayClickListener;
    }

    private ViewPager viewPager;

    private RelativeLayout fl_pager;

    private float x = 0f;
    //边距
    private float sideValue = 0f;
    //图片宽度
    private float pagerValue = 0f;
    //屏幕宽度
    private float screenWidthValue = 0f;

    private float distance = 0f;

    private List<String> list = new ArrayList();
    private ScaleViewPagerAdapter scaleViewPagerAdapter;

    public ScaleViewPager(Context context) {
        this(context, null);
    }

    public ScaleViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕宽度
        screenWidthValue = getResources().getDisplayMetrics().widthPixels;
        Log.e("screenWidthValue:", screenWidthValue + "");
        LayoutInflater.from(context).inflate(R.layout.scaleviewpager, this, true);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fl_pager = (RelativeLayout) findViewById(R.id.fl_pager);
        fl_pager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                distance = (screenWidthValue - 2 * sideValue - pagerValue) / 2.0f;
                Log.e("distance:", distance + "");
                Log.e("x:", x + "");
                viewPager.dispatchTouchEvent(event);
                return true;
            }
        });
    }

    public void setData(List<String> datas) {
        for (String str : datas) {
            list.add(str);
        }
        scaleViewPagerAdapter = new ScaleViewPagerAdapter();
        viewPager.setAdapter(scaleViewPagerAdapter);
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(3 * 1000 + 1);
    }

    public void setPageTransformer(ViewPager.PageTransformer pagerTransformer) {
        if (pagerTransformer != null) {
            viewPager.setPageTransformer(true, pagerTransformer);
        }
    }

    class ScaleViewPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container,
                                      final int position) {
            //获取当前图片的地址
            String imgUrl = list.get(position % list.size());
            //创建ImageView
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (onScaleViewPagerDisplayClickListener != null) {
                onScaleViewPagerDisplayClickListener.displayImage(imageView, imgUrl);
            }
            //设置图片点击事件
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取当前图片在数据源list中的位置
                    int number = position % list.size();
                    Log.e("number", number + "");
                    if (x < distance) {
                        switch (number) {
                            case 0:
                                number = 2;
                                break;
                            case 1:
                                number = 0;
                                break;
                            case 2:
                                number = 1;
                                break;
                        }
                        x = 0f;
                        distance = 0f;
                    }
                    if (onScaleViewPagerDisplayClickListener != null) {
                        onScaleViewPagerDisplayClickListener.clickImage(number, list.get(number));
                    }
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            RelativeLayout.MarginLayoutParams layoutParams = (MarginLayoutParams) fl_pager.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            Logger.t("leftMargin").d(leftMargin);
            sideValue += leftMargin;
            int rightMargin = layoutParams.rightMargin;
            Logger.t("rightMargin").d(rightMargin);
            sideValue += rightMargin;
            pagerValue = viewPager.getWidth();
            Logger.t("viewPagerWidth").d(pagerValue);
        }
    }
}
