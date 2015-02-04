/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.net;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import demo.xmy.com.mp3.model.SingleInfo;

/**
 * Created by xumengyang01 on 2015/2/4.
 */
public class JSONUtils {

    private static ObjectMapper mMapper;

    static{
        mMapper = new ObjectMapper();
    }

    /**
     * 将json转换成实体类
     * @param json
     * @return
     */
    public static <T> T read(String json,TypeReference<T> tr) {
        try {
            return mMapper.readValue(new JSONObject(json).get("data").toString(),tr);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
