package com.bigdata.agricultureplatform.specialist.activity.nongzi.threefragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.FertilizerBean;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeactivity.FertilizerAddActivity;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeadapter.FertilizerviewAdapter;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * @description：$des$ 化肥third
 **/
public class FertilizerFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.ib_SpecialistFertilizer_addpush)
    ImageButton ibSpecialistFertilizerAddpush;
    @Bind(R.id.lv_fertilizer)
    ListView lvFertilizer;
    @Bind(R.id.ib_addfertilizer_back)
    ImageButton ibAddfertilizerBack;
    private List<FertilizerBean.FertilizerServiceresultBean> fertilizerresultBeanList;
    private FertilizerviewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_fertilizer, null);
        //如果没用到下边这个句子使用了butterknife则会出现一个非常牛*的错误
        //会在底下adpter引用传入值得时候出现空指针：java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.ListView.setAdapter(android.widget.ListAdapter)' on a null object reference
        ButterKnife.bind(this, view);
        ibSpecialistFertilizerAddpush.setOnClickListener(this);
        ibAddfertilizerBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "化肥的fragment数据被初始化了");
        inithaufeiData();
    }

    private void inithaufeiData() {
        String url = Constants.NONGZI_HUAFEIINFO_URL;
        OkHttpUtils
                .get()
                .url(url)
                //请求参数暂时为空
                .build()
                .execute(new StringCallback() {
                    /*
                     * 当请求失败的时候回调，打
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "登录数据请求数据成功==" + response);
                        //解析response
                        fertilizerprocessData(response);
                        Log.e("TAG", "ceshi" + fertilizerresultBeanList.get(0).getFertilizerAddress());
                        //此处设置为puthActivity.this不再出错，不用this

                        //网上防止泄露内存的一种方式获取context，在清单文件中的application和.java文件中有定义
                        // Context context = MyApplication.getInstance();
                        adapter = new FertilizerviewAdapter(getActivity(), fertilizerresultBeanList);
                        lvFertilizer.setAdapter(adapter);
//监听事件设置在这里比较好
                        lvFertilizer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //这里所有默认的i都改成position   long l都改成id
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                //getcontext和getacitivity的区别是什么
                                Toast.makeText(getContext(), "position" + position, Toast.LENGTH_SHORT).show();

                                FertilizerBean.FertilizerServiceresultBean fertilizerServiceresultBean = fertilizerresultBeanList.get(position);
                                //抽取启动种子详情这个activity这个方法
                                //startseedinfoActivity(agrimachineresultBean);
                            }

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
//                                intent.putExtra("专家id",seedresultBean.getSpecialistId());
//
//                                startActivity(intent);
//                            }
                        });
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    private void fertilizerprocessData(String jsonarray) {
        FertilizerBean fertilizerBean = JSON.parseObject(jsonarray, FertilizerBean.class);
        fertilizerresultBeanList = fertilizerBean.getFertilizerServiceresult();
        //成功显示数据，数组对象其实已经赋值，我们要的就是对象啊，
        //知识用get位置来获取，当然不能直接打印所有
        //打印第一条种子数据的name
        Log.e(TAG, "数组显示用.get0" + fertilizerresultBeanList.get(0).getFertilizerAddress());

    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistFertilizerAddpush) {
            Intent intent = new Intent(getActivity(), FertilizerAddActivity.class);
            startActivity(intent);
        }else if (view==ibAddfertilizerBack){
            getActivity().finish();
        }
    }
}