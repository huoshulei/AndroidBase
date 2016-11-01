package com.example.icogn.mshb.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.icogn.mshb.R;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/11/1 9:29
 * 修改人:    ICOGN
 * 修改时间:  2016/11/1 9:29
 * 备注:
 * 版本:
 */

class BaseViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    private BaseViewHolder(View loadView) {
        super(loadView);
        binding = null;
    }

    private BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * @param view 根据view创建item布局
     * @return
     */
    static BaseViewHolder onCreate(View view) {
        return new BaseViewHolder(view);
    }

    /**
     * @param parent
     * @param layoutId
     * @return 创建默认布局
     */
    static BaseViewHolder onCreate(ViewGroup parent, int layoutId) {
        return new BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layoutId, parent, false));
    }

    /**
     * @param parent
     * @return 创建加载布局
     */
    static BaseViewHolder onCreateLoadView(ViewGroup parent) {
        return new BaseViewHolder((LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loading_view, parent, false)));
    }

    /**
     * @return 获取item对象
     */
    public View getItemView() {
        return itemView;
    }

    /**
     * @return 获取data bind对象
     */
    public ViewDataBinding getBinding() {
        return binding;
    }

    /**
     * @param id  variableId
     * @param t   variable
     * @param <T> 泛型
     * @return
     */
    public <T> BaseViewHolder setVariable(int id, T t) {
        binding.setVariable(id, t);
        binding.executePendingBindings();
        return this;
    }

    /**
     * @param t   variable
     * @param <T> 泛型
     * @return 添加标识为module. 的数据
     */
    public <T> BaseViewHolder setModule(T t) {
        binding.setVariable(BR.module, t);
        return this;
    }

    /**
     * @param t   variable
     * @param <T> 泛型
     * @return 添加标识为data.的数据
     */
    public <T> BaseViewHolder setData(T t) {
        binding.setVariable(BR.data, t);
        binding.executePendingBindings();
        return this;
    }
}
