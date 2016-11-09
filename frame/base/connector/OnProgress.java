package com.example.icogn.mshb.frame.base.connector;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/11/3 14:48
 * 修改人:    ICOGN
 * 修改时间:  2016/11/3 14:48
 * 备注:
 * 版本:
 */

public interface OnProgress {
    void onComplete();

    void showProgress();

    void onNavigate();

    void dismissProgress();

    void onError(String message);

    void addDisposable(Disposable disposable);

    void remove(Disposable disposable);

    void clear();
}
