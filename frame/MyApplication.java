package com.example.icogn.mshb.frame;

import android.app.Application;

import com.example.icogn.mshb.BuildConfig;
import com.example.icogn.mshb.base.BaseActivity;
import com.example.icogn.mshb.frame.component.AppComponent;
import com.example.icogn.mshb.frame.component.DaggerAppComponent;
import com.example.icogn.mshb.frame.module.ApiModule;
import com.example.icogn.mshb.frame.module.AppModule;
import com.example.icogn.mshb.utils.logger.LogLevel;
import com.example.icogn.mshb.utils.logger.Logger;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/9/9 11:37
 * 修改人:    ICOGN
 * 修改时间:  2016/9/9 11:37
 * 备注: 正则表达式混淆
 * 版本:
 */
public class MyApplication extends Application {
    private List<BaseActivity> activities = new ArrayList<>();
    private AppComponent appComponent;

    public List<BaseActivity> getActivities() {
        return activities;
    }

    public void addActivities(BaseActivity activities) {
        this.activities.add(activities);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        initLogger();
        initComponent();
    }

    private void initComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化日志信息
     */
    private void initLogger() {
        Logger.init("日志")
                .methodCount(2)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .methodOffset(2);
    }

    /**
     * 初始化image loader
     */
    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//默认图片 加载中图片 错误图片 圆角
//                .showImageForEmptyUri(R.mipmap.ic_avatar)
//                .showImageOnLoading(R.mipmap.ic_avatar)
//                .showImageOnFail(R.mipmap.ic_avatar)
//                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R
//                        .dimen.dp_80)))
                .cacheInMemory(true) // 打开内存缓存
                .cacheOnDisk(true) // 打开硬盘缓存
                .resetViewBeforeLoading(true)// 在ImageView加载前清除它上面之前的图片
                .build();
        // ImageLoader的配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder
                (getApplicationContext())
                .memoryCacheSize(5 * 1024 * 1024)// 设置内存缓存为5M
                .defaultDisplayImageOptions(options)// 设置默认的显示选项
                .denyCacheImageMultipleSizesInMemory()//禁止缓存多图
                .threadPriority(3)
                .diskCacheSize(200 * 1024 * 1024)//磁盘缓存大小
                .build();
        // 初始化ImageLoader
        ImageLoader.getInstance().init(config);
    }
}
