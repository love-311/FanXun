package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.PassengerUsedHouseBean;

import java.util.List;


public class PassengerUsedHouseViewHolder extends BaseViewHolder implements View.OnClickListener,View.OnLongClickListener{

    private TextView passenger_name, tv_renovation_info, tv_low_price, tv_tall_price,tv_area, tv_date;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;
    public PassengerUsedHouseViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
        super(itemView);
        passenger_name = (TextView) itemView.findViewById(R.id.passenger_name);
        tv_renovation_info = (TextView) itemView.findViewById(R.id.tv_renovation_info);
        tv_low_price = (TextView) itemView.findViewById(R.id.tv_low_price);
        tv_tall_price = (TextView) itemView.findViewById(R.id.tv_tall_price);
        tv_area = (TextView) itemView.findViewById(R.id.tv_area);
        tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void bindView(List<PassengerUsedHouseBean.ResBean.ContentBean> passengerUsedHouseData, int position) {
        passenger_name.setText(passengerUsedHouseData.get(position).getName());
        tv_renovation_info.setText(passengerUsedHouseData.get(position).getRenovationInfo()+"/");
        tv_low_price.setText(passengerUsedHouseData.get(position).getLowPrice()+"-");
        tv_tall_price.setText(passengerUsedHouseData.get(position).getTallPrice()+"万/");
        tv_area.setText(passengerUsedHouseData.get(position).getProportion()+"㎡");
        tv_date.setText(passengerUsedHouseData.get(position).getCreateDate());
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
