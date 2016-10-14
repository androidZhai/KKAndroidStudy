package com.kkandroidstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.kkandroidstudy.R;
import com.kkandroidstudy.transformer.RotateYTransformer;
import com.kkandroidstudy.transformer.ScaleInTransformer;
import com.kkandroidstudy.widget.ScaleViewPager;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomViewPagerActivity extends AppCompatActivity implements ScaleViewPager.OnScaleViewPagerDisplayClickListener {
    private ScaleViewPager scaleViewPager;
    private List<String> datas = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);

        scaleViewPager = (ScaleViewPager) findViewById(R.id.scaleViewPager);
        initData();
        scaleViewPager.setData(datas);
        scaleViewPager.setPageTransformer(new RotateYTransformer());
        scaleViewPager.setOnScaleViewPagerDisplayClickListener(this);
    }

    private void initData() {
        datas.add("http://192.168.0.247:80/upload/carousel/1.jpg");
        datas.add("http://192.168.0.247:80/upload/carousel/2.jpg");
        datas.add("http://192.168.0.247:80/upload/carousel/3.jpg");
    }

    @Override
    public void clickImage(int postion, String url) {
        Logger.d(postion);
    }

    @Override
    public void displayImage(ImageView imageView, String imgUrl) {
        Picasso.with(this).load(imgUrl).into(imageView);
    }
}
