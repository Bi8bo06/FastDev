package com.liangliang.android.imageloader;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.liangliang.android.imageloader.contract.ImageLoaderContract;
import com.liangliang.android.imageloader.glide.impl.GlideImageLoader;

/**
 * 图片加载器
 */
public class ImageLoader {
    /**
     * 构造加载器
     * @param o 参数必须是Context、FragmentActivity、Activity、Fragment中的一种
     */
    public static ImageLoaderContract create(Object o){

        if (o instanceof FragmentActivity){
            return GlideImageLoader.with((FragmentActivity) o);
        }
        if (o instanceof Activity){
            return GlideImageLoader.with((Activity) o);
        }
        if (o instanceof Context){
            return GlideImageLoader.with((Context) o);
        }
        if (o instanceof Fragment){
            return GlideImageLoader.with((Fragment) o);
        }
        throw new RuntimeException("你传入的Object对象不属于Context、FragmentActivity、Activity、Fragment中的一种");
    }
}
