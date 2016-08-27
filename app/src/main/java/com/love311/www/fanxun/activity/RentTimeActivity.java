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
public class RentTimeActivity extends AutoLayoutActivity implements View.OnClickListener {
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
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.rl_4)
    RelativeLayout rl4;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.rl_5)
    RelativeLayout rl5;
    @BindView(R.id.iv_6)
    ImageView iv6;
    @BindView(R.id.rl_6)
    RelativeLayout rl6;
    private Intent intent;
    private String statusString;
    private int rent_time_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_time_activity);
        ButterKnife.bind(this);
        rent_time_status=1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("rent_time", statusString);
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
            }
        });
        topMid.setText("租期");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);
        rl6.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("rent_time");
        if (statusString.equals("三十天内")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("一个月")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("三个月")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("半年")) {
            iv4.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("一年")) {
            iv5.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("一年以上")) {
            iv6.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("rent_time", "三十天内");
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("rent_time", "一个月");
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("rent_time", "三个月");
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("rent_time", "半年");
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
                break;
            case R.id.rl_5:
                intent = new Intent();
                intent.putExtra("rent_time", "一年");
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
                break;
            case R.id.rl_6:
                intent = new Intent();
                intent.putExtra("rent_time", "一年以上");
                intent.putExtra("rent_time_status", rent_time_status);
                RentTimeActivity.this.setResult(RESULT_OK, intent);
                RentTimeActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("rent_time", statusString);
        intent.putExtra("rent_time_status", rent_time_status);
        RentTimeActivity.this.setResult(RESULT_OK, intent);
        RentTimeActivity.this.finish();
    }
}
