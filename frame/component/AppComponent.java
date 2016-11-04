package com.example.icogn.mshb.frame.component;

import android.content.Context;

import com.example.icogn.mshb.frame.http.Api;
import com.example.icogn.mshb.frame.module.ApiModule;
import com.example.icogn.mshb.frame.module.AppModule;

import dagger.Component;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/11/4 8:49
 * 修改人:    ICOGN
 * 修改时间:  2016/11/4 8:49
 * 备注:
 * 版本:
 */
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    Context getContext();

    Api getApiService();
}
