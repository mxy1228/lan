/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.net;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by xumengyang01 on 2015/2/4.
 */
public class MyAsyncResponseHandler extends AsyncHttpResponseHandler{

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if(responseBody != null){
            onSuccess(statusCode,new String(responseBody));
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if(responseBody != null){
            onFailure(statusCode,new String(responseBody));
        }
    }

    public void onSuccess(int statusCode,String content){

    }

    public void onFailure(int statusCode,String content){

    }

    @Override
    public void onProgress(int bytesWritten, int totalSize) {
        super.onProgress(bytesWritten, totalSize);
    }
}
