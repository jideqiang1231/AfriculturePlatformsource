package com.bigdata.agricultureplatform.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.app.bean.LoginBean;
import com.bigdata.agricultureplatform.app.bean.SpecialistloginActivity;
import com.bigdata.agricultureplatform.home.activity.RegisterActivity;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends Activity implements View.OnClickListener {


    @Bind(R.id.ib_login_visible)
    ImageButton ibLoginVisible;
    @Bind(R.id.btn_login)
    Button btnLogin;
    //注册入口
    @Bind(R.id.tv_login_register)
    TextView tvLoginRegister;
    @Bind(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @Bind(R.id.tv_specialist_login)
    TextView tvSpecialistLogin;
    private String user_name;
    private String user_pass;
    @Bind(R.id.et_login_name)
    EditText etLoginName;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    private LoginBean loginBean;
    private LoginBean.LoginresultBean loginReslutBean;
//    private LoginfailedBean loginfailedBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tvLoginRegister.setOnClickListener(this);
        tvSpecialistLogin.setOnClickListener(this);
    }

//butterknife代码测试click方法，剩余的click在最底下
    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        //先不跳转，请求数据验证成功之后跳转,值一定要对
        user_name = etLoginName.getText().toString().trim();
        user_pass = etLoginPwd.getText().toString().trim();

        Log.e(TAG, user_name + user_pass);
        if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_pass)) {
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(this, user_name+user_pass,Toast.LENGTH_LONG).show();
            //初始化数据，点击登录获取后台数据
            initLoginData();
        }
//        Log.e(TAG,"aaaaa"+loginReslutBean.getUserName());
//        if (loginReslutBean!= null) {
//            Intent intent = new Intent();
//            intent.setClass(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(this, "用户名密码错误", Toast.LENGTH_LONG).show();
//        }
        /////////////////////////////////////////////////////////
        //当解决了点击两次才能登录问题时候，放开，这里就完美了
//        else if (loginfailedBean!=null){
//            Toast.makeText(this, "用户名密码错误", Toast.LENGTH_LONG).show();
//        }
    }

    private void initLoginData() {
        //获取数据
        getLoginDataFormat();
    }

    private void getLoginDataFormat() {
        //这里是Constans里边的咱的路径的url
        String url = Constants.LOGIN1_URL;
        OkHttpUtils
                .post()
                .url(url)
                //先干掉
                .addParams("userName", user_name)
                .addParams("userPass", user_pass)
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
                        Log.e(TAG, "登录数据请求数据成功==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        //抽出出来一个方法，传入response
                        loginprocessData(response);
                        if (loginBean.getMsg().equals("登录失败")) {
                            Toast.makeText(LoginActivity.this, "用户名密码错误", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });

    }

    private void loginprocessData(String json) {
        Log.e(TAG, "aslkdjf;aldkjf;lasjdfl;askdjfl;askdjfkl");
        loginBean = JSON.parseObject(json, LoginBean.class);
        //咱只要咱的admin这个人的信息，如果想要code或者想要msg，来这里拿就行，重新定义变量接受
        //获取后端返回的有用信息
        loginReslutBean = loginBean.getLoginresult();
        Log.e(TAG, loginBean.getMsg());
    }

    @Override
    public void onClick(View view) {
        if (view == tvLoginRegister) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }else if (view ==tvSpecialistLogin){
            Intent intent = new Intent(this, SpecialistloginActivity.class);
            startActivity(intent);
        }
    }

}
