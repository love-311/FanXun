package com.love311.www.fanxun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.SearchBean;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.love311.www.fanxun.viewholder.BaseViewHolder;
import com.love311.www.fanxun.viewholder.SearchViewHolder;
import com.love311.www.fanxun.viewholder.UsedHouseViewHolder;

import java.util.ArrayList;
import java.util.List;


//房源二手房界面recycleView数据适配器
public class SearchRecycleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<SearchBean.ResBean.ContentBean> mDataSet;

    public SearchRecycleAdapter(Context context) {
        mContext = context;
        mDataSet = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.search_list_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        SearchViewHolder textViewHolder = (SearchViewHolder) holder;
        textViewHolder.bindView(mDataSet, position);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
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

    public void add(SearchBean.ResBean.ContentBean text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    public void addAll(List<SearchBean.ResBean.ContentBean> list, int position) {
        if (list!=null){
            mDataSet.addAll(position, list);
            notifyItemRangeInserted(position, list.size());
        }

    }

}
