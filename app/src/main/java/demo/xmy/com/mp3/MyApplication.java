/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3;

import android.app.Application;

import demo.xmy.com.mp3.core.model.SPUtils;

/**
 * Created by xumengyang01 on 2015/1/19.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.getInstance().init(getApplicationContext());
    }
}
