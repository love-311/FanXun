package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.love311.www.fanxun.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/19.
 */
public class PickPictureAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> mList;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    public PickPictureAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mInflater =LayoutInflater.from(context);
        mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = mInflater.inflate(R.layout.picture_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.iv_pic = (ImageView) view.findViewById(R.id.iv_picture);
            view.setTag(viewHolder);
            AutoUtils.autoSize(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(mList.get(i)).into(viewHolder.iv_pic);
        return view;
    }
    public class ViewHolder {
        private ImageView iv_pic;
    }
}
