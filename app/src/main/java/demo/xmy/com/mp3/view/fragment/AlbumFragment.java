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
import demo.xmy.com.mp3.model.AlbumInfo;
import demo.xmy.com.mp3.view.BaseFragment;
import demo.xmy.com.mp3.view.adapter.AlbumAdapter;
import demo.xmy.com.mp3.view.presenter.AlbumPresenter;

/**
 * 专辑
 * Created by xumengyang01 on 2015/1/21.
 */
public class AlbumFragment extends BaseFragment implements IAlbumFragment{

    private ListView mLV;
    private LinearLayout mContainer;

    private AlbumAdapter mAdapter;
    private AlbumPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.album_layout,null);
        initView(contentView);
        initEvent();
        initData();
        return contentView;
    }

    @Override
    protected void initView(View view) {
        this.mLV = (ListView)view.findViewById(R.id.album_lv);
        this.mContainer = (LinearLayout)view.findViewById(R.id.container);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        this.mPresenter = new AlbumPresenter(this);
        this.mPresenter.requestAlbumData();
    }

    @Override
    public void onLoadAlbumData(List<AlbumInfo> data) {
        if(mAdapter == null){
            mAdapter = new AlbumAdapter(getActivity(),data);
            mLV.setAdapter(mAdapter);
        }else{
            //TODO
        }

    }
}
