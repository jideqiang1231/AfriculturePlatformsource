package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.adapter.SpecialistAgritechPushListViewAdapter;
import com.bigdata.agricultureplatform.specialist.adapter.SpecialistPushListViewAdapter;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistagritechlistBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpeclistagritechpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistagritechpush_back)
    ImageButton ibSpecialistagritechpushBack;
    @Bind(R.id.ib_Specialistagritechpush_addpush)
    ImageButton ibSpecialistagritechpushAddpush;
    @Bind(R.id.lv_Specialistagritechpush_push)
    ListView lvSpecialistagritechpushPush;

    //接受传值
    private int specialistId;
    private SpecialistagritechlistBean specialistagritechlistBean;
    private List<SpecialistagritechlistBean.SpecialistnongjiresultBean> specialistnongjiresultBeans;
    private SpecialistAgritechPushListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speclistagritechpush);
        ButterKnife.bind(this);
        //接受specialistactivity的传值
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        Log.e("TAG", String.valueOf(specialistId));
        //获取专家信息
        initSpecialistpushnongjiData(String.valueOf(specialistId));
        //设置点击事件
        ibSpecialistagritechpushBack.setOnClickListener(this);
        ibSpecialistagritechpushAddpush.setOnClickListener(this);
    }

    private void initSpecialistpushnongjiData(String specialistId) {
        getSpecialistpushnongjiDataFormat(specialistId);
    }

    private void getSpecialistpushnongjiDataFormat(String specialistId) {
        String url = Constants.SPECIALISTNONGJIINFO_URL;
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

                        adapter = new SpecialistAgritechPushListViewAdapter(SpeclistagritechpushActivity.this, specialistnongjiresultBeans);
                        lvSpecialistagritechpushPush.setAdapter(adapter);

                    }
                });
    }

    private void specialistpushnongjihistoryprocessData(String nongjijsonlist) {
        specialistagritechlistBean = JSON.parseObject(nongjijsonlist, SpecialistagritechlistBean.class);
        //表面bean里面的listbean
        specialistnongjiresultBeans = specialistagritechlistBean.getSpecialistnongjiresult();
        //打印一下
        Log.e(TAG, "数组显示用.get0" + specialistnongjiresultBeans.get(0).getRecommendTime());

    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistagritechpushBack) {
            finish();
        } else if (view == ibSpecialistagritechpushAddpush) {
            //传入专家的参数id,添加信息时候要插入
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistId);
            intent.setClass(this, SpecialistaddagritechpushActivity.class);
            startActivity(intent);
        }
    }
}
