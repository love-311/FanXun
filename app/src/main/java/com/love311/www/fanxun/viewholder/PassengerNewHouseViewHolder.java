package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.PassengerNewHouseBean;

import java.util.List;


public class PassengerNewHouseViewHolder extends BaseViewHolder  implements View.OnClickListener,View.OnLongClickListener{

    private TextView passenger_name, tv_renovation_info, tv_low_price, tv_tall_price,tv_area, tv_date;
    private MyItemClickListener mListener;
    private MyItemLongClickListener mLongClickListener;
    public PassengerNewHouseViewHolder(View itemView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
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

    public void bindView(List<PassengerNewHouseBean.ResBean.ContentBean> passengerNewHouseData, int position) {
        passenger_name.setText(passengerNewHouseData.get(position).getName());
        tv_renovation_info.setText(passengerNewHouseData.get(position).getRenovationInfo()+"/");
        tv_low_price.setText(passengerNewHouseData.get(position).getLowPrice()+"-");
        tv_tall_price.setText(passengerNewHouseData.get(position).getTallPrice()+"万/");
        tv_area.setText(passengerNewHouseData.get(position).getProportion()+"㎡");
        tv_date.setText(passengerNewHouseData.get(position).getCreateDate());
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
