package com.example.icogn.mshb.base;


import android.app.Fragment;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.example.icogn.mshb.http.Http;


import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected Map<String, String> map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (map == null) map = new ArrayMap<>();
        super.onCreate(savedInstanceState);
    }

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
                BaseFragment.this.onNavigate();
                BaseFragment.this.onNext(t);
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
     * @param t   返回加载数据
     * @param <T>
     */
    protected <T> void onNext(T t) {

    }

    /**
     * 页面导航
     */
    protected void onNavigate() {

    }

}
