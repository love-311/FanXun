package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.BrokerBean;
import com.love311.www.fanxun.bean.HouseTypeBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BrokerListAdapter extends BaseAdapter {

    private List<BrokerBean.ResBean> mDataSet;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder;

    public BrokerListAdapter(Context context, List<BrokerBean.ResBean> list) {
        mDataSet = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = mInflater.inflate(R.layout.broker_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_broker_name);
            viewHolder.iv_status = (ImageView) view.findViewById(R.id.iv_broker_name);
            view.setTag(viewHolder);
            AutoUtils.autoSize(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_name.setText(mDataSet.get(i).getName());
        return view;
    }
    public class ViewHolder {
        private TextView tv_name;
        private ImageView iv_status;
    }
}
