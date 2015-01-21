/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.xmy.com.mp3.model.MP3Info;
import demo.xmy.com.mp3.view.fragment.IMP3Fragment;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class MP3Presenter implements IMP3Presenter{

    private IMP3Fragment mView;

    public MP3Presenter(IMP3Fragment view){
        this.mView = view;
    }

    /**
     * 向服务器请求MP3数据
     */
    public void requestMP3InfosFromServer(){
        //TEST
        List<MP3Info> data = new ArrayList<MP3Info>();
        for(int i=0;i<10;i++){
            MP3Info info = new MP3Info();
            info.name = "光辉岁月";
            info.album = "真的见证";
            data.add(info);
        }
        this.mView.onRequestMP3Infos(data);
    }
}
