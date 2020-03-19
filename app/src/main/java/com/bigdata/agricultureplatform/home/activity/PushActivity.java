package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_zhongzipush_back)
    ImageButton ibZhongzipushBack;
    @Bind(R.id.lv_zhongzipush)
    ListView lvZhongzipush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        ButterKnife.bind(this);
        ibZhongzipushBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view==ibZhongzipushBack){
            finish();
        }
    }
}
