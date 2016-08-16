package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.PassengerNewHouseBean;
import com.love311.www.fanxun.bean.PassengerRentHouseBean;

import java.util.List;


public class PassengerNewHouseViewHolder extends BaseViewHolder {

    private TextView passenger_name, tv_renovation_info, tv_low_price, tv_tall_price,tv_area, tv_date;

    public PassengerNewHouseViewHolder(View itemView) {
        super(itemView);
        passenger_name = (TextView) itemView.findViewById(R.id.passenger_name);
        tv_renovation_info = (TextView) itemView.findViewById(R.id.tv_renovation_info);
        tv_low_price = (TextView) itemView.findViewById(R.id.tv_low_price);
        tv_tall_price = (TextView) itemView.findViewById(R.id.tv_tall_price);
        tv_area = (TextView) itemView.findViewById(R.id.tv_area);
        tv_date = (TextView) itemView.findViewById(R.id.tv_date);
    }

    public void bindView(List<PassengerNewHouseBean.ResBean.ContentBean> passengerNewHouseData, int position) {
        passenger_name.setText(passengerNewHouseData.get(position).getName());
        tv_renovation_info.setText(passengerNewHouseData.get(position).getRenovationInfo()+"/");
        tv_low_price.setText(passengerNewHouseData.get(position).getLowPrice()+"-");
        tv_tall_price.setText(passengerNewHouseData.get(position).getTallPrice()+"/");
        tv_area.setText(passengerNewHouseData.get(position).getProportion());
        tv_date.setText(passengerNewHouseData.get(position).getCreateDate());
    }

}
