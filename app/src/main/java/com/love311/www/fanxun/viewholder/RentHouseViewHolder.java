package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.RentHouseBean;

import java.util.List;


public class RentHouseViewHolder extends BaseViewHolder implements View.OnClickListener,View.OnLongClickListener{

    private TextView housesName, publishdTime, houseType, houseMaster, houseOrientation, housePrice, houseSize;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;

    public RentHouseViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
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

    public void bindView(List<RentHouseBean.ResBean.ContentBean> rentHouseData, int position) {
        housesName.setText(rentHouseData.get(position).getCommunity());
        publishdTime.setText(rentHouseData.get(position).getCreateDate());
        houseType.setText(rentHouseData.get(position).getHuXing());
        houseMaster.setText(rentHouseData.get(position).getOwner());
        houseOrientation.setText(rentHouseData.get(position).getRenovationInfo());
        housePrice.setText((int) rentHouseData.get(position).getSalePrice()+"元");
        houseSize.setText(rentHouseData.get(position).getProportion()+"㎡");
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
