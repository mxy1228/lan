/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import java.io.File;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.core.controller.MP3PlayerController;
import demo.xmy.com.mp3.core.controller.SingleController;
import demo.xmy.com.mp3.core.model.FileUtils;
import demo.xmy.com.mp3.core.model.SingleIndex;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.model.SingleList;
import demo.xmy.com.mp3.view.activity.IMP3PlayerActivity;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public class MP3PlayerPresenter implements IMP3PlayerPresenter,MP3PlayerController.PlayerListener{

    private IMP3PlayerActivity mView;
    private MP3PlayerController mController;
    private SingleController mSingleController;
    private SingleList mList;
    private SingleInfo mInfo;

    public MP3PlayerPresenter(IMP3PlayerActivity view,SingleInfo info){
        this.mView = view;
        this.mController = MP3PlayerController.getInstance();
        this.mController.setPlayListener(this);
        this.mSingleController = new SingleController();
        this.mList = mSingleController.getLocalSingleListCache();
        this.mInfo = info;
    }

    @Override
    public void play(String url) {
        this.mController.play(url);
    }

    @Override
    public void registEventBus(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregistEventBus(Object object) {
        EventBus.getDefault().unregister(object);
    }

    @Override
    public void checkCurrentPlayState(String url) {
        if(mController.getCurrentPlayUrl().equals(url)){
            if(mController.isPlaying()){
                mView.showPlayBtnText(R.string.pause);
            }else{
                mView.showPlayBtnText(R.string.play);
            }
        }
    }

    @Override
    public void playPre() {
        if(mList != null && mInfo != null){
            for(SingleInfo info : mList.single){
                if(info.id == mInfo.id){
                    SingleInfo targetInfo = mController.getSingle(SingleIndex.PRE,mList.single.indexOf(info));
                    if(targetInfo != null){
                        //先暂停
                        mController.play(mInfo.url);
                        mView.restartPlayerActivity(targetInfo);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void playNext() {
        if(mList != null && mInfo != null){
            for(SingleInfo info : mList.single){
                if(info.id == mInfo.id){
                    SingleInfo targetInfo = mController.getSingle(SingleIndex.NEXT,mList.single.indexOf(info));
                    if(targetInfo != null){
                        //先暂停
                        mController.play(mInfo.url);
                        mView.restartPlayerActivity(targetInfo);
                    }
                    break;
                }
            }
        }
    }


    @Override
    public void play() {
        mView.showPlayBtnText(R.string.pause);
    }

    @Override
    public void pause() {
        mView.showPlayBtnText(R.string.play);
    }

    @Override
    public void duration(int duration) {
        mView.onUpdateTotalDuration(duration);
    }


    @Override
    public void startPrepare() {
        mView.showWaitingDialog();
    }

    @Override
    public void prepareFinish() {
        mView.dissmisWaitingDialog();
    }
}
