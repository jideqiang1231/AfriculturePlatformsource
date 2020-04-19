package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.adapter.SpecialistPushListViewAdapter;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistseedlistBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistseedpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistseedpush_back)
    ImageButton ibSpecialistseedpushBack;
    @Bind(R.id.lv_specialist_zhongzipush)
    ListView lvSpecialistZhongzipush;
    @Bind(R.id.ib_pecialist_seed_addpush)
    ImageButton ibPecialistSeedAddpush;
    //前边两个activity逐级传过来的id，用于数据库查询
    private Integer specialistId;
    private String specialistType;
    //SpecialistseedlistBean和它list中的bean
    SpecialistseedlistBean specialistseedlistBean;
    private List<SpecialistseedlistBean.SpecialistseedresultBean> specialistseedresultBeans;
    private SpecialistPushListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistseedpush);
        ButterKnife.bind(this);
        //获取前边specialistactivity中传来的id,
        //注意：id是整型值，一定要妥善接受，有个deaultvalue设置为0，或者传送的时候用bundle或者intent绑定一下
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        specialistType=intent.getStringExtra("专家的类型");
        Log.e("TAG", String.valueOf(specialistId));
        Log.e("TAG", specialistType);
        //int类型连土司都要注意
        Toast.makeText(this, String.valueOf(specialistId), Toast.LENGTH_SHORT).show();

        //开始根据“专家的id"获取种子列表数据
        initSpecialistpushzhongziData(String.valueOf(specialistId));
        //返回的监听事件
        ibSpecialistseedpushBack.setOnClickListener(this);
        ibPecialistSeedAddpush.setOnClickListener(this);
    }

    private void initSpecialistpushzhongziData(String specialistId) {
        getSpecialistpushzhongziDataFormat(specialistId);
    }

    private void getSpecialistpushzhongziDataFormat(String specialistId) {
        String url = Constants.SPECIALISTZHONGZIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("specialistId", specialistId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "专家请求种子发布历史数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "专家获取发布过得历史数据成功==" + response);
                        specialistpushzhongziprocessData(response);

                        adapter = new SpecialistPushListViewAdapter(SpecialistseedpushActivity.this, specialistseedresultBeans);
                        lvSpecialistZhongzipush.setAdapter(adapter);
//                        gvZhongzipush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            //这里所有默认的i都改成position   long l都改成id
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                                //activity中的context不能写this，必须写本activity.this
//                                Toast.makeText(PushActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
//                                //往seedinfoactivity中传入数据
//                                //点那个咱就传入哪个，seedinfobean里边有一个msg，一个list<seedreslutbean>
//                                //而我们的seedresultBeanList.get(position);早就获得了，
//                                //根据位置，点一个获取一个的，一枪一个小朋友。
//                                SeedinfoBean.SeedresultBean seedresultBean=seedresultBeanList.get(position);
//                                //抽取启动种子详情这个activity这个方法
//                                startseedinfoActivity(seedresultBean);
//                            }
//
//                            private void startseedinfoActivity(SeedinfoBean.SeedresultBean seedresultBean) {
//                                //不再用传值的方式，
////                                        Intent intent=new Intent(PushActivity.this,SeedinfoActivity.class);
////                                        startActivity(intent);
//                                //带有参数的intent
//                                Intent intent=new Intent();
//                                intent.setClass(PushActivity.this, SeedinfoActivity.class);
//                                intent.putExtra("图片信息",seedresultBean.getSeedImage());
//                                intent.putExtra("种子名称",seedresultBean.getSeedName());
//                                intent.putExtra("种子类型",seedresultBean.getSeedType());
//                                intent.putExtra("种子详情",seedresultBean.getSeedIntroduce());
//                                intent.putExtra("种植方式",seedresultBean.getSeedMethod());
//                                intent.putExtra("种子价格",seedresultBean.getSeedPrice());
//                                intent.putExtra("适种区域",seedresultBean.getSeedPlantarea());
//                                intent.putExtra("联系电话",seedresultBean.getSeedPhone());
//                                startActivity(intent);
//                            }
//                        });
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    //解析专家发布得种子数据->SpecialistseedlistBean
    //补充,这里截取有用的信息，
    private void specialistpushzhongziprocessData(String json) {
        //表面bean
        specialistseedlistBean = JSON.parseObject(json, SpecialistseedlistBean.class);
        //表面bean里面的listbean
        specialistseedresultBeans = specialistseedlistBean.getSpecialistseedresult();
        //打印一下
        Log.e(TAG, "数组显示用.get0" + specialistseedresultBeans.get(0).getSeedIntroduce());

    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistseedpushBack) {
            finish();
        }
        if (view == ibPecialistSeedAddpush) {
            Log.e("TAG", String.valueOf(specialistId));//打印一下整形值
            Intent intent = new Intent();
            intent.putExtra("专家的id", specialistId);
            intent.putExtra("专家的类型", specialistType);
            intent.setClass(SpecialistseedpushActivity.this, SpecialistaddpushActivity.class);
            startActivity(intent);
        }
    }
}
