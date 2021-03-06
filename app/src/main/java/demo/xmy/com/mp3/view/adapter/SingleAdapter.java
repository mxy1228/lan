/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.core.model.FileUtils;
import demo.xmy.com.mp3.model.SingleInfo;
import demo.xmy.com.mp3.view.activity.MP3PlayerActivity;

/**
 * Created by xumengyang01 on 2015/1/20.
 */
public class SingleAdapter extends BaseAdapter implements View.OnClickListener{

    private List<SingleInfo> mData;
    private Context mCtx;

    private SingleActionListener mListener;

    public SingleAdapter(Context ctx, List<SingleInfo> data){
        this.mData = data;
        this.mCtx = ctx;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public SingleInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 清除数据
     */
    public void clear(){
        if(this.mData != null){
            this.mData.clear();
        }
    }

    public void resetData(List<SingleInfo> data){
        clear();
        if(this.mData != null){
            this.mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.single_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder)convertView.getTag();
        SingleInfo info = (SingleInfo)getItem(position);
        holder.mNameTV.setText(info.name);
        holder.mTipsTV.setText(info.album);
        holder.mPlayBtn.setOnClickListener(this);
        holder.mDownloadBtn.setOnClickListener(this);
        holder.mPlayBtn.setTag(info);
        holder.mDownloadBtn.setTag(info);
        holder.mDownloadBtn.setVisibility(info.downloadprogress == 100 ? View.GONE : View.VISIBLE);
        holder.mDownloadBtn.setText(info.downloadprogress == 0 ? mCtx.getString(R.string.download) : String.format("%2.0f%%",info.downloadprogress));
        return convertView;
    }


    @Override
    public void onClick(View v) {
        SingleInfo info = (SingleInfo)v.getTag();
       switch (v.getId()){
           case R.id.mp3_item_play_btn:
               if(info != null){
                   Intent intent = MP3PlayerActivity.createIntent(mCtx,info);
                   mCtx.startActivity(intent);
               }
               break;
           case R.id.mp3_item_download_btn:
                if(mListener != null && info != null){
                    mListener.download(info);
                }
               break;
       }
    }

    class ViewHolder{

        public ViewHolder(View view){
            this.mNameTV = (TextView)view.findViewById(R.id.mp3_item_name_tv);
            this.mTipsTV = (TextView)view.findViewById(R.id.mp3_item_tips_tv);
            this.mPlayBtn = (Button)view.findViewById(R.id.mp3_item_play_btn);
            this.mDownloadBtn = (Button)view.findViewById(R.id.mp3_item_download_btn);
        }

        public TextView mNameTV;
        public TextView mTipsTV;
        public Button mPlayBtn;
        public Button mDownloadBtn;
    }

    public void setActionListener(SingleActionListener listener){
        this.mListener  = listener;
    }

    public interface SingleActionListener{
        public void download(SingleInfo info);
    }
}
