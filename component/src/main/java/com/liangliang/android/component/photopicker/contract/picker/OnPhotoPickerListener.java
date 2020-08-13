package com.liangliang.android.component.photopicker.contract.picker;

import java.util.List;

/**
 * 照片选择监听器
 */
public interface OnPhotoPickerListener {
    /**
     * 照片选中回调
     *
     * @param photos 照片列表
     */
    void onPickerSelected(List<String> photos);
}
