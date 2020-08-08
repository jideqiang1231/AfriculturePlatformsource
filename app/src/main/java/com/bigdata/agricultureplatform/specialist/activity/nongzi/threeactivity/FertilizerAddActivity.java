package com.bigdata.agricultureplatform.specialist.activity.nongzi.threeactivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.util.Constants;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FertilizerAddActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {

    @Bind(R.id.ib_addfertilizer_back)
    ImageButton ibAddfertilizerBack;
    @Bind(R.id.et_fertilizer_name)
    EditText etFertilizerName;
    @Bind(R.id.et_fertilizer_instruction)
    EditText etFertilizerInstruction;
    @Bind(R.id.et_fertilizer_n)
    EditText etFertilizerN;
    @Bind(R.id.et_fertilizer_p)
    EditText etFertilizerP;
    @Bind(R.id.et_fertilizer_k)
    EditText etFertilizerK;
    @Bind(R.id.et_fertilizer_other)
    EditText etFertilizerOther;
    @Bind(R.id.et_fertilizer_price)
    EditText etFertilizerPrice;
    @Bind(R.id.et_fertilizer_adress)
    EditText etFertilizerAdress;
    @Bind(R.id.et_fertilizer_manufacturer)
    EditText etFertilizerManufacturer;
    @Bind(R.id.et_fertilizer_phone)
    EditText etFertilizerPhone;
    @Bind(R.id.et_fertilizer_type)
    EditText etFertilizerType;
    @Bind(R.id.et_fertilizer_moderatecrop)
    EditText etFertilizerModeratecrop;
    @Bind(R.id.et_fertilizer_productdata)
    EditText etFertilizerProductdata;
    @Bind(R.id.tv_fertilizer_productdata)
    TextView tvFertilizerProductdata;
    @Bind(R.id.et_fertilizer_shelflife)
    EditText etFertilizerShelflife;
    @Bind(R.id.b_fertilizer_submit)
    Button bFertilizerSubmit;


    //提取本地值
    private String fertilizer_Name;
    private String fertilizer_Instruction;

    private String fertilizer_n;
    private String fertilizer_p;
    private String fertilizer_k;
    private String fertilizer_other;

    private String fertilizer_manufacturer;
    private String fertilizer_price;
    private String fertilizer_adress;
    private String fertilizer_Phone;

    private String fertilizer_type;
    private String fertilizer_moderatecrop;

    private String fertilizer_productdata;
    private String fertilizer_shelflife;
    private String recommen_date;
    TimePickerDialog mDialogYearMonthDay1;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private String productdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer);
        ButterKnife.bind(this);
        //继承的appcompatactivity实现隐藏标题栏
        getSupportActionBar().hide();

        //获取当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        recommen_date=sdf.format(System.currentTimeMillis());
        Toast.makeText(this,recommen_date,Toast.LENGTH_LONG).show();

        long tenYears = 100L * 365 * 1000 * 60 * 60 * 24L;
        mDialogYearMonthDay1 = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
        ibAddfertilizerBack.setOnClickListener(this);
        tvFertilizerProductdata.setOnClickListener(this);
        bFertilizerSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==ibAddfertilizerBack){
            finish();
        }else if (view==tvFertilizerProductdata){
            mDialogYearMonthDay1.show(getSupportFragmentManager(), "year_month_day");
        }
        else if(view==bFertilizerSubmit){
            fertilizer_Name = etFertilizerName.getText().toString().trim();
            fertilizer_Instruction = etFertilizerInstruction.getText().toString().trim();
            fertilizer_n = etFertilizerN.getText().toString().trim();
            fertilizer_p = etFertilizerP.getText().toString().trim();
            fertilizer_k = etFertilizerK.getText().toString().trim();
            fertilizer_other = etFertilizerOther.getText().toString().trim();

            fertilizer_manufacturer = etFertilizerManufacturer.getText().toString().trim();
            fertilizer_price = etFertilizerPrice.getText().toString().trim();
            fertilizer_adress = etFertilizerAdress.getText().toString().trim();
            fertilizer_Phone = etFertilizerPhone.getText().toString().trim();
            fertilizer_type = etFertilizerType.getText().toString().trim();
            fertilizer_moderatecrop = etFertilizerModeratecrop .getText().toString().trim();
            fertilizer_productdata = etFertilizerProductdata .getText().toString().trim();
            fertilizer_shelflife = etFertilizerShelflife .getText().toString().trim();

            if (TextUtils.isEmpty(fertilizer_Name)
                    || TextUtils.isEmpty(fertilizer_Instruction)
                    || TextUtils.isEmpty(fertilizer_n)
                    || TextUtils.isEmpty(fertilizer_p)
                    || TextUtils.isEmpty(fertilizer_k)
                    || TextUtils.isEmpty(fertilizer_other)
                    || TextUtils.isEmpty(fertilizer_manufacturer)
                    || TextUtils.isEmpty(fertilizer_adress)
                    || TextUtils.isEmpty(fertilizer_price)
                    || TextUtils.isEmpty(fertilizer_Phone)
                    || TextUtils.isEmpty(fertilizer_type)
                    || TextUtils.isEmpty(fertilizer_moderatecrop)
                    || TextUtils.isEmpty(fertilizer_productdata)
                    || TextUtils.isEmpty(fertilizer_shelflife)
                    || TextUtils.isEmpty(recommen_date)) {
                Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
            } else {
                String url = Constants.NONGZI_INSERTHUAFEIINFO_URL;
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("fertilizerName", fertilizer_Name)
                        .addParams("fertilizerInstructions", fertilizer_Instruction)
                        .addParams("fertilizerN", fertilizer_n)
                        .addParams("fertilizerP", fertilizer_p)
                        .addParams("fertilizerK", fertilizer_k)
                        .addParams("fertilizerOther", fertilizer_other)
                        .addParams("fertilizerManufacturer", fertilizer_manufacturer)
                        .addParams("fertilizerPrice", fertilizer_price)
                        .addParams("fertilizerAddress", fertilizer_adress)
                        .addParams("fertilizerPhone", fertilizer_Phone)
                        .addParams("fertilizerType", fertilizer_type)
                        .addParams("fertilizerModeratecrop", fertilizer_moderatecrop)
                        .addParams("fertilizerProductiondate", fertilizer_productdata)
                        .addParams("fertilizerShelflife", fertilizer_shelflife)
                        .addParams("recommendData", recommen_date)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                                Toast.makeText(FertilizerAddActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "注册成功后的response==" + response);
                                Toast.makeText(FertilizerAddActivity.this, "成功发布农机的信息", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        productdate= getDateToString(millseconds);
        etFertilizerProductdata.setText(productdate);
    }
        private String getDateToString(long millseconds) {
            Date date = new Date(millseconds);
            return sf.format(date);
        }
    }

