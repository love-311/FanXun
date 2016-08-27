package com.love311.www.fanxun.activity;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.HouseDetailBean;
import com.love311.www.fanxun.bean.PassengerDetailBean;
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
 * Created by Administrator on 2016/8/26.
 */
public class PassengerSourceDetailActivity extends AutoLayoutActivity {

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
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.et_3)
    EditText et3;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
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
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.ll_8)
    LinearLayout ll8;
    @BindView(R.id.ll_rent_time)
    LinearLayout llRentTime;
    @BindView(R.id.et_4)
    EditText et4;
    @BindView(R.id.et_5)
    EditText et5;
    @BindView(R.id.et_6)
    EditText et6;
    @BindView(R.id.et_7)
    EditText et7;
    @BindView(R.id.et_8)
    EditText et8;
    @BindView(R.id.et_9)
    EditText et9;
    @BindView(R.id.house_number)
    TextView houseNumber;
    @BindView(R.id.et_10)
    EditText et10;
    @BindView(R.id.rl_use)
    RelativeLayout rlUse;
    //数据解析
    private String url = "admin/saleCustomer/appGetSaleCustomerDetail?";
    private String url1 = "admin/rentCustomer/appGetRentCustomerDetail?";
    private MyApplication my;
    private static String URL;
    private int type_fragment;
    private int id;
    private PassengerDetailBean.MsgBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_detail_activity);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id",0);
        type_fragment =getIntent().getIntExtra("type_fragment",0);
        my = (MyApplication)getApplication();
        if (type_fragment ==0){
            topMid.setText("二手房客源详情");
            URL = my.getURL() + url;
            llRentTime.setVisibility(View.GONE);
        }else if(type_fragment ==1){
            topMid.setText("租房客源详情");
            URL = my.getURL() + url1;
            rlAddress.setVisibility(View.GONE);
            rlUse.setVisibility(View.GONE);
        }else if(type_fragment ==2){
            topMid.setText("新房客源详情");
            URL = my.getURL() + url;
            llRentTime.setVisibility(View.GONE);
        }
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
                        Type listType = new TypeToken<PassengerDetailBean.MsgBean>() {
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
        et1.setText(bean.getPhone());
        et2.setText(bean.getName());
        if (type_fragment==0 || type_fragment ==2){
            et3.setText(bean.getAddress());
        }
        tv1.setText(bean.getRemarks());
        tv2.setText(bean.getCustomerNatureText());
        tv3.setText(bean.getFloorTypeText());
        tv4.setText(bean.getHuXingName());
        tv5.setText(bean.getStatusText());
        tv6.setText(bean.getRenovationInfoText());
        tv7.setText(bean.getOrientationText());
        if (type_fragment ==1){
            tv8.setText(bean.getRentTermText());
        }
        et4.setText(bean.getLowPrice()+"");
        et5.setText(bean.getTallPrice()+"");
        et6.setText(bean.getProportion().substring(0,1));
        et7.setText(bean.getProportion().substring(2,3));
        et8.setText(bean.getFloor().substring(0,1));
        et9.setText(bean.getFloor().substring(2,3));
        if (type_fragment == 0 || type_fragment ==2){
            et10.setText(bean.getHousesPurpose());
        }
    }
}
