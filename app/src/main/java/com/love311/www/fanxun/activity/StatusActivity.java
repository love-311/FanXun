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
public class StatusActivity extends AutoLayoutActivity implements View.OnClickListener {
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
    @BindView(R.id.iv_7)
    ImageView iv7;
    @BindView(R.id.rl_7)
    RelativeLayout rl7;
    @BindView(R.id.iv_8)
    ImageView iv8;
    @BindView(R.id.rl_8)
    RelativeLayout rl8;
    private Intent intent;
    private String statusString;
    private int statusStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_activity);
        ButterKnife.bind(this);
        statusStatus = 1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("status", statusString);
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
            }
        });
        topMid.setText("状态");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);
        rl6.setOnClickListener(this);
        rl7.setOnClickListener(this);
        rl8.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("status");
        if (statusString.equals("正常")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("成交")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("已租/已售")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("无效")) {
            iv4.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("暂不租/暂不售")) {
            iv5.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("待审")) {
            iv6.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("驳回")) {
            iv7.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("不限")) {
            iv8.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("status", "正常");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("status", "成交");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("status", "已租/已售");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("status", "无效");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_5:
                intent = new Intent();
                intent.putExtra("status", "暂不租/暂不售");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_6:
                intent = new Intent();
                intent.putExtra("status", "待审");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_7:
                intent = new Intent();
                intent.putExtra("status", "驳回");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            case R.id.rl_8:
                intent = new Intent();
                intent.putExtra("status", "不限");
                intent.putExtra("statusStatus", statusStatus);
                StatusActivity.this.setResult(RESULT_OK, intent);
                StatusActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("status", statusString);
        intent.putExtra("statusStatus", statusStatus);
        StatusActivity.this.setResult(RESULT_OK, intent);
        StatusActivity.this.finish();
    }
}
