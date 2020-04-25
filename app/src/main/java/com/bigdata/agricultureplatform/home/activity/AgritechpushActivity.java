package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.content.Context;
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
import com.bigdata.agricultureplatform.home.adapter.NongjiGridViewAdapter;
import com.bigdata.agricultureplatform.home.adapter.SeedGridViewAdapter;
import com.bigdata.agricultureplatform.home.bean.RecommendlistinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AgritechpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_agritech_push_back)
    ImageButton ibAgritechPushBack;
    @Bind(R.id.gv_agritech_push)
    GridView gvAgritechPush;
    private List<RecommendlistinfoBean.NongjiresultBean> nongjiresultBeanList;
    private NongjiGridViewAdapter adapter;
    private Context nongjicontext;
    private RecommendlistinfoBean recommendlistinfoBean;
    private RecommendlistinfoBean.NongjiresultBean nongjiresultBean;
    private List<RecommendlistinfoBean.NongjiresultBean> nongjiresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agritech);
        ButterKnife.bind(this);
        //1.首先定义在view上定义一个viewadpter，并且像其构造方法中传递上下文及数据
        initNongjiData();
        //设置监听事件
        ibAgritechPushBack.setOnClickListener(this);

    }

    private void initNongjiData() {
        getNongjiDataFormat();
    }

    private void getNongjiDataFormat() {
        String url = Constants.NONGJIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /*
                     * 当请求失败的时候回调，打
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "农技信息列表数据请求失败==" + e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "农技信息列表数据成功==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        nongjiprocessData(response);


                        Log.e("TAG","44444444444"+nongjiresultBeanList.get(0).getSeedName());
                        //此处设置为puthActivity.this不再出错，不用this
                        adapter = new NongjiGridViewAdapter(AgritechpushActivity.this,nongjiresultBeanList);
                        Log.e("TAG","333333333"+nongjiresultBeanList.get(0).getSeedName());
                        gvAgritechPush.setAdapter(adapter);
                        Log.e("TAG","2222222222"+nongjiresultBeanList.get(0).getSeedName());
//监听事件设置在这里比较好
                        gvAgritechPush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //这里所有默认的i都改成position   long l都改成id
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                //activity中的context不能写this，必须写本activity.this
                                Toast.makeText(AgritechpushActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
                                //往seedinfoactivity中传入数据
                                //点那个咱就传入哪个，seedinfobean里边有一个msg，一个list<seedreslutbean>
                                //而我们的seedresultBeanList.get(position);早就获得了，
                                //根据位置，点一个获取一个的，一枪一个小朋友。
                                RecommendlistinfoBean.NongjiresultBean nongjiresultBean=nongjiresultBeanList.get(position);
                                //抽取启动种子详情这个activity这个方法
                                startseedinfoActivity(nongjiresultBean);
                            }

                            private void startseedinfoActivity(RecommendlistinfoBean.NongjiresultBean nongjiresultBean) {
                                //不再用传值的方式，
//                                        Intent intent=new Intent(PushActivity.this,SeedinfoActivity.class);
//                                        startActivity(intent);
                               // 带有参数的intent
                                Intent intent=new Intent();
                                intent.setClass(AgritechpushActivity.this,AgritechninfoActivity.class);
                               // intent.putExtra("图片信息",seedresultBean.getSeedImage());
                                intent.putExtra("种子名称",nongjiresultBean.getSeedName());
                                //intent.putExtra("种子类型",seedresultBean.getSeedType());
                                intent.putExtra("农技推送详情",nongjiresultBean.getRecommendContent());
                                intent.putExtra("专家id",nongjiresultBean.getSpecialistId());
                                //截取日期substring函数
                                intent.putExtra("推送时间",nongjiresultBean.getRecommendTime().substring(5,7)+"月"+nongjiresultBean.getRecommendTime().substring(8,10)+"日");

                                startActivity(intent);
                            }
                        });
                    }

                    private void nongjiprocessData(String jsonlist) {
                        RecommendlistinfoBean recommendlistinfoBean= JSON.parseObject(jsonlist,RecommendlistinfoBean.class);
                        nongjiresultBeanList=recommendlistinfoBean.getNongjiresult();
                        //成功显示数据，数组对象其实已经赋值，我们要的就是对象啊，
                        //知识用get位置来获取，当然不能直接打印所有
                        //打印第一条种子数据的name
                        Log.e(TAG,"数组显示用.get0"+nongjiresultBeanList.get(0).getSeedName());
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    @Override
    public void onClick(View view) {
        if (view==ibAgritechPushBack){
            finish();
        }
    }
}
