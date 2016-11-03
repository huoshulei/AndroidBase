package com.example.icogn.mshb.frame.http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/21 13:18
 * 修改人:    ICOGN
 * 修改时间:  2016/10/21 13:18
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
    @POST("app/token.do")
    Call<String> refreshToken(@Field("token") String token);
}
