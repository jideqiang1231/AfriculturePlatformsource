package com.bigdata.agricultureplatform.home.activity.nongzi;

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
import com.bigdata.agricultureplatform.home.activity.nongzi.nongziinfoActivity.FertilizerinfoActivity;
import com.bigdata.agricultureplatform.home.activity.nongzi.nongziinfoActivity.MachineinfoActivity;
import com.bigdata.agricultureplatform.home.activity.nongzi.nongziinfoActivity.PesticideinfoActivity;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.FertilizerBean;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.MachineBean;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.PesticideBean;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeadapter.FertilizerviewAdapter;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeadapter.MachineviewAdapter;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeadapter.PesticideviewAdapter;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AgrisuppliesActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_agrisupplies_back)
    ImageButton ibAgrisuppliesBack;
    @Bind(R.id.gv_agrimechina)
    GridView gvAgrimechina;
    @Bind(R.id.gv_pesticide)
    GridView gvPesticide;
    @Bind(R.id.gv_fertilizer)
    GridView gvFertilizer;


    private List<FertilizerBean.FertilizerServiceresultBean> fertilizerList;
    private List<MachineBean.AgrimachineresultBean> machineList;
    private List<PesticideBean.PesticidesresultBean> pesticideList;
    //共后端专家农资里的adapter和item_view.xml。
    private FertilizerviewAdapter fertilizerviewadapter;
    private PesticideviewAdapter pesticideviewadapter;
    private MachineviewAdapter machineviewadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrisupplies);
        ButterKnife.bind(this);
        //用户显示农资信息，共后端专家里边的bean对象。
        initnongjiData();
        initnongyaoData();
        inithuafeiData();
        ibAgrisuppliesBack.setOnClickListener(this);
    }

    private void inithuafeiData() {
        String url = Constants.NONGZI_HUAFEIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "登录数据请求数据成功==" + response);
                        huafeidataProgress(response);
                        Log.e("TAG", "44444444444" + fertilizerList.get(0).getFertilizerAddress());
                        fertilizerviewadapter = new FertilizerviewAdapter(AgrisuppliesActivity.this, fertilizerList);
                        gvFertilizer.setAdapter(fertilizerviewadapter);
                        gvFertilizer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Toast.makeText(AgrisuppliesActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                                FertilizerBean.FertilizerServiceresultBean fertilizerServiceresultBean = fertilizerList.get(position);
                                starthuafeiinfoActivity(fertilizerServiceresultBean);
                            }

                            private void starthuafeiinfoActivity(FertilizerBean.FertilizerServiceresultBean fertilizerServiceresultBean) {

                                Intent intent = new Intent();
                                intent.setClass(AgrisuppliesActivity.this, FertilizerinfoActivity.class);

                                intent.putExtra("化肥名称", fertilizerServiceresultBean.getFertilizerName());
                                intent.putExtra("使用方法", fertilizerServiceresultBean.getFertilizerInstructions());

                                intent.putExtra("N", fertilizerServiceresultBean.getFertilizerN());
                                intent.putExtra("P", fertilizerServiceresultBean.getFertilizerP());
                                intent.putExtra("K", fertilizerServiceresultBean.getFertilizerK());
                                intent.putExtra("Other", fertilizerServiceresultBean.getFertilizerOther());

                                intent.putExtra("适种作物", fertilizerServiceresultBean.getFertilizerModeratecrop());
                                intent.putExtra("地址", fertilizerServiceresultBean.getFertilizerAddress());
                                intent.putExtra("生产商", fertilizerServiceresultBean.getFertilizerManufacturer());
                                intent.putExtra("联系方式", fertilizerServiceresultBean.getFertilizerPhone());
                                intent.putExtra("价格", fertilizerServiceresultBean.getFertilizerPrice());
                                intent.putExtra("型号", fertilizerServiceresultBean.getFertilizerType());

                                intent.putExtra("生产日期", fertilizerServiceresultBean.getFertilizerProductiondate());
                                intent.putExtra("保质期", fertilizerServiceresultBean.getFertilizerShelflife());
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    private void initnongyaoData() {
        String url = Constants.NONGZI_NONGYAOINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "农药数据请求数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "农药数据请求数据成功==" + response);

                        nongyaodataProgress(response);
                        Log.e("TAG", "44444444444" + pesticideList.get(0).getPesticideName());

                        pesticideviewadapter = new PesticideviewAdapter(AgrisuppliesActivity.this, pesticideList);

                        gvPesticide.setAdapter(pesticideviewadapter);
                        gvPesticide.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Toast.makeText(AgrisuppliesActivity.this, "position" + position, Toast.LENGTH_SHORT).show();

                                PesticideBean.PesticidesresultBean pesticidesresultBean = pesticideList.get(position);
                                startseedinfoActivity(pesticidesresultBean);
                            }

                            private void startseedinfoActivity(PesticideBean.PesticidesresultBean pesticidesresultBean) {
                                //传值
                                Intent intent = new Intent();
                                intent.setClass(AgrisuppliesActivity.this, PesticideinfoActivity.class);
                                intent.putExtra("农药名称", pesticidesresultBean.getPesticideName());
                                intent.putExtra("使用方法", pesticidesresultBean.getPesticideInstructions());

                                intent.putExtra("剂量", pesticidesresultBean.getPesticideDosage());
                                intent.putExtra("价格", pesticidesresultBean.getPesticidePrice());
                                intent.putExtra("含量", pesticidesresultBean.getPesticideContent());
                                intent.putExtra("地址", pesticidesresultBean.getPesticideContent());
                                intent.putExtra("联系方式", pesticidesresultBean.getPesticidePhone());

                                intent.putExtra("组成成分", pesticidesresultBean.getPesticideComposition());
                                intent.putExtra("登记号", pesticidesresultBean.getPesticideLicensenumber());
                                intent.putExtra("生产商", pesticidesresultBean.getPesticideManufacturer());
                                intent.putExtra("适用作物", pesticidesresultBean.getPesticideModeratecrop());
                                intent.putExtra("生产日期", pesticidesresultBean.getPesticideProductiondate());
                                intent.putExtra("保质期", pesticidesresultBean.getPesticideShelflife());
                                intent.putExtra("毒性", pesticidesresultBean.getPesticideVirulence());
                                intent.putExtra("注意事项", pesticidesresultBean.getPesticideNote());

                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    private void initnongjiData() {
        String url = Constants.NONGZI_NONGJIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "登录数据请求数据成功==" + response);
                        nongjidataProgress(response);
                        machineviewadapter = new MachineviewAdapter(AgrisuppliesActivity.this, machineList);
                        gvAgrimechina.setAdapter(machineviewadapter);
                        gvAgrimechina.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Toast.makeText(AgrisuppliesActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                                MachineBean.AgrimachineresultBean agrimachineresultBean = machineList.get(position);
                                startseedinfoActivity(agrimachineresultBean);
                            }

                            private void startseedinfoActivity(MachineBean.AgrimachineresultBean agrimachineresultBean) {
                                Intent intent = new Intent();
                                intent.setClass(AgrisuppliesActivity.this, MachineinfoActivity.class);

                                intent.putExtra("机器名称", agrimachineresultBean.getMachineName());
                                intent.putExtra("使用方法", agrimachineresultBean.getMachineFunction());
                                intent.putExtra("联系方式", agrimachineresultBean.getMachinePhone());
                                intent.putExtra("地址", agrimachineresultBean.getMachineAddress());
                                intent.putExtra("生产商", agrimachineresultBean.getMachineManufacturer());
                                intent.putExtra("价格", agrimachineresultBean.getMachinePrice());
                                intent.putExtra("型号", agrimachineresultBean.getMachineType());


                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    //三个农资数据的解析
    private void huafeidataProgress(String response) {
        FertilizerBean fertilizerBean = JSON.parseObject(response, FertilizerBean.class);
        fertilizerList = fertilizerBean.getFertilizerServiceresult();
    }

    private void nongjidataProgress(String response) {
        MachineBean machineBean = JSON.parseObject(response, MachineBean.class);
        machineList = machineBean.getAgrimachineresult();
    }

    private void nongyaodataProgress(String response) {
        PesticideBean pesticideBean = JSON.parseObject(response, PesticideBean.class);
        pesticideList = pesticideBean.getPesticidesresult();
    }

    @Override
    public void onClick(View view) {
        if (view == ibAgrisuppliesBack) {
            finish();
        }
    }
}
