package com.bigdata.agricultureplatform.specialist.activity.nongji;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.adapter.AgritechspinnerAdapter;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistseedlistBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistaddagritechpushActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {


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

    private SpecialistseedlistBean specialistseedlistbean;
    private List<SpecialistseedlistBean.SpecialistseedresultBean> includeseednamelist;
    private AgritechspinnerAdapter adapter;
    //设置日期这块
    //日期时间的选择
    TimePickerDialog mDialogYearMonthDay1;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    //用于接受下拉框里种子名称，向后端插入
    private String Spinneritem_seedname;
    //接受时间，内容，区域的值
    private String pushtime;
    private String content;
    private String area;
    private int specialistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddagritechpush);
        ButterKnife.bind(this);
        //继承的appcompatactivity实现隐藏标题栏
        getSupportActionBar().hide();
        //接受参数
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);

        //直接加载下拉框的信息
        getSpecialistnongjiSpinnerDataFormat(String.valueOf(specialistId));
        //设置日期
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mDialogYearMonthDay1 = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
//点击事件
        ibSpecialistNongjiAddpushinfoBack.setOnClickListener(this);
        tvSpecialistPushinfoNongjiPushdata.setOnClickListener(this);
        bSpecialistNongjiPushinfoSubmit.setOnClickListener(this);
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
                        specialistAgritechSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //用自己的方法获取这个adpterview中的值然后赋值的过程
                                //数据必须用bean对象接受
                                SpecialistseedlistBean.SpecialistseedresultBean specialistseedresultBean= (SpecialistseedlistBean.SpecialistseedresultBean) adapterView.getSelectedItem();
                                Spinneritem_seedname=specialistseedresultBean.getSeedName();
                                Log.e(TAG,"看看这次请求怎么样"+specialistseedresultBean.getSeedName());
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.getSelectedItem();
                            }
                        });
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
            case R.id.ib_specialist_nongji_addpushinfo_back:
                finish();
                break;
            case R.id.tv_specialist_pushinfo_nongji_pushdata:
                //使用这个别人的库需要继承appcompastActivity
                mDialogYearMonthDay1.show(getSupportFragmentManager(), "year_month_day");
                break;
            case R.id.b_specialist_nongji_pushinfo_submit:
                content=etSpecialistNongjiPushinfoIntroduce.getText().toString();
                area=etSpecialistNongjiPushinfoPusharea.getText().toString();
                //一共要传输下面五个值，不要为空
                //specialistId
                //Spinneritem_seedname
                //pushtime
                //content
                //area
                if (TextUtils.isEmpty(Spinneritem_seedname)
                        || TextUtils.isEmpty(pushtime)
                        || TextUtils.isEmpty(content)
                        || TextUtils.isEmpty(area)) {
                    Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
                } else {
                    String url = Constants.INSERTNONGJIINFO_URL;
                    OkHttpUtils
                            .post()
                            .url(url)
                            //先干掉
                            .addParams("specialistId", String.valueOf(specialistId))
                            .addParams("seedName", Spinneritem_seedname)
                            .addParams("recommendTime", pushtime)
                            .addParams("recommendContent", content)
                            .addParams("recommendArea", area)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                                    Toast.makeText(SpecialistaddagritechpushActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e(TAG, "注册成功后的response==" + response);
                                    Toast.makeText(SpecialistaddagritechpushActivity.this, "成功发布农技的信息", Toast.LENGTH_LONG).show();

                                }
                            });
                }
                finish();
                break;
        }

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        pushtime= getDateToString(millseconds);
        etSpecialistPushinfoNongjiPushdata.setText(pushtime);
    }

    private String getDateToString(long millseconds) {
        Date date = new Date(millseconds);
        return sf.format(date);
    }
}
