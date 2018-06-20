package com.beidou.utils.photo;

import android.app.Activity;
import android.widget.ImageView;


import com.beidou.utils.GlideUtils;
import com.lzy.imagepicker.loader.ImageLoader;

public class GlideImageLoader implements ImageLoader {



    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideUtils.loadImageView(activity,path,imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}