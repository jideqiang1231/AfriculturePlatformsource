package com.bigdata.agricultureplatform.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bigdata.agricultureplatform.R;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            //两秒钟进入主界面
            public void run() {
                //启动主页面
                //执行主线程
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        },2000);
    }
}
