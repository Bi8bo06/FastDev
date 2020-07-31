package com.liangliang.android.component.widget.adapter.animation;

import android.animation.Animator;
import android.view.View;

/**
 * 动画基类
 */
public interface BaseAnimation {
    Animator[] getAnimators(View view);

    int getDuration();
}
