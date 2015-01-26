/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.view.BaseFragment;
import demo.xmy.com.mp3.view.presenter.MP3Presenter;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class MP3Fragment extends BaseFragment implements IMP3Fragment,RadioGroup.OnCheckedChangeListener{

    private ViewPager mVP;
    private RadioGroup mRG;
    private RadioButton mSingleRBtn;
    private RadioButton mAlbumRBtn;

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
        this.mVP = (ViewPager)view.findViewById(R.id.mp3_layout_vp);
        this.mVP.setAdapter(new MyPagerAdapter(getFragmentManager()));
        this.mRG = (RadioGroup)view.findViewById(R.id.mp3_layout_rg);
        this.mSingleRBtn = (RadioButton)view.findViewById(R.id.mp3_layout_single_rbtn);
        this.mAlbumRBtn = (RadioButton)view.findViewById(R.id.mp3_layout_album_rbtn);
    }

    @Override
    protected void initEvent() {
        this.mRG.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        this.mPresenter = new MP3Presenter(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            //单曲
            case R.id.mp3_layout_single_rbtn:
//                mSingleRBtn.setSelected(true);
//                mAlbumRBtn.setSelected(false);
                mVP.setCurrentItem(0);
                break;
            //专辑
            case R.id.mp3_layout_album_rbtn:
//                mAlbumRBtn.setSelected(true);
//                mSingleRBtn.setSelected(false);
                mVP.setCurrentItem(1);
                break;

        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

        private List<BaseFragment> fragments;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<BaseFragment>();
            fragments.add(new SingleFragment());
            fragments.add(new AlbumFragment());
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
