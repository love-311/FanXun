package com.love311.www.fanxun.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

	public BaseViewHolder(View itemView) {
		super(itemView);
		AutoUtils.auto(itemView);
	}
	
}
