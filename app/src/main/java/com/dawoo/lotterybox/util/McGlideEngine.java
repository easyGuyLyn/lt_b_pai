package com.dawoo.lotterybox.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * Created by alex on 18-3-20.
 */

public class McGlideEngine implements ImageEngine {
    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions requestOptions = new RequestOptions()
                .override(resize, resize)
                .centerCrop()
                .placeholder(placeholder);
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void loadAnimatedGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                         Uri uri) {
        RequestOptions requestOptions = new RequestOptions()
                .override(resize, resize)
                .centerCrop()
                .placeholder(placeholder);
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions requestOptions = new RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                .centerCrop();
        Glide.with(context)
                .load(uri)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public void loadAnimatedGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions requestOptions = new RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                .centerCrop();
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(requestOptions)
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
