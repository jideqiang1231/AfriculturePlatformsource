package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeedinfoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seedinfo);
        ButterKnife.bind(this);
//        ibSeedinfoBack.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        if (view == ibSeedinfoBack) {
//            finish();
//        }
//    }
}
