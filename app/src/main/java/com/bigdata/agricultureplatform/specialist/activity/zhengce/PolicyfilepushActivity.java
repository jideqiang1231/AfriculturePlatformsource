package com.bigdata.agricultureplatform.specialist.activity.zhengce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PolicyfilepushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_policy_push_back)
    ImageButton ibPolicyPushBack;

    @Bind(R.id.lv_policyfile_push)
    ListView lvPolicyfilePush;
    @Bind(R.id.ib_policy_file_addpush)
    ImageButton ibPolicyFileAddpush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyfilepush);
        ButterKnife.bind(this);
        ibPolicyPushBack.setOnClickListener(this);
        ibPolicyFileAddpush.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ibPolicyPushBack) {
            finish();
        } else if (view == ibPolicyFileAddpush) {
            //传入专家的参数id,添加信息时候要插入
            Intent intent = new Intent();
            intent.setClass(this, PolicyfileaddpushActivity.class);
            startActivity(intent);
        }
    }
}
