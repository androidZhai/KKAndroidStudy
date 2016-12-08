package com.kkandroidstudy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by shiyan on 2016/12/2.
 */

public class RegionClickView extends View {
    private Region circleRegion;
    private Path circlePath;
    private Paint mDeafultPaint;
    private Context mContext;

    public RegionClickView(Context context) {
        this(context, null);
    }

    public RegionClickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegionClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(0xFF4E5268);
        circleRegion = new Region();
        circlePath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //在屏幕中间添加一个圆
        circlePath.addCircle(w / 2, h / 2, 300, Path.Direction.CW);
        //将裁剪边界设置成视图大小
        Region globalRegion = new Region(-w, -h, w, h);
        //将path添加到region中
        circleRegion.setPath(circlePath, globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (circleRegion.contains(x, y)) {
                    Toast.makeText(mContext, "点击了", 0).show();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = circlePath;
        //绘制path
        canvas.drawPath(path, mDeafultPaint);
    }
}
