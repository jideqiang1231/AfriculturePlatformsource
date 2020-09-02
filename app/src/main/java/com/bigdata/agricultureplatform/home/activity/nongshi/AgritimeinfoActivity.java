package com.bigdata.agricultureplatform.home.activity.nongshi;

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

public class AgritimeinfoActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.ib_agritimeinfo_back)
    ImageButton ibAgritimeinfoBack;
    @Bind(R.id.tv_top_type_info)
    TextView tvTopTypeInfo;
    @Bind(R.id.tv_agritimeinfo_time)
    TextView tvAgritimeinfoTime;
    @Bind(R.id.tv_agritimeinfo_introduce)
    TextView tvAgritimeinfoIntroduce;
    @Bind(R.id.tv_agritimeinfo_specialist_type)
    TextView tvAgritimeinfoSpecialistType;
    @Bind(R.id.tv_agritimeinfo_specialist_name)
    TextView tvAgritimeinfoSpecialistName;
    @Bind(R.id.tv_agritimeinfo_specialist_phone)
    TextView tvAgritimeinfoSpecialistPhone;
    @Bind(R.id.tv_agritimeinfo_specialist_introduce)
    TextView tvAgritimeinfoSpecialistIntroduce;
    @Bind(R.id.tv_agritimeinfo_specialist_address)
    TextView tvAgritimeinfoSpecialistAddress;
    private SpecialistloginBean specialistloginBean;
    private SpecialistloginBean.SpecialistloginresultBean specialistloginresultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agritimeinfo);
        ButterKnife.bind(this);
        //点击事件
        ibAgritimeinfoBack.setOnClickListener(this);
        Intent intent = getIntent();
        // String seedimage = intent.getStringExtra("图片信息");
        String seedname = intent.getStringExtra("种子名称");
        // String seedtype = intent.getStringExtra("种子类型");
        String pushinfo = intent.getStringExtra("农技推送详情");
        Integer specialistId = intent.getIntExtra("专家id", 0);
        String pushtime = intent.getStringExtra("推送时间");

        //设置信息
        tvTopTypeInfo.setText(seedname);
        //  tvSeedinfoType.setText(seedtype);
        tvAgritimeinfoIntroduce.setText(pushinfo);
        tvAgritimeinfoTime.setText(pushtime);
        //在农时详情信息中加入专家的基本信息：
        addSpecialistinfoinnongshiinfo(specialistId);
    }

    private void addSpecialistinfoinnongshiinfo(Integer specialistId) {
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
                            tvAgritimeinfoSpecialistType.setText(specialistloginresultBean.getSpecialistType());
                            tvAgritimeinfoSpecialistName.setText(specialistloginresultBean.getSpecialistName());
                            tvAgritimeinfoSpecialistIntroduce.setText(specialistloginresultBean.getSpecialistInstructions());
                            tvAgritimeinfoSpecialistPhone.setText(specialistloginresultBean.getSpecialistPhone());
                            tvAgritimeinfoSpecialistAddress.setText(specialistloginresultBean.getSpecialistAddress());
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
        if (view == ibAgritimeinfoBack) {
            finish();
        }
    }
}
