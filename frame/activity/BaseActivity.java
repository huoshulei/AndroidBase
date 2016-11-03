package com.example.icogn.mshb.frame.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;
import com.example.icogn.mshb.frame.connector.OnProgress;
import com.example.icogn.mshb.frame.viewModule.BaseViewModule;

public abstract class BaseActivity extends AppCompatActivity implements OnProgress {

    private BaseViewModule  viewModule;
    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = new BaseViewModule(this);
        init();
        configView();
        initData();
    }


    protected void init() {
        int layoutResId = getLayoutResId();
        if (layoutResId == 0) throw new NullPointerException("布局文件不能为空");
        binding = DataBindingUtil.setContentView(this, layoutResId);
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

    public final void setData(Object data) {
        binding.setVariable(BR.data, data);
    }

    private void setViewModule(Object data) {
        binding.setVariable(BR.module, data);
    }

    public final void setVariable(int id, Object data) {
        binding.setVariable(id, data);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onNavigate() {

    }

    public BaseViewModule getViewModule() {
        if (viewModule == null) viewModule = new BaseViewModule(this);
        return viewModule;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
