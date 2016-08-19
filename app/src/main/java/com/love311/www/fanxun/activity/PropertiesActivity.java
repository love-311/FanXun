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
public class PropertiesActivity extends AutoLayoutActivity implements View.OnClickListener {

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
    private Intent intent;
    private String statusString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.properties_activity);
        ButterKnife.bind(this);
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("properties", statusString);
                PropertiesActivity.this.setResult(RESULT_OK, intent);
                PropertiesActivity.this.finish();
            }
        });
        topMid.setText("性质");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("properties");
        if (statusString.equals("公盘")){
            iv1.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("私盘")){
            iv2.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("锁盘")){
            iv3.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("特盘")){
            iv4.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("properties", "公盘");
                PropertiesActivity.this.setResult(RESULT_OK, intent);
                PropertiesActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("properties", "私盘");
                PropertiesActivity.this.setResult(RESULT_OK, intent);
                PropertiesActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("properties", "锁盘");
                PropertiesActivity.this.setResult(RESULT_OK, intent);
                PropertiesActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("properties", "特盘");
                PropertiesActivity.this.setResult(RESULT_OK, intent);
                PropertiesActivity.this.finish();
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("properties", statusString);
        PropertiesActivity.this.setResult(RESULT_OK, intent);
        PropertiesActivity.this.finish();
    }
}
