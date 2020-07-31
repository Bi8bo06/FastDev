package com.liangliang.android.component.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.liangliang.android.component.R;

/**
 * 左侧弹框基类
 */
public abstract class BaseLeftDialog extends BaseDialog {

    public BaseLeftDialog(@NonNull Context context) {
        super(context);
    }

    public BaseLeftDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int getAnimations() {
        return R.style.animation_left_in_left_out;
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            if (isMatchHeight()) {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(layoutParams);
            }
        }
        super.show();
    }

    /**
     * 是否需要填满高度
     */
    protected boolean isMatchHeight() {
        return true;
    }
}
