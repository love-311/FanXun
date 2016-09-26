package com.love311.www.fanxun.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.SeePicBean;

import java.util.List;


public class SeePicViewHolder extends BaseViewHolder implements View.OnClickListener,View.OnLongClickListener{

    private ImageView iv_pic;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;
    private MyApplication my;
    private String url;
    public SeePicViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
        super(itemView);
        iv_pic = (ImageView) itemView.findViewById(R.id.pic_item);
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        my = (MyApplication) iv_pic.getContext().getApplicationContext();
        url = my.getURL();
    }

    public void bindView(List<SeePicBean.ResBean> picsData, int position) {
        Glide.with(iv_pic.getContext())
                .load(url+picsData.get(position).getUrl().substring(6))
                .into(iv_pic);
        Log.d("PictureUrl","http://62fa6d1e.ngrok.natapp.cn"+picsData.get(position).getUrl());
    }

    @Override
    public void onClick(View view) {
        if(mListener != null){
            mListener.onItemClick(view,getPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(mLongClickListener != null){
            mLongClickListener.onItemLongClick(view, getPosition());
        }
        return true;
    }
}
