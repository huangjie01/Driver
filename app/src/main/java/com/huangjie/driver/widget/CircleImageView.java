package com.huangjie.driver.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by huangjie on 2017/1/16.
 */

public class CircleImageView extends ImageView {
    private Bitmap mBitmap;
    private Paint mPaint;
    private BitmapShader mBitmapShader;

    public CircleImageView(Context context) {
        this(context, null);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        if (mBitmap == null) {
            return;
        }
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }


    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        init();

    }

    /**
     * 从drawable中获取bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {

        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {

            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap resultBitmap = null;
        try {

            if (drawable instanceof ColorDrawable) {
                resultBitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);

            } else {
                resultBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            }
            Canvas canvas = new Canvas(resultBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBitmap;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        init();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = uri != null ? getBitmapFromDrawable(getDrawable()) : null;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null || mBitmapShader == null) {
            return;
        }
        if (mBitmap.getWidth() == 0 || mBitmap.getHeight() == 0) {
            return;
        }
        updateBitmapShader();
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2,
                Math.min((float) getWidth() / 2, (float) getHeight() / 2), mPaint);

    }

    /**
     * 根据当前实际宽度做一些调整
     */
    private void updateBitmapShader() {

        int canvasWidth = Math.min(getWidth(), getHeight());
        if (canvasWidth != mBitmap.getWidth() || canvasWidth != mBitmap.getHeight()) {
            float scale = canvasWidth / (float) mBitmap.getWidth();
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            mBitmapShader.setLocalMatrix(matrix);

        }
    }
}
