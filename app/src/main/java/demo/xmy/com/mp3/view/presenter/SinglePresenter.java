/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.core.controller.SingleController;
import demo.xmy.com.mp3.model.DownloadProgressEvent;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.model.SingleList;
import demo.xmy.com.mp3.view.fragment.ISingleFragment;

/**
 * Created by xumengyang01 on 2015/1/21.
 */
public class SinglePresenter implements ISinglePresenter{

    private ISingleFragment mView;
    private SingleController mController;

    private SingleList mList;

    public SinglePresenter(ISingleFragment view){
        this.mView = view;
        this.mController = new SingleController();
        EventBus.getDefault().register(this);
    }

    /**
     * 向服务器请求MP3数据
     */
    public void requestSingleListFromServer(){
        this.mController.requestSingleList();
    }

    /**
     * 查询单曲列表缓存
     */
    public void getSingleListCache(){
        this.mController.getLocalSingleListCacheAndPost();
    }

    /**
     * 服务器返回单曲列表
     * @param list
     */
    public void onEventMainThread(SingleList list){
        mList = list;
        mView.onRequestMP3Infos(mList.single);
    }

    @Override
    public void download(SingleInfo info) {
        mController.downloadSingle(info.url);
    }

    @Override
    public void unregistEventHandler() {
        EventBus.getDefault().unregister(this);
    }

    /**
     * 下载进度
     * @param e
     */
    public void onEventMainThread(DownloadProgressEvent e){
        Log.d("download progress","progress = "+e.progress);
        for(SingleInfo info : mList.single){
            if(info.url.equals(e.url)){
                info.downloadprogress = e.progress;
            }
        }
        this.mView.showDownloadProgress(e.progress,e.url);
    }


}
