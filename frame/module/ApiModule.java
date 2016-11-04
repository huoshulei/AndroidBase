package com.example.icogn.mshb.frame.module;

import com.example.icogn.mshb.frame.http.Api;
import com.example.icogn.mshb.frame.http.Http;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/11/4 8:47
 * 修改人:    ICOGN
 * 修改时间:  2016/11/4 8:47
 * 备注:
 * 版本:
 */
@Module
public class ApiModule {
    @Provides
    public Api provideApiService() {
        return Http.HTTP.api;
    }
}
