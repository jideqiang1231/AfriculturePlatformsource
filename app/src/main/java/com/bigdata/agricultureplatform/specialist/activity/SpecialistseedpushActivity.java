package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistseedpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistseedpush_back)
    ImageButton ibSpecialistseedpushBack;
    @Bind(R.id.lv_specialist_zhongzipush)
    ListView lvSpecialistZhongzipush;

    //前边两个activity逐级传过来的id，用于数据库查询
    private Integer specialistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistseedpush);
        ButterKnife.bind(this);
        //获取前边specialistactivity中传来的id,
        //注意：id是整型值，一定要妥善接受，有个deaultvalue设置为0，或者传送的时候用bundle或者intent绑定一下
        Intent intent = getIntent();
        specialistId=intent.getIntExtra("专家的id",0);
        Log.e("TAG", String.valueOf(specialistId));
        //int类型连土司都要注意
        Toast.makeText(this, String.valueOf(specialistId), Toast.LENGTH_SHORT).show();
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
