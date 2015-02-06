/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.view.BaseFragment;
import demo.xmy.com.mp3.view.adapter.SingleAdapter;
import demo.xmy.com.mp3.view.presenter.SinglePresenter;

/**
 * 单曲
 * Created by xumengyang01 on 2015/1/21.
 */
public class SingleFragment extends BaseFragment implements ISingleFragment{

    private ListView mLV;
    private LinearLayout mHeaderView;

    private SingleAdapter mAdapter;
    private SinglePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.single_layout,null);
        initView(contentView);
        initEvent();
        initData();
        return contentView;
    }

    @Override
    protected void initView(View view) {
        this.mLV = (ListView)view.findViewById(R.id.single_layout_lv);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        this.mPresenter = new SinglePresenter(this);
        this.mPresenter.getSingleListCache();
        this.mPresenter.requestSingleListFromServer();
    }

    @Override
    public void onRequestMP3Infos(List<SingleInfo> data) {
        if(mAdapter == null){
            mAdapter = new SingleAdapter(getActivity(),data);
            mLV.setAdapter(mAdapter);
        }else{
            this.mAdapter.resetData(data);
        }
    }
}
