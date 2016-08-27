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
public class PassengerRemarkActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.et_passenger_remark)
    EditText etpassengerRemark;
    private Intent intent;
    private String statusString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_mark_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("passenger_remark");
        if (!statusString.equals("请完善客户个人信息等...")){
            etpassengerRemark.setText(statusString);
            etpassengerRemark.requestFocus();
            etpassengerRemark.setSelection(statusString.length());
        }
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etpassengerRemark.getText().toString().equals("")){
                    intent = new Intent();
                    intent.putExtra("passenger_remark","请完善客户个人信息等...");
                    PassengerRemarkActivity.this.setResult(RESULT_OK, intent);
                    PassengerRemarkActivity.this.finish();
                }else {
                    intent = new Intent();
                    intent.putExtra("passenger_remark", statusString);
                    PassengerRemarkActivity.this.setResult(RESULT_OK, intent);
                    PassengerRemarkActivity.this.finish();
                }
            }
        });
        topMid.setText("添加客户备注");
        topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etpassengerRemark.getText().toString().equals("")){
                    intent = new Intent();
                    intent.putExtra("passenger_remark","请完善客户个人信息等...");
                    PassengerRemarkActivity.this.setResult(RESULT_OK, intent);
                    PassengerRemarkActivity.this.finish();
                }else {
                    intent = new Intent();
                    intent.putExtra("passenger_remark", etpassengerRemark.getText().toString());
                    PassengerRemarkActivity.this.setResult(RESULT_OK, intent);
                    PassengerRemarkActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (etpassengerRemark.getText().toString().equals("")){
            intent = new Intent();
            intent.putExtra("passenger_remark","请完善客户个人信息等...");
            PassengerRemarkActivity.this.setResult(RESULT_OK, intent);
            PassengerRemarkActivity.this.finish();
        }else {
            intent = new Intent();
            intent.putExtra("passenger_remark", statusString);
            PassengerRemarkActivity.this.setResult(RESULT_OK, intent);
            PassengerRemarkActivity.this.finish();
        }
    }
}
