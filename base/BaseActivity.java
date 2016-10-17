package com.example.icogn.mshb.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.example.icogn.mshb.MyApplication;
import com.example.icogn.mshb.http.Http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

public abstract class BaseActivity extends AppCompatActivity {

    private MyApplication application;
    private static final String TAG = "BaseActivity";
    protected Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        application.addActivities(this);
        if (map == null) map = new ArrayMap<>();
        onCreate();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 关联布局
     */
    protected abstract void onCreate();

    /**
     * 加载布局
     */
    protected abstract void initView();

    /**
     * 绑定数据
     */
    protected abstract void initData();

    @Override
    public void finish() {
        if (application.getActivities().remove(this))
            super.finish();
    }

    protected <T> void http(Observable<HttpResult<T>> observable) {
        Http.HTTP.http(observable, new Subscriber<T>() {
            @Override
            public void onStart() {
                BaseActivity.this.showProgress();
            }

            @Override
            public void onCompleted() {
                BaseActivity.this.dismissProgress();
            }

            @Override
            public void onError(Throwable e) {
                BaseActivity.this.dismissProgress();
                BaseActivity.this.onError(e.getMessage());
                Log.d(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onNext(T t) {
                BaseActivity.this.onNextObject(t);
                BaseActivity.this.onNavigate();
            }
        });

    }

    /**
     * 错误信息
     */
    protected void onError(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
     * @param o 返回加载数据
     */
    private void onNextObject(Object o) {
        if (o instanceof ArrayList) {
            if (((ArrayList) o).size() > 0)
                onNext((ArrayList) o);
        } else onNext(o);
    }

    /**
     * 网络数据回调
     *
     * @param t 集合类型数据
     */
    protected void onNext(List t) {

    }

    /**
     * 网络数据回调
     *
     * @param o 单个数据
     */
    protected void onNext(Object o) {

    }

    /**
     * 页面导航
     */
    protected void onNavigate() {

    }


}
