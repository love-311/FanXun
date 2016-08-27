package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/17.
 */
public class PassengerPropertiesActivity extends AutoLayoutActivity implements View.OnClickListener {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    private Intent intent;
    private String statusString;
    private int passenger_properties_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_properties_activity);
        ButterKnife.bind(this);
        passenger_properties_status = 1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("passenger_properties", statusString);
                intent.putExtra("passenger_properties_status",passenger_properties_status);
                PassengerPropertiesActivity.this.setResult(RESULT_OK, intent);
                PassengerPropertiesActivity.this.finish();
            }
        });
        topMid.setText("房源性质");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("passenger_properties");
        if (statusString.equals("私客")){
            iv1.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("公客")){
            iv2.setBackgroundResource(R.mipmap.choosed);
        }
//        else if (statusString.equals("锁盘")){
//            iv3.setBackgroundResource(R.mipmap.choosed);
//        }else if (statusString.equals("特盘")){
//            iv4.setBackgroundResource(R.mipmap.choosed);
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("passenger_properties", "私客");
                intent.putExtra("passenger_properties_status",passenger_properties_status);
                PassengerPropertiesActivity.this.setResult(RESULT_OK, intent);
                PassengerPropertiesActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("passenger_properties", "公客");
                intent.putExtra("passenger_properties_status",passenger_properties_status);
                PassengerPropertiesActivity.this.setResult(RESULT_OK, intent);
                PassengerPropertiesActivity.this.finish();
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("passenger_properties", statusString);
        intent.putExtra("passenger_properties_status",passenger_properties_status);
        PassengerPropertiesActivity.this.setResult(RESULT_OK, intent);
        PassengerPropertiesActivity.this.finish();
    }
}
