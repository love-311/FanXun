package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.bean.SearchBean;

import java.util.List;


public class SearchViewHolder extends BaseViewHolder {

    private TextView tv_name, tv_area;

    public SearchViewHolder(View itemView) {
        super(itemView);
        tv_name = (TextView) itemView.findViewById(R.id.tv_search_village);
        tv_area = (TextView) itemView.findViewById(R.id.tv_search_belong);
    }

    public void bindView(List<SearchBean.ResBean.ContentBean> searchData, int position) {
        tv_name.setText(searchData.get(position).getName());
        tv_area.setText(searchData.get(position).getArea());
    }

}
