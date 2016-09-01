package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.ModifyPasswordBean;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ModifyPasswordActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.old_password)
    EditText oldPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.confirm_modify)
    ImageView confirmModify;
    //数据解析
    private String url = "admin/user/ajax/changePassword";
    private MyApplication my;
    private static String URL;
    private ModifyPasswordBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_password_activity);
        ButterKnife.bind(this);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topMid.setText("修改密码");
        topRight.setVisibility(View.GONE);
        confirmModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oldPassword.getText().toString().equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this, "请输入旧密码！", Toast.LENGTH_SHORT).show();
                } else if (newPassword.getText().toString().equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this, "请输入新密码！", Toast.LENGTH_SHORT).show();
                } else if (etConfirmPassword.getText().toString().equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this, "请确认新密码！", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    Toast.makeText(ModifyPasswordActivity.this, "两次输入密码不同！", Toast.LENGTH_SHORT).show();
                } else {
                    OkHttpUtils
                            .get()
                            .url(URL)
                            .addParams("oldPassword", oldPassword.getText().toString())
                            .addParams("newPassword", newPassword.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("ModifyPasswordActivity", "Http error!");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("ModifyPasswordActivity", "Http success!");
                                    Gson gson = new Gson();
                                    bean = gson.fromJson(response, ModifyPasswordBean.class);
                                    if (bean.getStatus().equals("success")){
                                        Intent intent = new Intent(ModifyPasswordActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        ModifyPasswordActivity.this.finish();
                                    }else if (bean.getStatus().equals("error")) {
                                        Toast.makeText(ModifyPasswordActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
