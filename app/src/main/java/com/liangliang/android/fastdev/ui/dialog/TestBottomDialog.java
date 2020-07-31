package com.liangliang.android.fastdev.ui.dialog;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.liangliang.android.component.widget.dialog.BaseBottomDialog;
import com.liangliang.android.fastdev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试底部弹框
 */
public class TestBottomDialog extends BaseBottomDialog {
    @BindView(R.id.sure_btn)
    TextView mSureBtn;

    @BindView(R.id.tips)
    TextView mTips;


    public TestBottomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_test_bottom_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSureBtn.setElevation(16f);//添加阴影
            mTips.setElevation(16f);
        }
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
