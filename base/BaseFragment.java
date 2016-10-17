package com.example.icogn.mshb.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.icogn.mshb.http.Http;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected Map<String, String> map;
    private   View                view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (map == null) map = new ArrayMap<>();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = onCreateView(inflater, container);
            ButterKnife.bind(this, view);
            onInitHttp();
            onViewCreated();
            onActivityCreated();
        }
        return view;
    }

    protected void onActivityCreated() {

    }

    /**
     * 初始化布局组件
     */
    protected void onViewCreated() {

    }

    /**
     * 首次加载网络数据
     */
    protected void onInitHttp() {
    }


    protected abstract View onCreateView(LayoutInflater inflater, ViewGroup container);

    protected <T> void http(Observable<HttpResult<T>> observable) {
        Http.HTTP.http(observable, new Subscriber<T>() {
            @Override
            public void onStart() {
                BaseFragment.this.showProgress();
            }

            @Override
            public void onCompleted() {
                BaseFragment.this.dismissProgress();
            }

            @Override
            public void onError(Throwable e) {
                BaseFragment.this.dismissProgress();
                BaseFragment.this.onError(e.getMessage());
                Log.d(TAG, "onError: " + e.getLocalizedMessage());
                Log.d(TAG, "onError: " + e.toString());
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(T t) {
                BaseFragment.this.onNextObject(t);
                BaseFragment.this.onNavigate();
            }
        });
    }

    /**
     * 显示加载动画
     */
    protected void showProgress() {
    }

    /**
     * 错误处理
     */
    protected void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 关闭加载动画
     */
    protected void dismissProgress() {

    }

    /**
     * @param t 返回加载数据
     */
    private void onNextObject(Object t) {
        if (t instanceof ArrayList) {
            if (((ArrayList) t).size() > 0)
                onNext((ArrayList) t);
        } else onNext(t);
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
