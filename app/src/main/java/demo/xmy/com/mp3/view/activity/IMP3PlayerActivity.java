/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.activity;

import demo.xmy.com.mp3.model.SingleInfo;

/**
 * Created by xumengyang01 on 2015/1/25.
 */
public interface IMP3PlayerActivity {

    /**
     * 更新整首歌的长度
     * @param duration
     */
    public void onUpdateTotalDuration(int duration);

    /**
     * 播放按钮文案显示
     * @param sreId
     */
    public void showPlayBtnText(int sreId);

    /**
     * 更新头文案
     * @param text
     */
    public void updateTitle(String text);

    public void resetProgressBar();

    public void updatePlayUrl(String url);

    public void restartPlayerActivity(SingleInfo info);

    public void showWaitingDialog();

    public void dissmisWaitingDialog();
}
