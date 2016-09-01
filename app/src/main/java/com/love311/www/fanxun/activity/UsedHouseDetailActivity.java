package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.net.Uri;
import android.opengl.ETC1Util;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.SeePicRecycleViewAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.HouseDetailBean;
import com.love311.www.fanxun.bean.SeePicBean;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/24.
 */
public class UsedHouseDetailActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.tv_village)
    TextView tvVillage;
    @BindView(R.id.ll_village)
    LinearLayout llVillage;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.tv_house_type)
    TextView tvHouseType;
    @BindView(R.id.ll_house_type)
    LinearLayout llHouseType;
    @BindView(R.id.tv_base_orientation)
    TextView tvBaseOrientation;
    @BindView(R.id.ll_orientation)
    LinearLayout llOrientation;
    @BindView(R.id.tv_base_decoration)
    TextView tvBaseDecoration;
    @BindView(R.id.ll_decoration)
    LinearLayout llDecoration;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_contacts)
    ImageView ivContacts;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.et_owner_name)
    EditText etOwnerName;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.ll_remark)
    LinearLayout llRemark;
    @BindView(R.id.tv_properties)
    TextView tvProperties;
    @BindView(R.id.ll_properties)
    LinearLayout llProperties;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.et_seat_numbers)
    EditText etSeatNumbers;
    @BindView(R.id.et_unit_numbers)
    EditText etUnitNumbers;
    @BindView(R.id.et_floor_numbers)
    EditText etFloorNumbers;
    @BindView(R.id.et_total_floor_numbers)
    EditText etTotalFloorNumbers;
    @BindView(R.id.house_number)
    TextView houseNumber;
    @BindView(R.id.et_house_number_name)
    EditText etHouseNumberName;
    @BindView(R.id.tv_property_right)
    TextView tvPropertyRight;
    @BindView(R.id.ll_property_right)
    LinearLayout llPropertyRight;
    @BindView(R.id.et_years)
    EditText etYears;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.ll_key)
    LinearLayout llKey;
    @BindView(R.id.tv_delegate)
    TextView tvDelegate;
    @BindView(R.id.ll_delegate)
    LinearLayout llDelegate;
    @BindView(R.id.tv_rent_time)
    TextView tvRentTime;
    @BindView(R.id.ll_rent_time)
    LinearLayout llRentTime;
    @BindView(R.id.ll_rent_time_total)
    LinearLayout llRentTimeTotal;
    @BindView(R.id.tv_house_source_remark)
    TextView tvHouseSourceRemark;
    @BindView(R.id.ll_house_source_remark)
    LinearLayout llHouseSourceRemark;
    @BindView(R.id.tv_picture)
    TextView tvPicture;
    @BindView(R.id.ll_picture)
    LinearLayout llPicture;
    private int id;
    //数据解析
    private String url = "admin/houses/housesDetail?";
    private MyApplication my;
    private static String URL;
    private HouseDetailBean.MsgBean bean;
    private int type_fragment;
    private Intent intent;
    //小区
    private int village_id;
    private String village;
    //户型
    private int house_type_id;
    private String house_type;
    //朝向
    private String orientation;
    private int orientation_status;
    private String tvBaseOrientation_value;
    //装修
    private String decoration;
    private int decoration_status;
    private String tvBaseDecoration_value;
    //保密备注
    private String owner_remark;
    //性质
    private String properties;
    private int properties_status;
    private String tvProperties_value;
    //类型
    private String type;
    private int house_type_status;
    private String tvType_value;
    //产权
    private String property_right;
    private int property_right_status;
    private String tvPropertyRight_value;
    //钥匙
    private String key;
    private int key_status;
    private String tvKey_value;
    //委托
    private String delegate;
    private int delegate_status;
    private String tvDelegate_value;
    //租期
    private String rent_time;
    private int rent_time_status;
    //房源备注
    private String house_source_remark;
    //照片
    private ArrayList<String> listPath = new ArrayList<>();
    private String picture;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_used_house_activity);
        ButterKnife.bind(this);
        my = (MyApplication)getApplication();
        URL = my.getURL() + url;
        id = getIntent().getIntExtra("id",0);
        type_fragment =getIntent().getIntExtra("type_fragment",0);
        village_id = 0;
        house_type_id = 0;
        orientation_status = 0;
        decoration_status = 0;
        properties_status = 0;
        house_type_status = 0;
        property_right_status = 0;
        key_status = 0;
        delegate_status = 0;
        rent_time_status = 0;
        Log.d("UsedHouseDettivity-",id+"");
        topRight.setText("修改");
        if (type_fragment ==0){
            topMid.setText("二手房详情");
        }else if(type_fragment ==1){
            topMid.setText("租房详情");
        }else if(type_fragment ==2){
            topMid.setText("新房详情");
        }
        ivContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivContacts.setImageResource(R.mipmap.blue_call);
                etPhone.setText(bean.getPhone());
                etOwnerName.setText(bean.getOwner());
                if (tvRemark.getText().toString().equals("")){
                    tvRemark.setText("请填写业主保密信息...");
                }else {
                    tvRemark.setText(bean.getBmremark());
                }
                ivContacts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + etPhone.getText().toString());
                        intent.setData(data);
                        startActivity(intent);
                    }
                });
            }
        });
        llPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(UsedHouseDetailActivity.this,SeePicturesActivity.class);
                startActivity(intent);
            }
        });
        topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRight.setText("保存");
                etPrice.setEnabled(true);
                etArea.setEnabled(true);
                etPhone.setEnabled(true);
                etOwnerName.setEnabled(true);
                etSeatNumbers.setEnabled(true);
                etUnitNumbers.setEnabled(true);
                etFloorNumbers.setEnabled(true);
                etTotalFloorNumbers.setEnabled(true);
                etHouseNumberName.setEnabled(true);
                etYears.setEnabled(true);
                etPhone.setText(bean.getPhone());
                etOwnerName.setText(bean.getOwner());
                ivContacts.setImageResource(R.mipmap.blue_call);
                if (tvRemark.getText().toString().equals("")){
                    tvRemark.setText("请填写业主保密信息...");
                }else {
                    tvRemark.setText(bean.getBmremark());
                }
                if (tvHouseSourceRemark.getText().toString().equals(""))
                {
                    tvHouseSourceRemark.setText("请填写房源其它公开信息...");
                }else {
                    tvHouseSourceRemark.setText(bean.getRemark());
                }
                //小区
                llVillage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, VillageActivity.class);
                        intent.putExtra("village", tvVillage.getText());
                        intent.putExtra("village_id", village_id);
                        Toast.makeText(UsedHouseDetailActivity.this, tvVillage.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 0);
                    }
                });
                //户型
                llHouseType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, HouseTypeActivity.class);
                        intent.putExtra("house_type", tvHouseType.getText());
                        intent.putExtra("house_type_id", house_type_id);
                        Toast.makeText(UsedHouseDetailActivity.this, tvHouseType.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 1);
                    }
                });
                //朝向
                llOrientation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, OrientationActivity.class);
                        intent.putExtra("orientation", tvBaseOrientation.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvBaseOrientation.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 2);
                    }
                });
                //装修
                llDecoration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, DecorationActivity.class);
                        intent.putExtra("decoration", tvBaseDecoration.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvBaseDecoration.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 3);
                    }
                });
                //保密信息
                llRemark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, OwnerRemarkActivity.class);
                        intent.putExtra("owner_remark", tvRemark.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvRemark.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 4);
                    }
                });
                //性质
                llProperties.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, PropertiesActivity.class);
                        intent.putExtra("properties", tvProperties.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvProperties.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 5);
                    }
                });
                //类型
                llType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, TypeActivity.class);
                        intent.putExtra("type", tvType.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvType.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 6);
                    }
                });
                //产权
                llPropertyRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, PropertyRightActivity.class);
                        intent.putExtra("property_right", tvPropertyRight.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvPropertyRight.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 7);
                    }
                });
                //钥匙
                llKey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, KeyActivity.class);
                        intent.putExtra("key", tvKey.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvKey.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 8);
                    }
                });
                //委托
                llDelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, DelegateActivity.class);
                        intent.putExtra("delegate", tvDelegate.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvDelegate.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 9);
                    }
                });
                //租期
                llRentTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, RentTimeActivity.class);
                        intent.putExtra("rent_time", tvRentTime.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvRentTime.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 10);
                    }
                });
                //房源备注
                llHouseSourceRemark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, HouseSourceRemarkActivity.class);
                        intent.putExtra("house_source_remark", tvHouseSourceRemark.getText());
                        Toast.makeText(UsedHouseDetailActivity.this, tvHouseSourceRemark.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 11);
                    }
                });
                //照片
                llPicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(UsedHouseDetailActivity.this, PickPictureActivity.class);
                        intent.putExtra("picture", tvPicture.getText());
                        intent.putStringArrayListExtra("list_path", listPath);
                        Toast.makeText(UsedHouseDetailActivity.this, tvPicture.getText(), Toast.LENGTH_LONG).show();
                        startActivityForResult(intent, 12);
                    }
                });
            }
        });
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("id",id+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("UsedHouseFragment", "response");
                        Type listType = new TypeToken<HouseDetailBean.MsgBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("msg");
                            Log.d("jsonElements--------", jsonObject1.toString());
                            while (bean == null) {
                                bean = gson.fromJson(jsonObject1.toString(), listType);
                            }
                            initData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    private void initData() {
        tvVillage.setText(bean.getCommuntyName());
        etPrice.setText(bean.getSalePrice()+"");
        etArea.setText(bean.getProportion()+"");
        tvHouseType.setText(bean.getHuXingName());
        tvBaseOrientation.setText(bean.getOrientationText());
        tvBaseDecoration.setText(bean.getRenovationInfoText());
        etPhone.setText("******");
        etOwnerName.setText("******");
        if (bean.getBmremark().equals("")){
            tvRemark.setText("");
        }else {
            tvRemark.setText("******");
        }
        tvProperties.setText(bean.getNatureText());
        tvType.setText(bean.getFloorTypeText());
        etSeatNumbers.setText(bean.getSeat());
        etUnitNumbers.setText(bean.getUnit());
        etFloorNumbers.setText(bean.getFloor()+"");
        etTotalFloorNumbers.setText(bean.getCountFloor()+"");
        etHouseNumberName.setText(bean.getHousesNum());
        tvPropertyRight.setText(bean.getCardText());
        etYears.setText(bean.getYear());
        tvKey.setText(bean.getKeyText());
        tvDelegate.setText(bean.getEntrustTypeText());
        tvHouseSourceRemark.setText(bean.getRemark());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                village = data.getStringExtra("village");
                village_id = data.getIntExtra("village_id", village_id);
                tvVillage.setText(village);
                etPrice.requestFocus();
                break;
            case 1:
                house_type = data.getStringExtra("house_type");
                house_type_id = data.getIntExtra("house_type_id", house_type_id);
                tvHouseType.setText(house_type);
                etPrice.requestFocus();
                break;
            case 2:
                orientation = data.getStringExtra("orientation");
                orientation_status = data.getIntExtra("orientation_status", orientation_status);
                tvBaseOrientation.setText(orientation);
                etPrice.requestFocus();
                break;
            case 3:
                decoration = data.getStringExtra("decoration");
                decoration_status = data.getIntExtra("decoration_status", decoration_status);
                tvBaseDecoration.setText(decoration);
                etPrice.requestFocus();
                break;
            case 4:
                owner_remark = data.getStringExtra("owner_remark");
                tvRemark.setText(owner_remark);
                etPhone.requestFocus();
                break;
            case 5:
                properties = data.getStringExtra("properties");
                properties_status = data.getIntExtra("properties_status", properties_status);
                tvProperties.setText(properties);
                etFloorNumbers.requestFocus();
                break;
            case 6:
                type = data.getStringExtra("type");
                house_type_status = data.getIntExtra("house_type_status", house_type_status);
                tvType.setText(type);
                etFloorNumbers.requestFocus();
                break;
            case 7:
                property_right = data.getStringExtra("property_right");
                property_right_status = data.getIntExtra("property_right_status", property_right_status);
                tvPropertyRight.setText(property_right);
                etFloorNumbers.requestFocus();
                break;
            case 8:
                key = data.getStringExtra("key");
                key_status = data.getIntExtra("key_status", key_status);
                tvKey.setText(key);
                etFloorNumbers.requestFocus();
                break;
            case 9:
                delegate = data.getStringExtra("delegate");
                delegate_status = data.getIntExtra("delegate_status", delegate_status);
                tvDelegate.setText(delegate);
                etFloorNumbers.requestFocus();
                break;
            case 10:
                rent_time = data.getStringExtra("rent_time");
                rent_time_status =data.getIntExtra("rent_time_status", rent_time_status);
                tvRentTime.setText(rent_time);
                etFloorNumbers.requestFocus();
                break;
            case 11:
                house_source_remark = data.getStringExtra("house_source_remark");
                tvHouseSourceRemark.setText(house_source_remark);
                etFloorNumbers.requestFocus();
                break;
            case 12:
                picture = data.getStringExtra("picture");
                listPath = data.getStringArrayListExtra("list_path");
                tvPicture.setText(picture);
                etFloorNumbers.requestFocus();
                break;
        }
    }
}
