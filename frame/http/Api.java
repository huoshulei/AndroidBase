package com.example.icogn.mshb.frame.http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 项目名称:
 * 类描述:
 * 创建人:
 * 创建时间:
 * 修改人:
 * 修改时间:
 * 备注:
 * 版本:
 */

public interface Api {

    /**
     * token刷新token
     *
     * @param token oldToken
     * @return
     */
    @FormUrlEncoded
    @POST("app/token")
    Call<String> refreshToken(@Field("token") String token);
}
