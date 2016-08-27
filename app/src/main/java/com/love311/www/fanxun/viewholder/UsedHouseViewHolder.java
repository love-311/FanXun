package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.UsedHouseBean;

import java.util.List;

import butterknife.BindView;


public class UsedHouseViewHolder extends BaseViewHolder implements View.OnClickListener,View.OnLongClickListener{

    private TextView housesName, publishdTime, houseType, houseMaster, houseOrientation, housePrice, houseSize;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;

    public UsedHouseViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
        super(itemView);
        housesName = (TextView) itemView.findViewById(R.id.houses_name);
        publishdTime = (TextView) itemView.findViewById(R.id.publishd_time);
        houseType = (TextView) itemView.findViewById(R.id.house_type);
        houseMaster = (TextView) itemView.findViewById(R.id.house_master);
        houseOrientation = (TextView) itemView.findViewById(R.id.house_orientation);
        housePrice = (TextView) itemView.findViewById(R.id.house_price);
        houseSize = (TextView) itemView.findViewById(R.id.house_size);
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void bindView(List<UsedHouseBean.ResBean.ContentBean> usedHouseData, int position) {
        housesName.setText(usedHouseData.get(position).getCommunity());
        publishdTime.setText(usedHouseData.get(position).getCreateDate());
        houseType.setText(usedHouseData.get(position).getHuXing());
        houseMaster.setText(usedHouseData.get(position).getOwner());
        houseOrientation.setText(usedHouseData.get(position).getRenovationInfo());
        housePrice.setText(usedHouseData.get(position).getSalePrice()+"万");
        houseSize.setText(usedHouseData.get(position).getProportion()+"㎡");
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
