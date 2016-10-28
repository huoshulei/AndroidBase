package com.example.icogn.mshb.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.android.databinding.library.baseAdapters.BR;


public abstract class BaseDataActivity extends BaseActivity {


    private ViewDataBinding binding;

    protected void init0() {
        int layoutResId = getLayoutResId();
        if (layoutResId == 0) throw new NullPointerException("布局文件不能为空");
        binding = DataBindingUtil.setContentView(this, layoutResId);
        setModule(this);
    }

    public final void setData(Object data) {
        binding.setVariable(BR.data, data);
    }

    private  void setModule(Object data) {
        binding.setVariable(BR.module, data);
    }


    public final void setData(int id, Object data) {
        binding.setVariable(id, data);
    }
}
