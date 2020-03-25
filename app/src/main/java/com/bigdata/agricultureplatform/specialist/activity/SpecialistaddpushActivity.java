package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistaddpushActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.ib_specialist_addpushinfo_back)
    ImageButton ibspecialistAddpushinfoBack;
    @Bind(R.id.tv_specialist_pushinfo_type)
    TextView tvSpecialistPushinfoType;
    @Bind(R.id.et_specialist_pushinfo_name)
    EditText etSpecialistPushinfoName;
    @Bind(R.id.et_specialist_pushinfo_introduce)
    EditText etSpecialistPushinfoIntroduce;
    @Bind(R.id.et_specialist_pushinfo_plantmethod)
    EditText etSpecialistPushinfoPlantmethod;
    @Bind(R.id.et_specialist_pushinfo_plantarea)
    EditText etSpecialistPushinfoPlantarea;
    @Bind(R.id.et_specialist_pushinfo_note)
    EditText etSpecialistPushinfoNote;
    @Bind(R.id.et_specialist_pushinfo_price)
    EditText etSpecialistPushinfoPrice;
    @Bind(R.id.et_specialist_pushinfo_manufacture)
    EditText etSpecialistPushinfoManufacture;
    @Bind(R.id.et_specialist_pushinfo_store)
    EditText etSpecialistPushinfoStore;
    @Bind(R.id.et_specialist_pushinfo_phone)
    EditText etSpecialistPushinfoPhone;
    @Bind(R.id.et_specialist_pushinfo_productdata)
    EditText etSpecialistPushinfoProductdata;
    @Bind(R.id.et_specialist_pushinfo_shelflife)
    EditText etSpecialistPushinfoShelflife;
    @Bind(R.id.et_specialist_pushinfo_plantnumber)
    EditText etSpecialistPushinfoPlantnumber;
    @Bind(R.id.et_specialist_pushinfo_image)
    EditText etSpecialistPushinfoImage;
    @Bind(R.id.ib_specialist_pushinfo_push)
    ImageButton ibSpecialistPushinfoPush;
    private Integer specialistId;
    private String specialistType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddpush);
        ButterKnife.bind(this);
        /////////////////接收一下前边传来的参数，push的时候会用到////////////////////////////////////
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        specialistType = intent.getStringExtra("专家的类型");
        Log.e("TAG", String.valueOf(specialistId));
        Log.e("TAG", specialistType);
        ////////////////////先把能放的参数设置上（专家的id,type到时候直接okhttp发送）///////////////////////////////
        tvSpecialistPushinfoType.setText(specialistType);
        ////////////////////////////////////////////////////////////////
        ibspecialistAddpushinfoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ibspecialistAddpushinfoBack) {
            finish();
        }
    }
}
