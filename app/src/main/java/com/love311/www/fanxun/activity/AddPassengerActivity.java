package com.love311.www.fanxun.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
 * Created by Administrator on 2016/8/24.
 */
public class AddPassengerActivity extends AutoLayoutActivity implements View.OnClickListener {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.et_1)
    EditText et1;
    @BindView(R.id.iv_contacts)
    ImageView ivContacts;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.et_2)
    EditText et2;
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
    @BindView(R.id.et_3)
    EditText et3;
    @BindView(R.id.et_4)
    EditText et4;
    @BindView(R.id.et_5)
    EditText et5;
    @BindView(R.id.et_6)
    EditText et6;
    @BindView(R.id.house_number)
    TextView houseNumber;
    @BindView(R.id.et_7)
    EditText et7;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.ll_5)
    LinearLayout ll5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.ll_6)
    LinearLayout ll6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.ll_7)
    LinearLayout ll7;
    @BindView(R.id.et_8)
    EditText et8;
    @BindView(R.id.et_9)
    EditText et9;
    @BindView(R.id.et_10)
    EditText et10;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.ll_8)
    LinearLayout ll8;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.ll_rent_time)
    LinearLayout llRentTime;
    @BindView(R.id.rl_use)
    RelativeLayout rlUse;
    private int type_fragment;
    private String type_fragment_value;
    private Intent intent;
    private String passenger_remark;
    private String passenger_properties;
    private int passenger_properties_status;
    private String type;
    private String tvType_value;
    private int house_type_status;
    private int house_type_id;
    private String house_type;
    private String status;
    private int status_status;
    private String decoration;
    private int decoration_status;
    private String tvBaseDecoration_value;
    private String orientation;
    private int orientation_status;
    private String tvBaseOrientation_value;
    private String tv7_value;
    private static Toast toast;
    //性质
    private String tvProperty_value;
    //状态
    private String tvStatus_value;
    private String url = "admin/saleCustomer/ajax/createSaleCustomer";
    private MyApplication my;
    private static String URL;
    //租期
    private String rent_time;
    private int rent_time_status;
    private String tvRentTime_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_passenger_activity);
        ButterKnife.bind(this);
        intent = getIntent();
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        type_fragment = intent.getIntExtra("type", 0);
        if (type_fragment == 0) {
            topMid.setText("添加二手房客源");
            type_fragment_value = "oldHouses";
            llRentTime.setVisibility(View.GONE);
        } else if (type_fragment == 1) {
            topMid.setText("添加租房客源");
            type_fragment_value = "rentHouses";
            rlAddress.setVisibility(View.GONE);
            rlUse.setVisibility(View.GONE);
        } else if (type_fragment == 2) {
            topMid.setText("添加新房客源");
            type_fragment_value = "newHouses";
            llRentTime.setVisibility(View.GONE);
        }
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        ll7.setOnClickListener(this);
        ll8.setOnClickListener(this);
        topRight.setOnClickListener(this);
        passenger_properties_status = 0;
        house_type_status = 0;
        house_type_id = 0;
        status_status = 0;
        decoration_status = 0;
        orientation_status = 0;
        rent_time_status = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_1:
                intent = new Intent(AddPassengerActivity.this, PassengerRemarkActivity.class);
                intent.putExtra("passenger_remark", tv1.getText());
                Toast.makeText(AddPassengerActivity.this, tv1.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_2:
                intent = new Intent(AddPassengerActivity.this, PassengerPropertiesActivity.class);
                intent.putExtra("passenger_properties", tv2.getText());
                Toast.makeText(AddPassengerActivity.this, tv2.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 2);
                break;
            case R.id.ll_3:
                intent = new Intent(AddPassengerActivity.this, TypePassengerActivity.class);
                intent.putExtra("type", tv3.getText());
                Toast.makeText(AddPassengerActivity.this, tv3.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 3);
                break;
            case R.id.ll_4:
                intent = new Intent(AddPassengerActivity.this, HouseTypeActivity.class);
                intent.putExtra("house_type", tv4.getText());
                intent.putExtra("house_type_id", house_type_id);
                Toast.makeText(AddPassengerActivity.this, tv4.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 4);
                break;
            case R.id.ll_5:
                intent = new Intent(AddPassengerActivity.this, StatusPassengerActivity.class);
                intent.putExtra("status", tv5.getText());
                Toast.makeText(AddPassengerActivity.this, tv5.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 5);
                break;
            case R.id.ll_6:
                intent = new Intent(AddPassengerActivity.this, DecorationPassengerActivity.class);
                intent.putExtra("decoration", tv6.getText());
                Toast.makeText(AddPassengerActivity.this, tv6.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 6);
                break;
            case R.id.ll_7:
                intent = new Intent(AddPassengerActivity.this, OrientationPassengerActivity.class);
                intent.putExtra("orientation", tv7.getText());
                Toast.makeText(AddPassengerActivity.this, tv7.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 7);
                break;
            case R.id.ll_8:
                intent = new Intent(AddPassengerActivity.this, RentTimeActivity.class);
                intent.putExtra("rent_time", tv8.getText());
                Toast.makeText(AddPassengerActivity.this, tv8.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 8);
                break;
            case R.id.top_right:
                Log.d("type_fragment==333", type_fragment + "");
                if (et1.getText().toString().equals("")) {
                    showToast(this, "请填写电话！");
                } else if (et2.getText().toString().equals("")) {
                    showToast(this, "请填写姓名！");
                } else if (passenger_properties_status == 0) {
                    showToast(this, "请选择性质！");
                } else if (house_type_status == 0) {
                    showToast(this, "请选择楼型！");
                } else if (house_type_id == 0) {
                    showToast(this, "请选择户型！");
                } else if (status_status == 0) {
                    showToast(this, "请选择状态！");
                } else if (decoration_status == 0) {
                    showToast(this, "请选择装修！");
                } else if (orientation_status == 0) {
                    showToast(this, "请选择朝向！");
                } else if (et4.getText().toString().equals("")) {
                    showToast(this, "请填写租金下限！");
                } else if (et5.getText().toString().equals("")) {
                    showToast(this, "请填写租金上限！");
                } else if (et6.getText().toString().equals("")) {
                    showToast(this, "请填写面积下限！");
                } else if (et7.getText().toString().equals("")) {
                    showToast(this, "请填写面积上限！");
                } else if (et8.getText().toString().equals("")) {
                    showToast(this, "请填写楼层下限！");
                } else if (et9.getText().toString().equals("")) {
                    showToast(this, "请填写楼层上限！");
                } else if (type_fragment == 1) {
                    if (rent_time_status == 0) {
                        showToast(this, "请选择租期！");
                    }
                } else if (type_fragment == 0 || type_fragment == 2) {
                    if (et3.getText().toString().equals("")) {
                        showToast(this, "请填写地址！");
                    } else if (et10.getText().toString().equals("")) {
                        showToast(this, "请填写楼层用途！");
                    }

                } else if (tv1.getText().toString().equals("请完善客户个人信息等...")) {
                    tv1.setText("");
                    Log.d("type_fragment==222", type_fragment + "");
                } else {
                    Log.d("type_fragment==111", type_fragment + "");
                    //性质对应关系
                    if (tv2.getText().toString().equals("公客")) {
                        tvProperty_value = "communal";
                    } else if (tv2.getText().toString().equals("私客")) {
                        tvProperty_value = "personal";
                    }
                    //楼型对应关系
                    if (tv3.getText().toString().equals("低层")) {
                        tvType_value = "diCeng";
                    } else if (tv3.getText().toString().equals("多层")) {
                        tvType_value = "duoCeng";
                    } else if (tv3.getText().toString().equals("中高层")) {
                        tvType_value = "ZGCeng";
                    } else if (tv3.getText().toString().equals("高层")) {
                        tvType_value = "gaoCeng";
                    } else if (tv3.getText().toString().equals("无要求")) {
                        tvType_value = "no";
                    }
                    //状态对应关系
                    if (tv5.getText().toString().equals("正常")) {
                        tvStatus_value = "noFinish";
                    } else if (tv5.getText().toString().equals("完成")) {
                        tvStatus_value = "Finish";
                    } else if (tv5.getText().toString().equals("急需")) {
                        tvStatus_value = "jiXu";
                    } else if (tv5.getText().toString().equals("无效")) {
                        tvStatus_value = "wuXiao";
                    } else if (tv5.getText().toString().equals("暂缓")) {
                        tvStatus_value = "zanHuan";
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
                    } else if (tv6.getText().toString().equals("无要求")) {
                        tvBaseDecoration_value = "N";
                    }
                    //朝向对应关系
                    if (tv7.getText().toString().equals("不限")) {
                        tv7_value = "no";
                    } else if (tv7.getText().toString().equals("东")) {
                        tv7_value = "e";
                    } else if (tv7.getText().toString().equals("西")) {
                        tv7_value = "w";
                    } else if (tv7.getText().toString().equals("南")) {
                        tv7_value = "s";
                    } else if (tv7.getText().toString().equals("北")) {
                        tv7_value = "n";
                    } else if (tv7.getText().toString().equals("东西")) {
                        tv7_value = "ew";
                    } else if (tv7.getText().toString().equals("南北")) {
                        tv7_value = "sn";
                    } else if (tv7.getText().toString().equals("南南")) {
                        tv7_value = "ss";
                    } else if (tv7.getText().toString().equals("东南")) {
                        tv7_value = "es";
                    } else if (tv7.getText().toString().equals("东北")) {
                        tv7_value = "en";
                    } else if (tv7.getText().toString().equals("西南")) {
                        tv7_value = "ws";
                    } else if (tv7.getText().toString().equals("西北")) {
                        tv7_value = "wn";
                    } else if (tv7.getText().toString().equals("其他")) {
                        tv7_value = "o";
                    }
                    //租期对应关系
                    if (tv8.getText().toString().equals("三十天内")) {
                        tvRentTime_value = "san";
                    } else if (tv8.getText().toString().equals("一个月")) {
                        tvRentTime_value = "oneMonth";
                    } else if (tv8.getText().toString().equals("三个月")) {
                        tvRentTime_value = "threeMonth";
                    } else if (tv8.getText().toString().equals("半年")) {
                        tvRentTime_value = "banYear";
                    } else if (tv8.getText().toString().equals("一年")) {
                        tvRentTime_value = "year";
                    } else if (tv8.getText().toString().equals("一年以上")) {
                        tvRentTime_value = "yearS";
                    }
                    Log.d("type_fragment==", type_fragment + "");
                    if (type_fragment == 0 || type_fragment == 2) {
                        OkHttpUtils
                                .post()
                                .url(URL)
                                .addParams("name", et1.getText().toString())
                                .addParams("phone", et2.getText().toString())
                                .addParams("address", et3.getText().toString())
                                .addParams("housesPurpose", et10.getText().toString())
                                .addParams("lowPrice", et4.getText().toString())
                                .addParams("tallPrice", et5.getText().toString())
                                .addParams("proportion", et6.getText().toString() + "-" + et7.getText().toString())
                                .addParams("floor", et8.getText().toString() + "-" + et9.getText().toString())
                                .addParams("remark", tv1.getText().toString())
                                .addParams("customerNature", tvProperty_value)
                                .addParams("status", tvStatus_value)
                                .addParams("renovationInfo", tvBaseDecoration_value)
                                .addParams("housetype", type_fragment_value)
                                .addParams("huXing", house_type_id + "")
                                .addParams("floorType", tvType_value)
                                .addParams("orientation", tv7_value)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.e("AddUsedHouseActivity--", "链接访问失败！");
                                        Toast.makeText(AddPassengerActivity.this, "链接访问失败！", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.e("AddUsedHouseActivity--", "链接访问成功！" + response);
                                        AddPassengerActivity.this.finish();
                                    }
                                });
                    } else if (type_fragment == 1) {
                        OkHttpUtils
                                .post()
                                .url(URL)
                                .addParams("name", et1.getText().toString())
                                .addParams("phone", et2.getText().toString())
                                .addParams("lowPrice", et4.getText().toString())
                                .addParams("tallPrice", et5.getText().toString())
                                .addParams("proportion", et6.getText().toString() + "-" + et7.getText().toString())
                                .addParams("floor", et8.getText().toString() + "-" + et9.getText().toString())
                                .addParams("remark", tv1.getText().toString())
                                .addParams("customerNature", tvProperty_value)
                                .addParams("status", tvStatus_value)
                                .addParams("renovationInfo", tvBaseDecoration_value)
                                .addParams("housetype", type_fragment_value)
                                .addParams("huXing", house_type_id + "")
                                .addParams("floorType", tvType_value)
                                .addParams("orientation", tv7_value)
                                .addParams("rentTerm", tvRentTime_value)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.e("AddUsedHouseActivity--", "链接访问失败！");
                                        Toast.makeText(AddPassengerActivity.this, "链接访问失败！22", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.e("AddUsedHouseActivity--", "链接访问成功！" + response);
                                        AddPassengerActivity.this.finish();
                                    }
                                });
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                passenger_remark = data.getStringExtra("passenger_remark");
                tv1.setText(passenger_remark);
                break;
            case 2:
                passenger_properties = data.getStringExtra("passenger_properties");
                passenger_properties_status = data.getIntExtra("passenger_properties_status", passenger_properties_status);
                tv2.setText(passenger_properties);
                et4.requestFocus();
                break;
            case 3:
                type = data.getStringExtra("type");
                house_type_status = data.getIntExtra("house_type_status", house_type_status);
                tv3.setText(type);
                et4.requestFocus();
                break;
            case 4:
                house_type = data.getStringExtra("house_type");
                house_type_id = data.getIntExtra("house_type_id", house_type_id);
                tv4.setText(house_type);
                et4.requestFocus();
                break;
            case 5:
                status = data.getStringExtra("status");
                status_status = data.getIntExtra("status_status", status_status);
                tv5.setText(status);
                et4.requestFocus();
                break;
            case 6:
                decoration = data.getStringExtra("decoration");
                decoration_status = data.getIntExtra("decoration_status", decoration_status);
                tv6.setText(decoration);
                et4.requestFocus();
                break;
            case 7:
                orientation = data.getStringExtra("orientation");
                orientation_status = data.getIntExtra("orientation_status", orientation_status);
                tv7.setText(orientation);
                et4.requestFocus();
                break;
            case 8:
                rent_time = data.getStringExtra("rent_time");
                rent_time_status = data.getIntExtra("rent_time_status", rent_time_status);
                tv8.setText(rent_time);
                et4.requestFocus();
                break;
        }
    }

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
