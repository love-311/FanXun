package com.love311.www.fanxun.application;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/8/15.
 */
public class MyApplication extends Application{
    //private static final String URL = "http://10.1.100.194:8080/wxfc/";
    private static final String URL = "http://test.kafeikeji.com:8088/wxfc/";
    private String url;
    @Override
    public void onCreate() {
        super.onCreate();
        setUrl(URL);
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .addInterceptor(new LoggerInterceptor("TAG"))
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
    public String getURL() {
        return URL;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
