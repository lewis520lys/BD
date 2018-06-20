package com.beidou.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class BaseAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    private final ConVert conVert;

    public BaseAdapter(int layoutResId, @Nullable List data, ConVert<T> conVert) {

        super(layoutResId, data);
        this.conVert =conVert;

    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        conVert.convert(helper,item);
    }
    public interface ConVert<T>{
        void convert(BaseViewHolder helper, T t);
    }
}
