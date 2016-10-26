package com.example.icogn.mshb;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/26 11:21
 * 修改人:    ICOGN
 * 修改时间:  2016/10/26 11:21
 * 备注:
 * 版本:
 */

public class DataBindAdapter {
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        ImageLoader.getInstance().displayImage(url, view);
    }

}
