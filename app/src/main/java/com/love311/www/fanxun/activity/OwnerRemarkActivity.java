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
public class OwnerRemarkActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.et_owner_remark)
    EditText etOwnerRemark;
    private Intent intent;
    private String statusString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_mark_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("owner_remark");
        if (!statusString.equals("请填写业主保密信息...")){
            etOwnerRemark.setText(statusString);
            etOwnerRemark.requestFocus();
            etOwnerRemark.setSelection(statusString.length());
        }
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etOwnerRemark.getText().toString().equals("")){
                    intent = new Intent();
                    intent.putExtra("owner_remark","请填写业主保密信息...");
                    OwnerRemarkActivity.this.setResult(RESULT_OK, intent);
                    OwnerRemarkActivity.this.finish();
                }else {
                    intent = new Intent();
                    intent.putExtra("owner_remark", statusString);
                    OwnerRemarkActivity.this.setResult(RESULT_OK, intent);
                    OwnerRemarkActivity.this.finish();
                }
            }
        });
        topMid.setText("添加业主备注");
        topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etOwnerRemark.getText().toString().equals("")){
                    intent = new Intent();
                    intent.putExtra("owner_remark","请填写业主保密信息...");
                    OwnerRemarkActivity.this.setResult(RESULT_OK, intent);
                    OwnerRemarkActivity.this.finish();
                }else {
                    intent = new Intent();
                    intent.putExtra("owner_remark", etOwnerRemark.getText().toString());
                    OwnerRemarkActivity.this.setResult(RESULT_OK, intent);
                    OwnerRemarkActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (etOwnerRemark.getText().toString().equals("")){
            intent = new Intent();
            intent.putExtra("owner_remark","请填写业主保密信息...");
            OwnerRemarkActivity.this.setResult(RESULT_OK, intent);
            OwnerRemarkActivity.this.finish();
        }else {
            intent = new Intent();
            intent.putExtra("owner_remark", statusString);
            OwnerRemarkActivity.this.setResult(RESULT_OK, intent);
            OwnerRemarkActivity.this.finish();
        }
    }
}
