/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.model.MP3PlayingEvent;
import demo.xmy.com.mp3.view.activity.IMP3PlayerActivity;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public class MP3PlayerPresenter implements IMP3PlayerPresenter{

    private IMP3PlayerActivity mView;
    private MediaPlayer mPlayer;

    private PlayerProgressTask mTimerTask;
    private Timer mTimer;

    //MediaPlayer是否已经被设置数据
    private boolean isPlayerHadData;

    public MP3PlayerPresenter(IMP3PlayerActivity view){
        this.mView = view;
        this.mPlayer = new MediaPlayer();
        this.mTimerTask = new PlayerProgressTask();
    }

    @Override
    public void play(String url) {
        if(isPlayerHadData){
            if(mPlayer.isPlaying()){
                mPlayer.pause();
                mView.showPlayBtnText(R.string.play);
                return;
            }else{
                mPlayer.start();
                mView.showPlayBtnText(R.string.pause);
                return;
            }
        }
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(MediaPlayer mp) {
                if(mTimer != null){
                    mTimer.cancel();
                }
                mView.onUpdateTotalDuration(mPlayer.getDuration());
                mTimer = new Timer();
                mTimer.schedule(mTimerTask,0,1000);
                mPlayer.start();
                mView.showPlayBtnText(R.string.pause);
                isPlayerHadData = true;
            }
        });
        try {
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registEventBus(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregistEventBus(Object object) {
        EventBus.getDefault().unregister(object);
    }


    private class PlayerProgressTask extends TimerTask{

        @Override
        public void run() {
            MP3PlayingEvent e = new MP3PlayingEvent();
            e.current = mPlayer.getCurrentPosition();
            EventBus.getDefault().post(e);
        }
    }

}
