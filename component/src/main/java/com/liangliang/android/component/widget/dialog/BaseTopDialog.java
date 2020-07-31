package com.liangliang.android.component.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.liangliang.android.component.R;

/**
 * 顶部弹框基类
 */
public abstract class BaseTopDialog extends BaseDialog {
    public BaseTopDialog(@NonNull Context context) {
        super(context);
    }

    public BaseTopDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int getAnimations() {
        return R.style.animation_top_in_top_out;
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            if (isMatchWidth()) {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(layoutParams);
            }
        }
        super.show();
    }

    /**
     * 是否需要填满宽度
     */
    protected boolean isMatchWidth() {
        return true;
    }
}
