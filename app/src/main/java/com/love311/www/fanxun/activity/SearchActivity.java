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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.SearchParameterBean;
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
public class SearchActivity extends AutoLayoutActivity implements View.OnClickListener {

    @BindView(R.id.top_search_left)
    ImageView topSearchLeft;
    @BindView(R.id.top_search_mid)
    TextView topSearchMid;
    @BindView(R.id.ll_search_mid)
    LinearLayout llSearchMid;
    @BindView(R.id.top_search_right)
    TextView topSearchRight;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    @BindView(R.id.et_1)
    EditText et1;
    @BindView(R.id.et_2)
    EditText et2;
    @BindView(R.id.et_3)
    EditText et3;
    @BindView(R.id.et_4)
    EditText et4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.ll_5)
    LinearLayout ll5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.ll_6)
    LinearLayout ll6;
    @BindView(R.id.et_5)
    EditText et5;
    @BindView(R.id.et_6)
    EditText et6;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.et_7)
    EditText et7;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.ll_return_btn)
    LinearLayout llReturnBtn;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    private Intent intent;
    //经纪人
    private String broker_id;
    private String broker;
    //板块
    private String plate_id;
    private String plate;
    //状态
    private String status;
    //楼型
    private String type;
    private int house_type_status;
    //户型
    private String house_type_id;
    private String house_type;
    //装修
    private String decoration;
    private int decoration_status;
    private int statusStatus;

    private int type_fragment;
    private String type_fragment_value;
    private String url = "admin/houses/";
    private MyApplication my;
    private static String URL;
    //状态键值对应关系 tv3
    private String tvStatus_value;
    //房型键值对应关系 tvType
    private String tvType_value;
    //装修键值对应关系 tvBaseDecoration
    private String tvBaseDecoration_value;
    private SearchParameterBean.ResBean bean;
    //记录搜索状态
    private SharedPreferences searchUsedSharedPreferences;
    private SharedPreferences.Editor usedEditor;
    private SharedPreferences searchRentSharedPreferences;
    private SharedPreferences.Editor rentEditor;
    private SharedPreferences searchNewSharedPreferences;
    private SharedPreferences.Editor newEditor;
    //取得搜索状态
    private SharedPreferences getUsedSearchSharedPreferences;
    private SharedPreferences getRentSearchSharedPreferences;
    private SharedPreferences getNewSearchSharedPreferences;
    private String broker_pre, plate_pre, status_pre, floor_type_pre, low_price_pre,
            tall_price_pre, low_area_pre, tall_area_pre, house_type_pre, decoration_pre, low_floor_pre, tall_floor_pre, key_word_pre;
    private int status_status_pre, floor_type_status_pre, decoration_status_pre;
    private String broker_id_value, plate_id_value, house_type_id_value;
    private String SearchUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        //二手房搜索状态
        searchUsedSharedPreferences = getSharedPreferences("search_used_status", MODE_PRIVATE);
        usedEditor = searchUsedSharedPreferences.edit();
        getUsedSearchSharedPreferences = getSharedPreferences("search_used_status", MODE_PRIVATE);
        //租房搜索状态
        searchRentSharedPreferences = getSharedPreferences("search_rent_status", MODE_PRIVATE);
        rentEditor = searchRentSharedPreferences.edit();
        getRentSearchSharedPreferences = getSharedPreferences("search_rent_status", MODE_PRIVATE);
        //新房搜索状态
        searchNewSharedPreferences = getSharedPreferences("search_new_status", MODE_PRIVATE);
        newEditor = searchNewSharedPreferences.edit();
        getNewSearchSharedPreferences = getSharedPreferences("search_new_status", MODE_PRIVATE);
        intent = getIntent();
        type_fragment = intent.getIntExtra("type", 0);
        if (type_fragment == 0) {
            topSearchMid.setText("二手房");
            type_fragment_value = "getOldHouses?";
            tvMoney.setText("价    格：");
            tvPrice.setText("万");
        } else if (type_fragment == 1) {
            topSearchMid.setText("租房");
            type_fragment_value = "getRentHouses?";
        } else if (type_fragment == 2) {
            topSearchMid.setText("新房");
            type_fragment_value = "getNewHouses?";
            tvMoney.setText("价    格：");
            tvPrice.setText("万");
        }
        my = (MyApplication) getApplication();
        URL = my.getURL() + url + type_fragment_value;
        if (getUsedSearchSharedPreferences.getString("broker_id", "pre") != "pre") {
            broker_id = getUsedSearchSharedPreferences.getString("broker_id", "pre");
        } else {
            broker_id = "0";
        }
        if (getUsedSearchSharedPreferences.getString("plate_id", "pre") != "pre") {
            plate_id = getUsedSearchSharedPreferences.getString("plate_id", "pre");
        } else {
            plate_id = "0";
        }
        if (getUsedSearchSharedPreferences.getString("house_type_id", "pre") != "pre") {
            house_type_id = getUsedSearchSharedPreferences.getString("house_type_id", "pre");
        } else {
            house_type_id = "0";
        }
        if (getUsedSearchSharedPreferences.getInt("floor_type_status", 1000) != 1000) {
            house_type_status = getUsedSearchSharedPreferences.getInt("floor_type_status", 1000);
        } else {
            house_type_status = 0;
        }
        if (getUsedSearchSharedPreferences.getInt("status_status", 1000) != 1000) {
            statusStatus = getUsedSearchSharedPreferences.getInt("status_status", 1000);
        } else {
            statusStatus = 0;
        }
        if (getUsedSearchSharedPreferences.getInt("decoration_status", 1000) != 1000) {
            decoration_status = getUsedSearchSharedPreferences.getInt("decoration_status", 1000);
        } else {
            decoration_status = 0;
        }

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        llSearch.setOnClickListener(this);
        //llSearchMid.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        Log.d("getSearchShared=--", getUsedSearchSharedPreferences.toString() + "");
        if (type_fragment == 0) {
            Log.d("lxy", ".......0");
            if (getUsedSearchSharedPreferences.getInt("my_search_status", 0) == 66) {
                Log.d("lxy", ".......066");
                broker_pre = getUsedSearchSharedPreferences.getString("broker_id", "pre");
                broker_id_value = getUsedSearchSharedPreferences.getString("broker_id_value", "pre");
                plate_pre = getUsedSearchSharedPreferences.getString("plate_id", "pre");
                plate_id_value = getUsedSearchSharedPreferences.getString("plate_id_value", "pre");
                status_pre = getUsedSearchSharedPreferences.getString("status", "pre");
                status_status_pre = getUsedSearchSharedPreferences.getInt("status_status", 100);
                floor_type_pre = getUsedSearchSharedPreferences.getString("floor_type", "pre");
                floor_type_status_pre = getUsedSearchSharedPreferences.getInt("floor_type_status", 100);
                low_price_pre = getUsedSearchSharedPreferences.getString("low_price", "pre");
                tall_price_pre = getUsedSearchSharedPreferences.getString("tall_price", "pre");
                low_area_pre = getUsedSearchSharedPreferences.getString("low_area", "pre");
                tall_area_pre = getUsedSearchSharedPreferences.getString("tall_area", "pre");
                house_type_pre = getUsedSearchSharedPreferences.getString("house_type_id", "pre");
                house_type_id_value = getUsedSearchSharedPreferences.getString("house_type_id_value", "pre");
                decoration_pre = getUsedSearchSharedPreferences.getString("decoration", "pre");
                decoration_status_pre = getUsedSearchSharedPreferences.getInt("decoration_status", 100);
                low_floor_pre = getUsedSearchSharedPreferences.getString("low_floor", "pre");
                tall_floor_pre = getUsedSearchSharedPreferences.getString("tall_floor", "pre");
                key_word_pre = getUsedSearchSharedPreferences.getString("key_word", "pre");
                if (broker_pre == "0") {
                    tv1.setText("不限");
                } else {
                    tv1.setText(broker_id_value);
                }
                if (plate_pre == "0") {
                    tv2.setText("不限");
                } else {
                    tv2.setText(plate_id_value);
                }
                if (status_status_pre == 0) {
                    tv3.setText("正常");
                } else {
                    tv3.setText(status_pre);
                }
                if (floor_type_status_pre == 0) {
                    tv4.setText("低层");
                } else {
                    tv4.setText(floor_type_pre);
                }
                if (house_type_pre == "0") {
                    tv5.setText("其他");
                } else {
                    tv5.setText(house_type_id_value);
                }
                if (decoration_pre == "0") {
                    tv6.setText("清水");
                } else {
                    tv6.setText(decoration_pre);
                }
                et1.setText(low_price_pre);
                et2.setText(tall_price_pre);
                et3.setText(low_area_pre);
                et4.setText(tall_area_pre);
                et5.setText(low_floor_pre);
                et6.setText(tall_floor_pre);
                et7.setText(key_word_pre);
            }
        } else if (type_fragment == 1) {
            Log.d("lxy", ".......1");
            if (getRentSearchSharedPreferences.getInt("my_search_status", 0) == 66) {
                Log.d("lxy", ".......1 66");
                broker_pre = getRentSearchSharedPreferences.getString("broker_id", "pre");
                broker_id_value = getRentSearchSharedPreferences.getString("broker_id_value", "pre");
                plate_pre = getRentSearchSharedPreferences.getString("plate_id", "pre");
                plate_id_value = getRentSearchSharedPreferences.getString("plate_id_value", "pre");
                status_pre = getRentSearchSharedPreferences.getString("status", "pre");
                status_status_pre = getRentSearchSharedPreferences.getInt("status_status", 100);
                floor_type_pre = getRentSearchSharedPreferences.getString("floor_type", "pre");
                floor_type_status_pre = getRentSearchSharedPreferences.getInt("floor_type_status", 100);
                low_price_pre = getRentSearchSharedPreferences.getString("low_price", "pre");
                tall_price_pre = getRentSearchSharedPreferences.getString("tall_price", "pre");
                low_area_pre = getRentSearchSharedPreferences.getString("low_area", "pre");
                tall_area_pre = getRentSearchSharedPreferences.getString("tall_area", "pre");
                house_type_pre = getRentSearchSharedPreferences.getString("house_type_id", "pre");
                house_type_id_value = getRentSearchSharedPreferences.getString("house_type_id_value", "pre");
                decoration_pre = getRentSearchSharedPreferences.getString("decoration", "pre");
                decoration_status_pre = getRentSearchSharedPreferences.getInt("decoration_status", 100);
                low_floor_pre = getRentSearchSharedPreferences.getString("low_floor", "pre");
                tall_floor_pre = getRentSearchSharedPreferences.getString("tall_floor", "pre");
                key_word_pre = getRentSearchSharedPreferences.getString("key_word", "pre");
                if (broker_pre == "0") {
                    tv1.setText("不限");
                } else {
                    tv1.setText(broker_id_value);
                }
                if (plate_pre == "0") {
                    tv2.setText("不限");
                } else {
                    tv2.setText(plate_id_value);
                }
                if (status_status_pre == 0) {
                    tv3.setText("正常");
                } else {
                    tv3.setText(status_pre);
                }
                if (floor_type_status_pre == 0) {
                    tv4.setText("低层");
                } else {
                    tv4.setText(floor_type_pre);
                }
                if (house_type_pre == "0") {
                    tv5.setText("其他");
                } else {
                    tv5.setText(house_type_id_value);
                }
                if (decoration_pre == "0") {
                    tv6.setText("清水");
                } else {
                    tv6.setText(decoration_pre);
                }
                et1.setText(low_price_pre);
                et2.setText(tall_price_pre);
                et3.setText(low_area_pre);
                et4.setText(tall_area_pre);
                et5.setText(low_floor_pre);
                et6.setText(tall_floor_pre);
                et7.setText(key_word_pre);
            }
        } else if (type_fragment == 2) {
            Log.d("lxy", ".......2");
            if (getNewSearchSharedPreferences.getInt("my_search_status", 0) == 66) {
                Log.d("lxy", ".......2 66");
                broker_pre = getNewSearchSharedPreferences.getString("broker_id", "pre");
                broker_id_value = getNewSearchSharedPreferences.getString("broker_id_value", "pre");
                plate_pre = getNewSearchSharedPreferences.getString("plate_id", "pre");
                plate_id_value = getNewSearchSharedPreferences.getString("plate_id_value", "pre");
                status_pre = getNewSearchSharedPreferences.getString("status", "pre");
                status_status_pre = getNewSearchSharedPreferences.getInt("status_status", 100);
                floor_type_pre = getNewSearchSharedPreferences.getString("floor_type", "pre");
                floor_type_status_pre = getNewSearchSharedPreferences.getInt("floor_type_status", 100);
                low_price_pre = getNewSearchSharedPreferences.getString("low_price", "pre");
                tall_price_pre = getNewSearchSharedPreferences.getString("tall_price", "pre");
                low_area_pre = getNewSearchSharedPreferences.getString("low_area", "pre");
                tall_area_pre = getNewSearchSharedPreferences.getString("tall_area", "pre");
                house_type_pre = getNewSearchSharedPreferences.getString("house_type_id", "pre");
                house_type_id_value = getNewSearchSharedPreferences.getString("house_type_id_value", "pre");
                decoration_pre = getNewSearchSharedPreferences.getString("decoration", "pre");
                decoration_status_pre = getNewSearchSharedPreferences.getInt("decoration_status", 100);
                low_floor_pre = getNewSearchSharedPreferences.getString("low_floor", "pre");
                tall_floor_pre = getNewSearchSharedPreferences.getString("tall_floor", "pre");
                key_word_pre = getNewSearchSharedPreferences.getString("key_word", "pre");
                if (broker_pre == "0") {
                    tv1.setText("不限");
                } else {
                    tv1.setText(broker_id_value);
                }
                if (plate_pre == "0") {
                    tv2.setText("不限");
                } else {
                    tv2.setText(plate_id_value);
                }
                if (status_status_pre == 0) {
                    tv3.setText("正常");
                } else {
                    tv3.setText(status_pre);
                }
                if (floor_type_status_pre == 0) {
                    tv4.setText("低层");
                } else {
                    tv4.setText(floor_type_pre);
                }
                if (house_type_pre == "0") {
                    tv5.setText("其他");
                } else {
                    tv5.setText(house_type_id_value);
                }
                if (decoration_pre == "0") {
                    tv6.setText("清水");
                } else {
                    tv6.setText(decoration_pre);
                }
                et1.setText(low_price_pre);
                et2.setText(tall_price_pre);
                et3.setText(low_area_pre);
                et4.setText(tall_area_pre);
                et5.setText(low_floor_pre);
                et6.setText(tall_floor_pre);
                et7.setText(key_word_pre);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                Log.d("type_fragment==", type_fragment + "");
                if (type_fragment == 0) {
                    searchUsedHouse();
                } else if (type_fragment == 1) {
                    searchRentHouse();
                } else if (type_fragment == 2) {
                    searchNewHouse();
                }
                break;
            case R.id.ll_1:
                intent = new Intent(SearchActivity.this, BrokerActivity.class);
                intent.putExtra("broker", tv1.getText());
                intent.putExtra("broker_id", broker_id);
                Toast.makeText(SearchActivity.this, tv1.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_2:
                intent = new Intent(SearchActivity.this, PlateActivity.class);
                intent.putExtra("plate", tv2.getText());
                intent.putExtra("plate_id", plate_id);
                Toast.makeText(SearchActivity.this, tv2.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 2);
                break;
            case R.id.ll_3:
                intent = new Intent(SearchActivity.this, StatusActivity.class);
                intent.putExtra("status", tv3.getText());
                Toast.makeText(SearchActivity.this, tv3.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 3);
                break;
            case R.id.ll_4:
                intent = new Intent(SearchActivity.this, TypeActivity.class);
                intent.putExtra("type", tv4.getText());
                Toast.makeText(SearchActivity.this, tv4.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 4);
                break;
            case R.id.ll_5:
                intent = new Intent(SearchActivity.this, HouseTypeSearchActivity.class);
                intent.putExtra("house_type", tv5.getText());
                intent.putExtra("house_type_id", house_type_id);
                Toast.makeText(SearchActivity.this, tv5.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 5);
                break;
            case R.id.ll_6:
                intent = new Intent(SearchActivity.this, DecorationActivity.class);
                intent.putExtra("decoration", tv6.getText());
                Toast.makeText(SearchActivity.this, tv6.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 6);
                break;
            case R.id.ll_search_mid:
                showPopupWindow(view);
                break;
            case R.id.iv_search:
                if (type_fragment == 0) {
                    searchUsedHouse();
                } else if (type_fragment == 1) {
                    searchRentHouse();
                } else if (type_fragment == 2) {
                    searchNewHouse();
                }
                break;
            case R.id.iv_delete:
                if (type_fragment == 0) {
                    getUsedSearchSharedPreferences.edit().clear().commit();
                    tv1.setText("不限");
                    tv2.setText("不限");
                    tv3.setText("正常");
                    tv4.setText("低层");
                    tv5.setText("其他");
                    tv6.setText("清水");
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");
                    et5.setText("");
                    et6.setText("");
                    et7.setText("");
                    broker_id = "0";
                    plate_id = "0";
                    house_type_id = "0";
                    house_type_status = 0;
                    statusStatus = 0;
                    decoration_status = 0;
                } else if (type_fragment == 1) {
                    getRentSearchSharedPreferences.edit().clear().commit();
                    tv1.setText("不限");
                    tv2.setText("不限");
                    tv3.setText("正常");
                    tv4.setText("低层");
                    tv5.setText("其他");
                    tv6.setText("清水");
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");
                    et5.setText("");
                    et6.setText("");
                    et7.setText("");
                    broker_id = "0";
                    plate_id = "0";
                    house_type_id = "0";
                    house_type_status = 0;
                    statusStatus = 0;
                    decoration_status = 0;
                } else if (type_fragment == 2) {
                    getNewSearchSharedPreferences.edit().clear().commit();
                    tv1.setText("不限");
                    tv2.setText("不限");
                    tv3.setText("正常");
                    tv4.setText("低层");
                    tv5.setText("其他");
                    tv6.setText("清水");
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");
                    et5.setText("");
                    et6.setText("");
                    et7.setText("");
                    broker_id = "0";
                    plate_id = "0";
                    house_type_id = "0";
                    house_type_status = 0;
                    statusStatus = 0;
                    decoration_status = 0;
                }

                break;
            default:
                break;
        }
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
        //新房
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
        broker = data.getStringExtra("broker");

        plate = data.getStringExtra("plate");

        status = data.getStringExtra("status");
        statusStatus = data.getIntExtra("statusStatus", statusStatus);
        type = data.getStringExtra("type");
        house_type_status = data.getIntExtra("house_type_status", house_type_status);
        house_type = data.getStringExtra("house_type");

        decoration = data.getStringExtra("decoration");
        decoration_status = data.getIntExtra("decoration_status", decoration_status);
        switch (requestCode) {
            case 1:
                broker_id = data.getStringExtra("broker_id");
                tv1.setText(broker);
                break;
            case 2:
                plate_id = data.getStringExtra("plate_id");
                tv2.setText(plate);
                break;
            case 3:
                tv3.setText(status);
                break;
            case 4:
                tv4.setText(type);
                break;
            case 5:
                house_type_id = data.getStringExtra("house_type_id");
                tv5.setText(house_type);
                break;
            case 6:
                tv6.setText(decoration);
                break;
            default:
                break;
        }
    }

    private void searchUsedHouse() {
        //usedEditor.clear().commit();
        usedEditor.putInt("my_search_status", 66);
        usedEditor.putString("broker_id", broker_id);
        usedEditor.putString("broker_id_value", tv1.getText().toString());
        usedEditor.putString("plate_id", plate_id);
        usedEditor.putString("plate_id_value", tv2.getText().toString());
        usedEditor.putString("status", tv3.getText().toString());
        usedEditor.putInt("status_status", statusStatus);
        usedEditor.putString("floor_type", tv4.getText().toString());
        usedEditor.putInt("floor_type_status", house_type_status);
        usedEditor.putString("low_price", et1.getText().toString());
        usedEditor.putString("tall_price", et2.getText().toString());
        usedEditor.putString("low_area", et3.getText().toString());
        usedEditor.putString("tall_area", et4.getText().toString());
        usedEditor.putString("house_type_id", house_type_id);
        usedEditor.putString("house_type_id_value", tv5.getText().toString());
        usedEditor.putString("decoration", tv6.getText().toString());
        usedEditor.putInt("decoration_status", decoration_status);
        usedEditor.putString("low_floor", et5.getText().toString());
        usedEditor.putString("tall_floor", et6.getText().toString());
        usedEditor.putString("key_word", et7.getText().toString());
        usedEditor.commit();
        //状态对应关系
        if (tv3.getText().toString().equals("正常")) {
            tvStatus_value = "pass";
        } else if (tv3.getText().toString().equals("成交")) {
            tvStatus_value = "finish";
        } else if (tv3.getText().toString().equals("已租/已售")) {
            tvStatus_value = "already";
        } else if (tv3.getText().toString().equals("无效")) {
            tvStatus_value = "invalid";
        } else if (tv3.getText().toString().equals("暂不租/暂不售")) {
            tvStatus_value = "temporary";
        } else if (tv3.getText().toString().equals("待审")) {
            tvStatus_value = "doing";
        } else if (tv3.getText().toString().equals("驳回")) {
            tvStatus_value = "refuse";
        }
        //楼型对应关系
        if (tv4.getText().toString().equals("低层")) {
            tvType_value = "diCeng";
        } else if (tv4.getText().toString().equals("多层")) {
            tvType_value = "duoCeng";
        } else if (tv4.getText().toString().equals("中高层")) {
            tvType_value = "ZGCeng";
        } else if (tv4.getText().toString().equals("高层")) {
            tvType_value = "gaoCeng";
        }
        //装修对应关系
        if (tv6.getText().toString().equals("清水")) {
            tvBaseDecoration_value = "S";
        } else if (tv6.getText().toString().equals("简装")) {
            tvBaseDecoration_value = "L";
        } else if (tv6.getText().toString().equals("中装")) {
            tvBaseDecoration_value = "M";
        } else if (tv6.getText().toString().equals("精装")) {
            tvBaseDecoration_value = "H";
        } else if (tv6.getText().toString().equals("豪装")) {
            tvBaseDecoration_value = "XH";
        }
        if (statusStatus == 0) {
            tvStatus_value = "";
        }
        if (house_type_status == 0) {
            tvType_value = "";
        }
        if (decoration_status == 0) {
            tvBaseDecoration_value = "";
        }
        if (broker_id.equals("0")) {
            broker_id = "";
        }
        if (plate_id.equals("0")) {
            plate_id = "";
        }
        if (house_type_id.equals("0")) {
            house_type_id = "";
        }
        Log.d("SearchActivity---", URL + "search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + plate_id + "&search.salePrice_gte=" + et1.getText().toString()
                + "&search.salePrice_lte=" + et2.getText().toString()
                + "&search.proportion_gte=" + et3.getText().toString()
                + "&search.proportion_lte=" + et4.getText().toString()
                + "&search.floor_gte=" + et5.getText().toString()
                + "&search.floor_lte=" + et6.getText().toString()
                + "&search.user.id_eq=" + broker_id
                + "&search.huXing.id_eq=" + house_type_id + ""
                + "&search.renovationInfo_eq=" + tvBaseDecoration_value
                + "&search.floorType_eq=" + tvType_value
                + "&a=" + "0.5301073853690059"
                + "&gjz=" + et7.getText().toString()
                + "&page.pn=" + "1"
                + "&page.size=" + "10");
        SearchUrl = URL + "search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + plate_id + "&search.salePrice_gte=" + et1.getText().toString()
                + "&search.salePrice_lte=" + et2.getText().toString()
                + "&search.proportion_gte=" + et3.getText().toString()
                + "&search.proportion_lte=" + et4.getText().toString()
                + "&search.floor_gte=" + et5.getText().toString()
                + "&search.floor_lte=" + et6.getText().toString()
                + "&search.user.id_eq=" + broker_id
                + "&search.huXing.id_eq=" + house_type_id + ""
                + "&search.renovationInfo_eq=" + tvBaseDecoration_value
                + "&search.floorType_eq=" + tvType_value
                + "&gjz=" + et7.getText().toString();
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("search.status_eq", tvStatus_value)
                .addParams("search.community.area.id_eq", plate_id)
                .addParams("search.salePrice_gte", et1.getText().toString())
                .addParams("search.salePrice_lte", et2.getText().toString())
                .addParams("search.proportion_gte", et3.getText().toString())
                .addParams("search.proportion_lte", et4.getText().toString())
                .addParams("search.floor_gte", et5.getText().toString())
                .addParams("search.floor_lte", et6.getText().toString())
                .addParams("search.user.id_eq", broker_id)
                .addParams("search.huXing.id_eq", house_type_id)
                .addParams("search.renovationInfo_eq", tvBaseDecoration_value)
                .addParams("search.floorType_eq", tvType_value)
                .addParams("gjz", et7.getText().toString())
                .addParams("a", "0.5301073853690059")
                .addParams("page.pn", "1")
                .addParams("page.size", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("SearchActivity--", "查询失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType = new TypeToken<SearchParameterBean.ResBean>() {
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
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        intent.putExtra("fragment", 0);
                        intent.putExtra("type_fragment", type_fragment);
                        intent.putExtra("total_numbers", bean.getTotalElements());
                        intent.putExtra("search_url", SearchUrl);
                        intent.putExtra("from", 1);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void searchRentHouse() {
        //rentEditor.clear().commit();
        rentEditor.putInt("my_search_status", 66);
        rentEditor.putString("broker_id", broker_id);
        rentEditor.putString("broker_id_value", tv1.getText().toString());
        rentEditor.putString("plate_id", plate_id);
        rentEditor.putString("plate_id_value", tv2.getText().toString());
        rentEditor.putString("status", tv3.getText().toString());
        rentEditor.putInt("status_status", statusStatus);
        rentEditor.putString("floor_type", tv4.getText().toString());
        rentEditor.putInt("floor_type_status", house_type_status);
        rentEditor.putString("low_price", et1.getText().toString());
        rentEditor.putString("tall_price", et2.getText().toString());
        rentEditor.putString("low_area", et3.getText().toString());
        rentEditor.putString("tall_area", et4.getText().toString());
        rentEditor.putString("house_type_id", house_type_id);
        rentEditor.putString("house_type_id_value", tv5.getText().toString());
        rentEditor.putString("decoration", tv6.getText().toString());
        rentEditor.putInt("decoration_status", decoration_status);
        rentEditor.putString("low_floor", et5.getText().toString());
        rentEditor.putString("tall_floor", et6.getText().toString());
        rentEditor.putString("key_word", et7.getText().toString());
        rentEditor.commit();
        //状态对应关系
        if (tv3.getText().toString().equals("正常")) {
            tvStatus_value = "pass";
        } else if (tv3.getText().toString().equals("成交")) {
            tvStatus_value = "finish";
        } else if (tv3.getText().toString().equals("已租/已售")) {
            tvStatus_value = "already";
        } else if (tv3.getText().toString().equals("无效")) {
            tvStatus_value = "invalid";
        } else if (tv3.getText().toString().equals("暂不租/暂不售")) {
            tvStatus_value = "temporary";
        } else if (tv3.getText().toString().equals("待审")) {
            tvStatus_value = "doing";
        } else if (tv3.getText().toString().equals("驳回")) {
            tvStatus_value = "refuse";
        }
        //楼型对应关系
        if (tv4.getText().toString().equals("低层")) {
            tvType_value = "diCeng";
        } else if (tv4.getText().toString().equals("多层")) {
            tvType_value = "duoCeng";
        } else if (tv4.getText().toString().equals("中高层")) {
            tvType_value = "ZGCeng";
        } else if (tv4.getText().toString().equals("高层")) {
            tvType_value = "gaoCeng";
        }
        //装修对应关系
        if (tv6.getText().toString().equals("清水")) {
            tvBaseDecoration_value = "S";
        } else if (tv6.getText().toString().equals("简装")) {
            tvBaseDecoration_value = "L";
        } else if (tv6.getText().toString().equals("中装")) {
            tvBaseDecoration_value = "M";
        } else if (tv6.getText().toString().equals("精装")) {
            tvBaseDecoration_value = "H";
        } else if (tv6.getText().toString().equals("豪装")) {
            tvBaseDecoration_value = "XH";
        }
        if (statusStatus == 0) {
            tvStatus_value = "";
        }
        if (house_type_status == 0) {
            tvType_value = "";
        }
        if (decoration_status == 0) {
            tvBaseDecoration_value = "";
        }
        if (broker_id.equals("0")) {
            broker_id = "";
        }
        if (plate_id.equals("0")) {
            plate_id = "";
        }
        if (house_type_id.equals("0")) {
            house_type_id = "";
        }
        Log.d("SearchActivity---", URL + "search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + plate_id + "&search.salePrice_gte=" + et1.getText().toString()
                + "&search.salePrice_lte=" + et2.getText().toString()
                + "&search.proportion_gte=" + et3.getText().toString()
                + "&search.proportion_lte=" + et4.getText().toString()
                + "&search.floor_gte=" + et5.getText().toString()
                + "&search.floor_lte=" + et6.getText().toString()
                + "&search.user.id_eq=" + broker_id
                + "&search.huXing.id_eq=" + house_type_id + ""
                + "&search.renovationInfo_eq=" + tvBaseDecoration_value
                + "&search.floorType_eq=" + tvType_value
                + "&a=" + "0.5301073853690059"
                + "&gjz=" + et7.getText().toString()
                + "&page.pn=" + "1"
                + "&page.size=" + "10");
        SearchUrl = URL + "search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + plate_id + "&search.salePrice_gte=" + et1.getText().toString()
                + "&search.salePrice_lte=" + et2.getText().toString()
                + "&search.proportion_gte=" + et3.getText().toString()
                + "&search.proportion_lte=" + et4.getText().toString()
                + "&search.floor_gte=" + et5.getText().toString()
                + "&search.floor_lte=" + et6.getText().toString()
                + "&search.user.id_eq=" + broker_id
                + "&search.huXing.id_eq=" + house_type_id + ""
                + "&search.renovationInfo_eq=" + tvBaseDecoration_value
                + "&search.floorType_eq=" + tvType_value
                + "&gjz=" + et7.getText().toString();
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("search.status_eq", tvStatus_value)
                .addParams("search.community.area.id_eq", plate_id)
                .addParams("search.salePrice_gte", et1.getText().toString())
                .addParams("search.salePrice_lte", et2.getText().toString())
                .addParams("search.proportion_gte", et3.getText().toString())
                .addParams("search.proportion_lte", et4.getText().toString())
                .addParams("search.floor_gte", et5.getText().toString())
                .addParams("search.floor_lte", et6.getText().toString())
                .addParams("search.user.id_eq", broker_id)
                .addParams("search.huXing.id_eq", house_type_id)
                .addParams("search.renovationInfo_eq", tvBaseDecoration_value)
                .addParams("search.floorType_eq", tvType_value)
                .addParams("gjz", et7.getText().toString())
                .addParams("a", "0.5301073853690059")
                .addParams("page.pn", "1")
                .addParams("page.size", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("SearchActivity--", "查询失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType = new TypeToken<SearchParameterBean.ResBean>() {
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
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        intent.putExtra("fragment", 0);
                        intent.putExtra("type_fragment", type_fragment);
                        intent.putExtra("total_numbers", bean.getTotalElements());
                        intent.putExtra("search_url", SearchUrl);
                        intent.putExtra("from", 2);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void searchNewHouse() {
        //newEditor.clear().commit();
        newEditor.putInt("my_search_status", 66);
        newEditor.putString("broker_id", broker_id);
        newEditor.putString("broker_id_value", tv1.getText().toString());
        newEditor.putString("plate_id", plate_id);
        newEditor.putString("plate_id_value", tv2.getText().toString());
        newEditor.putString("status", tv3.getText().toString());
        newEditor.putInt("status_status", statusStatus);
        newEditor.putString("floor_type", tv4.getText().toString());
        newEditor.putInt("floor_type_status", house_type_status);
        newEditor.putString("low_price", et1.getText().toString());
        newEditor.putString("tall_price", et2.getText().toString());
        newEditor.putString("low_area", et3.getText().toString());
        newEditor.putString("tall_area", et4.getText().toString());
        newEditor.putString("house_type_id", house_type_id);
        newEditor.putString("house_type_id_value", tv5.getText().toString());
        newEditor.putString("decoration", tv6.getText().toString());
        newEditor.putInt("decoration_status", decoration_status);
        newEditor.putString("low_floor", et5.getText().toString());
        newEditor.putString("tall_floor", et6.getText().toString());
        newEditor.putString("key_word", et7.getText().toString());
        newEditor.commit();
        //状态对应关系
        if (tv3.getText().toString().equals("正常")) {
            tvStatus_value = "pass";
        } else if (tv3.getText().toString().equals("成交")) {
            tvStatus_value = "finish";
        } else if (tv3.getText().toString().equals("已租/已售")) {
            tvStatus_value = "already";
        } else if (tv3.getText().toString().equals("无效")) {
            tvStatus_value = "invalid";
        } else if (tv3.getText().toString().equals("暂不租/暂不售")) {
            tvStatus_value = "temporary";
        } else if (tv3.getText().toString().equals("待审")) {
            tvStatus_value = "doing";
        } else if (tv3.getText().toString().equals("驳回")) {
            tvStatus_value = "refuse";
        }
        //楼型对应关系
        if (tv4.getText().toString().equals("低层")) {
            tvType_value = "diCeng";
        } else if (tv4.getText().toString().equals("多层")) {
            tvType_value = "duoCeng";
        } else if (tv4.getText().toString().equals("中高层")) {
            tvType_value = "ZGCeng";
        } else if (tv4.getText().toString().equals("高层")) {
            tvType_value = "gaoCeng";
        }
        //装修对应关系
        if (tv6.getText().toString().equals("清水")) {
            tvBaseDecoration_value = "S";
        } else if (tv6.getText().toString().equals("简装")) {
            tvBaseDecoration_value = "L";
        } else if (tv6.getText().toString().equals("中装")) {
            tvBaseDecoration_value = "M";
        } else if (tv6.getText().toString().equals("精装")) {
            tvBaseDecoration_value = "H";
        } else if (tv6.getText().toString().equals("豪装")) {
            tvBaseDecoration_value = "XH";
        }
        if (statusStatus == 0) {
            tvStatus_value = "";
        }
        if (house_type_status == 0) {
            tvType_value = "";
        }
        if (decoration_status == 0) {
            tvBaseDecoration_value = "";
        }
        if (broker_id.equals("0")) {
            broker_id = "";
        }
        if (plate_id.equals("0")) {
            plate_id = "";
        }
        if (house_type_id.equals("0")) {
            house_type_id = "";
        }
        Log.d("SearchActivity---", URL + "search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + plate_id + "&search.salePrice_gte=" + et1.getText().toString()
                + "&search.salePrice_lte=" + et2.getText().toString()
                + "&search.proportion_gte=" + et3.getText().toString()
                + "&search.proportion_lte=" + et4.getText().toString()
                + "&search.floor_gte=" + et5.getText().toString()
                + "&search.floor_lte=" + et6.getText().toString()
                + "&search.user.id_eq=" + broker_id
                + "&search.huXing.id_eq=" + house_type_id + ""
                + "&search.renovationInfo_eq=" + tvBaseDecoration_value
                + "&search.floorType_eq=" + tvType_value
                + "&a=" + "0.5301073853690059"
                + "&gjz=" + et7.getText().toString()
                + "&page.pn=" + "1"
                + "&page.size=" + "10");
        SearchUrl = URL + "search.status_eq=" + tvStatus_value
                + "&search.community.area.id_eq="
                + plate_id + "&search.salePrice_gte=" + et1.getText().toString()
                + "&search.salePrice_lte=" + et2.getText().toString()
                + "&search.proportion_gte=" + et3.getText().toString()
                + "&search.proportion_lte=" + et4.getText().toString()
                + "&search.floor_gte=" + et5.getText().toString()
                + "&search.floor_lte=" + et6.getText().toString()
                + "&search.user.id_eq=" + broker_id
                + "&search.huXing.id_eq=" + house_type_id + ""
                + "&search.renovationInfo_eq=" + tvBaseDecoration_value
                + "&search.floorType_eq=" + tvType_value
                + "&gjz=" + et7.getText().toString();
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("search.status_eq", tvStatus_value)
                .addParams("search.community.area.id_eq", plate_id)
                .addParams("search.salePrice_gte", et1.getText().toString())
                .addParams("search.salePrice_lte", et2.getText().toString())
                .addParams("search.proportion_gte", et3.getText().toString())
                .addParams("search.proportion_lte", et4.getText().toString())
                .addParams("search.floor_gte", et5.getText().toString())
                .addParams("search.floor_lte", et6.getText().toString())
                .addParams("search.user.id_eq", broker_id)
                .addParams("search.huXing.id_eq", house_type_id)
                .addParams("search.renovationInfo_eq", tvBaseDecoration_value)
                .addParams("search.floorType_eq", tvType_value)
                .addParams("gjz", et7.getText().toString())
                .addParams("a", "0.5301073853690059")
                .addParams("page.pn", "1")
                .addParams("page.size", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("SearchActivity--", "查询失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType = new TypeToken<SearchParameterBean.ResBean>() {
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
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        intent.putExtra("fragment", 0);
                        intent.putExtra("type_fragment", type_fragment);
                        intent.putExtra("total_numbers", bean.getTotalElements());
                        intent.putExtra("search_url", SearchUrl);
                        intent.putExtra("from", 3);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
