package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistaddpushActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.ib_Specialist_addpushinfo_back)
    ImageButton ibSpecialistAddpushinfoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddpush);
        ButterKnife.bind(this);
        ibSpecialistAddpushinfoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistAddpushinfoBack) {
            finish();
        }
    }
}
