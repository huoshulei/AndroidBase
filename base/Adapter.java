package com.example.icogn.mshb.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/25 11:13
 * 修改人:    ICOGN
 * 修改时间:  2016/10/25 11:13
 * 备注:
 * 版本:
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private int    layoutId;
    private List   mData;
    private Object view;

    public <T> Adapter(int layoutId, T view) {
        this.layoutId = layoutId;
        this.view = view;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreate(parent, layoutId);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setData(mData.get(position)).setActivity(view);
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public <T> void setData(List<T> data) {
        if (mData == null) mData = new ArrayList<T>();
        mData = data;
        notifyDataSetChanged();
    }

    public <T> void addData(List<T> data) {
        if (mData == null) mData = new ArrayList<T>();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public <T> void addData(T data) {
        if (mData == null) mData = new ArrayList<T>();
        mData.add(data);
        notifyDataSetChanged();
    }

    public <T> void addData(List<T> data, int position) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public <T> void addData(T data, int position) {
        mData.add(position, data);
        notifyDataSetChanged();
    }

    private Holder onCreate(ViewGroup parent, int layoutId) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layoutId, parent, false));
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        Holder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ViewDataBinding getBinding() {
            return binding;
        }

        <T> Holder setData(int id, T data) {
            binding.setVariable(id, data);
            binding.executePendingBindings();
            return this;
        }

        <T> Holder setActivity(T view) {
            binding.setVariable(BR.module, view);
            return this;
        }

        <T> Holder setData(T data) {
            binding.setVariable(BR.data, data);
            binding.executePendingBindings();
            return this;
        }
    }
}
