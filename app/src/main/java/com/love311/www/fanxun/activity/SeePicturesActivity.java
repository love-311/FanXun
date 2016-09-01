package com.love311.www.fanxun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.SeePicRecycleViewAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.SeePicBean;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SeePicturesActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.pic_grid)
    RecyclerView picGrid;
    private GridLayoutManager gridLayoutManager;
    private SeePicRecycleViewAdapter adapter;
    private String url = "admin/images/app/listByHouses?id=3445";
    private static String URL;
    private MyApplication my;
    private List<SeePicBean.ResBean> bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_pic_activity);
        ButterKnife.bind(this);
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topMid.setText("房源图片");
        topRight.setVisibility(View.GONE);
        gridLayoutManager = new GridLayoutManager(this, 3);
        picGrid.setLayoutManager(gridLayoutManager);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("SeePicActivity", "http error!!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("SeePicActivity", "response" + response);
                        Type listType = new TypeToken<List<SeePicBean.ResBean>>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("res");
                            bean = gson.fromJson(jsonArray.toString(), listType);
                            adapter = new SeePicRecycleViewAdapter(SeePicturesActivity.this, bean);
                            picGrid.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
