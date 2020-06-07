package com.bigdata.agricultureplatform.specialist.activity.zhengce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistaddpolicypushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_specialistpolicy_back)
    ImageButton ibSpecialistpolicyBack;
    @Bind(R.id.et_specialist_policytitle)
    EditText etSpecialistPolicytitle;
    @Bind(R.id.et_specialist_policycontent)
    EditText etSpecialistPolicycontent;
    @Bind(R.id.b_specialistpolicy_submit)
    Button bSpecialistpolicySubmit;
    private int specialistId;
    private String policytitle;
    private String policycontent;
    private String nowdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddpolicypush);
        ButterKnife.bind(this);
        //接受参数
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        ibSpecialistpolicyBack.setOnClickListener(this);
        bSpecialistpolicySubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_specialistpolicy_back:
                finish();
                break;
            case R.id.b_specialistpolicy_submit:
                //标题和内容
                policytitle=etSpecialistPolicytitle.getText().toString();
                policycontent=etSpecialistPolicycontent.getText().toString();
                //获取当前日期
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                nowdate=formatter.format(calendar.getTime());

                if (TextUtils.isEmpty(policytitle)
                        || TextUtils.isEmpty(policycontent)
                     ) {
                    Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
                } else {
                    String url = Constants.SPECIALISTINSETZHENGCE_URL;
                    OkHttpUtils
                            .post()
                            .url(url)
                            //先干掉
                            .addParams("specialistId", String.valueOf(specialistId))
                            .addParams("policyTitle", policytitle)
                            .addParams("policyTime", nowdate)
                            .addParams("policyContent",policycontent)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e(TAG, "插入数据失败==" + e.getMessage());
                                    Toast.makeText(SpecialistaddpolicypushActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e(TAG, "插入数据成功response==" + response);
                                    Toast.makeText(SpecialistaddpolicypushActivity.this, "成功发布政策的信息", Toast.LENGTH_LONG).show();

                                }
                            });
                }
                finish();
                break;
        }
    }
}