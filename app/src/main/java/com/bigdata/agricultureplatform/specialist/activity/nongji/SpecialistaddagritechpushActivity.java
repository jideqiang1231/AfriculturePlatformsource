package com.bigdata.agricultureplatform.specialist.activity.nongji;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment01;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment02;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment03;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment04;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistaddagritechpushActivity extends Activity implements View.OnClickListener {


    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.btn_water)
    Button btnWater;
    @Bind(R.id.btn_fertilizer)
    Button btnFertilizer;
    @Bind(R.id.btn_pesticide)
    Button btnPesticide;
    @Bind(R.id.btn_other)
    Button btnOther;
    @Bind(R.id.l_choice)
    LinearLayout lChoice;
    @Bind(R.id.fragment_content)
    FrameLayout fragmentContent;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private List<Button> btnList = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddagritechpush);
        ButterKnife.bind(this);
        //继承的appcompatactivity实现隐藏标题栏
        //getSupportActionBar().hide();
        //接受参数
//        Intent intent = getIntent();
//        specialistId = intent.getIntExtra("专家的id", 0);
        //直接加载下拉框的信息
        //getSpecialistnongjiSpinnerDataFormat(String.valueOf(specialistId));
        //点击事件
        ibBack.setOnClickListener(this);
        btnWater.setOnClickListener(this);
        btnFertilizer.setOnClickListener(this);
        btnPesticide.setOnClickListener(this);
        btnOther.setOnClickListener(this);

        btnList.add(btnWater);
        btnList.add(btnFertilizer);
        btnList.add(btnPesticide);
        btnList.add(btnOther);

        TimeAndEndtime();
    }

    private void TimeAndEndtime() {
        // 進入系統默認為movie
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        setBackgroundColorById(R.id.btn_water);
        ft.replace(R.id.fragment_content, new Fragment01());
        ft.commit();

    }

    //颜色
    private void setBackgroundColorById(int btnId) {
        for (Button btn : btnList) {
            if (btn.getId() == btnId) {
                btn.setBackgroundColor(Color.rgb(90, 120, 200));
                btn.setTextColor(Color.rgb(255, 255, 255));
            } else {
                btn.setBackgroundColor(Color.rgb(140, 150, 160));
                btn.setTextColor(Color.rgb(255, 255, 255));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clickshere. The action bar will
        // automatically handle clicks onthe Home/Up button, so long
        // as you specify a parentactivity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.btn_water) {
            return true;
        }
        Activity returnsuper;
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View view) {
        //必须，否则报错
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;

            case R.id.btn_water:
                setBackgroundColorById(R.id.btn_water);
                ft.replace(R.id.fragment_content, new Fragment01());
                break;

            case R.id.btn_fertilizer:
                setBackgroundColorById(R.id.btn_fertilizer);
                ft.replace(R.id.fragment_content, new Fragment02());
                break;

            case R.id.btn_pesticide:
                setBackgroundColorById(R.id.btn_pesticide);
                ft.replace(R.id.fragment_content, new Fragment03());
                break;

            case R.id.btn_other:
                setBackgroundColorById(R.id.btn_other);
                ft.replace(R.id.fragment_content, new Fragment04());
                break;

            default:
                break;
        }
        ft.commit();
    }


}
