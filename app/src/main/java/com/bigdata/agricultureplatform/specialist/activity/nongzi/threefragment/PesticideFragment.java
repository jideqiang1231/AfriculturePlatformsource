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
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.PesticideBean;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeactivity.PesticideAddActivity;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threeadapter.PesticideviewAdapter;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * @description：$des$ 农药second
 **/
public class PesticideFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.ib_SpecialistPesticide_addpush)
    ImageButton ibSpecialistPesticideAddpush;
    @Bind(R.id.lv_pesticide)
    ListView lvPesticide;
    @Bind(R.id.ib_addpesticide_back)
    ImageButton ibAddpesticideBack;
    private List<PesticideBean.PesticidesresultBean> pesticideresultBeanList;
    private PesticideviewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_pesticide, null);
        //如果没用到下边这个句子使用了butterknife则会出现一个非常牛*的错误
        //会在底下adpter引用传入值得时候出现空指针：java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.ListView.setAdapter(android.widget.ListAdapter)' on a null object reference
        ButterKnife.bind(this, view);
        ibSpecialistPesticideAddpush.setOnClickListener(this);
        ibAddpesticideBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "农药的fragment数据被初始化了");
        initnongyaoData();
    }

    private void initnongyaoData() {
        String url = Constants.NONGZI_NONGYAOINFO_URL;
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

                    /*当联网成功回调，这里的
                    @param response表示成功请求的数据，
                    @param id 区分http100和https101
                    */
                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "登录数据请求数据成功==" + response);
                        //解析response
                        pesticideprocessData(response);
                        Log.e("TAG", "ceshi" + pesticideresultBeanList.get(0).getPesticideName());
                        //此处设置为puthActivity.this不再出错，不用this

                        //网上防止泄露内存的一种方式获取context，在清单文件中的application和.java文件中有定义
                        // Context context = MyApplication.getInstance();
                        adapter = new PesticideviewAdapter(getActivity(), pesticideresultBeanList);
                        lvPesticide.setAdapter(adapter);
//监听事件设置在这里比较好
                        lvPesticide.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //这里所有默认的i都改成position   long l都改成id
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                //getcontext和getacitivity的区别是什么
                                Toast.makeText(getContext(), "position" + position, Toast.LENGTH_SHORT).show();

                                PesticideBean.PesticidesresultBean pesticidesresultBean = pesticideresultBeanList.get(position);
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
//                                startActivity(intent);
//                            }
                        });
                    }
//                    这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
//                    @Overridepublic void onError(Request request, Exception e){ }@Overridepublic void onResponse(String response){ }
                });
    }

    private void pesticideprocessData(String jsonarray) {
        PesticideBean pesticideBean = JSON.parseObject(jsonarray, PesticideBean.class);
        pesticideresultBeanList = pesticideBean.getPesticidesresult();
        //成功显示数据，数组对象其实已经赋值，我们要的就是对象啊，
        //知识用get位置来获取，当然不能直接打印所有
        //打印第一条种子数据的name
        Log.e(TAG, "数组显示用.get0" + pesticideresultBeanList.get(0).getPesticideName());

    }

    @Override
    public void onClick(View view) {
        if (view == ibSpecialistPesticideAddpush) {
            Intent intent = new Intent(getActivity(), PesticideAddActivity.class);
            startActivity(intent);
        }else if (view==ibAddpesticideBack){
            getActivity().finish();
        }
    }
}
