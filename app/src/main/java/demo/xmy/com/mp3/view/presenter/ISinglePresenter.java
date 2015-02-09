/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.presenter;

import demo.xmy.com.mp3.model.SingleInfo;

/**
 * Created by xumengyang01 on 2015/1/21.
 */
public interface ISinglePresenter {
    /**
     * 单曲下载
     * @param info
     */
    public void download(SingleInfo info);

    public void unregistEventHandler();
}
