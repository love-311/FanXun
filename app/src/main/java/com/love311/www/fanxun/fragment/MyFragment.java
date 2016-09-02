package com.love311.www.fanxun.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.FeedbackActivity;
import com.love311.www.fanxun.activity.ModifyPasswordActivity;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.MyFragmentBean;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.mabeijianxi.lookbigpicutils.view.material.MaterialTextView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MyFragment extends LazyLoadFragment {


    private ImageView ivMyIcon;
    private TextView tvName;
    private TextView tvPhone;
    private ImageView iv_top_left;
    private TextView tv_mid, tv_right;
    private RelativeLayout rl_1;
    private RelativeLayout rl_2;
    private RelativeLayout rl_3;
    private RelativeLayout rl_4;
    private ImageView iv_quit_btn;
    //数据解析
    private String url = "admin/user/ajax/getInfo";
    private MyApplication my;
    private static String URL;
    private MyFragmentBean.ResBean bean;
    private MaterialDialog dialog;
    //退出程序，清空房源搜索数据
    private SharedPreferences clearUsedSharedPreferences;
    private SharedPreferences.Editor usedEditor;
    private SharedPreferences clearRentSharedPreferences;
    private SharedPreferences.Editor rentEditor;
    private SharedPreferences clearNewSharedPreferences;
    private SharedPreferences.Editor newEditor;
    //退出程序，清空客源搜索数据
    private SharedPreferences clearUsedPassengerSharedPreferences;
    private SharedPreferences.Editor usedPassengerEditor;
    private SharedPreferences clearRentPassengerSharedPreferences;
    private SharedPreferences.Editor rentPassengerEditor;
    private SharedPreferences clearNewPassengerSharedPreferences;
    private SharedPreferences.Editor newPassengerEditor;
    //退出程序清空房源列表数据
    private SharedPreferences preferences1;
    private SharedPreferences.Editor editor1;
    private SharedPreferences preferences2;
    private SharedPreferences.Editor editor2;
    private SharedPreferences preferences3;
    private SharedPreferences.Editor editor3;
    //退出程序清空客源列表数据
    private SharedPreferences preferences4;
    private SharedPreferences.Editor editor4;
    private SharedPreferences preferences5;
    private SharedPreferences.Editor editor5;
    private SharedPreferences preferences6;
    private SharedPreferences.Editor editor6;

    //我的界面
    @Override
    public int getLayout() {
        return R.layout.my_fragment;
    }

    @Override
    public void initViews(View view) {
        iv_top_left = (ImageView) view.findViewById(R.id.top_left);
        tv_mid = (TextView) view.findViewById(R.id.top_mid);
        tv_right = (TextView) view.findViewById(R.id.top_right);
        ivMyIcon = (ImageView) view.findViewById(R.id.iv_my_icon);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvPhone = (TextView) view.findViewById(R.id.tv_phone);
        rl_1 = (RelativeLayout) view.findViewById(R.id.rl_1);
        rl_2 = (RelativeLayout) view.findViewById(R.id.rl_2);
        rl_3 = (RelativeLayout) view.findViewById(R.id.rl_3);
        rl_4 = (RelativeLayout) view.findViewById(R.id.rl_4);
        iv_quit_btn = (ImageView) view.findViewById(R.id.quit_btn);
        //清除房源信息
        clearUsedSharedPreferences = getActivity().getSharedPreferences("search_used_status", Context.MODE_PRIVATE);
        usedEditor = clearUsedSharedPreferences.edit();
        clearRentSharedPreferences = getActivity().getSharedPreferences("search_rent_status", Context.MODE_PRIVATE);
        rentEditor = clearRentSharedPreferences.edit();
        clearNewSharedPreferences = getActivity().getSharedPreferences("search_new_status", Context.MODE_PRIVATE);
        newEditor = clearNewSharedPreferences.edit();
        //清除客源信息
        clearUsedPassengerSharedPreferences = getActivity().getSharedPreferences("search_passenger_used_status", Context.MODE_PRIVATE);
        usedPassengerEditor = clearUsedPassengerSharedPreferences.edit();
        clearRentPassengerSharedPreferences = getActivity().getSharedPreferences("search_passenger_rent_status", Context.MODE_PRIVATE);
        rentPassengerEditor = clearRentPassengerSharedPreferences.edit();
        clearNewPassengerSharedPreferences = getActivity().getSharedPreferences("search_passenger_new_status", Context.MODE_PRIVATE);
        newPassengerEditor = clearNewPassengerSharedPreferences.edit();
        //清除房源列表数据
        preferences1 = getActivity().getSharedPreferences("used", Context.MODE_PRIVATE);
        editor1 = preferences1.edit();
        preferences2 = getActivity().getSharedPreferences("rent", Context.MODE_PRIVATE);
        editor2 = preferences2.edit();
        preferences3 = getActivity().getSharedPreferences("new", Context.MODE_PRIVATE);
        editor3 = preferences3.edit();
        //清除客源列表数据
        preferences4 = getActivity().getSharedPreferences("passenger_used", Context.MODE_PRIVATE);
        editor4 = preferences4.edit();
        preferences5 = getActivity().getSharedPreferences("passenger_rent", Context.MODE_PRIVATE);
        editor5 = preferences5.edit();
        preferences6 = getActivity().getSharedPreferences("passenger_new", Context.MODE_PRIVATE);
        editor6 = preferences6.edit();
        iv_top_left.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
        my = (MyApplication) getActivity().getApplication();
        URL = my.getURL() + url;
        rl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModifyPasswordActivity.class);
                startActivity(intent);
            }
        });
        rl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rl_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
        rl_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"已是最新版本！",Toast.LENGTH_SHORT).show();
            }
        });
        iv_quit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new MaterialDialog(getActivity())
                        .setMessage("           确认要退出的应用吗？          ")
                        .setCanceledOnTouchOutside(true)
                        .setPositiveButton("退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                usedEditor.clear().commit();
                                rentEditor.clear().commit();
                                newEditor.clear().commit();
                                usedPassengerEditor.clear().commit();
                                rentPassengerEditor.clear().commit();
                                newPassengerEditor.clear().commit();
                                editor1.clear().commit();
                                editor2.clear().commit();
                                editor3.clear().commit();
                                editor4.clear().commit();
                                editor5.clear().commit();
                                editor6.clear().commit();
                                getActivity().finish();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        });
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("MyFragment", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType = new TypeToken<MyFragmentBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            bean = gson.fromJson(jsonObject1.toString(), listType);
                            tvName.setText(bean.getName());
                            tvPhone.setText(bean.getPhone());
                            Log.d("MyFragment",my.getURL()+"admin/"+bean.getHeadImg().substring(6));
                            Glide.with(getActivity())
                                    .load(my.getURL()+bean.getHeadImg().substring(6))
                                    .into(ivMyIcon);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void loadData() {

    }
}
