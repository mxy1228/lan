/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.model;

import java.io.Serializable;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class SingleInfo implements Serializable{

    public int id;
    public String name;
    public String url;
    public long duration;
    public String album;
    public String size;
    public int islocal;//是否存在本地
    public String file;
    public String lyric;//歌词地址
    public double downloadprogress;//下载进度
}
