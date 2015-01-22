/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.view.fragment.IMP3Fragment;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class MP3Presenter implements IMP3Presenter{

    private IMP3Fragment mView;

    public MP3Presenter(IMP3Fragment view){
        this.mView = view;
    }

}
