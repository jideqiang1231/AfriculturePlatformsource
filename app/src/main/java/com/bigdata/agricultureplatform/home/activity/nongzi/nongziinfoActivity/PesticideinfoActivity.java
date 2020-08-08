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

public class PesticideinfoActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_pesticideinfo);
        ButterKnife.bind(this);
        ibBack.setOnClickListener(this);
        Intent intent = getIntent();

        String name = intent.getStringExtra("农药名称");
        String instruction = intent.getStringExtra("使用方法");
        String dose = intent.getStringExtra("剂量");
        String price = intent.getStringExtra("价格");
        String content = intent.getStringExtra("含量");
        String adress = intent.getStringExtra("地址");
        String compsition = intent.getStringExtra("组成成分");
        String licencenumber = intent.getStringExtra("登记号");
        String manufacture = intent.getStringExtra("生产商");
        String modercrop = intent.getStringExtra("适用作物");
        String phone = intent.getStringExtra("联系方式");
        String pesticide = intent.getStringExtra("毒性");
        String note = intent.getStringExtra("注意事项");
        String productdate = intent.getStringExtra("生产日期");
        String shildlife = intent.getStringExtra("保质期");
        tvName.setText(name);
        tvFunction.setText(instruction+dose);
        tvInstruction.setText(
                "组成成分与含量:"
                        +compsition+"，"+content
                        +"/n"+"适种作物："+"/r/n"+pesticide
                        +"/n"+"毒性："+"/r/n"+modercrop
                        +"/n"+"登记号："+"/r/n"+licencenumber
                        +"/r/n"+"生产商："+"/n"+manufacture
                        +"/n"+"地址："+"/n"+adress
                        +"/n"+"联系方式："+"/n"+phone
                        +"/n"+"价格："+"/n"+price
                        +"/n"+"注意事项："+"/n"+note
                        +"/n"+"生产日期："+"/n"+productdate
                        +"/n"+"保质期："+"/n"+shildlife
        );
    }

    @Override
    public void onClick(View view) {
        if (view==ibBack){
            finish();
        }
    }
}
