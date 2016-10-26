package com.example.icogn.mshb.login;

import android.databinding.BaseObservable;

import com.android.databinding.library.baseAdapters.BR;

import android.databinding.Bindable;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/25 16:15
 * 修改人:    ICOGN
 * 修改时间:  2016/10/25 16:15
 * 备注:
 * 版本:
 */

public class LoginBean extends BaseObservable {
    private String  phone;
    private String  password;
    private boolean progress;

    @Bindable
    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
