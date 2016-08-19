package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.love311.www.fanxun.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/17.
 */
public class OrientationAdapter extends BaseAdapter {

    private List<Map<String, Object>> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder;

    public OrientationAdapter(Context context, List<Map<String, Object>> list) {
        mList = list;
        mInflater =LayoutInflater.from(context);
        mContext = context;
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
            view = mInflater.inflate(R.layout.choose_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_oritation = (TextView) view.findViewById(R.id.tv_orientation);
            viewHolder.iv_choose_status = (ImageView) view.findViewById(R.id.iv_choose_status);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }
    public class ViewHolder {
        private TextView tv_oritation;
        private ImageView iv_choose_status;
    }
}
