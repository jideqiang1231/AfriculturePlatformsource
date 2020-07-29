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
    private  static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //抽取出来这个方法，初始化okhttputils这个库
        /////////////////////////////////////////////
        //然后就可以直接用了，fragment的initData里边
        initOkhttpClient();
        instance=this;
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
    public static MyApplication getInstance(){
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        return instance;
    }

}
