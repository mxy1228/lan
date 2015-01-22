/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo.xmy.com.mp3.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.xmy.com.mp3.R;
import demo.xmy.com.mp3.model.AlbumInfo;

/**
 * Created by xumengyang01 on 2015/1/22.
 */
public class AlbumAdapter extends BaseAdapter{
    private Context mCtx;
    private List<AlbumInfo> mData;

    public AlbumAdapter(Context ctx,List<AlbumInfo> data){
        this.mCtx = ctx;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.album_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder)convertView.getTag();
        AlbumInfo info = (AlbumInfo)getItem(position);
        holder.mNameTV.setText(info.name);
        holder.mYearTV.setText(info.year);
        return convertView;
    }

    private class ViewHolder{

        public ViewHolder(View view){
            mNameTV = (TextView)view.findViewById(R.id.album_item_name_tv);
            mYearTV = (TextView)view.findViewById(R.id.album_item_year_tv);
            mPicIV = (ImageView)view.findViewById(R.id.album_item_pic_iv);
        }

        public ImageView mPicIV;
        public TextView mNameTV;
        public TextView mYearTV;
    }
}
