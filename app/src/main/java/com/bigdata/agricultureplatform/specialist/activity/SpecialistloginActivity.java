package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.bigdata.agricultureplatform.app.LoginActivity;
import com.bigdata.agricultureplatform.app.MainActivity;
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
    @Bind(R.id.ib_login_visible)
    ImageButton ibLoginVisible;
    @Bind(R.id.btn_specialistlogin)
    Button btnSpecialistlogin;
    private String specialist_pass;
    private String specialist_name;
    //把bean对象声明过来才可以用
    private SpecialistloginBean specialistloginBean;
    private SpecialistloginBean.SpecialistloginresultBean specialistloginresultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistlogin);
        ButterKnife.bind(this);
        ibLoginVisible.setOnClickListener(this);
        btnSpecialistlogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == ibLoginVisible) {
            finish();
        } else if (view == btnSpecialistlogin) {
            //接收输入信息
            specialist_name = etSpecialistLoginname.getText().toString().trim();
            specialist_pass = etSpecialistLoginpass.getText().toString().trim();
            //先判断信息，不可以为空，不为空才加载数据请求
            if (TextUtils.isEmpty(specialist_name) || TextUtils.isEmpty(specialist_pass)) {
                Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, user_name+user_pass,Toast.LENGTH_LONG).show();
                //初始化数据，点击登录获取后台数据
                initLoginData();

            }
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
                //先干掉
                .addParams("specialistName", specialist_name)
                .addParams("specialistPass", specialist_pass)
                .build()
                .execute(new StringCallback() {
                    /*
                     * 当请求失败的时候回调，打
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                    }

                    /*当联网成功回调，这里的
                    @param response表示成功请求的数据，
                    @param id 区分http100和https101
                    */
                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "专家登录数据请求数据成功==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        //抽出出来一个方法，传入response
                        loginprocessData(response);
                        if (specialistloginBean.getMsg().equals("登录失败")) {
                            Toast.makeText(getBaseContext(), "用户名密码错误", Toast.LENGTH_LONG).show();
                        } else {

                            Intent intent = new Intent();
                            intent.setClass(SpecialistloginActivity.this, SpecialistActivity.class);
//                             向着专家界面传值specialistacitvity
                            //注意putExtra和putExtras方法的区别.
                            intent.putExtra("专家姓名", specialistloginresultBean.getSpecialistName());
                            intent.putExtra("专家类型", specialistloginresultBean.getSpecialistType());
                            //注意类型，传过去的是int类型id，接受一定注意
                            intent.putExtra("专家id", specialistloginresultBean.getSpecialistId());
                            startActivity(intent);
                            finish();
                        }
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    private void loginprocessData(String json) {
        specialistloginBean = JSON.parseObject(json, SpecialistloginBean.class);
        specialistloginresultBean = specialistloginBean.getSpecialistloginresult();
        Log.e(TAG, specialistloginBean.getMsg());
    }
}
