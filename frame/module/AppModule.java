package com.example.icogn.mshb.frame.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/11/4 8:45
 * 修改人:    ICOGN
 * 修改时间:  2016/11/4 8:45
 * 备注:
 * 版本:
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContent() {
        return context;
    }
}
