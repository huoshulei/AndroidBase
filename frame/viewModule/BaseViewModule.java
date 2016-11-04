package com.example.icogn.mshb.frame.viewModule;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.example.icogn.mshb.frame.connector.OnProgress;
import com.example.icogn.mshb.frame.http.Api;
import com.example.icogn.mshb.frame.http.Http;
import com.example.icogn.mshb.frame.http.HttpResult;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;


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
        showProgress();
        Http.HTTP.http(f, onNext, e -> onError(e.getMessage()), this::onComplete);
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
}
