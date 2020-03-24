package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.activity.PushActivity;
import com.bigdata.agricultureplatform.home.activity.SeedinfoActivity;
import com.bigdata.agricultureplatform.home.adapter.SeedGridViewAdapter;
import com.bigdata.agricultureplatform.home.bean.SeedinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistseedpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_Specialistseedpush_back)
    ImageButton ibSpecialistseedpushBack;
    @Bind(R.id.lv_specialist_zhongzipush)
    ListView lvSpecialistZhongzipush;
    //前边两个activity逐级传过来的id，用于数据库查询
    private Integer specialistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistseedpush);
        ButterKnife.bind(this);
        //获取前边specialistactivity中传来的id,
        //注意：id是整型值，一定要妥善接受，有个deaultvalue设置为0，或者传送的时候用bundle或者intent绑定一下
        Intent intent = getIntent();
        specialistId=intent.getIntExtra("专家的id",0);
        Log.e("TAG", String.valueOf(specialistId));
        //int类型连土司都要注意
        Toast.makeText(this, String.valueOf(specialistId), Toast.LENGTH_SHORT).show();

        //开始根据“专家的id"获取种子列表数据
        initSpecialistpushzhongziData(String.valueOf(specialistId));


        //返回的监听事件
        ibSpecialistseedpushBack.setOnClickListener(this);
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

                        //seedprocessData(response);

                        //adapter = new SeedGridViewAdapter(PushActivity.this,seedresultBeanList);
                        //gvZhongzipush.setAdapter(adapter);
                        //Log.e("TAG","2222222222"+seedresultBeanList.get(0).getSeedName());
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


    @Override
    public void onClick(View view) {
        if (view == ibSpecialistseedpushBack) {
            finish();
        }
    }
}
