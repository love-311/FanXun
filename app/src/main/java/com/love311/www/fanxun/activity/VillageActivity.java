package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.SearchListAdapter;
import com.love311.www.fanxun.adapter.SearchRecycleAdapter;
import com.love311.www.fanxun.adapter.UsedHouseRecycleViewAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.SearchBean;
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
 * Created by Administrator on 2016/8/18.
 */
public class VillageActivity extends AutoLayoutActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.lv_search)
    ListView lvSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    private String url = "admin/community/ajax/listSearch?a=0.4394681423251181";
    private MyApplication my;
    private static String URL;
    private LinkedList<SearchBean.ResBean.ContentBean> bean;
    private SearchListAdapter adapter;
    private SearchRecycleAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Intent intent;
    private String statusString;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.village_activity);
        ButterKnife.bind(this);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        lvSearch.setOnItemClickListener(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("village");
//        adapter.notifyDataSetChanged();
        linearLayoutManager = new LinearLayoutManager(this);
        //lvSearch.setLayoutManager(linearLayoutManager);
        mAdapter = new SearchRecycleAdapter(VillageActivity.this);
        //lvSearch.setAdapter(mAdapter);
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("village", statusString);
                VillageActivity.this.setResult(RESULT_OK, intent);
                VillageActivity.this.finish();
            }
        });
        topMid.setText("查询小区");
        topRight.setVisibility(View.GONE);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils
                        .get()
                        .url(URL)
                        .addParams("gjz",etSearch.getText().toString())
                        .addParams("page.pn","1")
                        .addParams("page.size","15")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("VillageActivity", "http error!");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                    Log.e("VillageActivity", response.toString()+"response");
                                    Type listType = new TypeToken<LinkedList<SearchBean.ResBean.ContentBean>>() {
                                    }.getType();
                                    Gson gson = new Gson();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                                        JSONArray jsonArray = jsonObject1.getJSONArray("content");
                                        Log.d("jsonElements--------", jsonArray.toString());
                                        while (bean==null){
                                            bean = gson.fromJson(jsonArray.toString(), listType);
                                            //mAdapter.addAll(bean,0);
                                            adapter = new SearchListAdapter(VillageActivity.this,bean);
                                            lvSearch.setAdapter(adapter);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                        });
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent = new Intent();
        intent.putExtra("village", bean.get(i).getName()+"("+bean.get(i).getArea()+")");
        VillageActivity.this.setResult(RESULT_OK, intent);
        VillageActivity.this.finish();
    }
    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("village", statusString);
        VillageActivity.this.setResult(RESULT_OK, intent);
        VillageActivity.this.finish();
    }
}
