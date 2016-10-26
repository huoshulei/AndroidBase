package com.example.icogn.mshb.register;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.BaseDataFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseDataFragment {


    private RegisterBean bean;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegisterActivity activity = (RegisterActivity) getActivity();
        activity.setToolbar("注册");
        bean = activity.getBean();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.register_fragment;
    }


    @Override
    protected void initData() {
        setData(bean);
    }

    /**
     * 显示登陆状态
     */
    @Override
    public void showProgress() {
        bean.setProgress(true);
    }

    /**
     * 关闭加载动画
     */
    @Override
    public void dismissProgress() {
        bean.setProgress(false);
    }

}
