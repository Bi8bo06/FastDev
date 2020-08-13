package com.liangliang.android.component.widget.adapter.bean;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.liangliang.android.component.R;
import com.liangliang.android.component.widget.adapter.swipe.SwipeMenuLayout;

/**
 * 侧滑ViewHolder
 */
public class SwipeViewHolder extends RecyclerView.ViewHolder{
    /** 侧滑布局 */
    public SwipeMenuLayout swipeMenuLayout;
    /** 内容布局 */
    public ViewGroup contentLayout;
    /** 右侧布局 */
    public ViewGroup rightLayout;
    /** 左侧布局 */
    public ViewGroup leftLayout;

    public SwipeViewHolder(View itemView) {
        super(itemView);
        swipeMenuLayout = itemView.findViewById(R.id.swipe_menu_layout);
        contentLayout = itemView.findViewById(R.id.content_view);
        rightLayout = itemView.findViewById(R.id.right_view);
        leftLayout = itemView.findViewById(R.id.left_view);
    }

    public void bindView(){
    }
}
