package com.example.icogn.mshb.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/9/26 14:05
 * 修改人:    ICOGN
 * 修改时间:  2016/9/26 14:05
 * 备注:
 * 版本:
 */

public class HttpResult<T> {
    @SerializedName("success")
    private int    result;
    @SerializedName("msg")
    private String state;
    @SerializedName("data")
    private T      data;

    public int getResult() {
        return result;
    }

    public String getState() {
        return state;
    }

    public T getData() {
        return data;
    }
//    List<T> data;
}
