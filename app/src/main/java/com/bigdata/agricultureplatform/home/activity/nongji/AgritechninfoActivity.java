package com.bigdata.agricultureplatform.home.activity.nongji;

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

public class AgritechninfoActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_agritechinfo_back)
    ImageButton ibAgritechinfoBack;
    @Bind(R.id.tv_top_type_info)
    TextView tvTopTypeInfo;
    @Bind(R.id.tv_agritechinfo_introduce)
    TextView tvAgritechinfoIntroduce;
    @Bind(R.id.tv_agritechinfo_specialist_type)
    TextView tvAgritechinfoSpecialistType;
    @Bind(R.id.tv_agritechinfo_specialist_name)
    TextView tvAgritechinfoSpecialistName;
    @Bind(R.id.tv_agritechinfo_specialist_phone)
    TextView tvAgritechinfoSpecialistPhone;
    @Bind(R.id.tv_agritechinfo_specialist_introduce)
    TextView tvAgritechinfoSpecialistIntroduce;
    @Bind(R.id.tv_agritechinfo_specialist_address)
    TextView tvAgritechinfoSpecialistAddress;
    @Bind(R.id.tv_agritechinfo_time)
    TextView tvAgritechinfoTime;
    //加入专家信息
    private SpecialistloginBean specialistloginBean;
    private SpecialistloginBean.SpecialistloginresultBean specialistloginresultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agritechninfo);
        ButterKnife.bind(this);
        ibAgritechinfoBack.setOnClickListener(this);


        Intent intent = getIntent();
        // String seedimage = intent.getStringExtra("图片信息");
        String seedname = intent.getStringExtra("种子名称");
        // String seedtype = intent.getStringExtra("种子类型");
        String pushinfo = intent.getStringExtra("农技推送详情");
        Integer specialistId = intent.getIntExtra("专家id", 0);
        String pushtime = intent.getStringExtra("推送时间");
//  照片先不要
//        if (Util.isOnMainThread() && !TextUtils.isEmpty(Constants.ImageBASE_URL + seedimage) && this != null && ivSeedinfoImage != null && ivSeedinfoImage.getContext() != null) {
//        Glide.with(this).load(Constants.ImageBASE_URL + seedimage)
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivSeedinfoImage);
//    }
        tvTopTypeInfo.setText(seedname);
        //  tvSeedinfoType.setText(seedtype);
        tvAgritechinfoIntroduce.setText(pushinfo);
        tvAgritechinfoTime.setText(pushtime);
        //注意int的id造成的java.lang.RuntimeException+Resources$NotFoundException:
        Toast.makeText(this, String.valueOf(specialistId), Toast.LENGTH_SHORT).show();
        //在种子详情信息中加入专家的基本信息：
        addSpecialistinfoinseedinfo(specialistId);
    }

    //抽取方法，增加专家的基本信息
    private void addSpecialistinfoinseedinfo(Integer specialistId) {
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
                            tvAgritechinfoSpecialistType.setText(specialistloginresultBean.getSpecialistType());
                            tvAgritechinfoSpecialistName.setText(specialistloginresultBean.getSpecialistName());
                            tvAgritechinfoSpecialistIntroduce.setText(specialistloginresultBean.getSpecialistInstructions());
                            tvAgritechinfoSpecialistPhone.setText(specialistloginresultBean.getSpecialistPhone());
                            tvAgritechinfoSpecialistAddress.setText(specialistloginresultBean.getSpecialistAddress());
                        }
                    }
                });
    }

    private void loginprocessData(String json) {
        specialistloginBean = JSON.parseObject(json, SpecialistloginBean.class);
        specialistloginresultBean = specialistloginBean.getSpecialistloginresult();
        Log.e(TAG, specialistloginBean.getMsg());
    }


    @Override
    public void onClick(View view) {
        if (view == ibAgritechinfoBack) {
            finish();
        }
    }
}
