package com.example.icogn.mshb.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icogn.mshb.App;
import com.example.icogn.mshb.http.Api;
import com.example.icogn.mshb.http.Http;
import com.example.icogn.mshb.utils.logger.Logger;

import java.util.Map;

import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected Map<String, String> map;
    private   View                view;
    protected final Api api = Http.HTTP.api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (map == null) map = new ArrayMap<>();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = init(inflater, container);
            configView(view);
            initData();
            onFirstNet();
        }
        return view;
    }



    protected View init(LayoutInflater inflater, ViewGroup container) {
        int layoutResId = getLayoutResId();
        if (layoutResId == 0) throw new NullPointerException("布局文件不能为空");
        View view = inflater.inflate(layoutResId, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract
    @LayoutRes
    int getLayoutResId();

    protected void initData() {

    }

    /**
     * 初始化布局组件
     */
    protected void configView(View view) {

    }

    /**
     * 首次加载网络数据
     */
    protected void onFirstNet() {
    }


    protected final <T> void http(Flowable<HttpResult<T>> f, Consumer<T> onNext) {
        showProgress();
        Http.HTTP.http(f, onNext, e -> onError(e.getMessage()), this::onComplete);
    }

    private void onComplete() {
        dismissProgress();
        onNavigate();
    }

    /**
     * 显示加载动画
     */
    protected void showProgress() {
    }


    /**
     * 错误信息
     */
    protected void onError(String msg) {
        App.toast(msg);
        dismissProgress();
        Logger.e("BaseFragment onError:" + msg);
    }


    /**
     * 关闭加载动画
     */
    protected void dismissProgress() {

    }


    /**
     * 页面导航
     */
    protected void onNavigate() {

    }

}
