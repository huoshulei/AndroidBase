package com.example.icogn.mshb;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

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
@BindingMethods({
        @BindingMethod(type = NestedScrollView.class, attribute = "android:scrollY", method = "setOnScrollChangeListener")
})
public class DataBindAdapter {
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        ImageLoader.getInstance().displayImage(url, view);
    }

    @BindingAdapter(" app:circle")
    public static void setCircleImage(ImageView view, String url) {
        if (TextUtils.isEmpty(url)) return;
        ImageLoader.getInstance().displayImage(url, view, options);
    }

    @BindingAdapter("android:src")
    public static void setImageRes(ImageView view, int resId) {
        view.setImageResource(resId);
    }


    /**
     * 设置圆形头像
     */
    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new CircleBitmapDisplayer())
            .build();
}
