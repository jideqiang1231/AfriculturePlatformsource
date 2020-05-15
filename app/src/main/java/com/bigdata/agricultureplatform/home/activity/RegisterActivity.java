package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.app.LoginActivity;
import com.bigdata.agricultureplatform.home.adapter.UseregisterspinnercropnameAdapter;
import com.bigdata.agricultureplatform.home.adapter.UserregistercropspinnerAdapter;
import com.bigdata.agricultureplatform.home.bean.RegisterBean;
import com.bigdata.agricultureplatform.home.bean.seednameBean;
import com.bigdata.agricultureplatform.home.bean.seedtypeBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegisterActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

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
    @Bind(R.id.rb_1)
    RadioButton rb1;
    @Bind(R.id.rb_2)
    RadioButton rb2;
    @Bind(R.id.rb_3)
    RadioButton rb3;
    @Bind(R.id.rb_4)
    RadioButton rb4;
    @Bind(R.id.rb_5)
    RadioButton rb5;
    @Bind(R.id.fivechoice)
    RadioGroup fivechoice;
    @Bind(R.id.s_croptype1)
    Spinner sCroptype1;
    @Bind(R.id.s_cropname1)
    Spinner sCropname1;
    @Bind(R.id.et_croparea1)
    EditText etCroparea1;
    @Bind(R.id.l_1)
    LinearLayout l1;
    @Bind(R.id.s_croptype2)
    Spinner sCroptype2;
    @Bind(R.id.s_cropname2)
    Spinner sCropname2;
    @Bind(R.id.et_croparea2)
    EditText etCroparea2;
    @Bind(R.id.l_2)
    LinearLayout l2;
    @Bind(R.id.s_croptype3)
    Spinner sCroptype3;
    @Bind(R.id.s_cropname3)
    Spinner sCropname3;
    @Bind(R.id.et_croparea3)
    EditText etCroparea3;
    @Bind(R.id.l_3)
    LinearLayout l3;
    @Bind(R.id.s_croptype4)
    Spinner sCroptype4;
    @Bind(R.id.s_cropname4)
    Spinner sCropname4;
    @Bind(R.id.et_croparea4)
    EditText etCroparea4;
    @Bind(R.id.l_4)
    LinearLayout l4;
    @Bind(R.id.s_croptype5)
    Spinner sCroptype5;
    @Bind(R.id.s_cropname5)
    Spinner sCropname5;
    @Bind(R.id.et_croparea5)
    EditText etCroparea5;
    @Bind(R.id.l_5)
    LinearLayout l5;


    @Bind(R.id.et_register_identity)
    EditText etRegisterIdentity;
    @Bind(R.id.et_register_address)
    EditText etRegisterAddress;
    private String registername;
    private String registerpass1;
    private String registerpass2;
    private String registerphone;
    // private RegisterBean registerBean;
    private String registersuccess;
    private seedtypeBean seedtypeBean;
    private seednameBean seednameBean;
    //初始化两个adapter
    private UserregistercropspinnerAdapter adapter;
    private UseregisterspinnercropnameAdapter adapter1;
    //创建这两个参数用于接受type,name的值传到后端
    private String Register_spinner_seedtype1;
    private String Register_spinner_seedtype2;
    private String Register_spinner_seedtype3;
    private String Register_spinner_seedtype4;
    private String Register_spinner_seedtype5;
    private String Register_spinner_seedname1;
    private String Register_spinner_seedname2;
    private String Register_spinner_seedname3;
    private String Register_spinner_seedname4;
    private String Register_spinner_seedname5;
    //虽然灰色，但是有用(只有被当作变量时颜色才变深)
    private String userSumAcres;
    private String userIdentityCard;
    private String userAdress;
    //acres 和area一个意思，打错单词（亩数）
    private String crop1area;
    private String crop2area;
    private String crop3area;
    private String crop4area;
    private String crop5area;
    private List<com.bigdata.agricultureplatform.home.bean.seedtypeBean.SeedtyperesultBean> seedtypelist;
    private List<com.bigdata.agricultureplatform.home.bean.seednameBean.SeednameresultBean> seednamelist;
    private RegisterBean registerBean;
    private String finalsumcroptype;
    private String finalsumcropname;
    private String finalsumcroparea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


        //为下拉框设置数据，请求数据：
        getspinnerCropsData();
        //初始化单选框与下拉框布局
        fivechoice.setOnCheckedChangeListener(this);
        //此时第一个已经选择了数据，根据此数据继续查询种子名称去数据库Register_spinner_seedtype
        //点击获取布局界面的数据：

        //设置监听事件
        ibRegisterBack.setOnClickListener(this);
        bRegister.setOnClickListener(this);
        //亩数的监听事件
        //  tvCropacres.setOnClickListener(this);

        //作物对应类型监听事件
        //将可选内容与ArrayAdapter的连接(从资源数组文件中获取数据)
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.crops, android.R.layout.simple_spinner_item);
        //new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, provinces);
        //设置下拉列表的风格
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将数据绑定到Spinner视图上
        // crops1.setAdapter(adapter);
        //crops2.setAdapter(adapter);
        //添加条目被选中监听器,即联机互动，作物与类型
//        crops1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //parent既是province对象
//                Spinner spinner = (Spinner) parent;
//                String pro = (String) spinner.getItemAtPosition(position);
//
//                //(处理省的市的显示)
//                //将默认值与ArrayAdapter连接(从资源数组文件中获取数据)
//                ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
//                        (RegisterActivity.this, R.array.typedefault, android.R.layout.simple_spinner_item);
//                //new  ArrayAdapter<CharSequence>
//                //           (MainActivity.this,android.R.layout.simple_spinner_item, cities);
//                //获取所在省含有哪些市(从资源数组文件中获取数据)
//                if (pro.equals("水稻")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.shuidaotype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("小麦")) {
//
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.xiaomaitype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("玉米")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.yumitype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("大豆")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.dadoutype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("葡萄")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.putaotype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("枸杞")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.gouqitype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("向日葵花")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.xiangrikuitype, android.R.layout.simple_spinner_item);
//                }
//                //绑定数据到Spinner(City)上
//                cropstype1.setAdapter(cityAdapter);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        crops2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //parent既是province对象
//                Spinner spinner = (Spinner) parent;
//                String pro = (String) spinner.getItemAtPosition(position);
//
//                //(处理省的市的显示)
//                //将默认值与ArrayAdapter连接(从资源数组文件中获取数据)
//                ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource
//                        (RegisterActivity.this, R.array.typedefault, android.R.layout.simple_spinner_item);
//                //new  ArrayAdapter<CharSequence>
//                //           (MainActivity.this,android.R.layout.simple_spinner_item, cities);
//                //获取所在省含有哪些市(从资源数组文件中获取数据)
//                if (pro.equals("水稻")) {
//
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.shuidaotype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("小麦")) {
//
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.xiaomaitype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("玉米")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.yumitype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("大豆")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.dadoutype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("葡萄")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.putaotype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("枸杞")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.gouqitype, android.R.layout.simple_spinner_item);
//                } else if (pro.equals("向日葵")) {
//                    cityAdapter = ArrayAdapter.createFromResource
//                            (RegisterActivity.this, R.array.xiangrikuitype, android.R.layout.simple_spinner_item);
//                }
//                //绑定数据到Spinner(City)上
//                cropstype2.setAdapter(cityAdapter);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void getspinnerCropsData() {
        String url = Constants.GETSEEDTYPE_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "专家请求种子发布历史数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "专家获取发布过得历史数据成功==" + response);
                        userRegisterSpinnerCropdataprocessData(response);
                        adapter = new UserregistercropspinnerAdapter(RegisterActivity.this, seedtypelist);
                        //以下公用一个adapter for user register spinner croptype data and view!
                        sCroptype1.setAdapter(adapter);
                        sCroptype1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //用自己的方法获取这个adpterview中的值然后赋值的过程
                                //数据必须用bean对象接受
                                seedtypeBean.SeedtyperesultBean seedtyperesultBean = (seedtypeBean.SeedtyperesultBean) adapterView.getSelectedItem();
                                Register_spinner_seedtype1 = seedtyperesultBean.getSeedType();
                                Log.e(TAG, "看看这次请求怎么样" + seedtyperesultBean.getSeedType());
                                //选择完成之后为种子名称获取数据
                                userseedtypetofindnameForseednameSpinner(Register_spinner_seedtype1);
                            }

                            private void userseedtypetofindnameForseednameSpinner(String register_spinner_seedtype1) {
                                String url = Constants.GETSEEDNAME_URL;
                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .addParams("seedType", register_spinner_seedtype1)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片错误==" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片成功==" + response);
                                                seednamebyseedtypeprocessData(response);
                                                //注意（以下两处）声明的adapter不能与上面的adapter重名
                                                adapter1 = new UseregisterspinnercropnameAdapter(RegisterActivity.this, seednamelist);
                                                //此处的sCroptpye其是对应就是cropsname
                                                sCropname1.setAdapter(adapter1);
                                                sCropname1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                        //用自己的方法获取这个adpterview中的值然后赋值的过程
                                                        //数据必须用bean对象接受
                                                        seednameBean.SeednameresultBean seednameresultBean = (seednameBean.SeednameresultBean) adapterView.getSelectedItem();
                                                        Register_spinner_seedname1 = seednameresultBean.getSeedName();
                                                        Log.e(TAG, "看看这次请求怎么样" + seednameresultBean.getSeedName());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                        adapterView.getSelectedItem();
                                                    }
                                                });
                                            }

                                            // 根据每个类型请求的种子名称数据的解析
                                            private void seednamebyseedtypeprocessData(String response) {
                                                seednameBean = JSON.parseObject(response, seednameBean.class);
                                                //表面bean里面的listbean
                                                seednamelist = seednameBean.getSeednameresult();
                                                //打印一下
                                                Log.e(TAG, "数组显示用.get0" + seednamelist.get(0).getSeedName());
                                            }
                                        });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.getSelectedItem();
                            }
                        });
                        //为第二行也是这么设计请求过的值
                        sCroptype2.setAdapter(adapter);
                        sCroptype2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //用自己的方法获取这个adpterview中的值然后赋值的过程
                                //数据必须用bean对象接受
                                seedtypeBean.SeedtyperesultBean seedtyperesultBean = (seedtypeBean.SeedtyperesultBean) adapterView.getSelectedItem();
                                Register_spinner_seedtype2 = seedtyperesultBean.getSeedType();
                                Log.e(TAG, "看看这次请求怎么样" + seedtyperesultBean.getSeedType());
                                userseedtypetofindnameForseednameSpinner2(Register_spinner_seedtype2);
                            }

                            private void userseedtypetofindnameForseednameSpinner2(String register_spinner_seedtype2) {
                                String url = Constants.GETSEEDNAME_URL;

                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .addParams("seedType", register_spinner_seedtype2)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片错误==" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片成功==" + response);
                                                seednamebyseedtypeprocessData(response);
                                                //注意（以下两处）声明的adapter不能与上面的adapter重名
                                                adapter1 = new UseregisterspinnercropnameAdapter(RegisterActivity.this, seednamelist);
                                                //此处的sCroptpye其是对应就是cropsname
                                                sCropname2.setAdapter(adapter1);
                                                sCropname2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                        //用自己的方法获取这个adpterview中的值然后赋值的过程
                                                        //数据必须用bean对象接受
                                                        seednameBean.SeednameresultBean seednameresultBean = (seednameBean.SeednameresultBean) adapterView.getSelectedItem();
                                                        Register_spinner_seedname2 = seednameresultBean.getSeedName();
                                                        Log.e(TAG, "看看这次请求怎么样" + seednameresultBean.getSeedName());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                        adapterView.getSelectedItem();
                                                    }
                                                });
                                            }

                                            // 根据每个类型请求的种子名称数据的解析
                                            private void seednamebyseedtypeprocessData(String response) {
                                                seednameBean = JSON.parseObject(response, seednameBean.class);
                                                //表面bean里面的listbean
                                                seednamelist = seednameBean.getSeednameresult();
                                                //打印一下
                                                Log.e(TAG, "数组显示用.get0" + seednamelist.get(0).getSeedName());
                                            }
                                        });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.getSelectedItem();
                            }
                        });
                        //同理，第三行的种类
                        sCroptype3.setAdapter(adapter);
                        sCroptype3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //用自己的方法获取这个adpterview中的值然后赋值的过程
                                //数据必须用bean对象接受
                                seedtypeBean.SeedtyperesultBean seedtyperesultBean = (seedtypeBean.SeedtyperesultBean) adapterView.getSelectedItem();
                                Register_spinner_seedtype3 = seedtyperesultBean.getSeedType();
                                Log.e(TAG, "看看这次请求怎么样" + seedtyperesultBean.getSeedType());
                                userseedtypetofindnameForseednameSpinner3(Register_spinner_seedtype3);
                            }

                            private void userseedtypetofindnameForseednameSpinner3(String register_spinner_seedtype3) {
                                String url = Constants.GETSEEDNAME_URL;

                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .addParams("seedType", register_spinner_seedtype3)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片错误==" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片成功==" + response);
                                                seednamebyseedtypeprocessData(response);
                                                //注意（以下两处）声明的adapter不能与上面的adapter重名
                                                adapter1 = new UseregisterspinnercropnameAdapter(RegisterActivity.this, seednamelist);
                                                //此处的sCroptpye其是对应就是cropsname
                                                sCropname3.setAdapter(adapter1);
                                                sCropname3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                        //用自己的方法获取这个adpterview中的值然后赋值的过程
                                                        //数据必须用bean对象接受
                                                        seednameBean.SeednameresultBean seednameresultBean = (seednameBean.SeednameresultBean) adapterView.getSelectedItem();
                                                        Register_spinner_seedname3 = seednameresultBean.getSeedName();
                                                        Log.e(TAG, "看看这次请求怎么样" + seednameresultBean.getSeedName());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                        adapterView.getSelectedItem();
                                                    }
                                                });
                                            }

                                            // 根据每个类型请求的种子名称数据的解析
                                            private void seednamebyseedtypeprocessData(String response) {
                                                seednameBean = JSON.parseObject(response, seednameBean.class);
                                                //表面bean里面的listbean
                                                seednamelist = seednameBean.getSeednameresult();
                                                //打印一下
                                                Log.e(TAG, "数组显示用.get0" + seednamelist.get(0).getSeedName());
                                            }
                                        });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.getSelectedItem();
                            }
                        });
                        //第四行种类
                        sCroptype4.setAdapter(adapter);
                        sCroptype4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //用自己的方法获取这个adpterview中的值然后赋值的过程
                                //数据必须用bean对象接受
                                seedtypeBean.SeedtyperesultBean seedtyperesultBean = (seedtypeBean.SeedtyperesultBean) adapterView.getSelectedItem();
                                Register_spinner_seedtype4 = seedtyperesultBean.getSeedType();
                                Log.e(TAG, "看看这次请求怎么样" + seedtyperesultBean.getSeedType());
                                userseedtypetofindnameForseednameSpinner4(Register_spinner_seedtype4);
                            }

                            private void userseedtypetofindnameForseednameSpinner4(String register_spinner_seedtype4) {
                                String url = Constants.GETSEEDNAME_URL;

                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .addParams("seedType", register_spinner_seedtype4)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片错误==" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片成功==" + response);
                                                seednamebyseedtypeprocessData(response);
                                                //注意（以下两处）声明的adapter不能与上面的adapter重名
                                                adapter1 = new UseregisterspinnercropnameAdapter(RegisterActivity.this, seednamelist);
                                                //此处的sCroptpye其是对应就是cropsname
                                                sCropname4.setAdapter(adapter1);
                                                sCropname4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                        //用自己的方法获取这个adpterview中的值然后赋值的过程
                                                        //数据必须用bean对象接受
                                                        seednameBean.SeednameresultBean seednameresultBean = (seednameBean.SeednameresultBean) adapterView.getSelectedItem();
                                                        Register_spinner_seedname4 = seednameresultBean.getSeedName();
                                                        Log.e(TAG, "看看这次请求怎么样" + seednameresultBean.getSeedName());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                        adapterView.getSelectedItem();
                                                    }
                                                });
                                            }

                                            // 根据每个类型请求的种子名称数据的解析
                                            private void seednamebyseedtypeprocessData(String response) {
                                                seednameBean = JSON.parseObject(response, seednameBean.class);
                                                //表面bean里面的listbean
                                                seednamelist = seednameBean.getSeednameresult();
                                                //打印一下
                                                Log.e(TAG, "数组显示用.get0" + seednamelist.get(0).getSeedName());
                                            }
                                        });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.getSelectedItem();
                            }
                        });
                        //第五行种类
                        sCroptype5.setAdapter(adapter);
                        sCroptype5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //用自己的方法获取这个adpterview中的值然后赋值的过程
                                //数据必须用bean对象接受
                                seedtypeBean.SeedtyperesultBean seedtyperesultBean = (seedtypeBean.SeedtyperesultBean) adapterView.getSelectedItem();
                                Register_spinner_seedtype5 = seedtyperesultBean.getSeedType();
                                Log.e(TAG, "看看这次请求怎么样" + seedtyperesultBean.getSeedType());
                                userseedtypetofindnameForseednameSpinner5(Register_spinner_seedtype5);
                            }

                            private void userseedtypetofindnameForseednameSpinner5(String register_spinner_seedtype5) {
                                String url = Constants.GETSEEDNAME_URL;

                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .addParams("seedType", register_spinner_seedtype5)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片错误==" + e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e(TAG, "根据种子类型请求种子图片成功==" + response);
                                                seednamebyseedtypeprocessData(response);
                                                //注意（以下两处）声明的adapter不能与上面的adapter重名
                                                adapter1 = new UseregisterspinnercropnameAdapter(RegisterActivity.this, seednamelist);
                                                //此处的sCroptpye其是对应就是cropsname
                                                sCropname5.setAdapter(adapter1);
                                                sCropname5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                        //用自己的方法获取这个adpterview中的值然后赋值的过程
                                                        //数据必须用bean对象接受
                                                        seednameBean.SeednameresultBean seednameresultBean = (seednameBean.SeednameresultBean) adapterView.getSelectedItem();
                                                        Register_spinner_seedname5 = seednameresultBean.getSeedName();
                                                        Log.e(TAG, "看看这次请求怎么样" + seednameresultBean.getSeedName());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                        adapterView.getSelectedItem();
                                                    }
                                                });
                                            }

                                            // 根据每个类型请求的种子名称数据的解析
                                            private void seednamebyseedtypeprocessData(String response) {
                                                seednameBean = JSON.parseObject(response, seednameBean.class);
                                                //表面bean里面的listbean
                                                seednamelist = seednameBean.getSeednameresult();
                                                //打印一下
                                                Log.e(TAG, "数组显示用.get0" + seednamelist.get(0).getSeedName());
                                            }
                                        });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.getSelectedItem();
                            }
                        });

                    }
                });
    }

    private void userRegisterSpinnerCropdataprocessData(String response) {
        seedtypeBean = JSON.parseObject(response, seedtypeBean.class);
        //表面bean里面的listbean
        seedtypelist = seedtypeBean.getSeedtyperesult();
        //打印一下
        Log.e(TAG, "数组显示用.get0" + seedtypelist.get(0).getSeedType());
    }


    @Override
    public void onClick(View view) {
        if (view == ibRegisterBack) {
            finish();
        } else if (view == bRegister) {
            //获取姓名,密码,手机号：
            registername = etRegisterName.getText().toString().trim();
            registerpass1 = etRegisterPass.getText().toString().trim();
            registerpass2 = etRegisterPassagain.getText().toString().trim();
            registerphone = etRegisterPhone.getText().toString().trim();
            //获取身份证号（可以为空）
            userIdentityCard = etRegisterIdentity.getText().toString();
            //总的亩数和地理位置
            userSumAcres = etRegisterAcres.getText().toString();
            userAdress=etRegisterAddress.getText().toString();
            //五个种植得种类的分亩数
            crop1area = etCroparea1.getText().toString();
            crop2area = etCroparea2.getText().toString();
            crop3area = etCroparea3.getText().toString();
            crop4area = etCroparea4.getText().toString();
            crop5area = etCroparea5.getText().toString();
            if (TextUtils.isEmpty(registername) || TextUtils.isEmpty(registerpass1)
                    || TextUtils.isEmpty(registerpass2) || TextUtils.isEmpty(registerphone)
                    || TextUtils.isEmpty(userIdentityCard) || TextUtils.isEmpty(userAdress)
                    || TextUtils.isEmpty(userSumAcres)
            ) {
                Toast.makeText(this, "请继续完善信息", Toast.LENGTH_LONG).show();
            } else if (!(registerpass1.equals(registerpass2))) {
                Toast.makeText(this, "两次密码输入不一致，请重新输入密码", Toast.LENGTH_LONG).show();
            }
            //抽取方法，字符串划分，并且显示结果，用于后端数据的传输
            splitData_getData();
            //像后端插入数据
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
            //点击获取所有得信息往后端传输
        }
    }

    //注册小逻辑
    private void initRegisterdata() {
        getRegisterDataFormat();
    }

    //注册获取数据
    private void getRegisterDataFormat() {
        String url = Constants.REGISTER_URL;
        OkHttpUtils
                .post()
                .url(url)
                //先干掉
                .addParams("userName", registername)
                .addParams("userPass", registerpass1)
                .addParams("userPhone", registerphone)

                .addParams("userCard", userIdentityCard)
                .addParams("userFieldadress", userAdress)
                .addParams("userFieldacres", userSumAcres)
                .addParams("userCropsname", finalsumcropname)
                .addParams("userCropstype", finalsumcroptype)
                .addParams("userCropsacres", finalsumcroparea)
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

    //注册处理数据
    private void registerprocessData(String json) {
        registerBean = JSON.parseObject(json, RegisterBean.class);
        registersuccess = registerBean.getRegistersuccess();
        Log.e(TAG, "注册之后==" + registersuccess);
    }

    //动态控制radiobutton的变化
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int position) {
        if (position == rb1.getId()) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);
            l3.setVisibility(View.INVISIBLE);
            l4.setVisibility(View.INVISIBLE);
            l5.setVisibility(View.INVISIBLE);
            Register_spinner_seedtype2 = null;
            Register_spinner_seedtype3 = null;
            Register_spinner_seedtype4 = null;
            Register_spinner_seedtype5 = null;
            Register_spinner_seedname2 = null;
            Register_spinner_seedname3 = null;
            Register_spinner_seedname4 = null;
            Register_spinner_seedname5 = null;
            crop2area = null;
            crop3area = null;
            crop4area = null;
            crop5area = null;

            //不能再此处接受值
            //---------------------------------------------------------------
            //上来点击一下它们出来了，就立马获取值，当然是空，因为还没有发生下拉框点击事件
            //------------------------------------------------------------------------

        } else {
            sCroptype1.setSelection(0);
            sCropname1.setSelection(0);
            etCroparea1.setText(null);
            //not need
//            tvSumcrop.setText(sCroptype1);
//            tvSumcroptype.setText(sCropname1);
//            tvSumcroparea.setText(crop1area);
        }
        if (position == rb2.getId()) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.INVISIBLE);
            l4.setVisibility(View.INVISIBLE);
            l5.setVisibility(View.INVISIBLE);
            Register_spinner_seedtype3 = null;
            Register_spinner_seedtype4 = null;
            Register_spinner_seedtype5 = null;
            Register_spinner_seedname3 = null;
            Register_spinner_seedname4 = null;
            Register_spinner_seedname5 = null;
            crop3area = null;
            crop4area = null;
            crop5area = null;
        } else {
            sCroptype1.setSelection(0);
            sCropname1.setSelection(0);
            etCroparea1.setText(null);
            sCroptype2.setSelection(0);
            sCropname2.setSelection(0);
            etCroparea2.setText(null);
        }
        if (position == rb3.getId()) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.INVISIBLE);
            l5.setVisibility(View.INVISIBLE);
            Register_spinner_seedtype4 = null;
            Register_spinner_seedtype5 = null;
            Register_spinner_seedname4 = null;
            Register_spinner_seedname5 = null;
            crop4area = null;
            crop5area = null;
        } else {
            sCroptype1.setSelection(0);
            sCropname1.setSelection(0);
            etCroparea1.setText(null);
            sCroptype2.setSelection(0);
            sCropname2.setSelection(0);
            etCroparea2.setText(null);
            sCroptype3.setSelection(0);
            sCropname3.setSelection(0);
            etCroparea3.setText(null);
        }
        if (position == rb4.getId()) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.VISIBLE);
            l5.setVisibility(View.INVISIBLE);
            Register_spinner_seedtype5 = null;
            Register_spinner_seedname5 = null;
            crop5area = null;
        } else {
            sCroptype1.setSelection(0);
            sCropname1.setSelection(0);
            etCroparea1.setText(null);
            sCroptype2.setSelection(0);
            sCropname2.setSelection(0);
            etCroparea2.setText(null);
            sCroptype3.setSelection(0);
            sCropname3.setSelection(0);
            etCroparea3.setText(null);
            sCroptype4.setSelection(0);
            sCropname4.setSelection(0);
            etCroparea4.setText(null);
        }
        if (position == rb5.getId()) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.VISIBLE);
            l5.setVisibility(View.VISIBLE);
        } else {
            sCroptype1.setSelection(0);
            sCropname1.setSelection(0);
            etCroparea1.setText(null);
            sCroptype2.setSelection(0);
            sCropname2.setSelection(0);
            etCroparea2.setText(null);
            sCroptype3.setSelection(0);
            sCropname3.setSelection(0);
            etCroparea3.setText(null);
            sCroptype4.setSelection(0);
            sCropname4.setSelection(0);
            etCroparea4.setText(null);
            sCroptype5.setSelection(0);
            sCropname5.setSelection(0);
            etCroparea5.setText(null);
        }
    }

    //抽取方法，字符串划分，并且显示结果，用于后端数据的传输
    private void splitData_getData() {
        //设置选中了啥,动态分割，因为原来的形式是：水稻，枸杞，null,null,null所以以“，null”分割
        String sumcroptype = Register_spinner_seedtype1 + "," + Register_spinner_seedtype2 + "," + Register_spinner_seedtype3 + "," + Register_spinner_seedtype4 + "," + Register_spinner_seedtype5;
        String[] a = sumcroptype.split(",null");
        finalsumcroptype = a[0];
        //finalsumcrop就是最终要插入到数据库的变量
        //tvSumcrop.setText(finalsumcroptype);
        //同上
        String sumcropname = Register_spinner_seedname1 + "," + Register_spinner_seedname2 + "," + Register_spinner_seedname3 + "," + Register_spinner_seedname4 + "," + Register_spinner_seedname5;
        String[] b = sumcropname.split(",null");
        finalsumcropname = b[0];
        //finalsumcrop就是最终要插入到数据库的变量
        //tvSumcroptype.setText(finalsumcropname);
        //同上（有所区别）
        //if语句是为了让亩数这块的“空”设置为null用于“，null”分割出字符串；
        if (crop1area.equals("")) {
            crop1area = null;
        }
        if (crop2area.equals("")) {
            crop2area = null;
        }
        if (crop3area.equals("")) {
            crop3area = null;
        }
        if (crop4area.equals("")) {
            crop4area = null;
        }
        if (crop5area.equals("")) {
            crop5area = null;
        }
        String sumcroparea = crop1area + "," + crop2area + "," + crop3area + "," + crop4area + "," + crop5area;
        String[] c = sumcroparea.split(",null");
        finalsumcroparea = c[0];
        //finalsumcrop就是最终要插入到数据库的变量
        //tvSumcroparea.setText(finalsumcroparea);
    }


}
