package com.bigdata.agricultureplatform.specialist.activity.nongji.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Fragment04 extends Fragment implements View.OnClickListener {
    @Bind(R.id.s_growdata_choose)
    Spinner sGrowdataChoose;
    @Bind(R.id.s_seed_choose)
    Spinner sSeedChoose;
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
    @Bind(R.id.other_submit)
    Button otherSubmit;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, null);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

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

        otherSubmit.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onClick(View view) {
        if (view == otherSubmit) {
            String specialistId = "1";
            String time = tvTime.getText().toString();
            String endtime = tvEndtime.getText().toString();
            String seedid = "2";
            //水肥药其他
            String recommend_type = "4";
            String readed = "0";
            String detail = etDetail.getText().toString();
            String notice = etNotice.getText().toString();
            String stage = "灌浆期";
            String sowmethod = "2";
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
}
