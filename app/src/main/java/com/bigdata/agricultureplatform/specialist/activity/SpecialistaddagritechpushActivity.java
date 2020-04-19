package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistaddagritechpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.et_specialist_nongji_pushinfo_name)
    EditText etSpecialistNongjiPushinfoName;
    @Bind(R.id.et_specialist_nongji_pushinfo_introduce)
    EditText etSpecialistNongjiPushinfoIntroduce;
    @Bind(R.id.et_specialist_nongji_pushinfo_pusharea)
    EditText etSpecialistNongjiPushinfoPusharea;
    @Bind(R.id.et_specialist_pushinfo_nongji_pushdata)
    EditText etSpecialistPushinfoNongjiPushdata;
    @Bind(R.id.b_specialist_nongji_pushinfo_submit)
    Button bSpecialistNongjiPushinfoSubmit;
    @Bind(R.id.ib_specialist_nongji_addpushinfo_back)
    ImageButton ibSpecialistNongjiAddpushinfoBack;
    @Bind(R.id.tv_specialist_pushinfo_nongji_pushdata)
    TextView tvSpecialistPushinfoNongjiPushdata;
    private int specialistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddagritechpush);
        ButterKnife.bind(this);
        //接受参数
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        ibSpecialistNongjiAddpushinfoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_agritech_push_back:
                finish();
                break;
        }
    }
}
