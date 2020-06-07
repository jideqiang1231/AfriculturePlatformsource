package com.bigdata.agricultureplatform.specialist.activity.zhengce;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.nongji.SpecialistaddagritechpushActivity;
import com.bigdata.agricultureplatform.specialist.adapter.SpecialistPolicyPushListViewAdapter;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistpolicylistBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistpolicypushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistpolicypush_back)
    ImageButton ibSpecialistpolicypushBack;
    @Bind(R.id.ib_Specialistpolicypush_addpush)
    ImageButton ibSpecialistpolicypushAddpush;
    @Bind(R.id.lv_Specialistpolicy_push)
    ListView lvSpecialistpolicyPush;
    //接受传值
    private int specialistId;
    private SpecialistpolicylistBean specialist_policylistBean;
    private List<SpecialistpolicylistBean.SpecialistseedresultBean> specialistpolivy_resultBeans;
    private SpecialistPolicyPushListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistpolicypush);
        ButterKnife.bind(this);
        //接受specialistactivity的传值
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        Log.e("TAG", String.valueOf(specialistId));
        //获取专家信息
        initSpecialistpushzhengceData(String.valueOf(specialistId));
        //设置点击事件
        ibSpecialistpolicypushBack.setOnClickListener(this);
        ibSpecialistpolicypushAddpush.setOnClickListener(this);
    }

    private void initSpecialistpushzhengceData(String specialistId) {
        String url = Constants.SPECIALISTZHENGCEINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("specialistId", specialistId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "专家请求种子发布历史数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "专家获取发布过得历史数据成功==" + response);
                        specialistpushnongjihistoryprocessData(response);

                        adapter = new SpecialistPolicyPushListViewAdapter(SpecialistpolicypushActivity.this, specialistpolivy_resultBeans);
                        lvSpecialistpolicyPush.setAdapter(adapter);

                    }
                });
    }

    private void specialistpushnongjihistoryprocessData(String response) {
        specialist_policylistBean = JSON.parseObject(response, SpecialistpolicylistBean.class);
        //表面bean里面的listbean
        specialistpolivy_resultBeans = specialist_policylistBean.getSpecialistseedresult();
        //打印一下
        Log.e(TAG, "数组显示用.get0" + specialistpolivy_resultBeans.get(0).getPolicyTime());

    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistpolicypushBack) {
            finish();
        } else if (view == ibSpecialistpolicypushAddpush) {
            //传入专家的参数id,添加信息时候要插入
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistId);
            intent.setClass(this, SpecialistaddpolicypushActivity.class);
            startActivity(intent);
        }
    }
}
