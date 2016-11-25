package com.kkandroidstudy.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.kkandroidstudy.R;

/**
 * Created by shiyan on 2016/11/25.
 */

public class MyView1 extends View {
    //当前的百分比[0,1]
    private float currentValue = 0;
    //当前点的位置
    private float[] pos;
    //当前点的tan值
    private float[] tan;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private Paint mPaint;


    public MyView1(Context context) {
        super(context);
        init();
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, option);
        mMatrix = new Matrix();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        //创建path
        Path path = new Path();
        //顺时针
        path.addCircle(0, 0, 200, Path.Direction.CW);
        //创建PathMeasure
        PathMeasure pathMeasure = new PathMeasure(path, false);
        //设置currentValue
        currentValue += 0.005;
        if (currentValue > 1) {
            currentValue = 0;
        }
        //获取当前的坐标以及矩形矩阵
        pathMeasure.getMatrix(pathMeasure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        //绘制path
        canvas.drawPath(path, mPaint);

        //绘制bitmap
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        invalidate();

    }
}
