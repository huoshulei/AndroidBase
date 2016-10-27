package com.example.icogn.mshb.shb.main.my.order;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.RatingBar;

import com.example.icogn.mshb.BR;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/27 15:34
 * 修改人:    ICOGN
 * 修改时间:  2016/10/27 15:34
 * 备注:
 * 版本:
 */

public class CommentBean extends BaseObservable {
    private float describe = 5;
    private float serve    = 5;
    private float speed    = 5;
    private boolean anonymity;
    private String  content;
    private int     contentNum=0;

    @Bindable
    public float getDescribe() {
        return describe;
    }

    public void setDescribe(float describe) {
        this.describe = describe;
        notifyPropertyChanged(BR.describe);
    }

    @Bindable
    public float getServe() {
        return serve;
    }

    public void setServe(float serve) {
        this.serve = serve;
        notifyPropertyChanged(BR.serve);
    }

    @Bindable
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);
    }

    @Bindable
    public boolean isAnonymity() {
        return anonymity;
    }

    public void setAnonymity(boolean anonymity) {
        this.anonymity = anonymity;
        notifyPropertyChanged(BR.anonymity);
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        setContentNum(content.length());
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public String getContentNum() {
        return contentNum + "/200";
    }

    public void setContentNum(int contentNum) {
        this.contentNum = contentNum;
        notifyPropertyChanged(BR.contentNum);
    }
}
