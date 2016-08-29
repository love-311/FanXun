package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.RentHouseBean;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.love311.www.fanxun.viewholder.BaseViewHolder;
import com.love311.www.fanxun.viewholder.MyItemClickListener;
import com.love311.www.fanxun.viewholder.MyItemLongClickListener;
import com.love311.www.fanxun.viewholder.RentHouseViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//房源租房界面recycleView数据适配器
public class RentHouseRecycleViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private LinkedList<RentHouseBean.ResBean.ContentBean> mDataSet;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public RentHouseRecycleViewAdapter(Context context) {
        mContext = context;
        mDataSet = new LinkedList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.house_soucrce_item, parent, false);
        return new RentHouseViewHolder(view, mItemClickListener, mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        RentHouseViewHolder textViewHolder = (RentHouseViewHolder) holder;
        textViewHolder.bindView(mDataSet, position);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public LinkedList<RentHouseBean.ResBean.ContentBean> getDataList() {
        return mDataSet;
    }

    /**
     * 从position开始删除，删除
     *
     * @param position
     * @param itemCount 删除的数目
     */
    protected void removeAll(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            mDataSet.remove(position);
        }
        notifyItemRangeRemoved(position, itemCount);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void add(RentHouseBean.ResBean.ContentBean text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void addAll(List<RentHouseBean.ResBean.ContentBean> list, int position) {
        if (list != null) {
            mDataSet.addAll(position, list);
            notifyItemRangeInserted(position, list.size());
        }
    }

    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }
}
