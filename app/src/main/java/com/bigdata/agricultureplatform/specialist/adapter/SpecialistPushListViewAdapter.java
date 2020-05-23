package com.bigdata.agricultureplatform.specialist.adapter;



import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.zhongzi.SpecialistseedpushActivity;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistseedlistBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;

import java.util.List;

/**
 * @description：$des$
 **/
public class SpecialistPushListViewAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private final List<SpecialistseedlistBean.SpecialistseedresultBean> datas;
    private  SpecialistseedpushActivity seedlistcontext;
    //在SpecialistseedpushActivity中
    //可以把SpecialistseedpushActivity specialistseedpushActivity改成mcontext，因为这就是默认得前边传来的context
    public SpecialistPushListViewAdapter(SpecialistseedpushActivity specialistseedpushActivity, List<SpecialistseedlistBean.SpecialistseedresultBean> specialistseedresultBeans) {
        layoutInflater = LayoutInflater.from(specialistseedpushActivity);
        this.datas = specialistseedresultBeans;
        this.seedlistcontext = specialistseedpushActivity;
    }


    //固定写法
    @Override
    public int getCount() {
        return datas.size();
    }

    //固定写法
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_specialistpushizhongzilist_listview,null);
            //7.然后把viewholder new出来
            /////////////////////////////////////////////////////////////////
            viewHolder = new ViewHolder();/////纪念再次出错，虽然viewholder已经设置为null了，但是想用还得new出来
            ////////////////////////////////////////////////////////////////
            viewHolder.ivSpecialistZhongzipushImage = convertView.findViewById(R.id.iv_specialist_zhongzipush_image);
            viewHolder.tvSpecialistZhongzipushName = convertView.findViewById(R.id.tv_specialist_zhongzipush_name);
            viewHolder.tvSpecialistZhongzipushType = convertView.findViewById(R.id.tv_specialist_zhongzipush_type);
            viewHolder.tvSpecialistZhongzipushContext = convertView.findViewById(R.id.tv_specialist_zhongzipush_context);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //view绑定完成后，开始弄数据
        //根据试图position获取数据
        SpecialistseedlistBean.SpecialistseedresultBean specialistseedresultBean = datas.get(position);

        //使用Glide加载图片
//*****************************************************************************************
        //  1.  这个pushActivity中传来的mcontext上下文，有两个用处，一个是传值给了layouginflate用于加载下面的布局
        //  2.  为了能够用到上下文，刚才没有this.seedcontext=mcontext,所以报错，用处是给本文中的上下文使用pushactivity中的
//*****************************************************************************************
        // 必须得判是否为空，只要有空值，就不执行就不会崩溃了，玩意数据库请求为空
        // 下边这个imgfebase_url已经到了http:服务器/zhongziinfo（file:G:/nongye_image/zhongzi/）
        if(Util.isOnMainThread()&&!TextUtils.isEmpty(Constants.ImageBASE_URL + specialistseedresultBean.getSeedImage()) && seedlistcontext != null && viewHolder.ivSpecialistZhongzipushImage != null && viewHolder.ivSpecialistZhongzipushImage.getContext() != null)
        {   Glide.with(seedlistcontext).load(Constants.ImageBASE_URL + specialistseedresultBean.getSeedImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.ivSpecialistZhongzipushImage);
            //连续吐司被注释：i详情可调式
//            Toast.makeText(seedcontext,                              //咱这里是为了toast咱请求的种子地址，shuidao/1.jpg 只是为了拼接它前边用/
//                    "http:服务器地址/config拦截地址zhongziinfo（拦截以后让其访问到file:G:/nongye_image/zhongzi/）/"
//                     +seedresultBean.getSeedImage(),Toast.LENGTH_SHORT).show();
            // Glide.with(context).load(Constants.ImageBASE_URL + seedresultBean.getSeedImage()).into(viewHolder.iv_zhongzipush_image);
        }
        viewHolder.tvSpecialistZhongzipushName.setText(specialistseedresultBean.getSeedName());
        viewHolder.tvSpecialistZhongzipushType.setText(specialistseedresultBean.getSeedType());
        viewHolder.tvSpecialistZhongzipushContext.setText(specialistseedresultBean.getSeedNote());
        return convertView;
    }

    //这里不可以用butterknife
    static class ViewHolder {
        ImageView ivSpecialistZhongzipushImage;
        TextView tvSpecialistZhongzipushName;
        TextView tvSpecialistZhongzipushType;
        TextView tvSpecialistZhongzipushContext;
    }
}
