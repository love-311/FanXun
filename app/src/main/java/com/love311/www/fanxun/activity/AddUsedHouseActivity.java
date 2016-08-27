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
import android.widget.TextView;
import android.widget.Toast;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/15.
 */
public class AddUsedHouseActivity extends AutoLayoutActivity implements View.OnClickListener {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.ll_village)
    LinearLayout llVillage;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_contacts)
    ImageView ivContacts;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.et_owner_name)
    EditText etOwnerName;
    @BindView(R.id.house_number)
    TextView houseNumber;
    @BindView(R.id.ll_remark)
    LinearLayout llRemark;
    @BindView(R.id.ll_properties)
    LinearLayout llProperties;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.et_floor_numbers)
    EditText etFloorNumbers;
    @BindView(R.id.et_total_floor_numbers)
    EditText etTotalFloorNumbers;
    @BindView(R.id.ll_property_right)
    LinearLayout llPropertyRight;
    @BindView(R.id.et_years)
    EditText etYears;
    @BindView(R.id.ll_key)
    LinearLayout llKey;
    @BindView(R.id.ll_house_source_remark)
    LinearLayout llHouseSourceRemark;
    @BindView(R.id.ll_picture)
    LinearLayout llPicture;
    @BindView(R.id.tv_village)
    TextView tvVillage;
    @BindView(R.id.tv_base_orientation)
    TextView tvBaseOrientation;
    @BindView(R.id.tv_base_decoration)
    TextView tvBaseDecoration;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_properties)
    TextView tvProperties;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_property_right)
    TextView tvPropertyRight;
    @BindView(R.id.tv_house_source_remark)
    TextView tvHouseSourceRemark;
    @BindView(R.id.tv_picture)
    TextView tvPicture;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.tv_house_type)
    TextView tvHouseType;
    @BindView(R.id.ll_house_type)
    LinearLayout llHouseType;
    @BindView(R.id.ll_orientation)
    LinearLayout llOrientation;
    @BindView(R.id.tv_delegate)
    TextView tvDelegate;
    @BindView(R.id.ll_delegate)
    LinearLayout llDelegate;
    @BindView(R.id.ll_decoration)
    LinearLayout llDecoration;
    @BindView(R.id.tv_rent_time)
    TextView tvRentTime;
    @BindView(R.id.ll_rent_time)
    LinearLayout llRentTime;
    @BindView(R.id.et_seat_numbers)
    EditText etSeatNumbers;
    @BindView(R.id.et_unit_numbers)
    EditText etUnitNumbers;
    @BindView(R.id.et_house_number_name)
    EditText etHouseNumberName;
    @BindView(R.id.ll_rent_time_total)
    LinearLayout llRentTimeTotal;
    private Intent intent;
    private String orientation;
    private String decoration;
    private String owner_remark;
    private String status;
    private String properties;
    private String type;
    private String property_right;
    //  private String garage;
    private String key;
    private String house_source_remark;
    private String village;
    private String house_type;
    private String picture;
    private String delegate;
    private String rent_time;
    private ArrayList<String> listPath = new ArrayList<>();
    private String url = "admin/houses/ajax/createHouses";
    private MyApplication my;
    private static String URL;
    private int village_id;
    private int house_type_id;
    private int orientation_status;
    private int properties_status;
    private int decoration_status;
    private int house_type_status;
    private int property_right_status;
    private int key_status;
    private int delegate_status;
    private int rent_time_status;
    private static Toast toast;
    //产权键值对应关系 tvPropertyRight
    private String tvPropertyRight_value;
    //装修键值对应关系 tvBaseDecoration
    private String tvBaseDecoration_value;
    //性质键值对应关系 tvProperties
    private String tvProperties_value;
    //房型键值对应关系 tvType
    private String tvType_value;
    //钥匙键值对应关系 tvKey
    private String tvKey_value;
    //委托键值对应关系 tvDelegate
    private String tvDelegate_value;
    //方向键值对应关系 tvBaseOrientation
    private String tvBaseOrientation_value;
    //租期
    private String tvRentTime_value;
    private int type_fragment;
    private String type_fragment_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_used_house_activity);
        ButterKnife.bind(this);
        intent = getIntent();
        type_fragment = intent.getIntExtra("type", 0);
        if (type_fragment == 0) {
            topMid.setText("添加二手房");
            llRentTimeTotal.setVisibility(View.GONE);
            type_fragment_value = "oldHouses";
        } else if (type_fragment == 1) {
            topMid.setText("添加租房");
            type_fragment_value = "rentHouses";
        } else if (type_fragment == 2) {
            topMid.setText("添加新房");
            llRentTimeTotal.setVisibility(View.GONE);
            type_fragment_value = "newHouses";
        }
        llOrientation.setOnClickListener(this);
        llDecoration.setOnClickListener(this);
        llRemark.setOnClickListener(this);
        //llStatus.setOnClickListener(this);
        llProperties.setOnClickListener(this);
        llType.setOnClickListener(this);
        llPropertyRight.setOnClickListener(this);
        llKey.setOnClickListener(this);
        llHouseSourceRemark.setOnClickListener(this);
        llVillage.setOnClickListener(this);
        llHouseType.setOnClickListener(this);
        llPicture.setOnClickListener(this);
        llDelegate.setOnClickListener(this);
        llRentTime.setOnClickListener(this);
        topRight.setOnClickListener(this);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        village_id = 0;
        house_type_id = 0;
        orientation_status = 0;
        properties_status = 0;
        house_type_status = 0;
        decoration_status = 0;
        property_right_status = 0;
        key_status = 0;
        delegate_status = 0;
        rent_time_status = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_right:
                if (village_id == 0) {
                    showToast(this, "请选择小区!");
                } else if (etPrice.getText().toString().equals("")) {
                    showToast(this, "请填写价格!");
                } else if (etArea.getText().toString().equals("")) {
                    showToast(this, "请填写面积！");
                } else if (house_type_id == 0) {
                    showToast(this, "请选择户型！");
                } else if (orientation_status == 0) {
                    showToast(this, "请选择房屋朝向！");
                } else if (decoration_status == 0) {
                    showToast(this, "请选择房屋装修！");
                } else if (etPhone.getText().toString().equals("")) {
                    showToast(this, "请填写业主电话！");
                } else if (etOwnerName.getText().toString().equals("")) {
                    showToast(this, "请填写业主名字！");
                } else if (tvRemark.getText().toString().equals("请填写业主保密信息...")) {
                    tvRemark.setText("");
                } else if (properties_status == 0) {
                    showToast(this, "请选择房源性质！");
                } else if (house_type_status == 0) {
                    showToast(this, "请选择房源类型！");
                } else if (etSeatNumbers.getText().toString().equals("")) {
                    showToast(this, "请填写房源座栋！");
                } else if (etUnitNumbers.getText().toString().equals("")) {
                    showToast(this, "请填写房源单元！");
                } else if (etFloorNumbers.getText().toString().equals("")) {
                    showToast(this, "请填写房源楼层！");
                } else if (etTotalFloorNumbers.getText().toString().equals("")) {
                    showToast(this, "请填写房源总楼层！");
                } else if (etHouseNumberName.getText().toString().equals("")) {
                    showToast(this, "请填写房源房号！");
                } else if (property_right_status == 0) {
                    showToast(this, "请选择房源产权！");
                } else if (etYears.getText().toString().equals("")) {
                    showToast(this, "请填写房源年代！");
                } else if (key_status == 0) {
                    showToast(this, "请选择钥匙信息！");
                } else if (delegate_status == 0) {
                    showToast(this, "请选择委托方式！");
                } else if (type_fragment == 1) {
                    if (rent_time_status == 0) {
                        showToast(this, "请选择租期！");
                    }
                } else if (tvHouseSourceRemark.getText().toString().equals("请填写房源其它公开信息...")) {
                    tvHouseSourceRemark.setText("");
                } else {
                    Log.d("AddUsedHouseActivity---", tvPropertyRight.getText().toString());
                    Log.d("AddUsedHouseActivity---", tvPropertyRight.getText() + "");
                    //产权对应关系
                    if (tvPropertyRight.getText().toString().equals("商品房合同")) {
                        tvPropertyRight_value = "shangPingFangHeTong";
                    } else if (tvPropertyRight.getText().toString().equals("证齐")) {
                        tvPropertyRight_value = "zhengQi";
                    } else if (tvPropertyRight.getText().toString().equals("满两年")) {
                        tvPropertyRight_value = "manLiangNian";
                    } else if (tvPropertyRight.getText().toString().equals("满五年")) {
                        tvPropertyRight_value = "manWuNian";
                    } else if (tvPropertyRight.getText().toString().equals("抵押房")) {
                        tvPropertyRight_value = "diYaZhong";
                    } else if (tvPropertyRight.getText().toString().equals("房产证")) {
                        tvPropertyRight_value = "fangChanZheng";
                    } else if (tvPropertyRight.getText().toString().equals("拆迁协议")) {
                        tvPropertyRight_value = "chaiQianXieYi";
                    } else if (tvPropertyRight.getText().toString().equals("抵押房")) {
                        tvPropertyRight_value = "diYaFang";
                    }
                    //装修对应关系
                    if (tvBaseDecoration.getText().toString().equals("清水")) {
                        tvBaseDecoration_value = "S";
                    } else if (tvBaseDecoration.getText().toString().equals("简装")) {
                        tvBaseDecoration_value = "L";
                    } else if (tvBaseDecoration.getText().toString().equals("中装")) {
                        tvBaseDecoration_value = "M";
                    } else if (tvBaseDecoration.getText().toString().equals("精装")) {
                        tvBaseDecoration_value = "H";
                    } else if (tvBaseDecoration.getText().toString().equals("豪装")) {
                        tvBaseDecoration_value = "XH";
                    }
                    //房源性质对应关系
                    if (tvProperties.getText().toString().equals("商品房")) {
                        tvProperties_value = "shangPin";
                    } else if (tvProperties.getText().toString().equals("个人产权")) {
                        tvProperties_value = "personal";
                    }
                    //楼型对应关系
                    if (tvType.getText().toString().equals("低层")) {
                        tvType_value = "diCeng";
                    } else if (tvType.getText().toString().equals("多层")) {
                        tvType_value = "duoCeng";
                    } else if (tvType.getText().toString().equals("中高层")) {
                        tvType_value = "ZGCeng";
                    } else if (tvType.getText().toString().equals("高层")) {
                        tvType_value = "gaoCeng";
                    }
                    //钥匙对应关系
                    if (tvKey.getText().toString().equals("无钥匙")) {
                        tvKey_value = "noKey";
                    } else if (tvKey.getText().toString().equals("本公司")) {
                        tvKey_value = "company";
                    } else if (tvKey.getText().toString().equals("其他公司")) {
                        tvKey_value = "other";
                    }
                    //委托方式对应关系
                    if (tvDelegate.getText().toString().equals("网络委托")) {
                        tvDelegate_value = "netWork";
                    } else if (tvDelegate.getText().toString().equals("电话")) {
                        tvDelegate_value = "phone";
                    } else if (tvDelegate.getText().toString().equals("朋友")) {
                        tvDelegate_value = "friend";
                    } else if (tvDelegate.getText().toString().equals("中介")) {
                        tvDelegate_value = "intermediary";
                    } else if (tvDelegate.getText().toString().equals("其他")) {
                        tvDelegate_value = "other";
                    }
                    //朝向对应关系
                    if (tvBaseOrientation.getText().toString().equals("东")) {
                        tvBaseOrientation_value = "e";
                    } else if (tvBaseOrientation.getText().toString().equals("西")) {
                        tvBaseOrientation_value = "w";
                    } else if (tvBaseOrientation.getText().toString().equals("南")) {
                        tvBaseOrientation_value = "s";
                    } else if (tvBaseOrientation.getText().toString().equals("北")) {
                        tvBaseOrientation_value = "n";
                    } else if (tvBaseOrientation.getText().toString().equals("东西")) {
                        tvBaseOrientation_value = "ew";
                    } else if (tvBaseOrientation.getText().toString().equals("南北")) {
                        tvBaseOrientation_value = "sn";
                    } else if (tvBaseOrientation.getText().toString().equals("南南")) {
                        tvBaseOrientation_value = "ss";
                    } else if (tvBaseOrientation.getText().toString().equals("东南")) {
                        tvBaseOrientation_value = "es";
                    } else if (tvBaseOrientation.getText().toString().equals("东北")) {
                        tvBaseOrientation_value = "en";
                    } else if (tvBaseOrientation.getText().toString().equals("西南")) {
                        tvBaseOrientation_value = "ws";
                    } else if (tvBaseOrientation.getText().toString().equals("西北")) {
                        tvBaseOrientation_value = "wn";
                    } else if (tvBaseOrientation.getText().toString().equals("其他")) {
                        tvBaseOrientation_value = "o";
                    }
                    //租期对应关系
                    if (tvRentTime.getText().toString().equals("三十天内")) {
                        tvRentTime_value = "san";
                    } else if (tvRentTime.getText().toString().equals("一个月")) {
                        tvRentTime_value = "oneMonth";
                    } else if (tvRentTime.getText().toString().equals("三个月")) {
                        tvRentTime_value = "threeMonth";
                    } else if (tvRentTime.getText().toString().equals("半年")) {
                        tvRentTime_value = "banYear";
                    } else if (tvRentTime.getText().toString().equals("一年")) {
                        tvRentTime_value = "year";
                    } else if (tvRentTime.getText().toString().equals("一年以上")) {
                        tvRentTime_value = "yearS";
                    }
                    OkHttpUtils
                            .post()
                            .url(URL)
                            .addParams("community.id", village_id + "")
                            .addParams("seat", etSeatNumbers.getText().toString())
                            .addParams("huXing.id", house_type_id + "")
                            .addParams("unit", etUnitNumbers.getText().toString())
                            .addParams("card", tvPropertyRight_value)
                            .addParams("floor", etFloorNumbers.getText().toString())
                            .addParams("countFloor", etTotalFloorNumbers.getText().toString())
                            .addParams("houseseNum", etHouseNumberName.getText().toString())
                            .addParams("renovationInfo", tvBaseDecoration_value)
                            .addParams("years", etYears.getText().toString())
                            .addParams("proportion", etArea.getText().toString())
                            .addParams("nature", tvProperties_value)
                            .addParams("floorType", tvType_value)
                            .addParams("keyInfo", tvKey_value)
                            .addParams("entrustType", tvDelegate_value)
                            .addParams("orientation", tvBaseOrientation_value)
                            .addParams("owner", etOwnerName.getText().toString())
                            .addParams("phone", etPhone.getText().toString())
                            .addParams("salePrice", etPrice.getText().toString())
                            .addParams("remark", tvRemark.getText().toString())
                            .addParams("bmremark", tvHouseSourceRemark.getText().toString())
                            .addParams("type", type_fragment_value)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("AddUsedHouseActivity--", "链接访问失败！");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("AddUsedHouseActivity--", "链接访问成功！" + response);
                                    AddUsedHouseActivity.this.finish();
                                }
                            });
                }
                break;
            case R.id.ll_village:
                intent = new Intent(AddUsedHouseActivity.this, VillageActivity.class);
                intent.putExtra("village", tvVillage.getText());
                intent.putExtra("village_id", village_id);
                Toast.makeText(AddUsedHouseActivity.this, tvVillage.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 0);
                break;
            case R.id.ll_orientation:
                intent = new Intent(AddUsedHouseActivity.this, OrientationActivity.class);
                intent.putExtra("orientation", tvBaseOrientation.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvBaseOrientation.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_decoration:
                intent = new Intent(AddUsedHouseActivity.this, DecorationActivity.class);
                intent.putExtra("decoration", tvBaseDecoration.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvBaseDecoration.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 2);
                break;
            case R.id.ll_remark:
                intent = new Intent(AddUsedHouseActivity.this, OwnerRemarkActivity.class);
                intent.putExtra("owner_remark", tvRemark.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvRemark.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 3);
                break;
//            case R.id.ll_status:
//                intent = new Intent(AddUsedHouseActivity.this, StatusActivity.class);
//                intent.putExtra("status", tvStatus.getText());
//                Toast.makeText(AddUsedHouseActivity.this, tvStatus.getText(), Toast.LENGTH_LONG).show();
//                startActivityForResult(intent, 4);
//                break;
            case R.id.ll_properties:
                intent = new Intent(AddUsedHouseActivity.this, PropertiesActivity.class);
                intent.putExtra("properties", tvProperties.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvProperties.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 5);
                break;
            case R.id.ll_type:
                intent = new Intent(AddUsedHouseActivity.this, TypeActivity.class);
                intent.putExtra("type", tvType.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvType.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 6);
                break;
            case R.id.ll_property_right:
                intent = new Intent(AddUsedHouseActivity.this, PropertyRightActivity.class);
                intent.putExtra("property_right", tvPropertyRight.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvPropertyRight.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 7);
                break;
//            case R.id.ll_garage:
//                intent = new Intent(AddUsedHouseActivity.this, GarageActivity.class);
//                intent.putExtra("garage", tvGarage.getText());
//                Toast.makeText(AddUsedHouseActivity.this, tvGarage.getText(), Toast.LENGTH_LONG).show();
//                startActivityForResult(intent, 8);
//                break;
            case R.id.ll_key:
                intent = new Intent(AddUsedHouseActivity.this, KeyActivity.class);
                intent.putExtra("key", tvKey.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvKey.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 9);
                break;
            case R.id.ll_house_source_remark:
                intent = new Intent(AddUsedHouseActivity.this, HouseSourceRemarkActivity.class);
                intent.putExtra("house_source_remark", tvHouseSourceRemark.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvHouseSourceRemark.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 10);
                break;
            case R.id.ll_picture:
                intent = new Intent(AddUsedHouseActivity.this, PickPictureActivity.class);
                intent.putExtra("picture", tvPicture.getText());
                intent.putStringArrayListExtra("list_path", listPath);
                Toast.makeText(AddUsedHouseActivity.this, tvPicture.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 11);
                break;
            case R.id.ll_house_type:
                intent = new Intent(AddUsedHouseActivity.this, HouseTypeActivity.class);
                intent.putExtra("house_type", tvHouseType.getText());
                intent.putExtra("house_type_id", house_type_id);
                Toast.makeText(AddUsedHouseActivity.this, tvHouseType.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 12);
                break;
            case R.id.ll_delegate:
                intent = new Intent(AddUsedHouseActivity.this, DelegateActivity.class);
                intent.putExtra("delegate", tvDelegate.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvDelegate.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 13);
                break;
            case R.id.ll_rent_time:
                intent = new Intent(AddUsedHouseActivity.this, RentTimeActivity.class);
                intent.putExtra("rent_time", tvRentTime.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvRentTime.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 14);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        orientation = data.getStringExtra("orientation");
        orientation_status = data.getIntExtra("orientation_status", orientation_status);
        decoration = data.getStringExtra("decoration");
        decoration_status = data.getIntExtra("decoration_status", decoration_status);
        owner_remark = data.getStringExtra("owner_remark");
        status = data.getStringExtra("status");
        properties = data.getStringExtra("properties");
        properties_status = data.getIntExtra("properties_status", properties_status);
        type = data.getStringExtra("type");
        house_type_status = data.getIntExtra("house_type_status", house_type_status);
        property_right = data.getStringExtra("property_right");
        property_right_status = data.getIntExtra("property_right_status", property_right_status);
        // garage = data.getStringExtra("garage");
        key = data.getStringExtra("key");
        key_status = data.getIntExtra("key_status", key_status);
        house_source_remark = data.getStringExtra("house_source_remark");
        village = data.getStringExtra("village");
        house_type = data.getStringExtra("house_type");
        house_type_id = data.getIntExtra("house_type_id", house_type_id);
        picture = data.getStringExtra("picture");
        listPath = data.getStringArrayListExtra("list_path");
        delegate = data.getStringExtra("delegate");
        delegate_status = data.getIntExtra("delegate_status", delegate_status);
        rent_time = data.getStringExtra("rent_time");
        rent_time_status =data.getIntExtra("rent_time_status", rent_time_status);
        village_id = data.getIntExtra("village_id", village_id);
        orientation_status = data.getIntExtra("orientation_status", orientation_status);
        switch (requestCode) {
            case 0:
                tvVillage.setText(village);
                etPrice.requestFocus();
                break;
            case 1:
                tvBaseOrientation.setText(orientation);
                etPrice.requestFocus();
                break;
            case 2:
                tvBaseDecoration.setText(decoration);
                etPrice.requestFocus();
                break;
            case 3:
                tvRemark.setText(owner_remark);
                etPhone.requestFocus();
                break;
//            case 4:
//                tvStatus.setText(status);
//                etFloorNumbers.requestFocus();
//                break;
            case 5:
                tvProperties.setText(properties);
                etFloorNumbers.requestFocus();
                break;
            case 6:
                tvType.setText(type);
                etFloorNumbers.requestFocus();
                break;
            case 7:
                tvPropertyRight.setText(property_right);
                etFloorNumbers.requestFocus();
                break;
//            case 8:
//                tvGarage.setText(garage);
//                etFloorNumbers.requestFocus();
//                break;
            case 9:
                tvKey.setText(key);
                etFloorNumbers.requestFocus();
                break;
            case 10:
                tvHouseSourceRemark.setText(house_source_remark);
                etFloorNumbers.requestFocus();
                break;
            case 11:
                tvPicture.setText(picture);
                etFloorNumbers.requestFocus();
                break;
            case 12:
                tvHouseType.setText(house_type);
                etPrice.requestFocus();
                break;
            case 13:
                tvDelegate.setText(delegate);
                etFloorNumbers.requestFocus();
                break;
            case 14:
                tvRentTime.setText(rent_time);
                etFloorNumbers.requestFocus();
                break;
            default:
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
