package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistseedpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistseedpush_back)
    ImageButton ibSpecialistseedpushBack;
    @Bind(R.id.lv_specialist_zhongzipush)
    ListView lvSpecialistZhongzipush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistseedpush);
        ButterKnife.bind(this);
        //返回的监听事件
        ibSpecialistseedpushBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistseedpushBack) {
            finish();
        }
    }
}
