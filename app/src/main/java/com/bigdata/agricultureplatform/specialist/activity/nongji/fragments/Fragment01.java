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
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.view.View.SCROLL_AXIS_HORIZONTAL;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class Fragment01 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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
    @Bind(R.id.et_notice)
    EditText etNotice;
    @Bind(R.id.et_detail)
    EditText etDetail;
    @Bind(R.id.l_fasten)
    LinearLayout lFasten;
    @Bind(R.id.water_submit)
    Button waterSubmit;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        //读取专家的缓存信息
        sp = getActivity().getSharedPreferences("specialistinfo", Context.MODE_PRIVATE);
        specid=sp.getString("specid",null);

        //为下拉框加载数据
        initseednametypedata();

//        sCroptype1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                                 @Override
//                                                 public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                                     Register_spinner_seedtype1 = seedtyperesultBean.getSeedType();
//
//                                                 })};
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
        waterSubmit.setOnClickListener(this);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        if (view == waterSubmit) {
            //打印一下sowmethod
            Log.e("TAG","sowmethod的值是："+sowmethod);
            String specialistId = specid;
            String time = tvTime.getText().toString();
            String endtime = tvEndtime.getText().toString();
            String seedid = "2";
            //水肥药其他
            String recommend_type = "1";
            String readed = "0";
            String detail = etDetail.getText().toString();
            String notice = etNotice.getText().toString();
            String stage = etGrowtimeStage.getText().toString();
            if (TextUtils.isEmpty(time) || TextUtils.isEmpty(endtime) || TextUtils.isEmpty(seedid) || TextUtils.isEmpty(detail) || TextUtils.isEmpty(notice) || TextUtils.isEmpty(stage) || TextUtils.isEmpty(sowmethod)) {
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
                                Log.e(TAG, "插入失败==" + e.getMessage());
                                Toast.makeText(getActivity(), "完犊子", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "注册成功后的response==" + response);
                                Toast.makeText(getActivity(), "成功发布农技推荐的信息", Toast.LENGTH_LONG).show();

                            }
                        });


            }
        }
    }

    //spinner的选中的两个点击事件
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      if(sGrowdataChoose.getSelectedItem().equals("插秧")){
          sowmethod="1";
      }else if (sGrowdataChoose.getSelectedItem().equals("保墒旱直播")){
          sowmethod="2";
      }else if (sGrowdataChoose.getSelectedItem().equals("播后上水")){
          sowmethod="3";
      }else if (sGrowdataChoose.getSelectedItem().equals("催芽撒播")){
          sowmethod="4";
      }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
