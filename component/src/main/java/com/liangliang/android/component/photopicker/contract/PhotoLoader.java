package com.liangliang.android.component.photopicker.contract;

import android.content.Context;
import android.widget.ImageView;

/**
 * 预览图片加载接口
 */
public interface PhotoLoader<T> {
    void displayImg(Context context, T source, ImageView imageView);
}
