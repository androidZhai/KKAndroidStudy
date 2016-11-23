package com.kkandroidstudy.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.kkandroidstudy.R;

/**
 * Created by shiyan on 2016/11/23.
 */

public class CheckView extends View {
    //动画状态  没有
    private final static int ANIM_NULL = 0;
    //动画状态  开启
    private final static int ANIM_CHECK = 1;
    //动画状态  关闭
    private final static int ANIM_UNCHECK = 2;

    private Context mContext;

    private Handler mHandler;

    private Paint mPaint;

    private Bitmap mBitmap;

    //当前页码
    private int animCurrentPage = -1;
    //总页数
    private int animMaxPage = 13;
    //d动画时长
    private int animDuration = 500;
    //动画状态
    private int animState = ANIM_NULL;
    // 是否只选中状态
    private boolean isCheck = false;

    //宽度
    private int mWidth;
    //高度
    private int mHeight;


    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        //画笔初始化
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        //获取bitmap资源文件
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.checkmark);
        //初始化handler
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage >= 0 && animCurrentPage < animMaxPage) {
                    if (animState == ANIM_NULL) {
                        return;
                    }
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }
                    invalidate();
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                } else {
                    if (animState == ANIM_CHECK) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
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

        //移动画布
        canvas.translate(mWidth / 2, mHeight / 2);

        //绘制圆形
        canvas.drawCircle(0f, 0f, 240f, mPaint);

        //得出每部分图片的边长
        int sideLength = mBitmap.getHeight();
        //计算出图像显区和实际绘制位置
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        RectF dest = new RectF(-200, -200, 200, 200);
        //绘制
        canvas.drawBitmap(mBitmap, src, dest, null);
    }

    public void check() {
        if (animState != ANIM_NULL || isCheck) {
            return;
        }

        animState = ANIM_CHECK;

        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    public void uncheck() {
        if (animState != ANIM_NULL || (!isCheck)) {
            return;
        }

        animState = ANIM_UNCHECK;

        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画时长
     *
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        mPaint.setColor(color);
    }
}
