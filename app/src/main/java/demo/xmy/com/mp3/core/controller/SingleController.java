/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.controller;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.MyApplication;
import demo.xmy.com.mp3.core.model.FileUtils;
import demo.xmy.com.mp3.core.model.SPUtils;
import demo.xmy.com.mp3.core.net.HttpURLContants;
import demo.xmy.com.mp3.core.net.JSONUtils;
import demo.xmy.com.mp3.core.net.MyAsyncResponseHandler;
import demo.xmy.com.mp3.core.net.MyHttpClient;
import demo.xmy.com.mp3.model.DownloadProgressEvent;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.model.SingleList;
import demo.xmy.com.mp3.view.adapter.SingleAdapter;

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
     * 从本地SP中查询单曲列表缓存
     */
    public void getLocalSingleListCache(){
        String content = SPUtils.getSingleList();
        if(content != null){
            postSingleList(content);
        }
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
        File file = new File(FileUtils.getSongDir(fileName));
        MyHttpClient client = new MyHttpClient();
        try{
            client.get(url,new FileAsyncHttpResponseHandler(file) {
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                    Log.e("file download",throwable.getLocalizedMessage());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, File file) {
                    Log.d("file download","download success and file = "+file.getAbsolutePath());
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
