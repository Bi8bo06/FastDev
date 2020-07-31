package com.liangliang.android.component.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.liangliang.android.component.R;

/**
 * 右侧弹框基类
 */
public abstract class BaseRightDialog extends BaseDialog {

    public BaseRightDialog(@NonNull Context context) {
        super(context);
    }

    public BaseRightDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int getAnimations() {
        return R.style.animation_right_in_right_out;
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
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
