package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
 * Created by Administrator on 2016/8/15.
 */
public class LoginActivity extends AutoLayoutActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.login_check)
    CheckBox loginCheck;
    private SharedPreferences loginSharedPreferences;
    private SharedPreferences.Editor editor;
    private String url = "login?";
    private static String URL;
    private MyApplication my;
    private SharedPreferences getLoginSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        loginSharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        getLoginSharedPreferences =getSharedPreferences("login", MODE_PRIVATE);
        editor = loginSharedPreferences.edit();
        Log.d("username",getLoginSharedPreferences.getString("username","2222"));
        if (getLoginSharedPreferences.getString("username","")!=null&&!getLoginSharedPreferences.getString("username","").equals("")){
            String username = getLoginSharedPreferences.getString("username","");
            String password = getLoginSharedPreferences.getString("password","");
            etUsername.setText(username);
            etPassword.setText(password);
        }
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("username", etUsername.getText().toString());
                editor.putString("password", etPassword.getText().toString());
                editor.commit();
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
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("LoginActivity=======", URL + "登录成功" + response.toString());

                            }
                        });
                if (etUsername.getText().toString().equals("006") & etPassword.getText().toString().equals("147258")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或者密码输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
