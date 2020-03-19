package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistActivity extends Activity {


    @Bind(R.id.tv_sprcialist_type)
    TextView tvSprcialistType;
    @Bind(R.id.tv_sprcialist_name)
    TextView tvSprcialistName;
    @Bind(R.id.tv_specialist_search)
    TextView tvSpecialistSearch;
    @Bind(R.id.tv_push)
    Button tvPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);
        ButterKnife.bind(this);
//intent要用this的getIntent()获取,specialistacitvity获取speclistloginactivity传过来的信息
        Intent intent = getIntent();
//用intent.getXXXExtra("key-name")或是intent.getXXXExtra("key-name", default-value)获取值
        String specialistname = intent.getStringExtra("专家姓名");
        String specialisttype = intent.getStringExtra("专家类型");
        tvSprcialistName.setText(specialistname);
        tvSprcialistType.setText(specialisttype);
    }
}
