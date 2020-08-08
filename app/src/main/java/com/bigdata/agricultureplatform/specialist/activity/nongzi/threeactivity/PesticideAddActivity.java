package com.bigdata.agricultureplatform.specialist.activity.nongzi.threeactivity;

import android.app.Activity;
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

public class PesticideAddActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {

    @Bind(R.id.ib_addpesticide_back)
    ImageButton ibAddpesticideBack;
    @Bind(R.id.et_pesticide_name)
    EditText etPesticideName;
    @Bind(R.id.et_pesticide_instruction)
    EditText etPesticideInstruction;
    @Bind(R.id.et_pesticide_note)
    EditText etPesticideNote;
    @Bind(R.id.et_pesticide_virulence)
    EditText etPesticideVirulence;
    @Bind(R.id.et_pesticide_composition)
    EditText etPesticideComposition;
    @Bind(R.id.et_pesticide_dosage)
    EditText etPesticideDosage;
    @Bind(R.id.et_pesticide_predisease)
    EditText etPesticidePredisease;
    @Bind(R.id.et_pesticide_content)
    EditText etPesticideContent;
    @Bind(R.id.et_pesticide_price)
    EditText etPesticidePrice;
    @Bind(R.id.et_pesticide_adress)
    EditText etPesticideAdress;
    @Bind(R.id.et_pesticide_manufacturer)
    EditText etPesticideManufacturer;
    @Bind(R.id.et_pesticide_phone)
    EditText etPesticidePhone;
    @Bind(R.id.et_pesticide_licensenumber)
    EditText etPesticideLicensenumber;
    @Bind(R.id.et_pesticide_type)
    EditText etPesticideType;
    @Bind(R.id.et_pesticide_moderatecrop)
    EditText etPesticideModeratecrop;
    @Bind(R.id.et_pesticide_productdata)
    EditText etPesticideProductdata;
    @Bind(R.id.tv_pesticide_productdata)
    TextView tvPesticideProductdata;
    @Bind(R.id.et_pesticide_shelflife)
    EditText etPesticideShelflife;
    @Bind(R.id.b_pesticide_submit)
    Button bPesticideSubmit;

    //提取本地值
    private String pesticide_name;
    private String pesticide_instruction;
    private String pesticide_note;

    private String pesticide_virulence;
    private String pesticide_composition;
    private String pesticide_dosage;

    private String pesticide_predisease;
    private String pesticide_content;
    private String pesticide_price;

    private String pesticide_adress;
    private String pesticide_manufacturer;
    private String pesticide_phone;

    private String pesticide_licensenumber;
    private String pesticide_type;
    private String pesticide_moderatecrop;

    private String pesticide_productdata;
    private String pesticide_shelflife;

    private String recommen_date;
    TimePickerDialog mDialogYearMonthDay;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private String productdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesticide);
        ButterKnife.bind(this);
        //继承的appcompatactivity实现隐藏标题栏
        getSupportActionBar().hide();

        //获取当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        recommen_date=sdf.format(System.currentTimeMillis());
        Toast.makeText(this,recommen_date,Toast.LENGTH_LONG).show();

        long tenYears = 100L * 365 * 1000 * 60 * 60 * 24L;
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
        ibAddpesticideBack.setOnClickListener(this);
        tvPesticideProductdata.setOnClickListener(this);
        bPesticideSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==ibAddpesticideBack)
        {
            finish();
        }else if (view==tvPesticideProductdata){
            mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
        }else if (view==bPesticideSubmit) {

            pesticide_name = etPesticideName.getText().toString().trim();
            pesticide_instruction = etPesticideInstruction.getText().toString().trim();
            pesticide_note = etPesticideNote.getText().toString().trim();
            pesticide_virulence = etPesticideVirulence.getText().toString().trim();
            pesticide_composition = etPesticideComposition.getText().toString().trim();
            pesticide_dosage = etPesticideDosage.getText().toString().trim();

            pesticide_predisease = etPesticidePredisease.getText().toString().trim();
            pesticide_content = etPesticideContent.getText().toString().trim();
            pesticide_price = etPesticidePrice.getText().toString().trim();
            pesticide_adress = etPesticideAdress.getText().toString().trim();
            pesticide_manufacturer = etPesticideManufacturer.getText().toString().trim();
            pesticide_phone = etPesticidePhone.getText().toString().trim();
            pesticide_licensenumber = etPesticideLicensenumber .getText().toString().trim();
            pesticide_type = etPesticideType .getText().toString().trim();
            pesticide_moderatecrop = etPesticideModeratecrop .getText().toString().trim();
            pesticide_productdata = etPesticideProductdata .getText().toString().trim();
            pesticide_shelflife = etPesticideShelflife .getText().toString().trim();
            if (TextUtils.isEmpty(pesticide_name)
                    || TextUtils.isEmpty(pesticide_instruction)
                    || TextUtils.isEmpty(pesticide_note)
                    || TextUtils.isEmpty(pesticide_virulence)
                    || TextUtils.isEmpty(pesticide_composition)
                    || TextUtils.isEmpty(pesticide_dosage)
                    || TextUtils.isEmpty(pesticide_predisease)
                    || TextUtils.isEmpty(pesticide_content)
                    || TextUtils.isEmpty(pesticide_price)
                    || TextUtils.isEmpty(pesticide_adress)
                    || TextUtils.isEmpty(pesticide_manufacturer)
                    || TextUtils.isEmpty(pesticide_phone)
                    || TextUtils.isEmpty(pesticide_licensenumber)
                    || TextUtils.isEmpty(pesticide_type)
                    || TextUtils.isEmpty(pesticide_moderatecrop)
                    || TextUtils.isEmpty(pesticide_productdata)
                    || TextUtils.isEmpty(pesticide_shelflife)
                    || TextUtils.isEmpty(recommen_date)) {
                Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
            } else {

                String url = Constants.NONGZI_INSERTNONGYAOINFO_URL;
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("pesticideName", pesticide_name)
                        .addParams("pesticideInstructions", pesticide_instruction)
                        .addParams("pesticideNote", pesticide_note)
                        .addParams("pesticideVirulence", pesticide_virulence)
                        .addParams("pesticideComposition", pesticide_composition)
                        .addParams("pesticideDosage", pesticide_dosage)
                        .addParams("pesticidePredisease", pesticide_predisease)
                        .addParams("pesticideContent", pesticide_content)
                        .addParams("pesticidePrice", pesticide_price)
                        .addParams("pesticideAddress", pesticide_adress)
                        .addParams("pesticideManufacturer", pesticide_manufacturer)
                        .addParams("pesticidePhone", pesticide_phone)
                        .addParams("pesticideLicensenumber", pesticide_licensenumber)
                        .addParams("fertilizerShelflife", pesticide_type)
                        .addParams("pesticideModeratecrop", pesticide_moderatecrop)
                        .addParams("pesticideProductiondate", pesticide_productdata)
                        .addParams("pesticideShelflife", pesticide_shelflife)
                        .addParams("recommendData", recommen_date)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                                Toast.makeText(PesticideAddActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "注册成功后的response==" + response);
                                Toast.makeText(PesticideAddActivity.this, "成功发布农药的信息", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        productdate= getDateToString(millseconds);
        etPesticideProductdata.setText(productdate);
    }
    private String getDateToString(long millseconds) {
        Date date = new Date(millseconds);
        return sf.format(date);
    }
}
