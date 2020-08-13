package com.liangliang.android.fastdev.bean;

import com.liangliang.android.fastdev.bean.base.BaseRequestBean;

/**
 * 景点数据
 */
public class SpotRequestBean extends BaseRequestBean {
    /** 编号 */
    public String id = "";
    /** 输出 */
    public String output = "";

    public SpotRequestBean(String id, String output) {
        this.id = id;
        this.output = output;
    }
}
