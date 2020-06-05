package com.bigdata.agricultureplatform.home.activity.zhengce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistloginBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PoclicyinfoActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Poclicyinfo_back)
    ImageButton ibPoclicyinfoBack;
    @Bind(R.id.tv_Poclicyinfo_time)
    TextView tvPoclicyinfoTime;
    @Bind(R.id.tv_Poclicyinfo_introduce)
    TextView tvPoclicyinfoIntroduce;
    @Bind(R.id.tv_Poclicyinfo_specialist_type)
    TextView tvPoclicyinfoSpecialistType;
    @Bind(R.id.tv_Poclicyinfo_specialist_name)
    TextView tvPoclicyinfoSpecialistName;
    @Bind(R.id.tv_Poclicyinfo_specialist_phone)
    TextView tvPoclicyinfoSpecialistPhone;
    @Bind(R.id.tv_Poclicyinfo_specialist_introduce)
    TextView tvPoclicyinfoSpecialistIntroduce;
    @Bind(R.id.tv_Poclicyinfo_specialist_address)
    TextView tvPoclicyinfoSpecialistAddress;
    @Bind(R.id.tv_Poclicyinfo_title)
    TextView tvPoclicyinfoTitle;
    private SpecialistloginBean specialistloginBean;
    private SpecialistloginBean.SpecialistloginresultBean specialistloginresultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poclicyinfo);
        ButterKnife.bind(this);
        ibPoclicyinfoBack.setOnClickListener(this);

        Intent intent = getIntent();
        String policytitle = intent.getStringExtra("标题");
        String policycontent = intent.getStringExtra("政策内容");
        Integer specialistId = intent.getIntExtra("专家id", 0);
        String pushtime = intent.getStringExtra("推送时间");

        //设置信息

        tvPoclicyinfoTitle.setText(policytitle);

        tvPoclicyinfoIntroduce.setText(policycontent);
        tvPoclicyinfoTime.setText(pushtime);
        //在农时详情信息中加入专家的基本信息：
        addSpecialistinfotozhengce(specialistId);
    }

    private void addSpecialistinfotozhengce(Integer specialistId) {
        String url = Constants.SPECIALISTINFOFORSEEDINFO;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("specialistId", String.valueOf(specialistId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "根据种子的外键信息请求专家基本信息数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "根据种子的外键信息请求专家基本信息成功==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        //抽出出来一个方法，传入response
                        loginprocessData(response);
                        if (specialistloginBean.getMsg().equals("获取专家信息失败")) {
                            Toast.makeText(getBaseContext(), "专家信息这块没有根据种子的外键得到", Toast.LENGTH_LONG).show();
                        } else {
//在这里往布局里边设置内容：专家的基本信息：
                            tvPoclicyinfoSpecialistType.setText(specialistloginresultBean.getSpecialistType());
                            tvPoclicyinfoSpecialistName.setText(specialistloginresultBean.getSpecialistName());
                            tvPoclicyinfoIntroduce.setText(specialistloginresultBean.getSpecialistInstructions());
                            tvPoclicyinfoSpecialistPhone.setText(specialistloginresultBean.getSpecialistPhone());
                            tvPoclicyinfoSpecialistAddress.setText(specialistloginresultBean.getSpecialistAddress());
                        }
                    }
                });
    }

    private void loginprocessData(String response) {
        specialistloginBean = JSON.parseObject(response, SpecialistloginBean.class);
        specialistloginresultBean = specialistloginBean.getSpecialistloginresult();
        Log.e(TAG, specialistloginBean.getMsg());
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
