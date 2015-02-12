/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.controller;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.core.model.FileUtils;
import demo.xmy.com.mp3.core.model.SPUtils;
import demo.xmy.com.mp3.core.net.HttpURLContants;
import demo.xmy.com.mp3.core.net.JSONUtils;
import demo.xmy.com.mp3.core.net.MyAsyncResponseHandler;
import demo.xmy.com.mp3.core.net.MyHttpClient;
import demo.xmy.com.mp3.model.DownloadProgressEvent;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.model.SingleList;

/**
 * Created by xumengyang01 on 2015/2/4.
 */
public class SingleController {

    /**
     * 向服务器请求单曲列表
     */
    public void requestSingleList(){

        new MyHttpClient().get(HttpURLContants.SINGLE_LIST_URL,null,new MyAsyncResponseHandler(){

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, String content) {
                postSingleList(content);
            }

            @Override
            public void onFailure(int statusCode, String content) {
                super.onFailure(statusCode, content);
            }
        });
    }

    /**
     * 从本地SP中查询单曲列表缓存并发送消息
     */
    public void getLocalSingleListCacheAndPost(){
        String content = SPUtils.getSingleList();
        if(content != null){
            postSingleList(content);
        }
    }

    /**
     * 从本地SP种查询单曲列表缓存
     * @return
     */
    public SingleList getLocalSingleListCache(){
        String content = SPUtils.getSingleList();
        if(content != null){
            return convertJsonToSingleList(content);
        }
        return null;
    }

    private void postSingleList(String json){
        SingleList list = convertJsonToSingleList(json);
        for(SingleInfo info : list.single){
            if(FileUtils.isExist(info.url)){
                info.downloadprogress = 100;
            }
        }
        EventBus.getDefault().post(list);
    }

    /**
     * 根据URL下载歌曲
     * @param url
     */
    public void downloadSingle(final String url){
        String fileName = FileUtils.getFileName(url);
        if(TextUtils.isEmpty(fileName)){
            Log.e("SingleController","fileName is null");
            return;
        }
        final File targetFile = new File(FileUtils.getSongDir(fileName));
        final File tmpFile = new File(FileUtils.getTmepFileDir(fileName));
        MyHttpClient client = new MyHttpClient();
        try{
            client.get(url,new FileAsyncHttpResponseHandler(tmpFile) {

                @Override
                public void onStart() {
                    super.onStart();
                    tmpFile.deleteOnExit();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                    Log.e("file download",throwable.getLocalizedMessage());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, File file) {
                    Log.d("file download","download success and file = "+file.getAbsolutePath());
                    file.renameTo(targetFile);
                }

                @Override
                public void onProgress(int bytesWritten, int totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    //发送下载进度
                    DownloadProgressEvent e = new DownloadProgressEvent();
                    e.progress = totalSize > 0 ? (bytesWritten * 1.0 / totalSize) * 100 : -1;
                    e.url = url;
                    EventBus.getDefault().post(e);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将Json转换成SingleList
     * @param content
     * @return
     */
    private SingleList convertJsonToSingleList(String content){
        return JSONUtils.read(content,new TypeReference<SingleList>() {
        });
    }

}
