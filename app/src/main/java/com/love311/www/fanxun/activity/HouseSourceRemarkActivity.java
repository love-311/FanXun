package com.love311.www.fanxun.activity;

import android.content.Intent;
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
public class HouseSourceRemarkActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.et_house_source_remark)
    EditText etHouseSourceRemark;
    private Intent intent;
    private String statusString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_source_mark_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("house_source_remark");
        if (!statusString.equals("请填写房源其它公开信息...")) {
            etHouseSourceRemark.setText(statusString);
            etHouseSourceRemark.requestFocus();
            etHouseSourceRemark.setSelection(statusString.length());
        }
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etHouseSourceRemark.getText().toString().equals("")) {
                    intent = new Intent();
                    intent.putExtra("house_source_remark", "请填写房源其它公开信息...");
                    HouseSourceRemarkActivity.this.setResult(RESULT_OK, intent);
                    HouseSourceRemarkActivity.this.finish();
                } else {
                    intent = new Intent();
                    intent.putExtra("house_source_remark", statusString);
                    HouseSourceRemarkActivity.this.setResult(RESULT_OK, intent);
                    HouseSourceRemarkActivity.this.finish();
                }
            }
        });
        topMid.setText("添加房源备注");
        topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etHouseSourceRemark.getText().toString().equals("")) {
                    intent = new Intent();
                    intent.putExtra("house_source_remark", "请填写房源其它公开信息...");
                    HouseSourceRemarkActivity.this.setResult(RESULT_OK, intent);
                    HouseSourceRemarkActivity.this.finish();
                } else {
                    intent = new Intent();
                    intent.putExtra("house_source_remark", etHouseSourceRemark.getText().toString());
                    HouseSourceRemarkActivity.this.setResult(RESULT_OK, intent);
                    HouseSourceRemarkActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (etHouseSourceRemark.getText().toString().equals("")) {
            intent = new Intent();
            intent.putExtra("house_source_remark", "请填写房源其它公开信息...");
            HouseSourceRemarkActivity.this.setResult(RESULT_OK, intent);
            HouseSourceRemarkActivity.this.finish();
        } else {
            intent = new Intent();
            intent.putExtra("house_source_remark", statusString);
            HouseSourceRemarkActivity.this.setResult(RESULT_OK, intent);
            HouseSourceRemarkActivity.this.finish();
        }
    }
}
