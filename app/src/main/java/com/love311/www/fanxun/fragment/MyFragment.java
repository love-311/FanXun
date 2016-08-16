package com.love311.www.fanxun.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.custom.LazyLoadFragment;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MyFragment extends LazyLoadFragment {

    private ImageView iv_top_left;
    private TextView tv_mid,tv_right;
    //我的界面
    @Override
    public int getLayout() {
        return R.layout.my_fragment;
    }

    @Override
    public void initViews(View view) {
        iv_top_left = (ImageView) view.findViewById(R.id.top_left);
        tv_mid = (TextView) view.findViewById(R.id.top_mid);
        tv_right = (TextView) view.findViewById(R.id.top_right);
        iv_top_left.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
    }

    @Override
    public void loadData() {

    }
}
