package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import dmax.dialog.SpotsDialog;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/15.
 */
public class LoginActivity extends AutoLayoutActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login_check)
    CheckBox loginCheck;
    @BindView(R.id.iv_login)
    ImageView ivLogin;
    private SharedPreferences loginSharedPreferences;
    private SharedPreferences.Editor editor;
    private String url = "login?";
    private static String URL;
    private MyApplication my;
    private SharedPreferences getLoginSharedPreferences;
    private ModifyPasswordBean bean;
    private SpotsDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        loginSharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        getLoginSharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = loginSharedPreferences.edit();
        Log.d("username", getLoginSharedPreferences.getString("username", "2222"));
        if (getLoginSharedPreferences.getString("password", "") != null && !getLoginSharedPreferences.getString("username", "").equals("")
                && getLoginSharedPreferences.getString("username", "") != null && !getLoginSharedPreferences.getString("password", "").equals("")) {
            loginCheck.setChecked(true);
            String username = getLoginSharedPreferences.getString("username", "");
            String password = getLoginSharedPreferences.getString("password", "");
            etUsername.setText(username);
            etPassword.setText(password);
            etPassword.requestFocus();
            etPassword.setSelection(password.length());
        }
        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginCheck.isChecked()) {
                    editor.putString("username", etUsername.getText().toString());
                    editor.putString("password", etPassword.getText().toString());
                    editor.commit();
                } else {
                    editor.clear().commit();
                }
                dialog = new SpotsDialog(LoginActivity.this, R.style.Custom);
                dialog.show();
                OkHttpUtils
                        .post()
                        .url(URL)
                        .addParams("username", etUsername.getText().toString())
                        .addParams("password", etPassword.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.d("LoginActivity=======", URL + "登录失败");
                                Toast.makeText(LoginActivity.this, "网络访问失败，请稍后重新登录！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("LoginActivity=======", URL + "登录成功" + response.toString());
                                Gson gson = new Gson();
                                bean = gson.fromJson(response, ModifyPasswordBean.class);
                                if (bean.getStatus().equals("success")) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                    dialog.dismiss();
                                } else if (bean.getStatus().equals("error")) {
                                    Toast.makeText(LoginActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
//                if (etUsername.getText().toString().equals("006") & etPassword.getText().toString().equals("147258")) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "账号或者密码输入错误", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}
