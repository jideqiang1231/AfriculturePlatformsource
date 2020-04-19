package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistActivity extends Activity implements View.OnClickListener {


    @Bind(R.id.tv_sprcialist_type)
    TextView tvSprcialistType;
    @Bind(R.id.tv_sprcialist_name)
    TextView tvSprcialistName;
    @Bind(R.id.tv_specialist_search)
    TextView tvSpecialistSearch;
    @Bind(R.id.tv_specialist_seedpush)
    TextView tvSpecialistSeedpush;
    @Bind(R.id.tv_specialist_agritech_push)
    TextView tvSpecialistAgritechPush;


    //    private String specialistid;
    private String specialistname;
    private String specialisttype;
    private Integer specialistid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);
        ButterKnife.bind(this);
//intent要用this的getIntent()获取,specialistacitvity获取speclistloginactivity传过来的信息
        Intent intent = getIntent();
//用intent.getXXXExtra("key-name")或是intent.getXXXExtra("key-name", default-value)获取值
        //这里的id传过来的是integer类型一定注意不然 nullpointexception
        specialistid = intent.getIntExtra("专家id", 0);
        specialistname = intent.getStringExtra("专家姓名");
        specialisttype = intent.getStringExtra("专家类型");
        tvSprcialistName.setText(specialistname);
        tvSprcialistType.setText(specialisttype);

        Log.e("TAG", String.valueOf(specialistid));
        //监听事件，专家发布信息列表跳转
        tvSpecialistSeedpush.setOnClickListener(this);
        //发布农技信息点击事件
        tvSpecialistAgritechPush.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvSpecialistSeedpush) {
            //把从specialistloginacitvity中传到本specialistactivity中的参数
            //传到下一个specilistseedpushactivity中用于外键查找，查找此专家发布过的产品，用于删除和发布
            Log.e("TAG", String.valueOf(specialistid));//打印一下整形值
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistid);
            intent.putExtra("专家的类型", specialisttype);
            intent.setClass(SpecialistActivity.this, SpecialistseedpushActivity.class);
            startActivity(intent);
        } else if (view == tvSpecialistAgritechPush) {
//            Intent intent = new Intent(this, SpeclistagritechpushActivity.class);
//            startActivity(intent);
            //同上传入专家的id
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistid);
            //不需要类型
            // intent.putExtra("专家的类型", specialisttype);
            intent.setClass(SpecialistActivity.this, SpeclistagritechpushActivity.class);
            startActivity(intent);
        }
    }
}
