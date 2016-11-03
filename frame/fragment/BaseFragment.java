package com.example.icogn.mshb.frame.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.icogn.mshb.frame.connector.OnProgress;
import com.example.icogn.mshb.frame.viewModule.BaseViewModule;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements OnProgress {
    private   View                view;
    protected BaseViewModule viewModule;
    private ViewDataBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule=new BaseViewModule(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = init(inflater, container);
            configView(view);
            initData();
            onFirstNet();
        }
        return view;
    }



    protected View init(LayoutInflater inflater, ViewGroup container) {
        int layoutResId = getLayoutResId();
        if (layoutResId == 0) throw new NullPointerException("布局文件不能为空");
        View view = inflater.inflate(layoutResId, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    protected abstract
    @LayoutRes
    int getLayoutResId();

    protected void initData() {

    }

    /**
     * 初始化布局组件
     */
    protected void configView(View view) {

    }

    /**
     * 首次加载网络数据
     */
    protected void onFirstNet() {
    }
    public final void setData(Object data) {
        binding.setVariable(BR.data, data);
    }

    private  void setModule(Object data) {
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
    public void onNavigate() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onError(String message) {

    }

    public BaseViewModule getViewModule() {
        if (viewModule==null)viewModule=new BaseViewModule(this);
        return viewModule;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
