package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.BrokerListAdapter;
import com.love311.www.fanxun.adapter.HouseTypeListAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.BrokerBean;
import com.love311.www.fanxun.bean.HouseTypeBean;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/17.
 */
public class BrokerActivity extends AutoLayoutActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.lv_broker)
    ListView lvBroker;
    private Intent intent;
    private String statusString;
    private BrokerListAdapter adapter;
    //数据解析
    private String url = "admin/user/ajax/workerListN";
    private MyApplication my;
    private static String URL;
    private LinkedList<BrokerBean.ResBean> bean;
    private String broker_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("broker");
        broker_id = intent1.getStringExtra("broker_id");
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("broker", statusString);
                intent.putExtra("broker_id", broker_id);
                BrokerActivity.this.setResult(RESULT_OK, intent);
                BrokerActivity.this.finish();
            }
        });
        lvBroker.setOnItemClickListener(this);
        topMid.setText("经纪人");
        topRight.setVisibility(View.GONE);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("BrokerActivity", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        {
                            Log.e("BrokerActivity", response.toString() + "response");
                            Type listType = new TypeToken<LinkedList<BrokerBean.ResBean>>() {
                            }.getType();
                            Gson gson = new Gson();
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("res");
                                while (bean == null) {
                                    bean = gson.fromJson(jsonArray.toString(), listType);
                                    adapter = new BrokerListAdapter(BrokerActivity.this, bean);
                                    lvBroker.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("broker", statusString);
        intent.putExtra("broker_id", broker_id);
        BrokerActivity.this.setResult(RESULT_OK, intent);
        BrokerActivity.this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent = new Intent();
        intent.putExtra("broker", bean.get(i).getName());
        intent.putExtra("broker_id", bean.get(i).getId()+"");
        BrokerActivity.this.setResult(RESULT_OK, intent);
        BrokerActivity.this.finish();
    }
}
