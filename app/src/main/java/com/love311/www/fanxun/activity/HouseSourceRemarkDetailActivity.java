package com.love311.www.fanxun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HouseSourceRemarkDetailActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.et_house_source_remark)
    EditText etHouseSourceRemark;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_source_mark_activity);
        ButterKnife.bind(this);
        etHouseSourceRemark.setEnabled(false);
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent().getIntExtra("from",0)==1){
            topMid.setText("客源备注详情");
        }else if (getIntent().getIntExtra("from",0)==2){
            topMid.setText("业主保密备注");
        }else {
            topMid.setText("房源备注详情");
        }
        topRight.setVisibility(View.GONE);
        if (getIntent().getStringExtra("content").equals("")){
            etHouseSourceRemark.setText("  ");
        }else {
            etHouseSourceRemark.setText(getIntent().getStringExtra("content"));
        }
    }
}
