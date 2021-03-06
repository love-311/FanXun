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
public class TypePassengerActivity extends AutoLayoutActivity implements View.OnClickListener {
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
    private Intent intent;
    private String statusString;
    private int house_type_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_passenget_activity);
        ButterKnife.bind(this);
        house_type_status = 1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("type", statusString);
                intent.putExtra("house_type_status", house_type_status);
                TypePassengerActivity.this.setResult(RESULT_OK, intent);
                TypePassengerActivity.this.finish();
            }
        });
        topMid.setText("楼型");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);
        rl5.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("type");
        if (statusString.equals("低层")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("多层")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("中高层")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("高层")) {
            iv4.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("无要求")) {
            iv5.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("type", "低层");
                intent.putExtra("house_type_status", house_type_status);
                TypePassengerActivity.this.setResult(RESULT_OK, intent);
                TypePassengerActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("type", "多层");
                intent.putExtra("house_type_status", house_type_status);
                TypePassengerActivity.this.setResult(RESULT_OK, intent);
                TypePassengerActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("type", "中高层");
                intent.putExtra("house_type_status", house_type_status);
                TypePassengerActivity.this.setResult(RESULT_OK, intent);
                TypePassengerActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("type", "高层");
                intent.putExtra("house_type_status", house_type_status);
                TypePassengerActivity.this.setResult(RESULT_OK, intent);
                TypePassengerActivity.this.finish();
                break;
            case R.id.rl_5:
                intent = new Intent();
                intent.putExtra("type", "无要求");
                intent.putExtra("house_type_status", house_type_status);
                TypePassengerActivity.this.setResult(RESULT_OK, intent);
                TypePassengerActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("type", statusString);
        intent.putExtra("house_type_status", house_type_status);
        TypePassengerActivity.this.setResult(RESULT_OK, intent);
        TypePassengerActivity.this.finish();
    }
}
