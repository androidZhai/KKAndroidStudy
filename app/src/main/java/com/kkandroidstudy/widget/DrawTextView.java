package com.kkandroidstudy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by shiyan on 2016/12/2.
 */

public class DrawTextView extends View {
    private Paint paint;
    private int mWidth;
    private int mHeight;


    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, 0);
        //左对齐
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("左对齐", 0, 200, paint);

        //居中对齐
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("居中对齐", 0, 400, paint);

        //右对齐
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("右对齐", 0, 600, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }
}
