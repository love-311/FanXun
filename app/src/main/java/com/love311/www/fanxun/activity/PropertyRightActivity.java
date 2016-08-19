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
public class PropertyRightActivity extends AutoLayoutActivity implements View.OnClickListener {
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
    @BindView(R.id.iv_9)
    ImageView iv9;
    @BindView(R.id.rl_9)
    RelativeLayout rl9;
    @BindView(R.id.iv_10)
    ImageView iv10;
    @BindView(R.id.rl_10)
    RelativeLayout rl10;
    @BindView(R.id.iv_11)
    ImageView iv11;
    @BindView(R.id.rl_11)
    RelativeLayout rl11;
    private Intent intent;
    private String statusString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_right_activity);
        ButterKnife.bind(this);
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("property_right", statusString);
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
            }
        });
        topMid.setText("产权");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);
        rl6.setOnClickListener(this);
        rl7.setOnClickListener(this);
        rl8.setOnClickListener(this);
        rl9.setOnClickListener(this);
        rl10.setOnClickListener(this);
        rl11.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("property_right");
        if (statusString.equals("其他")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("个人产权")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("单位产权")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("售后产权")) {
            iv4.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("家庭成员共有")) {
            iv5.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("使用权")) {
            iv6.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("小产权")) {
            iv7.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("协议房")) {
            iv8.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("私产")) {
            iv9.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("公产")) {
            iv10.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("合同发票")) {
            iv11.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("property_right", "其他");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("property_right", "个人产权");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("property_right", "单位产权");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("property_right", "售后产权");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_5:
                intent = new Intent();
                intent.putExtra("property_right", "家庭成员共有");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_6:
                intent = new Intent();
                intent.putExtra("property_right", "使用权");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_7:
                intent = new Intent();
                intent.putExtra("property_right", "小产权");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_8:
                intent = new Intent();
                intent.putExtra("property_right", "协议房");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_9:
                intent = new Intent();
                intent.putExtra("property_right", "私产");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_10:
                intent = new Intent();
                intent.putExtra("property_right", "公产");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            case R.id.rl_11:
                intent = new Intent();
                intent.putExtra("property_right", "合同发票");
                PropertyRightActivity.this.setResult(RESULT_OK, intent);
                PropertyRightActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("property_right", statusString);
        PropertyRightActivity.this.setResult(RESULT_OK, intent);
        PropertyRightActivity.this.finish();
    }
}
