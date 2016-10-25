package com.example.icogn.mshb.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;

import com.example.icogn.mshb.App;
import com.example.icogn.mshb.http.Api;
import com.example.icogn.mshb.utils.logger.Logger;

import java.util.Map;

import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

import static com.example.icogn.mshb.http.Http.HTTP;

public abstract class BaseActivity extends AppCompatActivity {

    protected Map<String, String> map;
    protected final Api api = HTTP.api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init0();
        if (map == null) map = new ArrayMap<>();
        App.application.addActivities(this);
        configView();
        initData();
    }

    protected void init0() {
        int layoutResId = getLayoutResId();
        if (layoutResId == 0) throw new NullPointerException("布局文件不能为空");
        setContentView(layoutResId);
        ButterKnife.bind(this);
    }

    protected abstract
    @LayoutRes
    int getLayoutResId();

    /**
     * 加载布局
     */
    protected abstract void configView();

    /**
     * 绑定数据
     */
    protected abstract void initData();

    @Override
    public void finish() {
        if (App.application.getActivities().remove(this))
            super.finish();
    }


    protected final <T> void http(Flowable<HttpResult<T>> f, Consumer<T> onNext) {
        showProgress();
        HTTP.http(f, onNext, e -> onError(e.getMessage()), this::onComplete);
    }

    private void onComplete() {
        dismissProgress();
        onNavigate();
    }

    /**
     * 错误信息
     */
    protected void onError(String msg) {
        App.toast(msg);
        dismissProgress();
        Logger.e("BaseActivity onError:" + msg);
    }

    /**
     * 显示加载动画
     */
    protected void showProgress() {

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
