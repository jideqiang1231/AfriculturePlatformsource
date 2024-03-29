package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.home.SpecialistActivity;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistloginBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistloginActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_specialist_back)
    ImageButton ibSpecialistBack;
    @Bind(R.id.et_specialist_loginname)
    EditText etSpecialistLoginname;
    @Bind(R.id.et_specialist_loginpass)
    EditText etSpecialistLoginpass;
    @Bind(R.id.btn_specialistlogin)
    Button btnSpecialistlogin;
    private String specialist_pass;
    private String specialist_name;
    //把bean对象声明过来才可以用
    private SpecialistloginBean specialistloginBean;
    private SpecialistloginBean.SpecialistloginresultBean specialistloginresultBean;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistlogin);
        ButterKnife.bind(this);
/////////////////////////////////////////////////////////////////////////
        //1.得到sp对象,用于存储登录状态
        //specialistinfo:存在底层的文件名，读取要用到
        sp = getSharedPreferences("specialistinfo", Context.MODE_PRIVATE);
        //5.保存用户登录信息///////////////////////////////////
        String specname=sp.getString("specname",null);
        String specpass=sp.getString("specpass",null);
        String specid=sp.getString("specid",null);
        if (specname==null||specpass==null|specid==null){
            Toast.makeText(this,"首次登录，请输入用户信息或者注册",Toast.LENGTH_SHORT).show();
        }else{
            etSpecialistLoginname.setText(specname);
            etSpecialistLoginpass.setText(specpass);
        }
        /////////////////////////////////////////////////////////////////////////
        btnSpecialistlogin.setOnClickListener(this);
        ibSpecialistBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSpecialistlogin) {
            //接收输入信息
            specialist_name = etSpecialistLoginname.getText().toString().trim();
            specialist_pass = etSpecialistLoginpass.getText().toString().trim();
            //先判断信息，不可以为空，不为空才加载数据请求
            if (TextUtils.isEmpty(specialist_name) || TextUtils.isEmpty(specialist_pass)) {
                Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_LONG).show();
            } else {
                //初始化数据，点击登录获取后台数据
                initLoginData();

            }
        }else if(view==ibSpecialistBack){
            finish();
        }
    }

    private void initLoginData() {
        //获取数据
        getLoginDataFormat();
    }

    private void getLoginDataFormat() {
        String url = Constants.SPECIALISTLOGIN_URL;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("specialistName", specialist_name)
                .addParams("specialistPass", specialist_pass)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        loginprocessData(response);
                        if (specialistloginBean.getMsg().equals("登录失败")) {
                            Toast.makeText(getBaseContext(), "用户名密码错误", Toast.LENGTH_LONG).show();
                        } else {
                            ////////////////////////////////////////////////////////////////////////
                                //把请求来的登录信息放入到sheraperfernce中保存专家状态！
                                //得到edtior,用于保存用户的id,name,pass这里多了一步获取这个Editor的前缀SharePreferences
                                SharedPreferences.Editor editor = sp.edit();
                                //得到输入用户名和密码的key和value
                                String specialistnamevalue = specialistloginresultBean.getSpecialistName();
                                String specialistpassvalue=specialistloginresultBean.getSpecialistPass();
                                Integer specialistidvalue=specialistloginresultBean.getSpecialistId();
                                //此处用到的apply与commit有所区别，注意对照网上
                                editor.putString("specname", specialistnamevalue).apply();
                                editor.putString("specpass", specialistpassvalue).apply();
                                editor.putString("specid", String.valueOf(specialistidvalue)).apply();
                                //提示
                                Log.e(TAG,"专家基本状态id,name，pass保存成功");
                              ////////////////////////////////////////////////////////////////////////////


                            Intent intent = new Intent();
                            intent.setClass(SpecialistloginActivity.this, SpecialistActivity.class);
                            //注意putExtra和putExtras方法的区别.
                            intent.putExtra("专家姓名", specialistloginresultBean.getSpecialistName());
                            intent.putExtra("专家类型", specialistloginresultBean.getSpecialistType());
                            //注意类型，传过去的是int类型id，接受一定注意
                            intent.putExtra("专家id", specialistloginresultBean.getSpecialistId());
                            startActivity(intent);
                            finish();
                        }
                    }

                });
    }

    private void loginprocessData(String json) {
        specialistloginBean = JSON.parseObject(json, SpecialistloginBean.class);
        specialistloginresultBean = specialistloginBean.getSpecialistloginresult();
        Log.e(TAG, specialistloginBean.getMsg());
    }
}
