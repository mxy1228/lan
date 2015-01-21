/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.view.TitleActivity;
import demo.xmy.com.mp3.view.fragment.MP3Fragment;
import demo.xmy.com.mp3.view.presenter.MainPresenter;


public class MainActivity extends TitleActivity implements RadioGroup.OnCheckedChangeListener,IMainActivity{

    private FrameLayout mContentContainerLL;
    private RadioGroup mRG;
    private RadioButton mMP3RBtn;
    private RadioButton mPicRBtn;
    private RadioButton mMoreRBtn;
    private RadioButton mVideoBtn;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        this.mContentContainerLL = (FrameLayout)findViewById(R.id.main_content_container);
        this.mRG = (RadioGroup)findViewById(R.id.main_rg);
        this.mMP3RBtn = (RadioButton)findViewById(R.id.main_mp3_rbtn);
        this.mPicRBtn = (RadioButton)findViewById(R.id.main_pic_rbtn);
        this.mMoreRBtn = (RadioButton)findViewById(R.id.main_more_rbtn);
        this.mVideoBtn = (RadioButton)findViewById(R.id.main_video_rbtn);
    }

    @Override
    protected void initData() {
        this.mPresenter = new MainPresenter(this);
        this.mPresenter.switchRadioBtn(R.id.main_mp3_rbtn);
    }

    @Override
    protected void initEvent() {
        //为RadioGroup注册切换时间监听器
        this.mRG.setOnCheckedChangeListener(this);
    }

    /**
     * 当RadioBtn被选择切换的时候会回调这个方法
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            //MP3
            case R.id.main_mp3_rbtn:

                break;
            //图文
            case R.id.main_pic_rbtn:

                break;
            //更多
            case R.id.main_more_rbtn:

                break;
            //视频
            case R.id.main_video_rbtn:

                break;
        }
    }

    @Override
    public void setupMP3Fragment() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_content_container,new MP3Fragment());
        transaction.commit();
        setTitleTxt(R.string.mp3);
        this.mMP3RBtn.setSelected(true);
    }

    @Override
    public void setupPicFragment() {

    }

    @Override
    public void setupMoreFragment() {

    }

    @Override
    public void setupAlbumFragment() {

    }
}
