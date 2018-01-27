package com.huangjie.driver.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.huangjie.driver.R;


/**
 * Created by huangjie on 2016/8/20.
 */
public class DateView extends View {
    private String mDate = "20";
    private int mColor;

    public DateView(Context context) {
        this(context, null);
    }

    public DateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateView);
            mDate = typedArray.getString(R.styleable.DateView_view_text);
            mColor = typedArray.getColor(R.styleable.DateView_view_color, 0xFF0000);
        } finally {
            if (typedArray != null)
                typedArray.recycle();
        }


    }

    /**
     * 设置Date
     */
    public void setDate(String date) {
        this.mDate = date;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(4);

        Paint mTextPaint = new Paint();
        mTextPaint.setColor(mColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(60);
        mTextPaint.setStrokeWidth(4);
        //绘制圆环
        int iconwidth = getWidth() / 2;
        canvas.drawCircle(iconwidth, iconwidth, iconwidth - 2, mPaint);
        //绘制字体
        Rect textBound = new Rect();
        mTextPaint.getTextBounds(mDate, 0, mDate.length(), textBound);//获取字体大小范围
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() + fontMetricsInt.top - fontMetricsInt.bottom) / 2
                - fontMetricsInt.top;
        int left = (getMeasuredWidth() - textBound.width()) / 2;
        canvas.drawText(mDate, left, baseline, mTextPaint);
        mPaint = null;
    }
}
