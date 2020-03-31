package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistloginBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SeedinfoActivity extends Activity implements View.OnClickListener {


    @Bind(R.id.ib_seedinfo_back)
    ImageButton ibSeedinfoBack;
    @Bind(R.id.iv_seedinfo_image)
    ImageView ivSeedinfoImage;
    @Bind(R.id.tv_seedinfo_name)
    TextView tvSeedinfoName;
    @Bind(R.id.tv_seedinfo_type)
    TextView tvSeedinfoType;
    @Bind(R.id.tv_seedinfo_price)
    TextView tvSeedinfoPrice;

    @Bind(R.id.tv_seedinfo_plantmethod)
    TextView tvSeedinfoPlantmethod;
    @Bind(R.id.tv_seedinfo_plantarea)
    TextView tvSeedinfoPlantarea;
    @Bind(R.id.tv_seedinfo_phone)
    TextView tvSeedinfoPhone;
    @Bind(R.id.tv_seedinfo_introduce)
    TextView tvSeedinfoIntroduce;
    @Bind(R.id.tv_top_type_info)
    TextView tvTopTypeInfo;
    @Bind(R.id.tv_seedinfo_specialist_type)
    TextView tvSeedinfoSpecialistType;
    @Bind(R.id.tv_seedinfo_specialist_name)
    TextView tvSeedinfoSpecialistName;
    @Bind(R.id.tv_seedinfo_specialist_phone)
    TextView tvSeedinfoSpecialistPhone;
    @Bind(R.id.tv_seedinfo_specialist_introduce)
    TextView tvSeedinfoSpecialistIntroduce;
    @Bind(R.id.tv_seedinfo_specialist_address)
    TextView tvSeedinfoSpecialistAddress;
    private SpecialistloginBean specialistloginBean;
    private SpecialistloginBean.SpecialistloginresultBean specialistloginresultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seedinfo);
        ButterKnife.bind(this);
        ibSeedinfoBack.setOnClickListener(this);
        Intent intent = getIntent();

        String seedimage = intent.getStringExtra("图片信息");
        String seedname = intent.getStringExtra("种子名称");
        String seedtype = intent.getStringExtra("种子类型");
        String seedintroduce = intent.getStringExtra("种子详情");
        String seedplantmethod = intent.getStringExtra("种植方式");
        String seedprice = intent.getStringExtra("种子价格");
        String seedplantarea = intent.getStringExtra("适种区域");
        String seedphone = intent.getStringExtra("联系电话");
        Integer specialistId = intent.getIntExtra("专家id", 0);
//        ivSeedinfoImage.setImageBitmap(seedimage);
        if (Util.isOnMainThread() && !TextUtils.isEmpty(Constants.ImageBASE_URL + seedimage) && this != null && ivSeedinfoImage != null && ivSeedinfoImage.getContext() != null) {
            Glide.with(this).load(Constants.ImageBASE_URL + seedimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivSeedinfoImage);
            //连续吐司被注释：i详情可调式
//            Toast.makeText(seedcontext,                              //咱这里是为了toast咱请求的种子地址，shuidao/1.jpg 只是为了拼接它前边用/
//                    "http:服务器地址/config拦截地址zhongziinfo（拦截以后让其访问到file:G:/nongye_image/zhongzi/）/"
//                            +seedresultBean.getSeedImage(),Toast.LENGTH_SHORT).show();
            // Glide.with(context).load(Constants.ImageBASE_URL + seedresultBean.getSeedImage()).into(viewHolder.iv_zhongzipush_image);
        }
        tvTopTypeInfo.setText(seedname);
        tvSeedinfoName.setText(seedname);
        tvSeedinfoType.setText(seedtype);
        tvSeedinfoPrice.setText(seedprice);
        tvSeedinfoIntroduce.setText(seedintroduce);
        tvSeedinfoPlantmethod.setText(seedplantmethod);
        tvSeedinfoPlantarea.setText(seedplantarea);
        tvSeedinfoPhone.setText(seedphone);
        //注意int的id造成的java.lang.RuntimeException+Resources$NotFoundException:
        Toast.makeText(this, String.valueOf(specialistId), Toast.LENGTH_SHORT).show();
        //在种子详情信息中加入专家的基本信息：
        addSpecialistinfoinseedinfo(specialistId);
    }

    //抽取方法，增加专家的基本信息
    private void addSpecialistinfoinseedinfo(Integer specialistId) {
        String url = Constants.SPECIALISTINFOFORSEEDINFO;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("specialistId", String.valueOf(specialistId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "根据种子的外键信息请求专家基本信息数据失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //请求成功打印
                        Log.e(TAG, "根据种子的外键信息请求专家基本信息成功==" + response);
                        //22222222222222222222222222请求完数据，需要解析
                        //抽出出来一个方法，传入response
                        loginprocessData(response);
                        if (specialistloginBean.getMsg().equals("获取专家信息失败")) {
                            Toast.makeText(getBaseContext(), "专家信息这块没有根据种子的外键得到", Toast.LENGTH_LONG).show();
                        } else {
//在这里往布局里边设置内容：专家的基本信息：
                            tvSeedinfoSpecialistType.setText(specialistloginresultBean.getSpecialistType());
                            tvSeedinfoSpecialistName.setText(specialistloginresultBean.getSpecialistName());
                            tvSeedinfoSpecialistIntroduce.setText(specialistloginresultBean.getSpecialistInstructions());
                            tvSeedinfoSpecialistPhone.setText(specialistloginresultBean.getSpecialistPhone());
                            tvSeedinfoSpecialistAddress.setText(specialistloginresultBean.getSpecialistAddress());
                        }

                    }
                });
    }

    private void loginprocessData(String json) {
        specialistloginBean = JSON.parseObject(json, SpecialistloginBean.class);
        specialistloginresultBean = specialistloginBean.getSpecialistloginresult();
        Log.e(TAG, specialistloginBean.getMsg());
    }

    @Override
    public void onClick(View view) {
        if (view == ibSeedinfoBack) {
            finish();
        }
    }

}
