package com.bigdata.agricultureplatform.home.activity.nongshi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.adapter.NongshiGridViewAdapter;
import com.bigdata.agricultureplatform.home.bean.AgritimelistinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AgritimepushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_agritime_push_back)
    ImageButton ibAgritimePushBack;
    @Bind(R.id.gv_agritime_push)
    GridView gvAgritimePush;
    private AgritimelistinfoBean agritimelistinfobean;
    private List<AgritimelistinfoBean.NongshiresultBean> nongshiresultBeanList;
    private NongshiGridViewAdapter adapter;
    private String nowData;
    private String useradress;
    private String userplanttype;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agritimepush);
        ButterKnife.bind(this);
        //获取系统的月份，获取用户地址，获取用户的种植类型
        //从缓存中获取
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        userplanttype = sp.getString("usercroptypes", null);
        useradress = sp.getString("useradress", null);
        //获取月份
        Time time = new Time("GMT+8");
        time.setToNow();
        nowData = String.valueOf(time.month);
        //打印当前月份,当前用户的种植类型和地址
        Log.e("TAG", nowData + userplanttype + useradress);
        //请求农时数据：
        initNongshiData();
        //返回的点击事件
        ibAgritimePushBack.setOnClickListener(this);
    }

    private void initNongshiData() {
        String url = Constants.NONGSHIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("recommendTime", nowData)
                .addParams("recommendArea", useradress)
                .addParams("seedType", userplanttype)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "农时信息获取失败了==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "农技信息列表数据成功==" + response);
                        //解析数据
                        nongshiprocessData(response);
                        //设置adatper往视图放数据
                        adapter = new NongshiGridViewAdapter(AgritimepushActivity.this, nongshiresultBeanList);
                        gvAgritimePush.setAdapter(adapter);
                        //为每一项设置点击事件
                        gvAgritimePush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //这里所有默认的i都改成position   long l都改成id
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                //activity中的context不能写this，必须写本activity.this
                                Toast.makeText(AgritimepushActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                                //往seedinfoactivity中传入数据
                                //点那个咱就传入哪个，seedinfobean里边有一个msg，一个list<seedreslutbean>
                                //而我们的seedresultBeanList.get(position);早就获得了，
                                //根据位置，点一个获取一个的，一枪一个小朋友。
                                AgritimelistinfoBean.NongshiresultBean nongshiresultBean = nongshiresultBeanList.get(position);
                                //抽取启动种子详情这个activity这个方法
                                startseedinfoActivity(nongshiresultBean);
                            }

                            private void startseedinfoActivity(AgritimelistinfoBean.NongshiresultBean nongshiresultBean) {
                                //不再用传值的方式，
//                                        Intent intent=new Intent(PushActivity.this,SeedinfoActivity.class);
//                                        startActivity(intent);
                                // 带有参数的intent
                                Intent intent = new Intent();
                                intent.setClass(AgritimepushActivity.this, AgritimeinfoActivity.class);
                                // intent.putExtra("图片信息",seedresultBean.getSeedImage());
                                intent.putExtra("种子名称", nongshiresultBean.getSeedName());
                                //intent.putExtra("种子类型",seedresultBean.getSeedType());
                                intent.putExtra("农技推送详情", nongshiresultBean.getRecommendContent());
                                intent.putExtra("专家id", nongshiresultBean.getSpecialistId());
                                //截取日期substring函数
                                intent.putExtra("推送时间", nongshiresultBean.getRecommendTime().substring(5, 7) + "月" + nongshiresultBean.getRecommendTime().substring(8, 10) + "日");

                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    //解析数据
    private void nongshiprocessData(String response) {
        agritimelistinfobean = JSON.parseObject(response, AgritimelistinfoBean.class);
        nongshiresultBeanList = agritimelistinfobean.getNongshiresult();
        //成功显示数据，数组对象其实已经赋值，我们要的就是对象啊，
        //知识用get位置来获取，当然不能直接打印所有
        //打印第一条种子数据的name
        Log.e(TAG, "数组显示用.get0" + nongshiresultBeanList.get(0).getSeedName());
    }


    //点击事件
    @Override
    public void onClick(View view) {
        if (view == ibAgritimePushBack) {
            finish();
        }
    }
}
