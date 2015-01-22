/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.xmy.com.mp3.model.AlbumInfo;
import demo.xmy.com.mp3.view.fragment.IAlbumFragment;

/**
 * Created by xumengyang01 on 2015/1/22.
 */
public class AlbumPresenter implements IAlbumPresenter{

    private IAlbumFragment mView;

    public AlbumPresenter(IAlbumFragment view){
        this.mView = view;
    }

    @Override
    public void requestAlbumData() {
        List<AlbumInfo> data = new ArrayList<AlbumInfo>();
        for (int i=0;i<10;i++){
            AlbumInfo info = new AlbumInfo();
            info.name = "真的见证";
            info.year = "1973";
            data.add(info);
        }
        this.mView.onLoadAlbumData(data);
    }
}
