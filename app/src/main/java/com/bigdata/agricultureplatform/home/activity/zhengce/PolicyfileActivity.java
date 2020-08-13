package com.bigdata.agricultureplatform.home.activity.zhengce;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.bean.PolicyfileBean;
import com.bigdata.agricultureplatform.home.adapter.PolicyfileAdapter;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PolicyfileActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.gv_policyfile)
    GridView gvPolicyfile;
    private PolicyfileBean policyfileinfobean;
    private List<PolicyfileBean.PolicyfileresultBean> policyfileList;
    private PolicyfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyfile);
        ButterKnife.bind(this);
        ibBack.setOnClickListener(this);
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
                        adapter = new PolicyfileAdapter(PolicyfileActivity.this,policyfileList);
                        gvPolicyfile.setAdapter(adapter);
                        //为每一项设置点击事件
                        gvPolicyfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Toast.makeText(PolicyfileActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
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
        Log.e(TAG,"数组显示用.get0"+policyfileList.get(0).getPolicyfileTopic());
    }

    @Override
    public void onClick(View view) {
        if (view==ibBack){
            finish();
        }
    }

}
