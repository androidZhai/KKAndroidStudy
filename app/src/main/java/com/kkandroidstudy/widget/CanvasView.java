package com.kkandroidstudy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shiyan on 2016/11/22.
 */

public class CanvasView extends View {
    private Paint paint;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布颜色
        canvas.drawColor(Color.BLUE);

        //绘制点
        canvas.drawPoint(200f, 200f, paint);

        //绘制一组点
        canvas.drawPoints(new float[]{
                500f, 500f,
                500f, 600f,
                500f, 700f
        }, paint);

        // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLine(300, 300, 500, 600, paint);

        // 绘制一组线 每四数字(两个点的坐标)确定一条线
        //  100,200,200,200,
        //100,300,200,300
        canvas.drawLines(new float[]{
                100, 200, 200, 200,
                100, 300, 200, 300
        }, paint);

        //绘制矩形
        canvas.drawRect(20, 20, 400, 150, paint);
        /**
         * Rect rect=new Rect(20,20,400,150);
         canvas.drawRect(rect,paint);
         */

        /**
         * RectF rectf=new RectF(20f,20f,400f,150f);
         * canvas.drawRectF(rectf,paint);
         */

        //绘制圆角矩形
        RectF rectF = new RectF(20, 400, 300, 500);
        canvas.drawRoundRect(rectF, 30, 30, paint);

        //绘制椭圆
        RectF ovalRectF = new RectF(500, 400, 700, 500);
        canvas.drawOval(ovalRectF, paint);

        //绘制圆
        canvas.drawCircle(600f, 200f, 150f, paint);

        //绘制圆弧
        RectF arcRectF = new RectF(20, 600, 200, 850);
        canvas.drawRect(arcRectF, paint);
        paint.setColor(Color.RED);
        canvas.drawArc(arcRectF, 30, 60, false, paint);
        canvas.drawArc(arcRectF, 90, 150, true, paint);
        paint.setColor(Color.BLACK);

        //保存画布
        canvas.save();
        //画布平移
        canvas.translate(200, 1300);
        canvas.drawCircle(0f, 0f, 200f, paint);
        canvas.restore();

        paint.setColor(Color.RED);
        //画线
        canvas.drawLine(0, 0, 400, 400, paint);


    }
}
