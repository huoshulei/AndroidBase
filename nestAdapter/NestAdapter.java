package com.example.icogn.mshb.shb.main.classify;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.icogn.mshb.App;
import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.Adapter;
import com.example.icogn.mshb.entity.Classify2;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/26 11:03
 * 修改人:    ICOGN
 * 修改时间:  2016/10/26 11:03
 * 备注:
 * 版本:
 */

public class NestAdapter extends RecyclerView.Adapter<NestAdapter.Holder> {
    private   int    layoutId;
    protected List   mData;
    protected Object view;

    public <T> NestAdapter(int layoutId, T view) {
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
        Adapter adapter = new Adapter(R.layout.classify_item_3, view);
        holder.recyclerView.setAdapter(adapter);
        adapter.setData(((Classify2) mData.get(position)).getData());
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

    public void clear() {
        if (mData != null) mData.clear();
        mData = null;
        notifyDataSetChanged();
    }

    public List getData() {
        return mData;
    }

    private Holder onCreate(ViewGroup parent, int layoutId) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layoutId, parent, false));
    }

    class Holder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        RecyclerView recyclerView;

        Holder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(App.application, 3));
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

