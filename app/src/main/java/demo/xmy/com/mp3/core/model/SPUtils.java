/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xumengyang01 on 2015/2/6.
 */
public class SPUtils {

    private static final String SINGLE_LIST = "single_list";

    private static SharedPreferences mSingleListSP;

    private static SPUtils mSPUtils;

    private SPUtils(){

    }

    public static SPUtils getInstance(){
        if(mSPUtils == null){
            mSPUtils = new SPUtils();
        }
        return mSPUtils;
    }

    public void init(Context ctx){
        mSingleListSP = ctx.getSharedPreferences(SPName.SINGLE_LIST.toString(),Context.MODE_PRIVATE);
    }


    /**
     * 保存单曲列表到SP
     * @param json
     */
    public static void saveSingleList(String json){
        SharedPreferences.Editor e = mSingleListSP.edit();
        e.putString(SINGLE_LIST,json);
        e.commit();
    }

    /**
     * 获取单曲列表缓存
     * @return
     */
    public static String getSingleList(){
       return mSingleListSP.getString(SINGLE_LIST,null);
    }
}
