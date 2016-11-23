package com.kkandroidstudy.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.kkandroidstudy.R;
import com.kkandroidstudy.bean.PieData;
import com.kkandroidstudy.widget.PieView;

import java.util.ArrayList;

public class PieViewActivity extends AppCompatActivity {
    private PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_view);

        pieView = (PieView) findViewById(R.id.pieView);

        ArrayList<PieData> datas = new ArrayList<>();
        PieData data = new PieData();
        data.setValue(20);
        datas.add(data);

        PieData data2 = new PieData();
        data.setValue(30);
        datas.add(data2);

        PieData data3 = new PieData();
        data3.setValue(50);
        datas.add(data3);

        PieData data4 = new PieData();
        data4.setValue(60);
        datas.add(data4);

        PieData data5 = new PieData();
        data5.setValue(80);
        datas.add(data5);

        PieData data6 = new PieData();
        data6.setValue(40);
        datas.add(data6);

        PieData data7 = new PieData();
        data7.setValue(70);
        datas.add(data7);

        pieView.setAngel(0);
        pieView.setData(datas);

        ObjectAnimator animator = ObjectAnimator.ofFloat(pieView, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }
}
