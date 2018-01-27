package com.huangjie.driver.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huangjie.driver.R;
import com.huangjie.driver.global.DriverApplication;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by huangjie on 2017/2/12.
 */

public class GlideBannderImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(DriverApplication.getContext())
                .load(path)
                .placeholder(R.drawable.img_two_bi_one)
                .error(R.drawable.img_four_bi_three)
                .crossFade(1000)
                .into(imageView);
    }
}
