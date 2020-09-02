package com.bigdata.agricultureplatform.specialist.activity.zhengce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.activity.zhengce.PolicyfileActivity;
import com.bigdata.agricultureplatform.home.adapter.PolicyfileAdapter;
import com.bigdata.agricultureplatform.home.bean.PolicyfileBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PolicyfilepushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_policy_push_back)
    ImageButton ibPolicyPushBack;
    @Bind(R.id.lv_policyfile_push)
    ListView lvPolicyfilePush;
    @Bind(R.id.ib_policy_file_addpush)
    ImageButton ibPolicyFileAddpush;
    private PolicyfileBean policyfileinfobean;
    private List<PolicyfileBean.PolicyfileresultBean> policyfileList;
    private PolicyfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyfilepush);
        ButterKnife.bind(this);
        ibPolicyPushBack.setOnClickListener(this);
        ibPolicyFileAddpush.setOnClickListener(this);
        //加载政策信息与用户公用adapter及其中的item
        initpolicyfileView();
    }
    private void initpolicyfileView() {
        String url = Constants.ZHENGCEWENJIANINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "政策文件获取失败了==" + e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        //解析数据
                        policyfileinfoprogress(response);
                        //设置adatper往视图放数据
                        adapter = new PolicyfileAdapter(PolicyfilepushActivity.this,policyfileList);
                        lvPolicyfilePush.setAdapter(adapter);
                        //为每一项设置点击事件
                        lvPolicyfilePush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                              //  Toast.makeText(PolicyfilepushActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
                                Toast.makeText(PolicyfilepushActivity.this,"专家还没有下载功能",Toast.LENGTH_SHORT).show();

                                PolicyfileBean.PolicyfileresultBean policyfileresultBean=policyfileList.get(position);
                            }
                        });
                    }
                });

    }
    //处理数据
    private void policyfileinfoprogress(String response) {
        policyfileinfobean= JSON.parseObject(response, PolicyfileBean.class);
        policyfileList=policyfileinfobean.getPolicyfileresult();
        Log.e(TAG,"数组显示用.get0"+policyfileList.get(0).getPolicyfileLocation());
    }
    @Override
    public void onClick(View view) {
        if (view == ibPolicyPushBack) {
            finish();
        } else if (view == ibPolicyFileAddpush) {
            //传入专家的参数id,添加信息时候要插入
            Intent intent = new Intent();
            intent.setClass(this, PolicyfileaddpushActivity.class);
            startActivity(intent);
        }
    }
}
