/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.view.activity.IMainActivity;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class MainPresenter implements IMainPresenter{

    private IMainActivity mView;

    public MainPresenter(IMainActivity view){
        this.mView = view;
    }


    public void switchRadioBtn(int btnId){
        switch (btnId){
            case R.id.main_mp3_rbtn:
                this.mView.setupMP3Fragment();
                break;
            case R.id.main_video_rbtn:

                break;
            case R.id.main_more_rbtn:

                break;
            case R.id.main_pic_rbtn:

                break;
        }
    }
}
