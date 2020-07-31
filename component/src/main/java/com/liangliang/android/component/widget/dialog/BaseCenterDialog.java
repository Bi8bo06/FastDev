package com.liangliang.android.component.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;

import androidx.annotation.NonNull;

import com.liangliang.android.component.R;

/**
 * 中间弹框基类（缩放动画）
 */
public abstract class BaseCenterDialog extends BaseDialog {

    public BaseCenterDialog(@NonNull Context context) {
        super(context);
    }

    public BaseCenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int getAnimations() {
        return hasAnimations() ? R.style.animation_center_in_center_out : super.getAnimations();
    }

    /**
     * 是否有动画
     */
    protected boolean hasAnimations() {
        return true;
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
        }
        super.show();
    }

}
