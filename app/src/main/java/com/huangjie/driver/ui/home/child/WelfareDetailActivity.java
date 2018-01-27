package com.huangjie.driver.ui.home.child;

import android.app.Service;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.huangjie.driver.R;
import com.huangjie.driver.constant.Constant;
import com.huangjie.driver.databinding.ActivityWelfareBinding;
import com.huangjie.driver.utils.ImageLoadUtils;
import com.huangjie.driver.utils.LogUtils;
import com.huangjie.driver.utils.StatusBarUtil;
import com.huangjie.driver.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by huangjie on 2017/5/29.
 */

public class WelfareDetailActivity extends AppCompatActivity {
    private ActivityWelfareBinding mBindingView;
    private PhotoViewAttacher mPhotoAtacher;
    private String mImgUrl;
    private Vibrator mVibrator;
    private boolean mShowToolbar = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentForImageView(this, null);
        mBindingView = DataBindingUtil.setContentView(this, R.layout.activity_welfare);
        mImgUrl = getIntent().getStringExtra(Constant.SEND_DATA);
        init();
        mBindingView.welfareToolbar.setAlpha(0.7F);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(mlistener);
            getWindow().setSharedElementEnterTransition(new ChangeBounds());
//            setStatusColor();
        }

    }

    private void init() {
        mPhotoAtacher = new PhotoViewAttacher(mBindingView.welfareImageview);
        LogUtils.error("图片url:" + mImgUrl);
        ImageLoadUtils.loadImage(mBindingView.welfareImageview, mImgUrl);
        mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        mBindingView.welfareToolbar.setNavigationIcon(Utils.getDrawable(R.drawable.ic_arrow_back));
        mBindingView.welfareToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWithAnimation();
            }
        });
        mPhotoAtacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                showOrHideToolbar();
            }
        });

        mPhotoAtacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mVibrator.vibrate(100);
                saveImage();
                return true;
            }
        });
    }

    /**
     * 保存图片
     */
    private void saveImage() {
        File imageSaveDir = new File(Environment.getExternalStorageDirectory(), "driver");
        if (!imageSaveDir.exists()) {
            imageSaveDir.mkdir();
        }

        Bitmap bitmap = mPhotoAtacher.getImageView().getDrawingCache();
        File file = new File(imageSaveDir, new Date().getTime() + ".jpg");
        try {
            FileOutputStream outpustStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outpustStream);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            sendBroadcast(intent);
            Snackbar.make(getWindow().getDecorView(), "图片保存成功", Snackbar.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(Utils.getContext(), "图片保存失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().removeListener(mlistener);
        }
        super.onDestroy();
    }

    /**
     * 隐藏或者现实Toolbar
     */
    private void showOrHideToolbar() {
        mBindingView.welfareToolbar.animate()
                .translationY(mShowToolbar ? 0 : -mBindingView.welfareToolbar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mShowToolbar = !mShowToolbar;
    }

    private void finishWithAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    private Transition.TransitionListener mlistener = new Transition.TransitionListener() {
        @Override
        public void onTransitionStart(@NonNull Transition transition) {
            mBindingView.welfareContainer.animate()
                    .alpha(1f)
                    .setDuration(1000L)
                    .setInterpolator(new AccelerateInterpolator())
                    .start();
        }

        @Override
        public void onTransitionEnd(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionCancel(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionPause(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionResume(@NonNull Transition transition) {

        }
    };

}
