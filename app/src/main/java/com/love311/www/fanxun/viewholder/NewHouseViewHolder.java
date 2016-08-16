package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.NewHouseBean;
import com.love311.www.fanxun.bean.RentHouseBean;

import java.util.List;


public class NewHouseViewHolder extends BaseViewHolder {

    private TextView housesName, publishdTime, houseType, houseMaster, houseOrientation, housePrice, houseSize;

    public NewHouseViewHolder(View itemView) {
        super(itemView);
        housesName = (TextView) itemView.findViewById(R.id.houses_name);
        publishdTime = (TextView) itemView.findViewById(R.id.publishd_time);
        houseType = (TextView) itemView.findViewById(R.id.house_type);
        houseMaster = (TextView) itemView.findViewById(R.id.house_master);
        houseOrientation = (TextView) itemView.findViewById(R.id.house_orientation);
        housePrice = (TextView) itemView.findViewById(R.id.house_price);
        houseSize = (TextView) itemView.findViewById(R.id.house_size);
    }

    public void bindView(List<NewHouseBean.ResBean.ContentBean> newHouseData, int position) {
        housesName.setText(newHouseData.get(position).getCommunity());
        publishdTime.setText(newHouseData.get(position).getCreateDate());
        houseType.setText(newHouseData.get(position).getHuXing());
        houseMaster.setText(newHouseData.get(position).getOwner());
        houseOrientation.setText(newHouseData.get(position).getRenovationInfo());
        housePrice.setText(newHouseData.get(position).getSalePrice()+"");
        houseSize.setText(newHouseData.get(position).getProportion()+"");
    }

}
