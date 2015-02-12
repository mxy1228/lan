/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.model;

import android.os.Environment;
import android.webkit.URLUtil;

import java.io.File;

/**
 * Created by xumengyang01 on 2015/2/7.
 */
public class FileUtils {

    public static final String SONGS_DIR = Environment.getExternalStorageDirectory()+"/MP3s";

    private static FileUtils mFileUtils;

    private FileUtils(){

    }

    public static FileUtils getInstance(){
        if(mFileUtils == null){
            mFileUtils = new FileUtils();
        }
        return mFileUtils;
    }

    public void init(){
        File file = new File(SONGS_DIR);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 根据url判断歌曲是否已经被下载
     * @param url
     * @return
     */
    public static boolean isExist(String url){
        String filename = getFileName(url);
        File file = new File(getSongDir(filename));
        return file.exists();
    }

    /**
     * 根据url获取文件名
     * @param url
     * @return
     */
    public static String getFileName(String url){
        return URLUtil.guessFileName(url, null, null);
    }

    /**
     * 根据文件名获取存储地址
     * @param filename
     * @return
     */
    public static String getSongDir(String filename){
        return FileUtils.SONGS_DIR+"/"+filename;
    }

    /**
     * 获取临时文件
     * @param filename
     * @return
     */
    public static String getTmepFileDir(String filename){
        return FileUtils.SONGS_DIR + "/" + filename + "_tmp";
    };
}
