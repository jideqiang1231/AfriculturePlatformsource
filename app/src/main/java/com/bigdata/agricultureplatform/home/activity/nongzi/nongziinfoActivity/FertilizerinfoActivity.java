package com.bigdata.agricultureplatform.home.activity.nongzi.nongziinfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FertilizerinfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_function)
    TextView tvFunction;
    @Bind(R.id.tv_instruction)
    TextView tvInstruction;
    @Bind(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer2);
        ButterKnife.bind(this);
        ibBack.setOnClickListener(this);
        Intent intent = getIntent();
        String fertilizername = intent.getStringExtra("化肥名称");
        String fertilizerinstruction = intent.getStringExtra("使用方法");
        String fertilizern = intent.getStringExtra("N");
        String fertilizerp = intent.getStringExtra("P");
        String fertilizerk = intent.getStringExtra("K");
        String fertilizerother = intent.getStringExtra("Other");
        String fertilizermodercorp = intent.getStringExtra("适种作物");
        String fertilizeradress = intent.getStringExtra("地址");
        String fertilizerproducter = intent.getStringExtra("生产商");
        String fertilizerphone = intent.getStringExtra("联系方式");
        String fertilizerprice = intent.getStringExtra("价格");
        String fertilizertype = intent.getStringExtra("型号");
        String fertilizerproductdate = intent.getStringExtra("生产日期");
        String fertilizershildlife = intent.getStringExtra("保质期");
        tvName.setText(fertilizername);
        tvFunction.setText(fertilizerinstruction);
        tvInstruction.setText(
                "产品氮磷钾和其他含量如下:"
                +fertilizern+"，"+fertilizerp+"，"+fertilizerk+"，"+fertilizerother
                +"/n"+"适种作物："+"/r/n"+fertilizermodercorp
                +"/r/n"+"生产商："+"/n"+fertilizerproducter
                +"/n"+"地址："+"/n"+fertilizeradress
                +"/n"+"联系方式："+"/n"+fertilizerphone
                +"/n"+"价格："+"/n"+fertilizerprice
                +"/n"+"型号："+"/n"+fertilizertype
                +"/n"+"生产日期："+"/n"+fertilizerproductdate
                +"/n"+"保质期："+"/n"+fertilizershildlife
        );
    }

    @Override
    public void onClick(View view) {
        if (view==ibBack)
        {finish();}
    }
}
