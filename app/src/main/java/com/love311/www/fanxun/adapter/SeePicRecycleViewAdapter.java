package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.SeePicBean;
import com.love311.www.fanxun.viewholder.BaseViewHolder;
import com.love311.www.fanxun.viewholder.MyItemClickListener;
import com.love311.www.fanxun.viewholder.MyItemLongClickListener;
import com.love311.www.fanxun.viewholder.SeePicViewHolder;

import java.util.List;


//房源二手房界面recycleView数据适配器
public class SeePicRecycleViewAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context mContext;
    private List<SeePicBean.ResBean> mDataSet;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public SeePicRecycleViewAdapter(Context context,List<SeePicBean.ResBean> data) {
        mContext = context;
        mDataSet = data;
        Log.d("getItem",mDataSet.size()+"");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.pic_item, parent, false);
        return new SeePicViewHolder(view, mItemClickListener, mItemLongClickListener);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        SeePicViewHolder textViewHolder = (SeePicViewHolder) holder;
        textViewHolder.bindView(mDataSet, position);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public List<SeePicBean.ResBean> getDataList() {
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

    public void add(SeePicBean.ResBean text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void addAll(List<SeePicBean.ResBean> list, int position) {
        if (list != null) {
            Log.d("addAll---==", list.size() + position + "");
            mDataSet.addAll(position, list);
            notifyItemRangeInserted(position + 1, list.size() + position);
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
