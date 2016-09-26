package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.SearchPassengerParameterBean;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

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
    private String broker_id;
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
    private SearchPassengerParameterBean.ResBean bean;
    //记录搜索状态
    private SharedPreferences searchPassengerUsedSharedPreferences;
    private SharedPreferences.Editor usedEditor;
    private SharedPreferences searchPassengerRentSharedPreferences;
    private SharedPreferences.Editor rentEditor;
    private SharedPreferences searchPassengerNewSharedPreferences;
    private SharedPreferences.Editor newEditor;
    //取得搜索状态
    private SharedPreferences getUsedSearchPassengerSharedPreferences;
    private SharedPreferences getRentSearchPassengerSharedPreferences;
    private SharedPreferences getNewSearchPassengerSharedPreferences;
    private String broker_pre, status_pre, key_word_pre;
    private int status_status_pre;
    private String broker_id_value;
    private String searchPassengerUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_passenger_activity);
        ButterKnife.bind(this);
        llReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //二手房搜索状态
        searchPassengerUsedSharedPreferences = getSharedPreferences("search_passenger_used_status", MODE_PRIVATE);
        usedEditor = searchPassengerUsedSharedPreferences.edit();
        getUsedSearchPassengerSharedPreferences = getSharedPreferences("search_passenger_used_status", MODE_PRIVATE);
        //租房搜索状态
        searchPassengerRentSharedPreferences = getSharedPreferences("search_passenger_rent_status", MODE_PRIVATE);
        rentEditor = searchPassengerRentSharedPreferences.edit();
        getRentSearchPassengerSharedPreferences = getSharedPreferences("search_passenger_rent_status", MODE_PRIVATE);
        //新房搜索状态
        searchPassengerNewSharedPreferences = getSharedPreferences("search_passenger_new_status", MODE_PRIVATE);
        newEditor = searchPassengerNewSharedPreferences.edit();
        getNewSearchPassengerSharedPreferences = getSharedPreferences("search_passenger_new_status", MODE_PRIVATE);
        intent = getIntent();
        my = (MyApplication) getApplication();
        type_fragment = intent.getIntExtra("type", 0);
        if (type_fragment == 0) {
            topSearchMid.setText("二手房");
        } else if (type_fragment == 1) {
            topSearchMid.setText("租房");
        } else if (type_fragment == 2) {
            topSearchMid.setText("新房");
        }
        URL = my.getURL();

        if (getUsedSearchPassengerSharedPreferences.getString("broker_id", "pre")!="pre" ){
            broker_id =getUsedSearchPassengerSharedPreferences.getString("broker_id", "pre");
        }else {
            broker_id = "0";
        }
        if (getUsedSearchPassengerSharedPreferences.getInt("status_status", 1000)!=1000){
            statusStatus = getUsedSearchPassengerSharedPreferences.getInt("status_status", 1000);
        }else {
            Log.d("ssss","dsdasdsa");
            statusStatus = 0;
        }
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        llSearch.setOnClickListener(this);
        ivPassengerSearch.setOnClickListener(this);
        ivPassengerDelete.setOnClickListener(this);
        llSearchMid.setOnClickListener(this);
        if (type_fragment == 0) {
            if (getUsedSearchPassengerSharedPreferences.getInt("my_search_status", 0) == 66) {
                broker_pre = getUsedSearchPassengerSharedPreferences.getString("broker_id", "pre");
                broker_id_value = getUsedSearchPassengerSharedPreferences.getString("broker_id_value", "pre");
                status_pre = getUsedSearchPassengerSharedPreferences.getString("status", "pre");
                status_status_pre = getUsedSearchPassengerSharedPreferences.getInt("status_status", 100);
                key_word_pre = getUsedSearchPassengerSharedPreferences.getString("key_word", "pre");
                if (broker_pre == "0") {
                    tv1.setText("不限");
                } else {
                    tv1.setText(broker_id_value);
                }
                if (status_status_pre == 0) {
                    tv2.setText("不限");
                } else {
                    tv2.setText(status_pre);
                }
                et1.setText(key_word_pre);
            }
        } else if (type_fragment == 1) {
            if (getRentSearchPassengerSharedPreferences.getInt("my_search_status", 0) == 66) {
                broker_pre = getRentSearchPassengerSharedPreferences.getString("broker_id", "pre");
                broker_id_value = getRentSearchPassengerSharedPreferences.getString("broker_id_value", "pre");
                status_pre = getRentSearchPassengerSharedPreferences.getString("status", "pre");
                status_status_pre = getRentSearchPassengerSharedPreferences.getInt("status_status", 100);
                key_word_pre = getRentSearchPassengerSharedPreferences.getString("key_word", "pre");
                if (broker_pre == "0") {
                    tv1.setText("不限");
                } else {
                    tv1.setText(broker_id_value);
                }
                if (status_status_pre == 0) {
                    tv2.setText("不限");
                } else {
                    tv2.setText(status_pre);
                }
                et1.setText(key_word_pre);
            }
        } else if (type_fragment == 2) {
            if (getNewSearchPassengerSharedPreferences.getInt("my_search_status", 0) == 66) {
                broker_pre = getNewSearchPassengerSharedPreferences.getString("broker_id", "pre");
                broker_id_value = getNewSearchPassengerSharedPreferences.getString("broker_id_value", "pre");
                status_pre = getNewSearchPassengerSharedPreferences.getString("status", "pre");
                status_status_pre = getNewSearchPassengerSharedPreferences.getInt("status_status", 100);
                if (broker_pre == "0") {
                    tv1.setText("不限");
                } else {
                    tv1.setText(broker_id_value);
                }
                if (status_status_pre == 0) {
                    tv2.setText("不限");
                } else {
                    tv2.setText(status_pre);
                }
                et1.setText(key_word_pre);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                if (type_fragment == 0) {
                    type_fragment_value = "oldHouses";
                    searchPassengerUsedHouse();
                    Log.d("searchPassengerUsed", "searchPassengerUsedHouse执行了。。");
                } else if (type_fragment == 1) {
                    type_fragment_value = "rentHouses";
                    searchPassengerRentHouse();
                    Log.d("searchPassengerRent", "searchPassengerRentHouse执行了。。");
                } else if (type_fragment == 2) {
                    type_fragment_value = "newHouses";
                    searchPassengerNewHouse();
                    Log.d("searchPassengerNew", "searchPassengerNew执行了。。");
                }
                break;
            case R.id.ll_1:
                intent = new Intent(SearchPassengerActivity.this, BrokerActivity.class);
                intent.putExtra("broker", tv1.getText());
                intent.putExtra("broker_id", broker_id);
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_2:
                intent = new Intent(SearchPassengerActivity.this, StatusPassengerSearchActivity.class);
                intent.putExtra("status", tv2.getText());
                startActivityForResult(intent, 2);
                break;
//            case R.id.ll_search_mid:
//                showPopupWindow(view);
//                break;
            case R.id.iv_passenger_search:
                if (type_fragment == 0) {
                    type_fragment_value = "oldHouses";
                    searchPassengerUsedHouse();
                    Log.d("searchPassengerUsed", "searchPassengerUsedHouse执行了。。");
                } else if (type_fragment == 1) {
                    type_fragment_value = "rentHouses";
                    searchPassengerRentHouse();
                    Log.d("searchPassengerRent", "searchPassengerRentHouse执行了。。");
                } else if (type_fragment == 2) {
                    type_fragment_value = "newHouses";
                    searchPassengerNewHouse();
                    Log.d("searchPassengerNew", "searchPassengerNew执行了。。");
                }
                break;
            case R.id.iv_passenger_delete:
                if (type_fragment == 0) {
                    getUsedSearchPassengerSharedPreferences.edit().clear().commit();
                    tv1.setText("不限");
                    tv2.setText("不限");
                    et1.setText("");
                    broker_id = "0";
                    statusStatus = 0;
                } else if (type_fragment == 1) {
                    getRentSearchPassengerSharedPreferences.edit().clear().commit();
                    tv1.setText("不限");
                    tv2.setText("不限");
                    et1.setText("");
                    broker_id = "0";
                    statusStatus = 0;
                } else if (type_fragment == 2) {
                    getNewSearchPassengerSharedPreferences.edit().clear().commit();
                    tv1.setText("不限");
                    tv2.setText("不限");
                    et1.setText("");
                    broker_id = "0";
                    statusStatus = 0;
                }
                break;
            default:
                break;
        }
    }


    private void searchPassengerUsedHouse() {
        usedEditor.putInt("my_search_status", 66);
        usedEditor.putString("broker_id", broker_id);
        usedEditor.putString("broker_id_value", tv1.getText().toString());
        usedEditor.putString("status", tv2.getText().toString());
        usedEditor.putInt("status_status", statusStatus);
        usedEditor.putString("key_word", et1.getText().toString());
        usedEditor.commit();
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
        }else if (tv2.getText().toString().equals("不限")) {
            tvStatus_value = "";
        }
        if (statusStatus == 0) {
            tvStatus_value = "";
            Log.d("ssdd","dsda");
        }
        if (broker_id.equals("0")) {
            broker_id = "";
        }
        searchPassengerUrl = URL + "admin/saleCustomer/ajax/myAndCommonSaleCustomer2"+"?search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + "&search.user.id_eq=" + broker_id
                + "&gjz=" + et1.getText().toString();
            OkHttpUtils
                    .get()
                    .url(URL+url)
                    .addParams("search.user.id_eq", broker_id)
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
                            Type listType = new TypeToken<SearchPassengerParameterBean.ResBean>() {
                            }.getType();
                            Gson gson = new Gson();
                            try {
                                // Log.d("jsonElements--------", response);
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                                // Log.d("jsonElements--------", jsonObject1.toString());
                                while (bean == null) {
                                    bean = gson.fromJson(jsonObject1.toString(), listType);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Log.e("SearchActivity--", "查询成功" + response);
                            Intent intent = new Intent(SearchPassengerActivity.this, MainActivity.class);
                            intent.putExtra("fragment", 1);
                            intent.putExtra("type_fragment", type_fragment);
                            intent.putExtra("total_numbers", bean.getTotalElements());
                            intent.putExtra("search_url", searchPassengerUrl);
                            Log.d("dsdsd", "search_url=" + searchPassengerUrl);
                            intent.putExtra("from", 4);
                            startActivity(intent);
                            finish();
                        }
                    });
        }

    private void searchPassengerRentHouse() {
        rentEditor.putInt("my_search_status", 66);
        rentEditor.putString("broker_id", broker_id);
        rentEditor.putString("broker_id_value", tv1.getText().toString());
        rentEditor.putString("status", tv2.getText().toString());
        rentEditor.putInt("status_status", statusStatus);
        rentEditor.putString("key_word", et1.getText().toString());
        rentEditor.commit();
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
        }else if (tv2.getText().toString().equals("不限")) {
            tvStatus_value = "";
        }
        if (statusStatus == 0) {
            tvStatus_value = "";
        }
        if (broker_id.equals("0")) {
            broker_id = "";
        }
        searchPassengerUrl = URL +"admin/rentCustomer/ajax/myAndCommonRentCustomer2"+"?search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + "&search.user.id_eq=" + broker_id
                + "&gjz=" + et1.getText().toString();
            OkHttpUtils
                    .get()
                    .url(URL+url1)
                    .addParams("search.user.id_eq", broker_id)
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
                            Type listType = new TypeToken<SearchPassengerParameterBean.ResBean>() {
                            }.getType();
                            Gson gson = new Gson();
                            try {
                                //Log.d("jsonElements--------", response);
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                                //Log.d("jsonElements--------", jsonObject1.toString());
                                while (bean == null) {
                                    bean = gson.fromJson(jsonObject1.toString(), listType);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.e("SearchActivity--", "查询成功" + response);
                            Intent intent = new Intent(SearchPassengerActivity.this, MainActivity.class);
                            intent.putExtra("fragment", 1);
                            intent.putExtra("type_fragment", type_fragment);
                            intent.putExtra("total_numbers", bean.getTotalElements());
                            intent.putExtra("search_url", searchPassengerUrl);
                            intent.putExtra("from", 5);
                            startActivity(intent);
                            finish();
                        }
                    });
    }

    private void searchPassengerNewHouse() {
        newEditor.putInt("my_search_status", 66);
        newEditor.putString("broker_id", broker_id);
        newEditor.putString("broker_id_value", tv1.getText().toString());
        newEditor.putString("status", tv2.getText().toString());
        newEditor.putInt("status_status", statusStatus);
        newEditor.putString("key_word", et1.getText().toString());
        newEditor.commit();
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
        }else if (tv2.getText().toString().equals("不限")) {
            tvStatus_value = "";
        }
        if (statusStatus == 0) {
            tvStatus_value = "";
        }
        if (broker_id.equals("0")) {
            broker_id = "";
        }
        searchPassengerUrl = URL +  "admin/saleCustomer/ajax/myAndCommonSaleCustomer2"+"?search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + "&search.user.id_eq=" + broker_id
                + "&gjz=" + et1.getText().toString();
            OkHttpUtils
                    .get()
                    .url(URL+url)
                    .addParams("search.user.id_eq", broker_id)
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
                            Type listType = new TypeToken<SearchPassengerParameterBean.ResBean>() {
                            }.getType();
                            Gson gson = new Gson();
                            try {
                                Log.d("jsonElements--------", response);
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                                Log.d("jsonElements--------", jsonObject1.toString());
                                while (bean == null) {
                                    bean = gson.fromJson(jsonObject1.toString(), listType);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.e("SearchActivity--", "查询成功" + response);
                            Intent intent = new Intent(SearchPassengerActivity.this, MainActivity.class);
                            intent.putExtra("fragment", 1);
                            intent.putExtra("type_fragment", type_fragment);
                            intent.putExtra("total_numbers", bean.getTotalElements());
                            intent.putExtra("search_url", searchPassengerUrl);
                            intent.putExtra("from", 6);
                            startActivity(intent);
                            finish();
                        }
                    });
    }

    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.top_mid_pop_window, null);
        // 设置按钮的点击事件
        LinearLayout ll_1 = (LinearLayout) contentView.findViewById(R.id.ll_1);
        LinearLayout ll_2 = (LinearLayout) contentView.findViewById(R.id.ll_2);
        LinearLayout ll_3 = (LinearLayout) contentView.findViewById(R.id.ll_3);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.color.popwindow));
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view, 240, -8);
        //二手房
        ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type_fragment = 0;
                topSearchMid.setText("二手房");
                popupWindow.dismiss();
            }
        });
        //租房
        ll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type_fragment = 1;
                topSearchMid.setText("租房");
                popupWindow.dismiss();
            }
        });
        //时间升序
        ll_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type_fragment = 2;
                topSearchMid.setText("新房");
                popupWindow.dismiss();
            }
        });
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
                statusStatus = data.getIntExtra("status_status", statusStatus);
                tv2.setText(status);
                break;
            default:
                break;
        }
    }
}
