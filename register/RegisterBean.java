package com.example.icogn.mshb.register;

import com.android.databinding.library.baseAdapters.BR;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/25 16:43
 * 修改人:    ICOGN
 * 修改时间:  2016/10/25 16:43
 * 备注:
 * 版本:
 */

public class RegisterBean extends BaseObservable {
    private String  phone;
    private String  name;
    private String  password;
    private String  repeatPWD;
    private String  authCode;
    private boolean progress;
    private boolean timing;
    private String time = "获取验证码";
@Bindable
    public boolean isTiming() {
        return timing;
    }

    public void setTiming(boolean timing) {
        this.timing = timing;
        notifyPropertyChanged(BR.timing);
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getRepeatPWD() {
        return repeatPWD;
    }

    public void setRepeatPWD(String repeatPWD) {
        this.repeatPWD = repeatPWD;
        notifyPropertyChanged(BR.repeatPWD);
    }

    @Bindable
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
        notifyPropertyChanged(BR.authCode);
    }
    @Bindable
    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
    }
}
