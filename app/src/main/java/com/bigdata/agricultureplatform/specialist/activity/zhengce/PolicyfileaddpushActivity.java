package com.bigdata.agricultureplatform.specialist.activity.zhengce;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PolicyfileaddpushActivity extends Activity {

    @Bind(R.id.ib_policyfile_addpush_back)
    ImageButton ibPolicyfileAddpushBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyfileaddpush);
        ButterKnife.bind(this);
    }
}
