package com.bigdata.agricultureplatform.specialist.activity.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.app.LoginActivity;
import com.bigdata.agricultureplatform.specialist.activity.SpecialistloginActivity;
import com.bigdata.agricultureplatform.specialist.activity.nongji.SpeclistagritechpushActivity;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.SpecialistAgriSuppliesActivity;
import com.bigdata.agricultureplatform.specialist.activity.zhengce.PolicyfilepushActivity;
import com.bigdata.agricultureplatform.specialist.activity.zhengce.SpecialistpolicypushActivity;
import com.bigdata.agricultureplatform.specialist.activity.zhongzi.SpecialistseedpushActivity;

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
    @Bind(R.id.tv_specialist_policypush)
    TextView tvSpecialistPolicypush;
    @Bind(R.id.tv_specialist_agrisupplies_push)
    TextView tvSpecialistAgrisuppliesPush;
    @Bind(R.id.back_to_userlogin)
    TextView backToUserlogin;


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
        //种子，农技，政策，农资
        tvSpecialistSeedpush.setOnClickListener(this);
        tvSpecialistAgritechPush.setOnClickListener(this);
        tvSpecialistPolicypush.setOnClickListener(this);
        tvSpecialistAgrisuppliesPush.setOnClickListener(this);
        //点击回到用户登录界面
        backToUserlogin.setOnClickListener(this);
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

            //同上传入专家的id
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistid);

            intent.setClass(SpecialistActivity.this, SpeclistagritechpushActivity.class);
            startActivity(intent);
        } else if (view == tvSpecialistPolicypush) {
            new AlertDialog.Builder(this)
                    .setTitle("请选择下边两种推送模式")
                    .setNegativeButton("文字形式发送", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.putExtra("专家的id", specialistid);
                            intent.setClass(SpecialistActivity.this, SpecialistpolicypushActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setPositiveButton("文件形式发送", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.putExtra("专家的id", specialistid);
                            intent.setClass(SpecialistActivity.this, PolicyfilepushActivity.class);
                            startActivity(intent);

                        }
                    })
                    .create()
                    .show();
        } else if (view == tvSpecialistAgrisuppliesPush) {
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistid);
            intent.setClass(SpecialistActivity.this, SpecialistAgriSuppliesActivity.class);
            startActivity(intent);
        }else if (view == backToUserlogin) {
            Intent intent = new Intent();
            intent.setClass(SpecialistActivity.this, SpecialistloginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
