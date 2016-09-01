package com.love311.www.fanxun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.ModifyPasswordActivity;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.MyFragmentBean;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private ImageView iv_quit_btn;
    //数据解析
    private String url = "admin/user/ajax/getInfo";
    private MyApplication my;
    private static String URL;
    private MyFragmentBean.ResBean bean;

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
        iv_quit_btn = (ImageView) view.findViewById(R.id.quit_btn);
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
        iv_quit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
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
