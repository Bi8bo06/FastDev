package com.liangliang.android.component.photopicker.contract;

import android.content.Context;

import com.liangliang.android.component.photopicker.contract.preview.PreviewController;

/**
 * 点击事件
 */
public interface OnClickListener<T> {
    void onClick(Context context, T source, int position, PreviewController controller);
}
