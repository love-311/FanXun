package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.SearchBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class SearchListAdapter extends BaseAdapter {

    private List<SearchBean.ResBean.ContentBean> mDataSet;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder;

    public SearchListAdapter(Context context, List<SearchBean.ResBean.ContentBean> list) {
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
            view = mInflater.inflate(R.layout.search_list_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_search_village);
            viewHolder.tv_area = (TextView) view.findViewById(R.id.tv_search_belong);
            view.setTag(viewHolder);
            AutoUtils.autoSize(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_area.setText(mDataSet.get(i).getArea());
        viewHolder.tv_name.setText(mDataSet.get(i).getName());
        return view;
    }
    public class ViewHolder {
        private TextView tv_name;
        private TextView tv_area;
    }
}
