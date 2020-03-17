package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.app.LoginActivity;
import com.bigdata.agricultureplatform.home.bean.RegisterBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegisterActivity extends Activity implements View.OnClickListener {
    //作物一spinner
    @Bind(R.id.crops1)
    Spinner crops1;
    @Bind(R.id.cropstype1)
    Spinner cropstype1;
    //作物二spinner
    @Bind(R.id.crops2)
    Spinner crops2;
    @Bind(R.id.cropstype2)
    Spinner cropstype2;
    @Bind(R.id.ib_register_back)
    ImageButton ibRegisterBack;
    @Bind(R.id.b_register)
    Button bRegister;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pass)
    EditText etRegisterPass;
    @Bind(R.id.et_register_passagain)
    EditText etRegisterPassagain;
    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.et_register_acres)
    EditText etRegisterAcres;
    @Bind(R.id.et_cropacres1)
    EditText etCropacres1;
    @Bind(R.id.et_cropacres2)
    EditText etCropacres2;


    private String registername;
    private String registerpass1;
    private String registerpass2;
    private String registerphone;

    //实现亩数的自动获取
//    private String sum_acres;
//    private String cropsacres_1;
//    private String cropsacres_2;

    //返回的registerbean里面的数据
    private RegisterBean registerBean;
    private String registersuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //设置监听事件
        ibRegisterBack.setOnClickListener(this);
        bRegister.setOnClickListener(this);

        //亩数的监听事件
//        tvCropacres.setOnClickListener(this);

        //作物对应类型监听事件
        //将可选内容与ArrayAdapter的连接(从资源数组文件中获取数据)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.crops, android.R.layout.simple_spinner_item);
        //new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, provinces);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将数据绑定到Spinner视图上
        crops1.setAdapter(adapter);
        crops2.setAdapter(adapter);
        //添加条目被选中监听器,即联机互动，作物与类型
        crops1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //parent既是province对象
                Spinner spinner = (Spinner) parent;
                String pro = (String) spinner.getItemAtPosition(position);

                //(处理省的市的显示)
                //将默认值与ArrayAdapter连接(从资源数组文件中获取数据)
                ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
                        (RegisterActivity.this, R.array.typedefault, android.R.layout.simple_spinner_item);
                //new  ArrayAdapter<CharSequence>
                //           (MainActivity.this,android.R.layout.simple_spinner_item, cities);
                //获取所在省含有哪些市(从资源数组文件中获取数据)
                if (pro.equals("水稻")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.shuidaotype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("小麦")) {

                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.xiaomaitype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("玉米")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.yumitype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("大豆")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.dadoutype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("葡萄")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.putaotype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("枸杞")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.gouqitype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("向日葵花")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.xiangrikuitype, android.R.layout.simple_spinner_item);
                }
                //绑定数据到Spinner(City)上
                cropstype1.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        crops2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //parent既是province对象
                Spinner spinner = (Spinner) parent;
                String pro = (String) spinner.getItemAtPosition(position);

                //(处理省的市的显示)
                //将默认值与ArrayAdapter连接(从资源数组文件中获取数据)
                ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
                        (RegisterActivity.this, R.array.typedefault, android.R.layout.simple_spinner_item);
                //new  ArrayAdapter<CharSequence>
                //           (MainActivity.this,android.R.layout.simple_spinner_item, cities);
                //获取所在省含有哪些市(从资源数组文件中获取数据)
                if (pro.equals("水稻")) {

                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.shuidaotype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("小麦")) {

                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.xiaomaitype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("玉米")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.yumitype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("大豆")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.dadoutype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("葡萄")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.putaotype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("枸杞")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.gouqitype, android.R.layout.simple_spinner_item);
                } else if (pro.equals("向日葵")) {
                    cityAdapter = ArrayAdapter.createFromResource
                            (RegisterActivity.this, R.array.xiangrikuitype, android.R.layout.simple_spinner_item);
                }
                //绑定数据到Spinner(City)上
                cropstype2.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == ibRegisterBack) {
            finish();
        } else if (view == bRegister) {
            registername = etRegisterName.getText().toString().trim();
            registerpass1 = etRegisterPass.getText().toString().trim();
            registerpass2 = etRegisterPassagain.getText().toString().trim();
            registerphone = etRegisterPhone.getText().toString().trim();
//            Log.e(TAG, "?????");
            if (TextUtils.isEmpty(registername) || TextUtils.isEmpty(registerpass1)
                    || TextUtils.isEmpty(registerpass2) || TextUtils.isEmpty(registerphone)) {
                Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_LONG).show();
            } else if (!(registerpass1.equals(registerpass2))) {
                Toast.makeText(this, "两次密码输入不一致，请重新输入密码", Toast.LENGTH_LONG).show();
            }
            initRegisterdata();


//                 Log.e(TAG,"aaaaa"+registersuccess);
//                if(registersuccess.equals("用户已经存在")){
//                    Toast.makeText(this, "该手机号已经注册", Toast.LENGTH_LONG).show();
//                }else

//                    }
//                       if (registersuccess.equals("用户已经存在")){
//                            Toast.makeText(this, "用户已经存在", Toast.LENGTH_LONG).show();
//                            Log.e(TAG,registerBean.getRegistersuccess());
//                        }
        }
    }

    //        else if (view == tvCropacres) {
//            sum_acres = etRegisterAcres.getText().toString().trim();
//            cropsacres_1 = etCropacres.getText().toString().trim();
//            if (!sum_acres.equals(null) && !cropsacres_1.equals(null)) {
//                Log.e(TAG, sum_acres + cropsacres_1);
////                tvCropacres.setText(sum_acres);
//                tvCropacres.setText(String.valueOf(Double.parseDouble(sum_acres) - Double.parseDouble(cropsacres_1)));
//            } else {
//                tvCropacres.setText("0");
//            }
//        }
//    }
    //    }
    private void initRegisterdata() {
        getRegisterDataFormat();
    }

    private void getRegisterDataFormat() {
        String url = Constants.REGISTER_URL;
        OkHttpUtils
                .post()
                .url(url)
                //先干掉
                .addParams("userName", registername)
                .addParams("userPass", registerpass1)
                .addParams("userPhone", registerphone)
                .build()
                .execute(new StringCallback() {
                    /*
                     * 当请求失败的时候回调，打印
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
//                        processData(e.getMessage());
                    }

                    /*当联网成功回调，这里的
                    @param response表示成功请求的数据，
                    @param id 区分http100和https101
                    */
                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "注册成功后的response==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        //抽出出来一个方法，传入response
                        registerprocessData(response);
                        if (registersuccess.equals("注册成功")) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_LONG).show();
//                            Log.e(TAG, registerBean.getRegistersuccess());
                            finish();
                        }
                        if (registersuccess.equals("用户已经存在")) {
                            Toast.makeText(RegisterActivity.this, "用户已经存在，请重新输入手机号", Toast.LENGTH_LONG).show();
                        }

                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    private void registerprocessData(String json) {
        RegisterBean registerBean = JSON.parseObject(json, RegisterBean.class);
        registersuccess = registerBean.getRegistersuccess();
        Log.e(TAG, "注册之后==" + registersuccess);
    }


}
