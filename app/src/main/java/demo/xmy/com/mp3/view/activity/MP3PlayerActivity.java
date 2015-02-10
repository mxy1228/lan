/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.activity;

import android.app.Activity;
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
import demo.xmy.com.mp3.view.TitleActivity;
import demo.xmy.com.mp3.view.presenter.MP3PlayerPresenter;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public class MP3PlayerActivity extends TitleActivity implements View.OnClickListener,IMP3PlayerActivity{

    private static final String PLAY_URL = "url";
    private static final String SONG_NAME = "name";

    private Button mPreBtn;
    private Button mPlayBtn;
    private Button mNextBtn;
    private ProgressBar mPB;

    private MP3PlayerPresenter mPresenter;
    private String mPlayURL;
    private String mSongName;

    public static Intent createIntent(Context ctx,String url,String name){
        Intent intent = new Intent();
        intent.putExtra(PLAY_URL,url);
        intent.putExtra(SONG_NAME,name);
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
        setTitleTxt(FileUtils.isExist(mPlayURL) ? getString(R.string.local_file,mSongName) : mSongName);
        this.mPlayURL = FileUtils.isExist(mPlayURL) ? FileUtils.getSongDir(FileUtils.getFileName(mPlayURL)) : mPlayURL;
        this.mPresenter.checkCurrentPlayState(mPlayURL);
    }

    @Override
    protected void initView() {
        this.mPreBtn = (Button)findViewById(R.id.mp3_player_pre_btn);
        this.mPlayBtn = (Button)findViewById(R.id.mp3_player_play_btn);
        this.mNextBtn = (Button)findViewById(R.id.mp3_player_next_btn);
        this.mPB = (ProgressBar)findViewById(R.id.mp3_player_pb);
    }

    @Override
    protected void initData() {
        this.mPresenter.registEventBus(this);
        this.mPlayURL = getIntent().getStringExtra(PLAY_URL);
        this.mSongName = getIntent().getStringExtra(SONG_NAME);
        setTitleTxt(mSongName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.unregistEventBus(this);
    }

    @Override
    protected void initEvent() {
        this.mPresenter = new MP3PlayerPresenter(this);
        this.mPreBtn.setOnClickListener(this);
        this.mPlayBtn.setOnClickListener(this);
        this.mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.mp3_player_pre_btn:

                break;
            case R.id.mp3_player_play_btn:
                if(!TextUtils.isEmpty(mPlayURL)){
                    mPresenter.play(mPlayURL);
                }else{
                    //TODO
                }
                break;
            case R.id.mp3_player_next_btn:

                break;
        }
    }

    /**
     * 接收到播放器进度
     * @param e
     */
    public void onEventMainThread(MP3PlayingEvent e){
        if(e != null && !TextUtils.isEmpty(e.playUrl)){
            if(e.playUrl.equals(mPlayURL)){
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
}
