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
public class OrientationPassengerActivity extends AutoLayoutActivity implements View.OnClickListener {
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.iv_choose_status_1)
    ImageView ivChooseStatus1;
    @BindView(R.id.rl_south)
    RelativeLayout rlSouth;
    @BindView(R.id.iv_choose_status_2)
    ImageView ivChooseStatus2;
    @BindView(R.id.rl_south_north)
    RelativeLayout rlSouthNorth;
    @BindView(R.id.iv_choose_status_3)
    ImageView ivChooseStatus3;
    @BindView(R.id.rl_south_south)
    RelativeLayout rlSouthSouth;
    @BindView(R.id.iv_choose_status_4)
    ImageView ivChooseStatus4;
    @BindView(R.id.rl_east)
    RelativeLayout rlEast;
    @BindView(R.id.iv_choose_status_5)
    ImageView ivChooseStatus5;
    @BindView(R.id.rl_west)
    RelativeLayout rlWest;
    @BindView(R.id.iv_choose_status_6)
    ImageView ivChooseStatus6;
    @BindView(R.id.rl_north)
    RelativeLayout rlNorth;
    @BindView(R.id.iv_choose_status_7)
    ImageView ivChooseStatus7;
    @BindView(R.id.rl_east_west)
    RelativeLayout rlEastWest;
    @BindView(R.id.iv_choose_status_8)
    ImageView ivChooseStatus8;
    @BindView(R.id.rl_east_south)
    RelativeLayout rlEastSouth;
    @BindView(R.id.iv_choose_status_9)
    ImageView ivChooseStatus9;
    @BindView(R.id.rl_west_south)
    RelativeLayout rlWestSouth;
    @BindView(R.id.iv_choose_status_10)
    ImageView ivChooseStatus10;
    @BindView(R.id.rl_east_north)
    RelativeLayout rlEastNorth;
    @BindView(R.id.iv_choose_status_11)
    ImageView ivChooseStatus11;
    @BindView(R.id.rl_west_north)
    RelativeLayout rlWestNorth;
    @BindView(R.id.iv_choose_status_12)
    ImageView ivChooseStatus12;
    @BindView(R.id.rl_other)
    RelativeLayout rlOther;
    @BindView(R.id.iv_choose_status_0)
    ImageView ivChooseStatus0;
    @BindView(R.id.rl_no)
    RelativeLayout rlNo;
    private Intent intent;
    private String statusString;
    private int orientation_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orientation_passenger_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("orientation");
        orientation_status = 1;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("orientation", statusString);
                intent.putExtra("orientation_status", 0);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
            }
        });
        topMid.setText("朝向");
        topRight.setVisibility(View.GONE);
        rlSouth.setOnClickListener(this);
        rlSouthNorth.setOnClickListener(this);
        rlSouthSouth.setOnClickListener(this);
        rlEast.setOnClickListener(this);
        rlWest.setOnClickListener(this);
        rlNorth.setOnClickListener(this);
        rlEastWest.setOnClickListener(this);
        rlEastSouth.setOnClickListener(this);
        rlWestSouth.setOnClickListener(this);
        rlEastNorth.setOnClickListener(this);
        rlWestNorth.setOnClickListener(this);
        rlOther.setOnClickListener(this);
        if (statusString.equals("不限")) {
            ivChooseStatus0.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("南")) {
            ivChooseStatus1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("南北")) {
            ivChooseStatus2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("南南")) {
            ivChooseStatus3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("东")) {
            ivChooseStatus4.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("西")) {
            ivChooseStatus5.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("北")) {
            ivChooseStatus6.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("东西")) {
            ivChooseStatus7.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("东南")) {
            ivChooseStatus8.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("西南")) {
            ivChooseStatus9.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("东北")) {
            ivChooseStatus10.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("西北")) {
            ivChooseStatus11.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("其他")) {
            ivChooseStatus12.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_no:
                intent = new Intent();
                intent.putExtra("orientation", "不限");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_south:
                intent = new Intent();
                intent.putExtra("orientation", "南");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_south_north:
                intent = new Intent();
                intent.putExtra("orientation", "南北");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_south_south:
                intent = new Intent();
                intent.putExtra("orientation", "南南");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_east:
                intent = new Intent();
                intent.putExtra("orientation", "东");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_west:
                intent = new Intent();
                intent.putExtra("orientation", "西");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_north:
                intent = new Intent();
                intent.putExtra("orientation", "北");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_east_west:
                intent = new Intent();
                intent.putExtra("orientation", "东西");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_east_south:
                intent = new Intent();
                intent.putExtra("orientation", "东南");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_west_south:
                intent = new Intent();
                intent.putExtra("orientation", "西南");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_east_north:
                intent = new Intent();
                intent.putExtra("orientation", "东北");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_west_north:
                intent = new Intent();
                intent.putExtra("orientation", "西北");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            case R.id.rl_other:
                intent = new Intent();
                intent.putExtra("orientation", "其他");
                intent.putExtra("orientation_status", orientation_status);
                OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
                OrientationPassengerActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("orientation", statusString);
        intent.putExtra("orientation_status", 0);
        OrientationPassengerActivity.this.setResult(RESULT_OK, intent);
        OrientationPassengerActivity.this.finish();
    }
}
