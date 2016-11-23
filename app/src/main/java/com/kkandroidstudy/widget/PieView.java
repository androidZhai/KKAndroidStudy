package com.kkandroidstudy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.kkandroidstudy.bean.PieData;

import java.util.ArrayList;

/**
 * Created by shiyan on 2016/11/23.
 * 饼状图
 */
public class PieView extends View {
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //饼状图初始化角度
    private float mStartAngle = 0;

    //数据
    private ArrayList<PieData> mData;

    //画笔
    private Paint mPaint = new Paint();

    //饼状图宽度,高度
    private int mWidth, mHeight;


    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置画笔风格
        mPaint.setStyle(Paint.Style.FILL);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取控件的宽度和高度
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData) {
            return;
        }
        //设置起始角度
        float mCurrentStartAngel = mStartAngle;
        //将画布坐标原点移动到中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        //饼状图半径
        float r = (float) (Math.min(mWidth / 2, mHeight / 2) * 0.8);
        //饼状图绘制区域
        RectF rectF = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            PieData data = mData.get(i);
            //设置画笔颜色
            mPaint.setColor(data.getColor());
            //绘制弧度
            canvas.drawArc(rectF, mCurrentStartAngel, data.getAngel(), true, mPaint);
            mCurrentStartAngel += data.getAngel();
        }
    }

    //设置起始角度
    public void setAngel(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    //设置数据
    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        //刷新
        invalidate();
    }

    private void initData(ArrayList<PieData> mData) {
        //数据有问题直接返回
        if (mData == null || mData.size() == 0) {
            return;
        }
        //总大小
        float sumValue = 0;
        //设置颜色
        for (int i = 0; i < mData.size(); i++) {
            PieData data = mData.get(i);
            //计算数值和
            sumValue += data.getValue();
            //设置颜色
            int j = i % mData.size();
            data.setColor(mColors[j]);
        }

        //计算百分比,计算角度
        for (int i = 0; i < mData.size(); i++) {
            PieData data = mData.get(i);
            //计算百分比
            float percentage = data.getValue() / sumValue;
            //计算角度
            float angel = percentage * 360;
            //设置百分比
            data.setPercentage(percentage);
            //设置角度
            data.setAngel(angel);
        }
    }
}
