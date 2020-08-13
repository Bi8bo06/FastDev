package com.liangliang.android.fastdev.ui.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.liangliang.android.component.widget.dialog.BaseTopDialog;
import com.liangliang.android.fastdev.R;

/**
 * 测试顶部弹框
 */
public class TestTopDialog extends BaseTopDialog {
    public TestTopDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_test_top_layout;
    }

    @Override
    protected void findViews() {

    }

//    @Override
//    protected boolean isMatchWidth() {
//        return false;
//    }
}
