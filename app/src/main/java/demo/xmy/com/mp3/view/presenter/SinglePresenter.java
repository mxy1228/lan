/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.view.fragment.ISingleFragment;

/**
 * Created by xumengyang01 on 2015/1/21.
 */
public class SinglePresenter implements ISinglePresenter{

    private ISingleFragment mView;

    public SinglePresenter(ISingleFragment view){
        this.mView = view;
    }

    /**
     * 向服务器请求MP3数据
     */
    public void requestMP3InfosFromServer(){
        //TEST
        List<SingleInfo> data = new ArrayList<SingleInfo>();
        for(int i=0;i<10;i++){
            SingleInfo info = new SingleInfo();
            info.name = "光辉岁月";
            info.album = "真的见证";
            data.add(info);
        }
        this.mView.onRequestMP3Infos(data);
    }
}
