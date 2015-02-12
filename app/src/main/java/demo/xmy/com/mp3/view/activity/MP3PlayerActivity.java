/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.core.model.FileUtils;
import demo.xmy.com.mp3.model.MP3PlayingEvent;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.view.TitleActivity;
import demo.xmy.com.mp3.view.presenter.MP3PlayerPresenter;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public class MP3PlayerActivity extends TitleActivity implements View.OnClickListener,IMP3PlayerActivity{

    private static final String INFO = "info";

    private Button mPreBtn;
    private Button mPlayBtn;
    private Button mNextBtn;
    private ProgressBar mPB;
    private ProgressDialog mWaitingDialog;

    private MP3PlayerPresenter mPresenter;
    private SingleInfo mInfo;

    public static Intent createIntent(Context ctx,SingleInfo info){
        Intent intent = new Intent();
        intent.putExtra(INFO,info);
        intent.setClass(ctx,MP3PlayerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp3_player_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mInfo != null){
            setTitleTxt(FileUtils.isExist(mInfo.url) ? getString(R.string.local_file,mInfo.name) : mInfo.name);
            this.mInfo.url = FileUtils.isExist(mInfo.url) ? FileUtils.getSongDir(FileUtils.getFileName(mInfo.url)) : mInfo.url;
            this.mPresenter.checkCurrentPlayState(mInfo.url);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mInfo = (SingleInfo)intent.getSerializableExtra(INFO);
        if(mInfo != null){
            setTitleTxt(mInfo.name);
        }
        this.mPresenter = new MP3PlayerPresenter(this,mInfo);
    }

    @Override
    protected void initView() {
        this.mPreBtn = (Button)findViewById(R.id.mp3_player_pre_btn);
        this.mPlayBtn = (Button)findViewById(R.id.mp3_player_play_btn);
        this.mNextBtn = (Button)findViewById(R.id.mp3_player_next_btn);
        this.mPB = (ProgressBar)findViewById(R.id.mp3_player_pb);
        this.mWaitingDialog = new ProgressDialog(this);
    }

    @Override
    protected void initData() {
        SingleInfo info = (SingleInfo)getIntent().getSerializableExtra(INFO);
        if(info != null){
            this.mInfo = info;
            setTitleTxt(mInfo.name);
        }
        this.mPresenter = new MP3PlayerPresenter(this,mInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.unregistEventBus(this);
    }

    @Override
    protected void initEvent() {
        this.mPreBtn.setOnClickListener(this);
        this.mPlayBtn.setOnClickListener(this);
        this.mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.mp3_player_pre_btn:
                mPresenter.playPre();
                break;
            case R.id.mp3_player_play_btn:
                if(mInfo != null){
                    mPresenter.play(mInfo.url);
                }else{
                    //TODO
                }
                break;
            case R.id.mp3_player_next_btn:
                mPresenter.playNext();
                break;
        }
    }

    /**
     * 接收到播放器进度
     * @param e
     */
    public void onEventMainThread(MP3PlayingEvent e){
        if(e != null && !TextUtils.isEmpty(e.playUrl)){
            if(e.playUrl.equals(mInfo.url)){
                this.mPB.setProgress(e.playProgress.intValue());
            }
        }
    }

    @Override
    public void onUpdateTotalDuration(int duration) {
       this.mPB.setMax(duration);
    }

    @Override
    public void showPlayBtnText(int strId) {
        this.mPlayBtn.setText(strId);
    }

    @Override
    public void updateTitle(String text) {
        setTitleTxt(text);
    }

    @Override
    public void resetProgressBar() {
        this.mPB.setProgress(0);
    }

    @Override
    public void updatePlayUrl(String url) {
        this.mInfo.url = url;
    }

    @Override
    public void restartPlayerActivity(SingleInfo info) {
        Intent intent = createIntent(this,info);
        startActivity(intent);
    }

    @Override
    public void showWaitingDialog() {
        if(!mWaitingDialog.isShowing()){
            this.mWaitingDialog.show();
        }
    }

    @Override
    public void dissmisWaitingDialog() {
        if(mWaitingDialog.isShowing()){
            mWaitingDialog.dismiss();
        }
    }
}
