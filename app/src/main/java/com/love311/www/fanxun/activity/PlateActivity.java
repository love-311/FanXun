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
import com.love311.www.fanxun.adapter.PlateListAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.PlateBean;
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
public class PlateActivity extends AutoLayoutActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.lv_plate)
    ListView lvplate;
    private Intent intent;
    private String statusString;
    private PlateListAdapter adapter;
    //数据解析
    private String url = "admin/area/listByAdmin?a=0.2778611467764922&page.pn=1&page.size=15";
    private MyApplication my;
    private static String URL;
    private LinkedList<PlateBean.ResBean.ContentBean> bean;
    private String plate_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plate_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("plate");
        plate_id = intent1.getStringExtra("plate_id");
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("plate", statusString);
                intent.putExtra("plate_id", plate_id);
                PlateActivity.this.setResult(RESULT_OK, intent);
                PlateActivity.this.finish();
            }
        });
        lvplate.setOnItemClickListener(this);
        topMid.setText("板块");
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
                        Log.e("plateActivity", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        {
                            Log.e("plateActivity", response.toString() + "response");
                            Type listType = new TypeToken<LinkedList<PlateBean.ResBean.ContentBean>>() {
                            }.getType();
                            Gson gson = new Gson();
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                                JSONArray jsonArray = jsonObject1.getJSONArray("content");
                                while (bean == null) {
                                    bean = gson.fromJson(jsonArray.toString(), listType);
                                    adapter = new PlateListAdapter(PlateActivity.this, bean);
                                    lvplate.setAdapter(adapter);
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
        intent.putExtra("plate", statusString);
        intent.putExtra("plate_id", plate_id);
        PlateActivity.this.setResult(RESULT_OK, intent);
        PlateActivity.this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent = new Intent();
        intent.putExtra("plate", bean.get(i).getName());
        intent.putExtra("plate_id", bean.get(i).getId()+"");
        PlateActivity.this.setResult(RESULT_OK, intent);
        PlateActivity.this.finish();
    }
}
