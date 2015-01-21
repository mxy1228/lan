/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.fragment;

import java.util.List;

import demo.xmy.com.mp3.model.MP3Info;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public interface IMP3Fragment {
    public void onRequestMP3Infos(List<MP3Info> data);
}
