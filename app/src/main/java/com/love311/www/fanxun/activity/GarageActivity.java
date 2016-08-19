package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
public class GarageActivity extends AutoLayoutActivity implements View.OnClickListener{
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.iv_1)
    CheckBox iv1;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.et_garage_remark)
    EditText etGarageRemark;
    private Intent intent;
    private String statusString;
    private String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garage_activity);
        ButterKnife.bind(this);
        topMid.setText("车库信息");
        topRight.setText("确定");
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("garage");
        topRight.setOnClickListener(this);
        topLeft.setOnClickListener(this);
        if (statusString.equals("无")){
            iv1.setChecked(false);
        }else if (statusString.equals("有")){
            iv1.setChecked(true);
            Log.d("GarageActivity",statusString.substring(0,1));
        }else {
            iv1.setChecked(true);
            etGarageRemark.setText(statusString.substring(2));
            etGarageRemark.requestFocus();
            etGarageRemark.setSelection(statusString.substring(2).length());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.top_left:
                intent = new Intent();
                intent.putExtra("garage", statusString);
                GarageActivity.this.setResult(RESULT_OK, intent);
                GarageActivity.this.finish();
                break;
            case R.id.top_right:
                intent = new Intent();
                if (iv1.isChecked()){
                    if (etGarageRemark.getText().toString().equals("")){
                        content = "有";
                    }else {
                        content = "有/"+etGarageRemark.getText();
                    }

                }else if (!iv1.isChecked()){
                    content = "无";
                }
                intent.putExtra("garage", content);
                GarageActivity.this.setResult(RESULT_OK, intent);
                GarageActivity.this.finish();
                break;
            case R.id.iv_1:
                if (iv1.isChecked()){
                    etGarageRemark.setEnabled(true);
                }else if (!iv1.isChecked()){
                    etGarageRemark.setEnabled(false);
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("garage", statusString);
        GarageActivity.this.setResult(RESULT_OK, intent);
        GarageActivity.this.finish();
    }
}
