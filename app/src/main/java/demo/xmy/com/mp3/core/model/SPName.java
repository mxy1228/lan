/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.core.model;

/**
 * Created by xumengyang01 on 2015/2/6.
 */
public enum SPName {
    SINGLE_LIST("SINGLE_LIST");

    private String name;

    private SPName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
