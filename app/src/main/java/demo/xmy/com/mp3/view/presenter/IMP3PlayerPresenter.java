/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import android.content.Context;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public interface IMP3PlayerPresenter {

    /**
     * 开始播放MP3
     * @param url
     */
    public void play(String url);

    /**
     * 注册消息中心
     */
    public void registEventBus(Object object);

    /**
     * 反注册消息中心
     * @param clazz
     */
    public void unregistEventBus(Object object);
}
