/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.core.controller.SingleController;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.model.SingleList;
import demo.xmy.com.mp3.view.fragment.ISingleFragment;

/**
 * Created by xumengyang01 on 2015/1/21.
 */
public class SinglePresenter implements ISinglePresenter{

    private ISingleFragment mView;
    private SingleController mController;

    public SinglePresenter(ISingleFragment view){
        this.mView = view;
        this.mController = new SingleController();
        EventBus.getDefault().register(this);
    }

    /**
     * 向服务器请求MP3数据
     */
    public void requestMP3InfosFromServer(){
        this.mController.requestSingleList();
    }

    /**
     * 服务器返回单曲列表
     * @param list
     */
    public void onEventMainThread(SingleList list){
        mView.onRequestMP3Infos(list.single);
    }

}
