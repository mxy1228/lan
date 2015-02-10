/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.core.controller.MP3PlayerController;
import demo.xmy.com.mp3.view.activity.IMP3PlayerActivity;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public class MP3PlayerPresenter implements IMP3PlayerPresenter,MP3PlayerController.PlayerListener{

    private IMP3PlayerActivity mView;
    private MP3PlayerController mController;

    public MP3PlayerPresenter(IMP3PlayerActivity view){
        this.mView = view;
        this.mController = MP3PlayerController.getInstance();
        this.mController.setPlayListener(this);
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
}
