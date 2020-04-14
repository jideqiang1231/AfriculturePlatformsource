package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpeclistagritechpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistagritechpush_back)
    ImageButton ibSpecialistagritechpushBack;
    @Bind(R.id.ib_Specialistagritechpush_addpush)
    ImageButton ibSpecialistagritechpushAddpush;
    @Bind(R.id.lv_Specialistagritechpush_zhongzipush)
    ListView lvSpecialistagritechpushZhongzipush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speclistagritechpush);
        ButterKnife.bind(this);

        //设置点击事件
        ibSpecialistagritechpushBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==ibSpecialistagritechpushBack){
            finish();
        }
    }
}
