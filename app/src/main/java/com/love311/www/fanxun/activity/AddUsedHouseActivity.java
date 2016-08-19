package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.love311.www.fanxun.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
//    @BindView(R.id.et_room_number)
//    EditText etRoomNumber;
//    @BindView(R.id.et_hall_number)
//    EditText etHallNumber;
//    @BindView(R.id.et_toilet_number)
//    EditText etToiletNumber;
    @BindView(R.id.ll_orientation)
    LinearLayout llOrientation;
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
    @BindView(R.id.house_number)
    TextView houseNumber;
    @BindView(R.id.et_door_plate)
    EditText etDoorPlate;
    @BindView(R.id.ll_remark)
    LinearLayout llRemark;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
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
    @BindView(R.id.ll_garage)
    LinearLayout llGarage;
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
    @BindView(R.id.tv_status)
    TextView tvStatus;
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
    @BindView(R.id.tv_garage)
    TextView tvGarage;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.tv_house_type)
    TextView tvHouseType;
    @BindView(R.id.ll_house_type)
    LinearLayout llHouseType;
    private Intent intent;
    private String orientation;
    private String decoration;
    private String owner_remark;
    private String status;
    private String properties;
    private String type;
    private String property_right;
    private String garage;
    private String key;
    private String house_source_remark;
    private String village;
    private String house_type;
    private String picture;
    private ArrayList<String> listPath = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_used_house_activity);
        ButterKnife.bind(this);
        llOrientation.setOnClickListener(this);
        llDecoration.setOnClickListener(this);
        llRemark.setOnClickListener(this);
        llStatus.setOnClickListener(this);
        llProperties.setOnClickListener(this);
        llType.setOnClickListener(this);
        llPropertyRight.setOnClickListener(this);
        llGarage.setOnClickListener(this);
        llKey.setOnClickListener(this);
        llHouseSourceRemark.setOnClickListener(this);
        llVillage.setOnClickListener(this);
        llHouseType.setOnClickListener(this);
        llPicture.setOnClickListener(this);
        topMid.setText("二手房");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_orientation:
                intent = new Intent(AddUsedHouseActivity.this, OrientationActivity.class);
                intent.putExtra("orientation", tvBaseOrientation.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvBaseOrientation.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_village:
                intent = new Intent(AddUsedHouseActivity.this, VillageActivity.class);
                intent.putExtra("village", tvVillage.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvVillage.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 0);
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
            case R.id.ll_status:
                intent = new Intent(AddUsedHouseActivity.this, StatusActivity.class);
                intent.putExtra("status", tvStatus.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvStatus.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 4);
                break;
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
            case R.id.ll_garage:
                intent = new Intent(AddUsedHouseActivity.this, GarageActivity.class);
                intent.putExtra("garage", tvGarage.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvGarage.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 8);
                break;
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
                intent.putStringArrayListExtra("list_path",listPath);
                Toast.makeText(AddUsedHouseActivity.this, tvPicture.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 11);
                break;
            case R.id.ll_house_type:
                intent = new Intent(AddUsedHouseActivity.this, HouseTypeActivity.class);
                intent.putExtra("house_type", tvHouseType.getText());
                Toast.makeText(AddUsedHouseActivity.this, tvHouseType.getText(), Toast.LENGTH_LONG).show();
                startActivityForResult(intent, 12);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        orientation = data.getStringExtra("orientation");
        decoration = data.getStringExtra("decoration");
        owner_remark = data.getStringExtra("owner_remark");
        status = data.getStringExtra("status");
        properties = data.getStringExtra("properties");
        type = data.getStringExtra("type");
        property_right = data.getStringExtra("property_right");
        garage = data.getStringExtra("garage");
        key = data.getStringExtra("key");
        house_source_remark = data.getStringExtra("house_source_remark");
        village = data.getStringExtra("village");
        house_type = data.getStringExtra("house_type");
        picture = data.getStringExtra("picture");
        listPath = data.getStringArrayListExtra("list_path");
        switch (requestCode) {
            case 0:
                tvVillage.setText(village);
                etPrice.requestFocus();
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
            case 4:
                tvStatus.setText(status);
                etFloorNumbers.requestFocus();
                break;
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
            case 8:
                tvGarage.setText(garage);
                etFloorNumbers.requestFocus();
                break;
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
        }
    }
}
