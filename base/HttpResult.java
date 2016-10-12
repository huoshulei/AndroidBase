package com.example.icogn.mshb.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ICOGN on 2016/9/26.
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
