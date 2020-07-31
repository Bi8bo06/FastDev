package com.liangliang.android.fastdev.ui.dialog;

import android.content.Context;

import com.liangliang.android.component.widget.dialog.BaseLeftDialog;
import com.liangliang.android.fastdev.R;

/**
 * 测试左侧弹框
 */
public class TestLeftDialog extends BaseLeftDialog {
    public TestLeftDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_test_left_layout;
    }

    @Override
    protected void findViews() {

    }

//    @Override
//    protected boolean isMatchHeight() {
//        return false;
//    }
}
