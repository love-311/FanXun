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
public class StatusPassengerSearchActivity extends AutoLayoutActivity implements View.OnClickListener {
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
    private int status_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_passenger_search_activity);
        ButterKnife.bind(this);
        status_status = 1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("status", statusString);
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
            }
        });
        topMid.setText("客户状态");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);
        rl6.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("status");
        if (statusString.equals("正常")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("完成")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("急需")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("无效")) {
            iv4.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("暂缓")) {
            iv5.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("已售")) {
            iv6.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("status", "正常");
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("status", "完成");
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("status", "急需");
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("status", "无效");
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
                break;
            case R.id.rl_5:
                intent = new Intent();
                intent.putExtra("status", "暂缓");
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
                break;
            case R.id.rl_6:
                intent = new Intent();
                intent.putExtra("status", "已售");
                intent.putExtra("status_status", status_status);
                StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
                StatusPassengerSearchActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("status", statusString);
        intent.putExtra("status_status", status_status);
        StatusPassengerSearchActivity.this.setResult(RESULT_OK, intent);
        StatusPassengerSearchActivity.this.finish();
    }
}
