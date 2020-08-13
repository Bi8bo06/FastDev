package com.liangliang.android.fastdev.ui.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.liangliang.android.component.base.activity.BaseActivity;
import com.liangliang.android.fastdev.R;
import com.liangliang.android.fastdev.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义控件测试类
 */
public class CustomViewTestActivity extends BaseActivity {
    private static final int[] COLORS = new int[]{Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.LTGRAY};

    /**
     * 自定义view
     */
    @BindView(R.id.custom_view)
    CustomView mCustomView;
    /**
     * 点击次数
     */
    private int index = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_view_layout;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        getTitleBarLayout().setTitleName(getIntent().getStringExtra(MainActivity.EXTRA_TITLE_NAME));
        mCustomView.setCircleColor(Color.LTGRAY);
    }

    @Override
    protected void clickBackBtn() {
        super.clickBackBtn();
        finish();
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mCustomView.setOnCircleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomView.setCircleColor(COLORS[index % COLORS.length]);
                index++;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showStatusCompleted();
    }
}
