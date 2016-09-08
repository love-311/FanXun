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
public class DecorationActivity extends AutoLayoutActivity implements View.OnClickListener {
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.iv_blank)
    ImageView ivBlank;
    @BindView(R.id.rl_blank)
    RelativeLayout rlBlank;
    @BindView(R.id.iv_simple)
    ImageView ivSimple;
    @BindView(R.id.rl_simple)
    RelativeLayout rlSimple;
    @BindView(R.id.iv_middle)
    ImageView ivMiddle;
    @BindView(R.id.rl_middle)
    RelativeLayout rlMiddle;
    @BindView(R.id.iv_elaborate)
    ImageView ivElaborate;
    @BindView(R.id.rl_elaborate)
    RelativeLayout rlElaborate;
    @BindView(R.id.iv_big)
    ImageView ivBig;
    @BindView(R.id.rl_big)
    RelativeLayout rlBig;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    private Intent intent;
    private String statusString;
    private int decoration_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decoration_activity);
        ButterKnife.bind(this);
        decoration_status = 1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("decoration", statusString);
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
            }
        });
        topMid.setText("装修");
        topRight.setVisibility(View.GONE);
        rlBlank.setOnClickListener(this);
        rlSimple.setOnClickListener(this);
        rlMiddle.setOnClickListener(this);
        rlElaborate.setOnClickListener(this);
        rlBig.setOnClickListener(this);
        rl1.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("decoration");
        if (statusString.equals("清水")) {
            ivBlank.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("简装")) {
            ivSimple.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("中装")) {
            ivMiddle.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("精装")) {
            ivElaborate.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("豪装")) {
            ivBig.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("不限")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_blank:
                intent = new Intent();
                intent.putExtra("decoration", "清水");
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
                break;
            case R.id.rl_simple:
                intent = new Intent();
                intent.putExtra("decoration", "简装");
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
                break;
            case R.id.rl_middle:
                intent = new Intent();
                intent.putExtra("decoration", "中装");
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
                break;
            case R.id.rl_elaborate:
                intent = new Intent();
                intent.putExtra("decoration", "精装");
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
                break;
            case R.id.rl_big:
                intent = new Intent();
                intent.putExtra("decoration", "豪装");
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
                break;
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("decoration", "不限");
                intent.putExtra("decoration_status", decoration_status);
                DecorationActivity.this.setResult(RESULT_OK, intent);
                DecorationActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("decoration", statusString);
        intent.putExtra("decoration_status", decoration_status);
        DecorationActivity.this.setResult(RESULT_OK, intent);
        DecorationActivity.this.finish();
    }
}
