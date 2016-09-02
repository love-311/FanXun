package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.love311.www.fanxun.viewholder.BaseViewHolder;
import com.love311.www.fanxun.viewholder.MyItemClickListener;
import com.love311.www.fanxun.viewholder.MyItemLongClickListener;
import com.love311.www.fanxun.viewholder.UsedHouseViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//房源二手房界面recycleView数据适配器
public class UsedHouseRecycleViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<UsedHouseBean.ResBean.ContentBean> mDataSet;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public UsedHouseRecycleViewAdapter(Context context) {
        mContext = context;
        mDataSet = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.house_soucrce_item, parent, false);
        return new UsedHouseViewHolder(view,mItemClickListener,mItemLongClickListener);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        UsedHouseViewHolder textViewHolder = (UsedHouseViewHolder) holder;
        textViewHolder.bindView(mDataSet, position);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public List<UsedHouseBean.ResBean.ContentBean> getDataList() {
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

    public void add(UsedHouseBean.ResBean.ContentBean text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void addAll(List<UsedHouseBean.ResBean.ContentBean> list, int position) {
        if (list!=null){
            Log.d("addAll---==",list.size()+position+"position="+position);
            mDataSet.addAll(position, list);
            notifyItemRangeInserted(position+1, list.size()+position);
            Log.d("addall---","from"+(position+1)+"to"+(list.size()+position));
        }else {
            Log.d("addAll---==","list为空----");
        }

    }
    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }
}
