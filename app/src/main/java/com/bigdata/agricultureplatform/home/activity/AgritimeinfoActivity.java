package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AgritimeinfoActivity extends Activity {

    @Bind(R.id.ib_agritimeinfo_back)
    ImageButton ibAgritimeinfoBack;
    @Bind(R.id.tv_top_type_info)
    TextView tvTopTypeInfo;
    @Bind(R.id.tv_agritimeinfo_time)
    TextView tvAgritimeinfoTime;
    @Bind(R.id.tv_agritimeinfo_introduce)
    TextView tvAgritimeinfoIntroduce;
    @Bind(R.id.tv_agritimeinfo_specialist_type)
    TextView tvAgritimeinfoSpecialistType;
    @Bind(R.id.tv_agritimeinfo_specialist_name)
    TextView tvAgritimeinfoSpecialistName;
    @Bind(R.id.tv_agritimeinfo_specialist_phone)
    TextView tvAgritimeinfoSpecialistPhone;
    @Bind(R.id.tv_agritimeinfo_specialist_introduce)
    TextView tvAgritimeinfoSpecialistIntroduce;
    @Bind(R.id.tv_agritimeinfo_specialist_address)
    TextView tvAgritimeinfoSpecialistAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agritimeinfo);
        ButterKnife.bind(this);
    }
}
