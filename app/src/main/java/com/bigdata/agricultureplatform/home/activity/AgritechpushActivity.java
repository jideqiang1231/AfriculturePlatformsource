package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AgritechpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_agritech_push_back)
    ImageButton ibAgritechPushBack;
    @Bind(R.id.gv_agritech_push)
    GridView gvAgritechPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agritech);
        ButterKnife.bind(this);
        //设置监听事件
        ibAgritechPushBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==ibAgritechPushBack){
            finish();
        }
    }
}
