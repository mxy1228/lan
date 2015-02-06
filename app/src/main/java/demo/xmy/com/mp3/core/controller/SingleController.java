/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import de.greenrobot.event.EventBus;
import demo.xmy.com.mp3.core.model.SPUtils;
import demo.xmy.com.mp3.core.net.HttpURLContants;
import demo.xmy.com.mp3.core.net.JSONUtils;
import demo.xmy.com.mp3.core.net.MyAsyncResponseHandler;
import demo.xmy.com.mp3.core.net.MyHttpClient;
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
                SingleList list = convertJsonToSingleList(content);
                if(list != null){
                    SPUtils.getInstance().saveSingleList(content);
                    EventBus.getDefault().post(list);
                }
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
            SingleList list = convertJsonToSingleList(content);
            EventBus.getDefault().post(list);
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
