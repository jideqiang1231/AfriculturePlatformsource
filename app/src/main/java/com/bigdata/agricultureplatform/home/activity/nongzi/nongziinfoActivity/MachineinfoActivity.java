package com.bigdata.agricultureplatform.home.activity.nongzi.nongziinfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MachineinfoActivity extends Activity implements View.OnClickListener {

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
        setContentView(R.layout.activity_machineinfo);
        ButterKnife.bind(this);
        ibBack.setOnClickListener(this);
        Intent intent = getIntent();

        String name = intent.getStringExtra("机器名称");
        String instruction = intent.getStringExtra("使用方法");
        String phone = intent.getStringExtra("联系方式");
        String adress = intent.getStringExtra("地址");
        String producter = intent.getStringExtra("生产商");
        String price = intent.getStringExtra("价格");
        String type = intent.getStringExtra("型号");

        tvName.setText(name);
        tvFunction.setText(instruction);

        tvInstruction.setText(
                "产品说明:"
                        + "/r/n" + "生产商：" + "/n" + producter
                        + "/n" + "地址：" + "/n" + adress
                        + "/n" + "联系方式：" + "/n" + phone
                        + "/n" + "价格：" + "/n" + price
                        + "/n" + "型号：" + "/n" + type
        );
    }

    @Override
    public void onClick(View view) {

        if (view==ibBack)
        {
            finish();
        }
    }
}
