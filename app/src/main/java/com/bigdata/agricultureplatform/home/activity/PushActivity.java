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
import com.bigdata.agricultureplatform.home.adapter.SeedGridViewAdapter;
import com.bigdata.agricultureplatform.home.bean.SeedinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PushActivity extends Activity implements View.OnClickListener {
    
    @Bind(R.id.ib_zhongzipush_back)
    ImageButton ibZhongzipushBack;
    @Bind(R.id.gv_zhongzipush)
    GridView gvZhongzipush;

    private SeedGridViewAdapter adapter;
    private Context seedcontext;
    private SeedinfoBean seedinfoBean;
    private SeedinfoBean.SeedresultBean seedresultBean;
    private List<SeedinfoBean.SeedresultBean> seedresultBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhongzipush);
        ButterKnife.bind(this);
        ibZhongzipushBack.setOnClickListener(this);
        //1.首先定义在view上定义一个viewadpter，并且像其构造方法中传递上下文及数据
        initZhongziData();
        //adpter和对应的数据传输都放到获取数据内部
    }
    //启动种子详细信息界面
    //*************************************************************
    //这中抽取的方法在其它的activity中可以调用。
    //*************************************************************
//    private void startseedinfoActivity(SeedinfoBean seedinfoBean) {

//    }
    private void initZhongziData() {
        getZhongziDataFormat();
    }

    private void getZhongziDataFormat() {
        String url = Constants.ZHONGZIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
//先干掉
//                .addParams("userName", user_name)
//                .addParams("userPass", user_pass)
                .build()
                .execute(new StringCallback() {
                    /*
                     * 当请求失败的时候回调，打
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                    }
                    /*当联网成功回调，这里的
                    @param response表示成功请求的数据，
                    @param id 区分http100和https101
                    */
                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "登录数据请求数据成功==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        //抽出出来一个方法，传入response
                        seedprocessData(response);
                        Log.e("TAG","44444444444"+seedresultBeanList.get(0).getSeedName());
                        //此处设置为puthActivity.this不再出错，不用this
                        adapter = new SeedGridViewAdapter(PushActivity.this,seedresultBeanList);
                        Log.e("TAG","333333333"+seedresultBeanList.get(0).getSeedName());
                        gvZhongzipush.setAdapter(adapter);
                        Log.e("TAG","2222222222"+seedresultBeanList.get(0).getSeedName());
//监听事件设置在这里比较好
                        gvZhongzipush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //这里所有默认的i都改成position   long l都改成id
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                //activity中的context不能写this，必须写本activity.this
                                Toast.makeText(PushActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
                                //往seedinfoactivity中传入数据
                                //点那个咱就传入哪个，seedinfobean里边有一个msg，一个list<seedreslutbean>
                                //而我们的seedresultBeanList.get(position);早就获得了，
                                //根据位置，点一个获取一个的，一枪一个小朋友。
                                SeedinfoBean.SeedresultBean seedresultBean=seedresultBeanList.get(position);
                                //抽取启动种子详情这个activity这个方法
                                startseedinfoActivity(seedresultBean);
                            }

                            private void startseedinfoActivity(SeedinfoBean.SeedresultBean seedresultBean) {
                                //不再用传值的方式，
//                                        Intent intent=new Intent(PushActivity.this,SeedinfoActivity.class);
//                                        startActivity(intent);
                                //带有参数的intent
                                Intent intent=new Intent();
                                intent.setClass(PushActivity.this,SeedinfoActivity.class);
                                intent.putExtra("图片信息",seedresultBean.getSeedImage());
                                intent.putExtra("种子名称",seedresultBean.getSeedName());
                                intent.putExtra("种子类型",seedresultBean.getSeedType());
                                intent.putExtra("种子详情",seedresultBean.getSeedIntroduce());
                                intent.putExtra("种植方式",seedresultBean.getSeedMethod());
                                intent.putExtra("种子价格",seedresultBean.getSeedPrice());
                                intent.putExtra("适种区域",seedresultBean.getSeedPlantarea());
                                intent.putExtra("联系电话",seedresultBean.getSeedPhone());
                                intent.putExtra("专家id",seedresultBean.getSpecialistId());

                                startActivity(intent);
                            }
                        });
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    private void seedprocessData(String jsonarray) {
        SeedinfoBean seedinfoBean=JSON.parseObject(jsonarray,SeedinfoBean.class);
        seedresultBeanList=seedinfoBean.getSeedresult();
        //成功显示数据，数组对象其实已经赋值，我们要的就是对象啊，
        //知识用get位置来获取，当然不能直接打印所有
        //打印第一条种子数据的name
        Log.e(TAG,"数组显示用.get0"+seedresultBeanList.get(0).getSeedName());

//哎全废了，白浪费两个小时，基础知识很重要，
//        if (!TextUtils.isEmpty(jsonarray)) {
//        JSONObject jsonObject = JSON.parseObject(jsonarray);
//        String msg=jsonObject.getString("msg");
//        String seedresult=jsonObject.getString("seedresult");
//        SeedinfoBean.SeedresultBean seedresultBean= JSON.parseObject(seedresult, SeedinfoBean.SeedresultBean.class);
//        seedinfoBean =JSON.parseObject(jsonarray, SeedinfoBean.class);
//        先把json格式字符串转换成json对象
//        JSONObject jsonObject = JSON.parseObject(jsonarray);
        //然后提取出来seedresult这部分对象的值
//        String seedresult = jsonObject.getString("seedresult");
        //此时这个json对象//得到result里边的数据,bean里边一定要有继承jsonoject对象
//        JSONObject seedinfoBean = JSON.parseObject(seedresult, SeedinfoBean.class);
//        String msg=seedinfoBean.getMsg();
//        seedresultBeanList= seedinfoBean.getSeedresult();
//        String seedresult1=seedinfoBean.getString("seedresult");
//        //此时拿到了这个list对象，怎么办，当然是要给他赋值
//        seedresultBeanList=JSON.parseArray(seedresult, SeedinfoBean.SeedresultBean.class);
//        ((SeedinfoBean) seedinfoBean).setSeedresult(seedresultBeanList);
////        seedresultBeanList=JSON.parseArray(seedinfoBean, SeedinfoBean.SeedresultBean.class);
//           Log.e(TAG, "6666666666666==" + seedresultBeanList);
        }
    @Override
    public void onClick(View view) {
        if (view == ibZhongzipushBack) {
            finish();
        }
    }
}
