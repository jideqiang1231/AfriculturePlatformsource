package com.bigdata.agricultureplatform.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;

import butterknife.Bind;
import butterknife.ButterKnife;

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
//        ivSeedinfoImage.setImageBitmap(seedimage);
        if(Util.isOnMainThread()&&!TextUtils.isEmpty(Constants.ImageBASE_URL + seedimage) && this != null && ivSeedinfoImage != null && ivSeedinfoImage.getContext() != null)
        {   Glide.with(this).load(Constants.ImageBASE_URL + seedimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivSeedinfoImage);
            //连续吐司被注释：i详情可调式
//            Toast.makeText(seedcontext,                              //咱这里是为了toast咱请求的种子地址，shuidao/1.jpg 只是为了拼接它前边用/
//                    "http:服务器地址/config拦截地址zhongziinfo（拦截以后让其访问到file:G:/nongye_image/zhongzi/）/"
//                            +seedresultBean.getSeedImage(),Toast.LENGTH_SHORT).show();
            // Glide.with(context).load(Constants.ImageBASE_URL + seedresultBean.getSeedImage()).into(viewHolder.iv_zhongzipush_image);
        }
        tvSeedinfoName.setText(seedname);
        tvSeedinfoType.setText(seedtype);
        tvSeedinfoPrice.setText(seedprice);
        tvSeedinfoIntroduce.setText(seedintroduce);
        tvSeedinfoPlantmethod.setText(seedplantmethod);
        tvSeedinfoPlantarea.setText(seedplantarea);
        tvSeedinfoPhone.setText(seedphone);
    }

    @Override
    public void onClick(View view) {
        if (view == ibSeedinfoBack) {
            finish();
        }
    }

}
