package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/23.
 */
public class SearchPassengerActivity extends AutoLayoutActivity implements View.OnClickListener {

    @BindView(R.id.top_search_left)
    ImageView topSearchLeft;
    @BindView(R.id.ll_return_btn)
    LinearLayout llReturnBtn;
    @BindView(R.id.top_search_mid)
    TextView topSearchMid;
    @BindView(R.id.ll_search_mid)
    LinearLayout llSearchMid;
    @BindView(R.id.top_search_right)
    TextView topSearchRight;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.et_1)
    EditText et1;
    @BindView(R.id.iv_passenger_search)
    ImageView ivPassengerSearch;
    @BindView(R.id.iv_passenger_delete)
    ImageView ivPassengerDelete;
    private Intent intent;
    //经纪人
    private static String broker_id;
    private String broker;
    //状态
    private String status;
    private int statusStatus;

    private int type_fragment;
    private String type_fragment_value;
    private String url = "admin/saleCustomer/ajax/myAndCommonSaleCustomer2";
    private String url1 = "admin/rentCustomer/ajax/myAndCommonRentCustomer2";
    private MyApplication my;
    private static String URL;
    //状态键值对应关系 tv3
    private String tvStatus_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_passenger_activity);
        ButterKnife.bind(this);
        intent = getIntent();
        my = (MyApplication) getApplication();
        type_fragment = intent.getIntExtra("type", 0);
        if (type_fragment == 0) {
            topSearchMid.setText("二手房");
            type_fragment_value = "oldHouses";
            URL = my.getURL() + url;
        } else if (type_fragment == 1) {
            topSearchMid.setText("租房");
            type_fragment_value = "rentHouses";
            URL = my.getURL() + url1;
        } else if (type_fragment == 2) {
            topSearchMid.setText("新房");
            type_fragment_value = "newHouses";
            URL = my.getURL() + url;
        }

        broker_id = "0";
        statusStatus = 0;
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        llSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                //状态对应关系
                //状态对应关系
                if (tv2.getText().toString().equals("正常")) {
                    tvStatus_value = "noFinish";
                } else if (tv2.getText().toString().equals("完成")) {
                    tvStatus_value = "Finish";
                } else if (tv2.getText().toString().equals("急需")) {
                    tvStatus_value = "jiXu";
                } else if (tv2.getText().toString().equals("无效")) {
                    tvStatus_value = "wuXiao";
                } else if (tv2.getText().toString().equals("暂缓")) {
                    tvStatus_value = "zanHuan";
                } else if (tv2.getText().toString().equals("已售")) {
                    tvStatus_value = "yiZhu";
                }
                if (statusStatus == 0) {
                    tvStatus_value = "";
                }
                if (type_fragment==1){
                    OkHttpUtils
                            .get()
                            .url(URL)
                            .addParams("search.user.id_eq", broker_id.equals("0") ? "" : broker_id)
                            .addParams("search.status_eq", tvStatus_value)
                            .addParams("gjz", et1.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("SearchActivity--1", "查询失败");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("SearchActivity--1", "查询成功" + response);
                                    Intent intent = new Intent(SearchPassengerActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                }else {
                    OkHttpUtils
                            .get()
                            .url(URL)
                            .addParams("search.user.id_eq", broker_id.equals("0") ? "" : broker_id)
                            .addParams("search.status_eq", tvStatus_value)
                            .addParams("search.type_eq", type_fragment_value)
                            .addParams("gjz", et1.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("SearchActivity--02", "查询失败");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("SearchActivity--02", "查询成功" + response);
                                    Intent intent = new Intent(SearchPassengerActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                }

                break;
            case R.id.ll_1:
                intent = new Intent(SearchPassengerActivity.this, BrokerActivity.class);
                intent.putExtra("broker", tv1.getText());
                intent.putExtra("broker_id", broker_id);
                Toast.makeText(SearchPassengerActivity.this, tv1.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_2:
                intent = new Intent(SearchPassengerActivity.this, StatusPassengerSearchActivity.class);
                intent.putExtra("status", tv2.getText());
                Toast.makeText(SearchPassengerActivity.this, tv2.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                broker = data.getStringExtra("broker");
                broker_id = data.getStringExtra("broker_id");
                tv1.setText(broker);
                break;
            case 2:
                status = data.getStringExtra("status");
                statusStatus = data.getIntExtra("statusStatus", statusStatus);
                tv2.setText(status);
                break;
            default:
                break;
        }
    }
}
