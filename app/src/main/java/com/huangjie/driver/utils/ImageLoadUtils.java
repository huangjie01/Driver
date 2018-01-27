package com.huangjie.driver.utils;

import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huangjie.driver.R;

import java.lang.ref.WeakReference;

/**
 * Created by huangjie on 2017/5/28.
 */

public class ImageLoadUtils {
    public static void loadImage(ImageView imageView, String imageurl) {
        WeakReference<ImageView> reference = new WeakReference<>(imageView);
        if (imageView == null) {
            Toast.makeText(Utils.getContext(), "ImageView 不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Glide.with(Utils.getContext())
                .load(imageurl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(Utils.getDrawable(R.drawable.about_logo))
                .into(reference.get());
    }

    /**
     * 用于干货item，将gif图转换为静态图
     */
    public static void displayGif(ImageView imageView, String url) {

        Glide.with(imageView.getContext()).load(url)
                .asBitmap()
                .placeholder(R.drawable.img_one_bi_one)
                .error(R.drawable.img_one_bi_one)
//                .skipMemoryCache(true) //跳过内存缓存
//                .crossFade(1000)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)// 缓存图片源文件（解决加载gif内存溢出问题）
//                .into(new GlideDrawableImageViewTarget(imageView, 1));
                .into(imageView);
    }
}
