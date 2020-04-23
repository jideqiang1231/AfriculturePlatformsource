package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.adapter.AgritechspinnerAdapter;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistseedlistBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistaddagritechpushActivity extends Activity implements View.OnClickListener {


    @Bind(R.id.et_specialist_nongji_pushinfo_introduce)
    EditText etSpecialistNongjiPushinfoIntroduce;
    @Bind(R.id.et_specialist_nongji_pushinfo_pusharea)
    EditText etSpecialistNongjiPushinfoPusharea;
    @Bind(R.id.et_specialist_pushinfo_nongji_pushdata)
    EditText etSpecialistPushinfoNongjiPushdata;
    @Bind(R.id.b_specialist_nongji_pushinfo_submit)
    Button bSpecialistNongjiPushinfoSubmit;
    @Bind(R.id.ib_specialist_nongji_addpushinfo_back)
    ImageButton ibSpecialistNongjiAddpushinfoBack;
    @Bind(R.id.tv_specialist_pushinfo_nongji_pushdata)
    TextView tvSpecialistPushinfoNongjiPushdata;
    @Bind(R.id.specialist_agritech_spinner)
    Spinner specialistAgritechSpinner;
    private int specialistId;
    private SpecialistseedlistBean specialistseedlistbean;
    private List<SpecialistseedlistBean.SpecialistseedresultBean> includeseednamelist;
    private AgritechspinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddagritechpush);
        ButterKnife.bind(this);
        //接受参数
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        ibSpecialistNongjiAddpushinfoBack.setOnClickListener(this);
        //直接加载下拉框的信息
        getSpecialistnongjiSpinnerDataFormat(String.valueOf(specialistId));
    }

    private void getSpecialistnongjiSpinnerDataFormat(String specialistId) {
        String url = Constants.SPECIALISTZHONGZIINFO_URL;
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
                        specialistpushnongjiSpinnerprocessData(response);

                        adapter = new AgritechspinnerAdapter(SpecialistaddagritechpushActivity.this, includeseednamelist);
                        specialistAgritechSpinner.setAdapter(adapter);

                    }
                });
    }

    private void specialistpushnongjiSpinnerprocessData(String response) {
        specialistseedlistbean = JSON.parseObject(response, SpecialistseedlistBean.class);
        //表面bean里面的listbean
        includeseednamelist = specialistseedlistbean.getSpecialistseedresult();
        //打印一下
        Log.e(TAG, "数组显示用.get0" + includeseednamelist.get(0).getSeedName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_agritech_push_back:
                finish();
                break;
        }
    }
}
