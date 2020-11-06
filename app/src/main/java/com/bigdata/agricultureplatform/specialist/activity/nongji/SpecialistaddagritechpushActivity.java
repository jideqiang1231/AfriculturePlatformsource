package com.bigdata.agricultureplatform.specialist.activity.nongji;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment01;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment02;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment03;
import com.bigdata.agricultureplatform.specialist.activity.nongji.fragments.Fragment04;


import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistaddagritechpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.btn_water)
    Button btnWater;
    @Bind(R.id.btn_fertilizer)
    Button btnFertilizer;
    @Bind(R.id.btn_pesticide)
    Button btnPesticide;
    @Bind(R.id.btn_other)
    Button btnOther;
    @Bind(R.id.l_choice)
    LinearLayout lChoice;
    @Bind(R.id.s_seed_choose)
    Spinner sSeedChoose;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_endtime)
    TextView tvEndtime;
    @Bind(R.id.l_fasten)
    LinearLayout lFasten;
    @Bind(R.id.fragment_content)
    FrameLayout fragmentContent;
    @Bind(R.id.ib_back)
    ImageButton ibBack;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private List<Button> btnList = new ArrayList<Button>();
//    private SpecialistseedlistBean specialistseedlistbean;
//    private List<SpecialistseedlistBean.SpecialistseedresultBean> includeseednamelist;
//    private AgritechspinnerAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddagritechpush);
        ButterKnife.bind(this);
        //继承的appcompatactivity实现隐藏标题栏
        //getSupportActionBar().hide();
        //接受参数
//        Intent intent = getIntent();
//        specialistId = intent.getIntExtra("专家的id", 0);


        //直接加载下拉框的信息
        //getSpecialistnongjiSpinnerDataFormat(String.valueOf(specialistId));
         ibBack.setOnClickListener(this);
        btnWater.setOnClickListener(this);
        btnFertilizer.setOnClickListener(this);
        btnPesticide.setOnClickListener(this);
        btnOther.setOnClickListener(this);
        btnList.add(btnWater);
        btnList.add(btnFertilizer);
        btnList.add(btnPesticide);
        btnList.add(btnOther);

        TimeAndEndtime();
    }

    private void TimeAndEndtime() {

        // 進入系統默認為movie
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        setBackgroundColorById(R.id.btn_water);
        ft.replace(R.id.fragment_content,new Fragment01());
        ft.commit();
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过自定义控件AlertDialog实现
                AlertDialog.Builder builder = new AlertDialog.Builder(SpecialistaddagritechpushActivity.this);
                View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date_dialog, null);
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
                        int month = datePicker.getMonth()+1;
                        int dayOfMonth = datePicker.getDayOfMonth();
                        tvTime.setText(year+"年"+month+"月"+dayOfMonth+"日");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(SpecialistaddagritechpushActivity.this);
                View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date_dialog, null);

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
                        int month = datePicker.getMonth()+1;
                        int dayOfMonth = datePicker.getDayOfMonth();
                        tvEndtime.setText(year+"年"+month+"月"+dayOfMonth+"日");
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
    }

    //颜色
    private  void setBackgroundColorById(int btnId) {
        for (Button btn : btnList) {
            if (btn.getId() == btnId){
                btn.setBackgroundColor(Color.rgb(170,255,255));
            }else {
                btn.setBackgroundColor(Color.rgb(136,255,160));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clickshere. The action bar will
        // automatically handle clicks onthe Home/Up button, so long
        // as you specify a parentactivity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.btn_water) {
            return true;
        }
        Activity returnsuper;
        return super.onOptionsItemSelected(item);
    }

//    private void getSpecialistnongjiSpinnerDataFormat(String specialistId) {
//        String url = Constants.SPECIALISTZHONGZIINFO_URL;
//        OkHttpUtils
//                .get()
//                .url(url)
//                .addParams("specialistId", specialistId)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e(TAG, "专家请求种子发布历史数据失败==" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e(TAG, "专家获取发布过得历史数据成功==" + response);
//                        specialistpushnongjiSpinnerprocessData(response);
//
//                        adapter = new AgritechspinnerAdapter(SpecialistaddagritechpushActivity.this, includeseednamelist);
//                        specialistAgritechSpinner.setAdapter(adapter);
//                        specialistAgritechSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                //用自己的方法获取这个adpterview中的值然后赋值的过程
//                                //数据必须用bean对象接受
//                                SpecialistseedlistBean.SpecialistseedresultBean specialistseedresultBean = (SpecialistseedlistBean.SpecialistseedresultBean) adapterView.getSelectedItem();
//                                Spinneritem_seedname = specialistseedresultBean.getSeedName();
//                                Log.e(TAG, "请求结果" + specialistseedresultBean.getSeedName());
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> adapterView) {
//                                adapterView.getSelectedItem();
//                            }
//                        });
//                    }
//                });
//    }

//    private void specialistpushnongjiSpinnerprocessData(String response) {
//        specialistseedlistbean = JSON.parseObject(response, SpecialistseedlistBean.class);
//        //表面bean里面的listbean
//        includeseednamelist = specialistseedlistbean.getSpecialistseedresult();
//        //打印一下
//        Log.e(TAG, "数组显示用.get0" + includeseednamelist.get(0).getSeedName());
//    }

    @Override
    public void onClick(View view) {
        //必须，否则报错
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;

            case R.id.btn_water:
                setBackgroundColorById(R.id.btn_water);
                ft.replace(R.id.fragment_content, new Fragment01());
                break;

            case R.id.btn_fertilizer:
                setBackgroundColorById(R.id.btn_fertilizer);

                ft.replace(R.id.fragment_content, new Fragment02());
                break;

            case R.id.btn_pesticide:
                setBackgroundColorById(R.id.btn_pesticide);

                ft.replace(R.id.fragment_content, new Fragment03());
                break;

            case R.id.btn_other:
                setBackgroundColorById(R.id.btn_other);

                ft.replace(R.id.fragment_content, new Fragment04());
                break;

            default:
                break;
//            case R.id.b_specialist_nongji_pushinfo_submit:
//                content=etSpecialistNongjiPushinfoIntroduce.getText().toString();
//                area=etSpecialistNongjiPushinfoPusharea.getText().toString();
//                //一共要传输下面五个值，不要为空
//                //specialistId、Spinneritem_seedname、pushtime、content、area
//                if (TextUtils.isEmpty(Spinneritem_seedname)
//                        || TextUtils.isEmpty(pushtime)
//                        || TextUtils.isEmpty(content)
//                        || TextUtils.isEmpty(area)) {
//                    Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
//                } else {
//                    String url = Constants.INSERTNONGJIINFO_URL;
//                    OkHttpUtils
//                            .post()
//                            .url(url)
//                            .addParams("specialistId", String.valueOf(specialistId))
//                            .addParams("seedName", Spinneritem_seedname)
//                            .addParams("recommendTime", pushtime)
//                            .addParams("recommendContent", content)
//                            .addParams("recommendArea", area)
//                            .build()
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onError(Call call, Exception e, int id) {
//                                    Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
//                                    Toast.makeText(SpecialistaddagritechpushActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
//                                }
//
//                                @Override
//                                public void onResponse(String response, int id) {
//                                    Log.e(TAG, "注册成功后的response==" + response);
//                                    Toast.makeText(SpecialistaddagritechpushActivity.this, "成功发布农技的信息", Toast.LENGTH_LONG).show();
//
//                                }
//                            });
//                }
//                finish();
//                break
        }
        ft.commit();
    }


}
