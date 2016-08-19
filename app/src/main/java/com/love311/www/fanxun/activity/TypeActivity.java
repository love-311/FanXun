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
public class TypeActivity extends AutoLayoutActivity implements View.OnClickListener {
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
    @BindView(R.id.iv_12)
    ImageView iv12;
    @BindView(R.id.rl_12)
    RelativeLayout rl12;
    @BindView(R.id.iv_13)
    ImageView iv13;
    @BindView(R.id.rl_13)
    RelativeLayout rl13;
    @BindView(R.id.iv_14)
    ImageView iv14;
    @BindView(R.id.rl_14)
    RelativeLayout rl14;
    @BindView(R.id.iv_15)
    ImageView iv15;
    @BindView(R.id.rl_15)
    RelativeLayout rl15;
    @BindView(R.id.iv_16)
    ImageView iv16;
    @BindView(R.id.rl_16)
    RelativeLayout rl16;
    @BindView(R.id.iv_17)
    ImageView iv17;
    @BindView(R.id.rl_17)
    RelativeLayout rl17;
    @BindView(R.id.iv_18)
    ImageView iv18;
    @BindView(R.id.rl_18)
    RelativeLayout rl18;
    @BindView(R.id.iv_19)
    ImageView iv19;
    @BindView(R.id.rl_19)
    RelativeLayout rl19;
    @BindView(R.id.iv_20)
    ImageView iv20;
    @BindView(R.id.rl_20)
    RelativeLayout rl20;
    @BindView(R.id.iv_21)
    ImageView iv21;
    @BindView(R.id.rl_21)
    RelativeLayout rl21;
    private Intent intent;
    private String statusString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_activity);
        ButterKnife.bind(this);
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("type", statusString);
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
            }
        });
        topMid.setText("类型");
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
        rl12.setOnClickListener(this);
        rl13.setOnClickListener(this);
        rl14.setOnClickListener(this);
        rl15.setOnClickListener(this);
        rl16.setOnClickListener(this);
        rl17.setOnClickListener(this);
        rl18.setOnClickListener(this);
        rl19.setOnClickListener(this);
        rl20.setOnClickListener(this);
        rl21.setOnClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("type");
        if (statusString.equals("多层住宅")) {
            iv1.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("小高层")) {
            iv2.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("高层住宅")) {
            iv3.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("公寓")) {
            iv4.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("商铺")) {
            iv5.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("写字楼")) {
            iv6.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("商住楼")) {
            iv7.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("独栋别墅")) {
            iv8.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("联排别墅")) {
            iv9.setBackgroundResource(R.mipmap.choosed);
        }
        else if (statusString.equals("叠加别墅")) {
            iv10.setBackgroundResource(R.mipmap.choosed);
        }else if (statusString.equals("双拼别墅")) {
            iv11.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("太平墅")) {
            iv12.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("多层复式")) {
            iv13.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("民房")) {
            iv14.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("洋房")) {
            iv15.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("四合院")) {
            iv16.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("厂房")) {
            iv17.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("仓库")) {
            iv18.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("车库")) {
            iv19.setBackgroundResource(R.mipmap.choosed);
        } else if (statusString.equals("地皮")) {
            iv20.setBackgroundResource(R.mipmap.choosed);
        }
        else if (statusString.equals("其他")) {
            iv21.setBackgroundResource(R.mipmap.choosed);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_1:
                intent = new Intent();
                intent.putExtra("type", "多层住宅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_2:
                intent = new Intent();
                intent.putExtra("type", "小高层");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_3:
                intent = new Intent();
                intent.putExtra("type", "高层住宅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_4:
                intent = new Intent();
                intent.putExtra("type", "公寓");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_5:
                intent = new Intent();
                intent.putExtra("type", "商铺");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_6:
                intent = new Intent();
                intent.putExtra("type", "写字楼");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_7:
                intent = new Intent();
                intent.putExtra("type", "商住楼");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_8:
                intent = new Intent();
                intent.putExtra("type", "独栋别墅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_9:
                intent = new Intent();
                intent.putExtra("type", "联排别墅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_10:
                intent = new Intent();
                intent.putExtra("type", "叠加别墅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_11:
                intent = new Intent();
                intent.putExtra("type", "双拼别墅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_12:
                intent = new Intent();
                intent.putExtra("type", "太平墅");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_13:
                intent = new Intent();
                intent.putExtra("type", "多层复式");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_14:
                intent = new Intent();
                intent.putExtra("type", "民房");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_15:
                intent = new Intent();
                intent.putExtra("type", "洋房");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_16:
                intent = new Intent();
                intent.putExtra("type", "四合院");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_17:
                intent = new Intent();
                intent.putExtra("type", "厂房");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_18:
                intent = new Intent();
                intent.putExtra("type", "仓库");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_19:
                intent = new Intent();
                intent.putExtra("type", "车库");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_20:
                intent = new Intent();
                intent.putExtra("type", "地皮");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            case R.id.rl_21:
                intent = new Intent();
                intent.putExtra("type", "其他");
                TypeActivity.this.setResult(RESULT_OK, intent);
                TypeActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("type", statusString);
        TypeActivity.this.setResult(RESULT_OK, intent);
        TypeActivity.this.finish();
    }
}
