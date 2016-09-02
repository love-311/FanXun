package com.love311.www.fanxun.application;

import android.app.Application;
import android.content.Context;
import android.os.HandlerThread;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.pizidea.imagepicker.BuildConfig;
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
   // private static final String URL = "http://test.kafeikeji.com:8088/wxfc/";
   // private static final String URL = "http://192.168.0.110:8080/wxfc/";
   private static final String URL = "http://192.168.0.111:8080/wxfc/";
  //  private static final String URL = "http://192.168.0.112:8080/wxfc/"; http://62fa6d1e.ngrok.natapp.cn/
   // private static final String URL = "http://62fa6d1e.ngrok.natapp.cn/wxfc/";
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
        HandlerThread workerThread = new HandlerThread("global_worker_thread");
        workerThread.start();
        initImageLoader(getApplicationContext());
    }
    private void initImageLoader(Context context) {
        {
            ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
            config.threadPriority(Thread.NORM_PRIORITY - 2);
//        config.denyCacheImageMultipleSizesInMemory();//不会在内存中缓存多个大小的图片
            config.diskCacheFileNameGenerator(new Md5FileNameGenerator());//为了保证图片名称唯一
            //内存缓存大小默认是：app可用内存的1/8
            config.tasksProcessingOrder(QueueProcessingType.LIFO);
            config.writeDebugLogs(); // Remove for release app
            // Initialize ImageLoader with configuration.
            ImageLoader.getInstance().init(config.build());
        }
    }
/*    public static void initImageLoader(Context context){
        if(!ImageLoader.getInstance().isInited()){
            ImageLoaderConfiguration config = null;
            if(BuildConfig.DEBUG){
                config = new ImageLoaderConfiguration.Builder(context)
						*//*.threadPriority(Thread.NORM_PRIORITY - 2)
						.memoryCacheSize((int) (Runtime.getRuntime().maxMemory() / 4))
						.diskCacheSize(500 * 1024 * 1024)
						.writeDebugLogs()
						.diskCacheFileNameGenerator(new Md5FileNameGenerator())
						.tasksProcessingOrder(QueueProcessingType.LIFO).build();*//*

                        //.memoryCacheExtraOptions(200, 200)
                        //.diskCacheExtraOptions(200, 200, null).threadPoolSize(3)
                        .threadPriority(Thread.NORM_PRIORITY - 1)
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        //.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024))
						*//*.memoryCacheSize(20 * 1024 * 1024)*//*
                        .memoryCacheSizePercentage(13)
                        .diskCacheSize(500 * 1024 * 1024)
                        //.imageDownloader(new BaseImageDownloader(A3App.getInstance().getApplicationContext()))
                        //.imageDecoder(new BaseImageDecoder(true))
                        //.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        //.writeDebugLogs()
                        .build();
            }else{
                config = new ImageLoaderConfiguration.Builder(context)
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .diskCacheSize(500 * 1024 * 1024)
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO).build();
            }
            ImageLoader.getInstance().init(config);
        }

    }*/

    public String getURL() {
        return URL;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
