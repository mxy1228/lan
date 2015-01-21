/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import demo.xmy.com.mp3.R;

/**
 * Created by xumengyang01 on 2015/1/19.
 * 每个使用Title的Activity可继承该Activity
 * 开放了Title的相关接口
 */
public abstract class TitleActivity extends BaseActivity implements View.OnClickListener{

    private Button mLeftBtn;
    private Button mRightBtn;
    private TextView mTitleTV;
    private ViewGroup mContentVG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //这里负责做一些布局初始化的工作
    protected abstract void initView();

    //这里负责做数据初始化工作
    protected abstract void initData();

    //这里是事件初始化
    protected abstract void initEvent();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.base_layout);
        mContentVG = (ViewGroup)findViewById(R.id.base_layout_content_fl);
        mContentVG.addView(LayoutInflater.from(this).inflate(layoutResID,null));
        this.mLeftBtn = (Button)findViewById(R.id.title_left_btn);
        this.mTitleTV = (TextView)findViewById(R.id.title_tv);
        this.mRightBtn = (Button)findViewById(R.id.title_right_btn);
        this.mLeftBtn.setOnClickListener(this);
        this.mRightBtn.setOnClickListener(this);
        initView();
        initEvent();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left_btn:
                onLeftBtnClick();
                break;
            case R.id.title_right_btn:
                onRightBtnClick();
                break;
        }
    }

    /**
     * 左边Btn被点击
     */
    public void onLeftBtnClick(){

    }

    /**
     * 右边Btn被点击
     */
    public void onRightBtnClick(){

    }

    /**
     * 设置Title文案
     * @param txt
     */
    public void setTitleTxt(String txt){
        if(mTitleTV != null && !TextUtils.isEmpty(txt)){
            mTitleTV.setText(txt);
        }
    }

    /**
     * 设置Title文案
     * @param txtId
     */
    public void setTitleTxt(int txtId){
        setTitleTxt(getString(txtId));
    }
}
