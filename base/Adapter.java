package com.example.icogn.mshb.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.icogn.mshb.R;
import com.example.icogn.mshb.utils.logger.Logger;

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
    private final int     LOAD_VIEW = 1;
    private       boolean loading   = false;//是否开启加载更多功能
    private View       loadView; //加载更多布局
    private boolean    mLoadingMoreEnable; //是否加载中
    private OnLoadMore loadMore;

    public <T> Adapter(int layoutId, T view) {
        this.layoutId = layoutId;
        this.view = view;
        loading = false;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOAD_VIEW) {
            Holder loadView = onCreateLoadView(parent);
            this.loadView = loadView.itemView;
            return loadView;
        }
        return onCreate(parent, layoutId);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case LOAD_VIEW:
                LoadMore();
                break;
            default:
                holder.setData(mData.get(position)).setActivity(view);
        }
    }

    /**
     * @param loadMore 加载更多回调接口
     */
    public void setLoadMore(OnLoadMore loadMore) {
        this.loadMore = loadMore;
        openLoading();
    }

    private void LoadMore() {
        if (loading && mLoadingMoreEnable) {
            mLoadingMoreEnable = false;
            if (loadMore == null) return;
            loadMore.onLoadMore();
        }
    }

    /**
     * 打开加载更多
     */
    private void openLoading() {
        this.loading = true;
    }

    /**
     * 关闭加载更多
     */
    public void closeLoading() {
        loading = false;
        hideLoadView();
    }

    /**
     * 显示加载更多view
     */
    private void showLoadView() {
        if (loadView != null) loadView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载更多view
     */
    public void hideLoadView() {
        mLoadingMoreEnable = false;
        if (loadView != null) loadView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + (loading ? 1 : 0);
    }

    public <T> void setData(List<T> data) {
        if (mData == null) mData = new ArrayList<T>();
        mLoadingMoreEnable = true;
        showLoadView();
        mData = data;
        notifyDataSetChanged();
    }

    public <T> void addData(List<T> data) {
        if (mData == null) mData = new ArrayList<T>();
        mLoadingMoreEnable = true;
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public <T> void addData(T data) {
        if (mData == null) mData = new ArrayList<T>();
        mLoadingMoreEnable = true;
        mData.add(data);
        notifyDataSetChanged();
    }

    public <T> void addData(List<T> data, int position) {
        mLoadingMoreEnable = true;
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public <T> void addData(T data, int position) {
        mLoadingMoreEnable = true;
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mData != null) mData.clear();
        mData = null;
        notifyDataSetChanged();
    }

    public <T> void remove(T data) {
        if (mData != null) mData.remove(data);
        notifyDataSetChanged();
    }

    public List getData() {
        return mData;
    }

    private Holder onCreate(ViewGroup parent, int layoutId) {
        return new Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layoutId, parent, false));
    }

    private Holder onCreateLoadView(ViewGroup parent) {
        return new Holder((LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_view, parent, false)));
    }

    @Override
    public int getItemViewType(int position) {
        if (loading && position == getItemCount() - 1) return LOAD_VIEW;
        return super.getItemViewType(position);
    }

    /**
     * default view
     */
    class Holder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        Holder(View loadView) {
            super(loadView);
            binding = null;
        }

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
