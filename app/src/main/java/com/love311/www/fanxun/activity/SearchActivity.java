package com.love311.www.fanxun.activity;

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
import com.love311.www.fanxun.fragment.HouseResourceFragment;
import com.love311.www.fanxun.fragment.UsedHouseFragment;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    private Intent intent;
    //经纪人
    private static String broker_id;
    private String broker;
    //板块
    private static String plate_id;
    private String plate;
    //状态
    private String status;
    //楼型
    private String type;
    private int house_type_status;
    //户型
    private static String house_type_id;
    ;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        intent = getIntent();
        type_fragment = intent.getIntExtra("type", 0);
        if (type_fragment == 0) {
            topSearchMid.setText("二手房");
            type_fragment_value = "getOldHouses?";
        } else if (type_fragment == 1) {
            topSearchMid.setText("租房");
            type_fragment_value = "getRentHouses?";
        } else if (type_fragment == 2) {
            topSearchMid.setText("新房");
            type_fragment_value = "getNewHouses?";
        }
        my = (MyApplication) getApplication();
        URL = my.getURL() + url + type_fragment_value;
        broker_id = "0";
        plate_id = "0";
        house_type_id = "0";
        house_type_status = 0;
        statusStatus = 0;
        decoration_status = 0;
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        llSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
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
                Log.d("SearchActivity---",URL+"search.status_eq="+tvStatus_value
                        +"&search.community.area.id_eq="
                        +plate_id+"&search.salePrice_gte="+ et1.getText().toString()
                +"&search.salePrice_lte="+ et2.getText().toString()
                +"&search.proportion_gte="+ et3.getText().toString()
                +"&search.proportion_lte="+ et4.getText().toString()
                +"&search.floor_gte="+ et5.getText().toString()
                +"&search.floor_lte="+ et6.getText().toString()
                +"&search.user.id_eq="+ broker_id
                +"&search.huXing.id_eq="+ house_type_id + ""
                +"&search.renovationInfo_eq="+ tvBaseDecoration_value
                +"&search.floorType_eq="+ tvType_value
                +"&a="+ "0.5301073853690059"
                +"&page.pn="+ "1"
                +"&page.size="+ "10");
                OkHttpUtils
                        .post()
                        .url(URL)
                        .addParams("search.status_eq", tvStatus_value)
                        .addParams("search.community.area.id_eq", plate_id.equals("0")?"":plate_id)
                        .addParams("search.salePrice_gte", et1.getText().toString())
                        .addParams("search.salePrice_lte", et2.getText().toString())
                        .addParams("search.proportion_gte", et3.getText().toString())
                        .addParams("search.proportion_lte", et4.getText().toString())
                        .addParams("search.floor_gte", et5.getText().toString())
                        .addParams("search.floor_lte", et6.getText().toString())
                        .addParams("search.user.id_eq", broker_id.equals("0")?"":broker_id)
                        .addParams("search.huXing.id_eq", house_type_id.equals("0")?"":house_type_id)
                        .addParams("search.renovationInfo_eq", tvBaseDecoration_value)
                        .addParams("search.floorType_eq", tvType_value)
                        .addParams("a", "0.5301073853690059")
                        .addParams("page.pn", "1")
                        .addParams("page.size", "10")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("SearchActivity--","查询失败");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("SearchActivity--","查询成功"+response);
                                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                                intent.putExtra("type_fragment",type_fragment);
                                startActivity(intent);
                            }
                        });
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
        }
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
}
