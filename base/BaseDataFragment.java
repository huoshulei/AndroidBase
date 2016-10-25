package com.example.icogn.mshb.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

/**
 * A simple {@link BaseFragment} subclass.
 */
public abstract class BaseDataFragment extends BaseFragment {

    private ViewDataBinding binding;

    @Override
    protected View init(LayoutInflater inflater, ViewGroup container) {
        int layoutResId = getLayoutResId();
        if (layoutResId == 0) throw new NullPointerException("布局文件不能为空");
        View view = inflater.inflate(layoutResId, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    public final void setData(Object data) {
        binding.setVariable(BR.data, data);
    }

    public final void setData(int id, Object data) {
        binding.setVariable(id, data);
    }
}
