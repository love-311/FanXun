package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.UsedHouseBean;

import java.util.List;

import butterknife.BindView;


public class UsedHouseViewHolder extends BaseViewHolder {

    private TextView housesName, publishdTime, houseType, houseMaster, houseOrientation, housePrice, houseSize;

    public UsedHouseViewHolder(View itemView) {
        super(itemView);
        housesName = (TextView) itemView.findViewById(R.id.houses_name);
        publishdTime = (TextView) itemView.findViewById(R.id.publishd_time);
        houseType = (TextView) itemView.findViewById(R.id.house_type);
        houseMaster = (TextView) itemView.findViewById(R.id.house_master);
        houseOrientation = (TextView) itemView.findViewById(R.id.house_orientation);
        housePrice = (TextView) itemView.findViewById(R.id.house_price);
        houseSize = (TextView) itemView.findViewById(R.id.house_size);
    }

    public void bindView(List<UsedHouseBean.ResBean.ContentBean> usedHouseData, int position) {
        housesName.setText(usedHouseData.get(position).getCommunity());
        publishdTime.setText(usedHouseData.get(position).getCreateDate());
        houseType.setText(usedHouseData.get(position).getHuXing());
        houseMaster.setText(usedHouseData.get(position).getOwner());
        houseOrientation.setText(usedHouseData.get(position).getRenovationInfo());
        housePrice.setText(usedHouseData.get(position).getSalePrice()+"元");
        houseSize.setText(usedHouseData.get(position).getProportion()+"㎡");
    }

}
