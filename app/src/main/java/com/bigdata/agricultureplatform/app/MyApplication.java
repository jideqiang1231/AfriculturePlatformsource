package com.bigdata.agricultureplatform.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @description：代表整个软件
 * 用okhttputils必备的配置okhttpclient，
 * address：https://github.com/hongyangAndroid/okhttputils
 **/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //抽取出来这个方法，初始化okhttputils这个库
        /////////////////////////////////////////////
        //然后就可以直接用了，fragment的initData里边
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
