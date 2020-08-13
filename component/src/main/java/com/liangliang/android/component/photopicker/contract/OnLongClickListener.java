package com.liangliang.android.component.photopicker.contract;

import android.content.Context;

import com.liangliang.android.component.photopicker.contract.preview.PreviewController;

/**
 * 长按事件
 */
public interface OnLongClickListener<T> {
    void onLongClick(Context context, T source, int position, PreviewController controller);
}
