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
import com.love311.www.fanxun.adapter.HouseTypeListAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.HouseTypeBean;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HouseTypeActivity extends AutoLayoutActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.lv_house_type)
    ListView lvHouseType;
    private Intent intent;
    private String statusString;
    private HouseTypeListAdapter adapter;
    //数据解析
    private String url = "admin/huXing/ajax/list?a=0.3765979291655326&page.pn=1&page.size=15";
    private MyApplication my;
    private static String URL;
    private LinkedList<HouseTypeBean.ResBean.ContentBean> bean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_type_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("house_type");
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("house_type", statusString);
                HouseTypeActivity.this.setResult(RESULT_OK, intent);
                HouseTypeActivity.this.finish();
            }
        });
        lvHouseType.setOnItemClickListener(this);
        topMid.setText("户型");
        topRight.setVisibility(View.GONE);
        my = (MyApplication)getApplication();
        URL = my.getURL() + url;
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("HouseTypeActivity", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        {
                            Log.e("HouseTypeActivity",response.toString()+ "response");
                            Type listType = new TypeToken<LinkedList<HouseTypeBean.ResBean.ContentBean>>() {
                            }.getType();
                            Gson gson = new Gson();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                                JSONArray jsonArray = jsonObject1.getJSONArray("content");
                                Log.d("jsonElements--------", jsonArray.toString());
                                while (bean==null){
                                    bean = gson.fromJson(jsonArray.toString(), listType);
                                    adapter = new HouseTypeListAdapter(HouseTypeActivity.this,bean);
                                    lvHouseType.setAdapter(adapter);
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
        intent.putExtra("house_type", statusString);
        HouseTypeActivity.this.setResult(RESULT_OK, intent);
        HouseTypeActivity.this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent = new Intent();
        intent.putExtra("house_type", bean.get(i).getName());
        HouseTypeActivity.this.setResult(RESULT_OK, intent);
        HouseTypeActivity.this.finish();
    }
}
