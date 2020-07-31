package com.liangliang.android.fastdev.ui.dialog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.liangliang.android.component.widget.dialog.BaseRightDialog;
import com.liangliang.android.fastdev.R;

/**
 * 测试右侧弹框
 */
public class TestRightDialog extends BaseRightDialog {
    public TestRightDialog(@NonNull Context context) {
        super(context, R.style.NoDimDialog);//无半透明灰色背景
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_test_right_layout;
    }

    @Override
    protected void findViews() {
        setElevation(16f, ContextCompat.getDrawable(getContext(), R.color.white));
    }

    @Override
    protected void initData() {
        super.initData();
        setCanceledOnTouchOutside(false);//点击空白处不会消失
    }

//    @Override
//    protected boolean isMatchHeight() {
//        return false;
//    }
}
