package com.bigdata.agricultureplatform.home.activity.zhengce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.adapter.ZhengceGridViewAdapter;
import com.bigdata.agricultureplatform.home.bean.PolicylistinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PolicypushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_policy_push_back)
    ImageButton ibPolicyPushBack;
    @Bind(R.id.gv_policy_push)
    GridView gvPolicyPush;
    @Bind(R.id.ib_postfileinfo)
    ImageButton ibPostfileinfo;
    private PolicylistinfoBean policyinfolistinfobean;
    private List<PolicylistinfoBean.PolicyresultBean> zhengceList;
    private ZhengceGridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policypush);
        ButterKnife.bind(this);
        //请求政策数据：
        initZhengceData();
        ibPolicyPushBack.setOnClickListener(this);
        ibPostfileinfo.setOnClickListener(this);
    }

    private void initZhengceData() {
        String url = Constants.ZHENGCEINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "政策信息获取失败了==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "政策信息列表数据成功==" + response);
                        //解析数据
                        zhengceprocessData(response);
                        //设置adatper往视图放数据
                        adapter = new ZhengceGridViewAdapter(PolicypushActivity.this, zhengceList);
                        gvPolicyPush.setAdapter(adapter);
                        //为每一项设置点击事件
                        gvPolicyPush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //这里所有默认的i都改成position   long l都改成id
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                //activity中的context不能写this，必须写本activity.this
                                Toast.makeText(PolicypushActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                                //往seedinfoactivity中传入数据
                                //点那个咱就传入哪个，seedinfobean里边有一个msg，一个list<seedreslutbean>
                                //而我们的seedresultBeanList.get(position);早就获得了，
                                //根据位置，点一个获取一个的，一枪一个小朋友。
                                PolicylistinfoBean.PolicyresultBean policyresultBean = zhengceList.get(position);
                                //抽取启动种子详情这个activity这个方法
                                startseedinfoActivity(policyresultBean);
                            }

                            private void startseedinfoActivity(PolicylistinfoBean.PolicyresultBean policyresultBean) {
                                //不再用传值的方式，
//                                        Intent intent=new Intent(PushActivity.this,SeedinfoActivity.class);
//                                        startActivity(intent);
                                // 带有参数的intent
                                Intent intent = new Intent();
                                intent.setClass(PolicypushActivity.this, PoclicyinfoActivity.class);
                                // intent.putExtra("图片信息",seedresultBean.getSeedImage());
                                intent.putExtra("标题", policyresultBean.getPolicyTitle());
                                //intent.putExtra("种子类型",seedresultBean.getSeedType());
                                intent.putExtra("政策内容", policyresultBean.getPolicyContent());
                                intent.putExtra("专家id", policyresultBean.getSpecialistId());
                                //截取日期substring函数
                                intent.putExtra("推送时间", policyresultBean.getPolicyTime().substring(5, 7) + "月" + policyresultBean.getPolicyTime().substring(8, 10) + "日");

                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    private void zhengceprocessData(String response) {
        policyinfolistinfobean = JSON.parseObject(response, PolicylistinfoBean.class);
        zhengceList = policyinfolistinfobean.getPolicyresult();
        //成功显示数据，数组对象其实已经赋值，我们要的就是对象啊，
        //知识用get位置来获取，当然不能直接打印所有
        //打印第一条种子数据的name
        Log.e(TAG, "数组显示用.get0" + zhengceList.get(0).getPolicyTitle());
    }

    @Override
    public void onClick(View view) {
        if (view == ibPolicyPushBack) {
            finish();
        } else if (view == ibPostfileinfo) {
            Intent intent = new Intent(PolicypushActivity.this, PolicyfileActivity.class);
            startActivity(intent);
        }
    }
}
