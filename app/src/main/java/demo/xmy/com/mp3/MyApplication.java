/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3;

import android.app.Application;
import android.content.Context;

import demo.xmy.com.mp3.core.model.FileUtils;
import demo.xmy.com.mp3.core.model.SPUtils;

/**
 * Created by xumengyang01 on 2015/1/19.
 */
public class MyApplication extends Application{

    private static Context mApplicationCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.getInstance().init(getApplicationContext());
        FileUtils.getInstance().init();
        this.mApplicationCtx = getApplicationContext();
    }

    public static Context getMyApplicationContext(){
        return mApplicationCtx;
    }
}
