package com.example.icogn.mshb;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icogn.mshb.view.MyToolbar;
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
        @BindingMethod(type = NestedScrollView.class, attribute = "android:scrollY", method = "setOnScrollChangeListener"),
        @BindingMethod(type = ImageView.class, attribute = "android:src", method = "setImageResource"),
        @BindingMethod(type = MyToolbar.class, attribute = "app:titleText", method = "setTitle")
})
public class DataBindAdapter {
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        ImageLoader.getInstance().displayImage(url, view);
    }

    @BindingAdapter(" app:circle")
    public static void setCircle(ImageView view, String url) {
        if (TextUtils.isEmpty(url)) return;
        ImageLoader.getInstance().displayImage(url, view, options);
    }

//    @BindingAdapter("android:src")
//    public static void setImageRes(ImageView view, int resId) {
//        view.setImageResource(resId);
//    }

    @BindingAdapter("android:drawableRight")
    public static void setDrawableRight(TextView view, int resId) {
        Drawable drawable = view.getResources().getDrawable(resId);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            Drawable[] drawables = view.getCompoundDrawables();
            view.setCompoundDrawables(drawables[0], drawables[1], drawable,
                    drawables[3]);
        }
    }

//    @BindingAdapter("app:titleText")
//    public static void setTitle(MyToolbar view, CharSequence title) {
//        view.setTitle(title);
//    }


    /**
     * 设置圆形头像
     */
    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new CircleBitmapDisplayer())
            .build();
}
