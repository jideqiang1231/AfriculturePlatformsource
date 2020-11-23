package com.bigdata.agricultureplatform.specialist.activity.nongji.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.adapter.UseregisterspinnercropnameAdapter;
import com.bigdata.agricultureplatform.home.adapter.UserregistercropspinnerAdapter;
import com.bigdata.agricultureplatform.home.bean.seednameBean;
import com.bigdata.agricultureplatform.home.bean.seedtypeBean;
import com.bigdata.agricultureplatform.new_all_beans.recommend;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Fragment03 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Bind(R.id.s_growdata_choose)
    Spinner sGrowdataChoose;
    @Bind(R.id.s_seedtpye_choose)
    Spinner sSeedtpyeChoose;
    @Bind(R.id.s_seedname_choose)
    Spinner sSeednameChoose;
    @Bind(R.id.et_growtime_stage)
    EditText etGrowtimeStage;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_endtime)
    TextView tvEndtime;
    @Bind(R.id.et_disease_name)
    EditText etDiseaseName;
    @Bind(R.id.et_ingredient)
    EditText etIngredient;
    @Bind(R.id.et_notice)
    EditText etNotice;
    @Bind(R.id.et_detail)
    EditText etDetail;
    @Bind(R.id.l_fasten)
    LinearLayout lFasten;
    @Bind(R.id.pesticide_submit)
    Button pesticideSubmit;
    private seedtypeBean seedtypeBean;
    private seednameBean seednameBean;
    private List<com.bigdata.agricultureplatform.home.bean.seedtypeBean.SeedtyperesultBean> seedtypelist;
    private List<com.bigdata.agricultureplatform.home.bean.seednameBean.SeednameresultBean> seednamelist;
    private UseregisterspinnercropnameAdapter adapter1;
    private UserregistercropspinnerAdapter adapter;
    private String Register_spinner_seedtype1;
    private String Register_spinner_seedname1;
    private SharedPreferences sp;
    private String specid;
    private String sowmethod;


    private recommend nearlyinsert_allinfo;
    private recommend.OnerecommndresultBean nearlyinsert_include_recommend_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        //读取专家的缓存信息
        sp = getActivity().getSharedPreferences("specialistinfo", Context.MODE_PRIVATE);
        specid = sp.getString("specid", null);

        //为下拉框加载数据
        initseednametypedata();
        sGrowdataChoose.setOnItemSelectedListener(this);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过自定义控件AlertDialog实现
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.date_dialog, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //设置date布局
                builder.setView(view);
                builder.setTitle("选择发布日期");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth() + 1;
                        int dayOfMonth = datePicker.getDayOfMonth();
                        tvTime.setText(year + "-" + month + "-" + dayOfMonth);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
        tvEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过自定义控件AlertDialog实现
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.date_dialog, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //设置date布局
                builder.setView(view);
                builder.setTitle("选择日期");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth() + 1;
                        int dayOfMonth = datePicker.getDayOfMonth();
                        tvEndtime.setText(year + "-" + month + "-" + dayOfMonth);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
        pesticideSubmit.setOnClickListener(this);
    }

    //为下拉框加载数据
    private void initseednametypedata() {
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
                        adapter = new UserregistercropspinnerAdapter(getActivity(), seedtypelist);
                        //以下公用一个adapter for user register spinner croptype data and view!
                        sSeedtpyeChoose.setAdapter(adapter);
                        sSeedtpyeChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                                adapter1 = new UseregisterspinnercropnameAdapter(getActivity(), seednamelist);
                                                //此处的sCroptpye其是对应就是cropsname
                                                sSeednameChoose.setAdapter(adapter1);
                                                sSeednameChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        if (view == pesticideSubmit) {
            //打印一下sowmethod
            Log.e("TAG", "sowmethod的值是：" + sowmethod);
            String specialistId = specid;
            String time = tvTime.getText().toString();
            String endtime = tvEndtime.getText().toString();
            String seedid = "2";
            //水肥药其他分别为1,2,3,4
            String recommend_type = "3";
            String readed = "0";
            String detail = etDetail.getText().toString();
            String notice = etNotice.getText().toString();
            String stage = etGrowtimeStage.getText().toString();
            //这两个值局部变量，下边用还需重新定义
            String diseasename = etDiseaseName.getText().toString();
            String ingredient = etIngredient.getText().toString();
            //判断是否填写完整
            if (TextUtils.isEmpty(time)
                    || TextUtils.isEmpty(endtime)
                    || TextUtils.isEmpty(seedid)
                    || TextUtils.isEmpty(detail)
                    || TextUtils.isEmpty(notice)
                    || TextUtils.isEmpty(stage)
                    || TextUtils.isEmpty(sowmethod)
                    || TextUtils.isEmpty(diseasename)
                    || TextUtils.isEmpty(ingredient)) {
                Toast.makeText(getActivity(), "请检查没有填写的地方", Toast.LENGTH_SHORT);
            } else {
                String url = Constants.NEWNONGJIRECOMMENDINSERT;
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("specialistId", specialistId)
                        .addParams("recommendTime", time)
                        .addParams("recommendEndtime", endtime)
                        .addParams("seedId", seedid)
                        .addParams("recommendType", recommend_type)
                        .addParams("recommendReaded", readed)
                        .addParams("detail", detail)
                        .addParams("notice", notice)
                        .addParams("stage", stage)
                        //sowmethod 定义在spinner的onitemselect点击事件中
                        .addParams("sowmethod", sowmethod)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "recommend基本信息插入失败==" + e.getMessage());
                                Toast.makeText(getActivity(), "完犊子", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "recommend基本信息插入成功==" + response);
                                Toast.makeText(getActivity(), "成功发布农技推荐的信息", Toast.LENGTH_LONG).show();

                                ////////////////////当插入肥基本信息“成功”之后再次插入肥的元素信息///////////////////////////////////////////////
                                //首先查找刚插入的一条信息的id，然后根据那个在进行嵌套插入
                                String specialistId = specid;
                                String url = Constants.ONENERLYINSERTRECOMMENDINFO;
                                OkHttpUtils
                                        .post()
                                        .url(url)
                                        .addParams("specialistId", specialistId)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                Log.e(TAG, "没有刚插入的推荐信息" + e.getMessage());
                                                Toast.makeText(getActivity(), "sorry i cant", Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e(TAG, "查找到了刚插入的推荐信息==" + response);
                                                Toast.makeText(getActivity(), "成功了查找到刚插入的推荐信息用于进一步插入副表", Toast.LENGTH_LONG).show();
                                                //解析一下查找的刚才插入的一条数据，用最新的recommendbean
                                                processNearlyinsertrecommend(response);
                                                //这就是获取的最近插入的一条推荐信息的id,打印一下即可,此处id是整型值，直接打印会报错，转成string即可
                                                Toast.makeText(getActivity(), String.valueOf(nearlyinsert_include_recommend_id.getRecommendId()), Toast.LENGTH_LONG).show();

                                                ///////////////////////////最后一步插入副表中的信息/////////////////////////////////////////////////////////////////////
                                                int recommend_id = nearlyinsert_include_recommend_id.getRecommendId();//记得转换string
                                                String diseaseName = etDiseaseName.getText().toString();
                                                String ingredient = etIngredient.getText().toString();
                                                String url1 = Constants.NONGJIPESTICIDEINERT;
                                                OkHttpUtils
                                                        .post()
                                                        .url(url1)
                                                        .addParams("recommendId", String.valueOf(recommend_id))
                                                        .addParams("name", diseaseName)
                                                        .addParams("ingredient", ingredient)
                                                        .build()
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onError(Call call, Exception e, int id) {
                                                                Log.e(TAG, "recommend_pesticide插入失败" + e.getMessage());
                                                                Toast.makeText(getActivity(), "最后一步插入失败", Toast.LENGTH_LONG).show();
                                                            }

                                                            @Override
                                                            public void onResponse(String response, int id) {
                                                                Log.e(TAG, "recommend_pesticide插入成功" + response);
                                                                Toast.makeText(getActivity(), "最后一步插入成功", Toast.LENGTH_LONG).show();
                                                            }
                                                        });


                                            }
                                        });


                                ////////////////////////////////////////////////////////////////////////////////////////////////


                            }
                        });

            }
        }
    }

    //解析一下查找的刚才插入的一条数据，用最新的recommendbean//二级嵌套请求
    private void processNearlyinsertrecommend(String response) {
        nearlyinsert_allinfo = JSON.parseObject(response, recommend.class);
        nearlyinsert_include_recommend_id = nearlyinsert_allinfo.getOnerecommndresult();
    }

    //spinner的选中的两个点击事件
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (sGrowdataChoose.getSelectedItem().equals("插秧")) {
            sowmethod = "1";
        } else if (sGrowdataChoose.getSelectedItem().equals("保墒旱直播")) {
            sowmethod = "2";
        } else if (sGrowdataChoose.getSelectedItem().equals("播后上水")) {
            sowmethod = "3";
        } else if (sGrowdataChoose.getSelectedItem().equals("催芽撒播")) {
            sowmethod = "4";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
