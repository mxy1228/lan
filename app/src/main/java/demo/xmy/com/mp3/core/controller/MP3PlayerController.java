/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.controller;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.core.model.SingleIndex;
import demo.xmy.com.mp3.model.MP3PlayingEvent;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.model.SingleList;

/**
 * Created by xumengyang01 on 2015/2/10.
 */
public class MP3PlayerController {

    private static MP3PlayerController mInstance;

    //MediaPlayer是否已经被设置数据
    private boolean isPlayerHadData;
    private MediaPlayer mPlayer;
    private Timer mTimer;
    private PlayerListener mListener;
    private SingleList mSingleList;

    //当前播放的url
    private static String mCurrentPlayUrl;
    private int mTotalDuration;

    public static MP3PlayerController getInstance(){
        if(mInstance == null){
            mInstance = new MP3PlayerController();
        }
        return mInstance;
    }

    private MP3PlayerController(){
        this.mPlayer = new MediaPlayer();
        this.mSingleList = new SingleController().getLocalSingleListCache();
    }

    /**
     * 判断传进来的url和当前播放的url是否一致
     * @param url
     * @return
     */
    public boolean isSameUrl(String url){
        if(!TextUtils.isEmpty(mCurrentPlayUrl)){
            return mCurrentPlayUrl.equals(url);
        }
        return false;
    }

    /**
     * 获取当前播放url
     * @return
     */
    public String getCurrentPlayUrl(){
        return TextUtils.isEmpty(this.mCurrentPlayUrl) ? "" : this.mCurrentPlayUrl;
    }

    /**
     * 获取当前Player状态
     * @return
     */
    public boolean isPlaying(){
        return mPlayer.isPlaying();
    }

    public void play(final String url) {
        //如果url不一样则重置状态
        if(!isSameUrl(url)){
            mPlayer.stop();
            mPlayer.reset();
            isPlayerHadData = false;
            mCurrentPlayUrl = null;
            mTotalDuration = 0;
        }
        if(isPlayerHadData){
            if(mPlayer.isPlaying()){
                mPlayer.pause();
                if(mListener != null){
                    mListener.pause();
                }
                return;
            }else{
                mPlayer.start();
                if(mListener != null){
                    mListener.play();
                }
                return;
            }
        }
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            Log.d("play url", "url = " + url);
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
                mTimer = new Timer();
                mTimer.schedule(new PlayerProgressTask(),0,1000);
                mPlayer.start();
                if(mListener != null){
                    mListener.play();
                    mTotalDuration = mPlayer.getDuration();
                }
                isPlayerHadData = true;
                mCurrentPlayUrl = url;
                if(mListener != null){
                    mListener.prepareFinish();
                }
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mCurrentPlayUrl = null;
                mPlayer.reset();
                isPlayerHadData = false;
                mTimer.cancel();
            }
        });
        try{
            mPlayer.prepareAsync();
            if(mListener != null){
                mListener.startPrepare();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据下标获取单曲信息
     */
    public SingleInfo getSingle(SingleIndex index,int currentIndex){
        if( mSingleList != null){
            if(currentIndex != 0 && index.getValue() == SingleIndex.PRE.getValue()){
                return mSingleList.single.get(currentIndex - 1);
            }else if(currentIndex != mSingleList.single.size() - 1 && index.getValue() == SingleIndex.NEXT.getValue()){
                return mSingleList.single.get(currentIndex + 1);
            }
        }
        return null;
    }

    public interface PlayerListener{
        /**
         * 开始播放
         */
        public void play();

        /**
         * 暂停
         */
        public void pause();

        /**
         * 时长
         * @param duration
         */
        public void duration(int duration);

        /**
         * 开始准备
         */
        public void startPrepare();

        /**
         * 准备结束
         */
        public void prepareFinish();
    }

    public void setPlayListener(PlayerListener listener){
        this.mListener = listener;
    }

    private class PlayerProgressTask extends TimerTask {

        @Override
        public void run() {
            MP3PlayingEvent e = new MP3PlayingEvent();
            e.playProgress = mTotalDuration == 0 ?  0 : (mPlayer.getCurrentPosition() * 1.0 / mTotalDuration) * 100;
            e.playUrl = mCurrentPlayUrl;
            Log.d("play progress","play progress = "+e.playProgress);
            EventBus.getDefault().post(e);
        }
    }
}
