package com.bigdata.agricultureplatform.specialist.activity.nongzi.threeactivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MachineAddActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_addagrimachine_back)
    ImageButton ibAddagrimachineBack;
    @Bind(R.id.et_agrimachine_name)
    EditText etAgrimachineName;
    @Bind(R.id.et_agrimachine_introduce)
    EditText etAgrimachineIntroduce;
    @Bind(R.id.et_agrimachine_manufacturer)
    EditText etAgrimachineManufacturer;
    @Bind(R.id.et_agrimachine_phone)
    EditText etAgrimachinePhone;
    @Bind(R.id.et_agrimachine_price)
    EditText etAgrimachinePrice;
    @Bind(R.id.et_agrimachine_adress)
    EditText etAgrimachineAdress;
    @Bind(R.id.et_agrimachine_type)
    EditText etAgrimachineType;
    @Bind(R.id.et_agrimachine_moderatecrop)
    EditText etAgrimachineModeratecrop;
    @Bind(R.id.b_agrimachine_submit)
    Button bAgrimachineSubmit;

    //点击选择日期
    //提取本地值
    private String machineName;
    private String machineInstruction;
    private String machineManufacturer;
    private String macineprice;
    private String macineadress;
    private String machinePhone;
    private String macinetype;
    private String machinemoderatecrop;
    private String recommendate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        ButterKnife.bind(this);
        //获取当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        recommendate=sdf.format(System.currentTimeMillis());
        Toast.makeText(this,recommendate,Toast.LENGTH_LONG).show();

        ibAddagrimachineBack.setOnClickListener(this);
        bAgrimachineSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //返回按钮
        if (view == ibAddagrimachineBack) {
            finish();
        } else if (view == bAgrimachineSubmit) {

            machineName = etAgrimachineName.getText().toString().trim();
            machineInstruction = etAgrimachineIntroduce.getText().toString().trim();
            machineManufacturer = etAgrimachineManufacturer.getText().toString().trim();
            macineprice = etAgrimachinePrice.getText().toString().trim();
            macineadress = etAgrimachineAdress.getText().toString().trim();
            machinePhone = etAgrimachinePhone.getText().toString().trim();
            macinetype = etAgrimachineType.getText().toString().trim();
            machinemoderatecrop = etAgrimachineModeratecrop .getText().toString().trim();

            if (TextUtils.isEmpty(machineName)
                    || TextUtils.isEmpty(machineInstruction)
                    || TextUtils.isEmpty(machineManufacturer)
                    || TextUtils.isEmpty(macineprice)
                    || TextUtils.isEmpty(macineadress)
                    || TextUtils.isEmpty(machinePhone)
                    || TextUtils.isEmpty(macinetype)
                    || TextUtils.isEmpty(machinemoderatecrop)
                    || TextUtils.isEmpty(recommendate)) {
                Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
            } else {
                String url = Constants.NONGZI_INSERTNONGJIINFO_URL;
                OkHttpUtils
                        .post()
                        .url(url)
                        //先干掉

                        .addParams("machineName", machineName)
                        .addParams("machineFunction", machineInstruction)
                        .addParams("machineManufacturer", machineManufacturer)
                        .addParams("machinePrice", macineprice)
                        .addParams("machineAddress", macineadress)
                        .addParams("machinePhone", machinePhone)
                        .addParams("machineType", macinetype)
                        .addParams("machineModeratecrop", machinemoderatecrop)
                        .addParams("recommendData", recommendate)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                                Toast.makeText(MachineAddActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "注册成功后的response==" + response);
                                Toast.makeText(MachineAddActivity.this, "成功发布农机的信息", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }
    }



}