/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public abstract class BaseFragment extends Fragment {

    protected int mContentViewID;


    protected abstract void initView(View view);

    protected abstract void initEvent();

    protected abstract void initData();
}
