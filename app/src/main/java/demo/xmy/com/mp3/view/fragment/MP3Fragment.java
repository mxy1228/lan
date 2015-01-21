/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.model.MP3Info;
import demo.xmy.com.mp3.view.BaseFragment;
import demo.xmy.com.mp3.view.adapter.MP3Adapter;
import demo.xmy.com.mp3.view.presenter.MP3Presenter;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class MP3Fragment extends BaseFragment implements IMP3Fragment{

    private ListView mLV;

    private MP3Adapter mAdapter;
    private MP3Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.mp3_layout,null);
        initView(contentView);
        initEvent();
        initData();
        return contentView;
    }

    @Override
    protected void initView(View view) {
        this.mLV = (ListView)view.findViewById(R.id.mp3_layout_lv);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        this.mPresenter = new MP3Presenter(this);
        this.mPresenter.requestMP3InfosFromServer();
    }

    @Override
    public void onRequestMP3Infos(List<MP3Info> data) {
        if(mAdapter == null){
            mAdapter = new MP3Adapter(getActivity(),data);
            mLV.setAdapter(mAdapter);
        }else{
            this.mAdapter.resetData(data);
        }
    }
}
