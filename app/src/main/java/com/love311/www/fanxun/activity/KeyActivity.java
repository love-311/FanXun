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
 * Created by Administrator on 2016/8/18.
 */
public class KeyActivity extends AutoLayoutActivity implements View.OnClickListener {

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
    private Intent intent;
    private String statusString;
    private String content;
    private int key_status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.key_activity);
        ButterKnife.bind(this);
        key_status = 1;
        topMid.setText("钥匙信息");
        topRight.setVisibility(View.GONE);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("key");
        if (statusString.equals("无钥匙")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("本公司")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("其他公司")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        }
//        topRight.setText("确定");
//        Intent intent1 = this.getIntent();
//        statusString = intent1.getStringExtra("key");
//        topRight.setOnClickListener(this);
//        topLeft.setOnClickListener(this);
//        if (statusString.equals("无")) {
//            iv1.setChecked(false);
//        } else if (statusString.equals("有")) {
//            iv1.setChecked(true);
//            Log.d("GarageActivity", statusString.substring(0, 1));
//        } else {
//            iv1.setChecked(true);
//            etKey.setText(statusString.substring(2));
//            etKey.requestFocus();
//            etKey.setSelection(statusString.substring(2).length());
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_left:
                intent = new Intent();
                intent.putExtra("key", statusString);
                intent.putExtra("key_status", key_status);
                KeyActivity.this.setResult(RESULT_OK, intent);
                KeyActivity.this.finish();
                break;
//            case R.id.top_right:
//                intent = new Intent();
//                if (iv1.isChecked()) {
//                    if (etKey.getText().toString().equals("")) {
//                        content = "有";
//                    } else {
//                        content = "有/" + etKey.getText();
//                    }
//
//                } else if (!iv1.isChecked()) {
//                    content = "无";
//                }
//                intent.putExtra("key", content);
//                KeyActivity.this.setResult(RESULT_OK, intent);
//                KeyActivity.this.finish();
//                break;
//            case R.id.iv_1:
//                if (iv1.isChecked()) {
//                    etKey.setEnabled(true);
//                } else if (!iv1.isChecked()) {
//                    etKey.setEnabled(false);
//                }
//                break;
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("key", "无钥匙");
                intent.putExtra("key_status", key_status);
                KeyActivity.this.setResult(RESULT_OK, intent);
                KeyActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("key", "本公司");
                intent.putExtra("key_status", key_status);
                KeyActivity.this.setResult(RESULT_OK, intent);
                KeyActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("key", "其他公司");
                intent.putExtra("key_status", key_status);
                KeyActivity.this.setResult(RESULT_OK, intent);
                KeyActivity.this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("key", statusString);
        intent.putExtra("key_status", key_status);
        KeyActivity.this.setResult(RESULT_OK, intent);
        KeyActivity.this.finish();
    }
}
