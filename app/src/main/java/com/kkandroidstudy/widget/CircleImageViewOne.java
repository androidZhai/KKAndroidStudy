package com.kkandroidstudy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.kkandroidstudy.R;

/**
 * Created by shiyan on 2016/11/25.
 */

public class CircleImageViewOne extends View {
    private Bitmap mSrc;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public CircleImageViewOne(Context context) {
        this(context, null);
    }

    public CircleImageViewOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageViewOne);
        int drawable = typedArray.getResourceId(R.styleable.CircleImageViewOne_src, 0);
        mSrc = BitmapFactory.decodeResource(getResources(), drawable);

        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取图片的最小边
        int result = Math.min(mWidth, mHeight);
        //创建缩放图像
        mSrc = Bitmap.createScaledBitmap(mSrc, result, result, false);

        Bitmap target = Bitmap.createBitmap(result, result, Bitmap.Config.ARGB_8888);

        Canvas mCanvas = new Canvas(target);
        //绘制一个圆形
        mCanvas.drawCircle(result / 2, result / 2, result / 2, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mCanvas.drawBitmap(mSrc, 0, 0, mPaint);

        canvas.drawBitmap(target, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置宽度
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        //如果父亲指定儿子的模式为MeasureSpec.EXACTLY，也就是父亲希望儿子的大小是一个定值，所以这时儿子的大小应当等于父亲对它的要求的大小
        // match_parent , accurate
        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
        } else {
            //图片的宽度
            int desireByImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
            //wrap_content
            if (specMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(desireByImg, specSize);
            } else {
                mWidth = desireByImg;
            }
        }

        //设置高度
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        //match_parent , accurate
        if (specMode == MeasureSpec.EXACTLY) {
            mHeight = specSize;
        } else {
            //图片的高度
            int desireByImg = getPaddingTop() + getPaddingBottom() + mSrc.getHeight();
            //wrap_content
            if (specMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desireByImg, specSize);
            } else {
                mHeight = desireByImg;
            }
        }
        Log.e("mWidth:",mWidth+"");
        Log.e("mHeight:",mHeight+"");
        setMeasuredDimension(mWidth, mHeight);

    }
}
