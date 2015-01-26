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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.model.MP3PlayingEvent;
import demo.xmy.com.mp3.view.TitleActivity;
import demo.xmy.com.mp3.view.presenter.MP3PlayerPresenter;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public class MP3PlayerActivity extends TitleActivity implements View.OnClickListener,IMP3PlayerActivity{

    private Button mPreBtn;
    private Button mPlayBtn;
    private Button mNextBtn;
    private ProgressBar mPB;

    private MP3PlayerPresenter mPresenter;

//    private MediaPlayer mPlayer;
    public static Intent createIntent(Context ctx){
        Intent intent = new Intent();
        intent.setClass(ctx,MP3PlayerActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp3_player_layout);
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
                mPresenter.play("http://7u2omh.com1.z0.glb.clouddn.com/01.AMANI.mp3");
//                final  MediaPlayer player = new MediaPlayer();
//                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try {
//                    player.setDataSource("http://7u2omh.com1.z0.glb.clouddn.com/01.AMANI.mp3");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
//
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            player.start();
//                        }
//                    });
//                    player.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
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
        if(e != null){
            this.mPB.setProgress(e.current);
        }
    }

    @Override
    public void onUpdateTotalDuration(int duration) {
       this.mPB.setMax(duration);
    }
}
