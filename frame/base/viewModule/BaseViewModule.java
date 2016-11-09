package com.example.icogn.mshb.frame.base.viewModule;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.example.icogn.mshb.frame.base.connector.OnProgress;
import com.example.icogn.mshb.frame.exception.ApiException;
import com.example.icogn.mshb.frame.http.Api;
import com.example.icogn.mshb.frame.http.Http;
import com.example.icogn.mshb.frame.http.HttpResult;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/11/2 9:16
 * 修改人:    ICOGN
 * 修改时间:  2016/11/2 9:16
 * 备注:
 * 版本:
 */

public class BaseViewModule implements Observable {
    private transient PropertyChangeRegistry mCallbacks;
    protected         Api                    api;
    private           OnProgress             progress;

    @Inject
    public BaseViewModule(OnProgress progress, Api api) {
        this.api = api;
        this.progress = progress;
    }

    protected final <T> void http(Flowable<HttpResult<T>> f, Consumer<T> onNext) {
        http(f, onNext, e -> onError(e.getMessage()));
    }

    protected final <T> void http(Flowable<HttpResult<T>> f, Consumer<T> onNext, Consumer<Throwable> e) {
        http(f, onNext, e, this::onComplete);
    }

    private void onComplete() {
        dismissProgress();
        onNavigate();
        progress.onComplete();
    }

    private void showProgress() {
        progress.showProgress();
    }

    private void onNavigate() {
        progress.onNavigate();
    }

    private void dismissProgress() {
        progress.dismissProgress();
    }

    private void onError(String message) {
        dismissProgress();
        progress.onError(message);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
    }

    /**
     * 通知侦听器,所有这个实例的属性已经改变了
     */
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    /**
     * 通知侦听器,一个特定的属性已经改变了。属性的getter变化应标有{@link Bindable}来生成一个字段
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }

    /**
     * 封装网络方法调用
     *
     * @param observable 观察者
     * @param onNext     订阅者
     * @param <T>        data类型
     */
    public final <T> void http(Flowable<HttpResult<T>> observable, Consumer<T> onNext,
                               Consumer<Throwable> onError, Action onComplete) {
        showProgress();
        progress.addDisposable(observable.map(this::apply)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(onNext, onError, onComplete, s -> s.request(Long.MAX_VALUE)));
    }

    private <T> T apply(HttpResult<T> result) {
        if (result.getResultCode() != 200) throw new ApiException(result.getState());
        if (result.getData() == null) return (T) new Object();
        return result.getData();
    }
}
