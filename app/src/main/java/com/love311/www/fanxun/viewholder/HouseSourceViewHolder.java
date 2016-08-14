package com.love311.www.fanxun.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.love311.www.fanxun.R;


public class HouseSourceViewHolder extends BaseViewHolder {

	public TextView text;
	public ImageView image;

	public HouseSourceViewHolder(View itemView) {
		super(itemView);
		text = (TextView) itemView.findViewById(R.id.text);
		image = (ImageView) itemView.findViewById(R.id.image);
	}

	public void bindView(String str, int position) {
	}

}
