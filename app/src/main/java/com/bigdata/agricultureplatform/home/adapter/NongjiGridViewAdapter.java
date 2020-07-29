package com.bigdata.agricultureplatform.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.bean.RecommendlistinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;

import java.util.List;

/**
 * @description：$des$
 **/
public class NongjiGridViewAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final List<RecommendlistinfoBean.NongjiresultBean> datas;
    private final Context nongjicontext;

    public NongjiGridViewAdapter(Context mcontext, List<RecommendlistinfoBean.NongjiresultBean> nongjiresultBeanList) {
        //2 得到上下文和数据，并且创建参数
//        this.seedContext = seedcontext;
        //注释原来的context
        layoutInflater = LayoutInflater.from(mcontext);
        this.datas = nongjiresultBeanList;
        this.nongjicontext=mcontext;
    }




    @Override
    public int getCount() {
        return datas.size();
    }

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
        //6设置完viewholde之后定义出来
        ViewHolder viewHolder=null;
        //4.绑定试图
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_nongjipush_gridview, null);
            //7.然后把viewholder new出来
            viewHolder = new ViewHolder();
            //种子图片注释
            //viewHolder.iv_nongjipush_image = convertView.findViewById(R.id.iv_nongjipush_image);
            viewHolder.tv_nongjipush_name =  convertView.findViewById(R.id.tv_nongjipush_name);
            //种子类型先不要
            viewHolder.tv_nongjipush_time=convertView.findViewById(R.id.tv_nongjipush_time);
            //viewHolder.tv_nongjipush_type =  convertView.findViewById(R.id.tv_nongjipush_type);
            viewHolder.tv_nongji_context =  convertView.findViewById(R.id.tv_nongji_context);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //8根据位置得到对应的数据//这里的position是上边的position
        RecommendlistinfoBean.NongjiresultBean nongjiresultBean = datas.get(position);
        //使用Glide加载图片
//*****************************************************************************************
        //  1.  这个AgritechpushActivity中传来的mcontext上下文，有两个用处，一个是传值给了layouginflate用于加载下面的布局
        //  2.  为了能够用到上下文，刚才没有this.nongjicontext=mcontext,所以报错，用处是给本文中的上下文使用AgritechpushActivity中的
//*****************************************************************************************
        // 必须得判是否为空，只要有空值，就不执行就不会崩溃了，玩意数据库请求为空
        //这块图片就不要了，想要的话可以通过语句传值过来
//        if(Util.isOnMainThread()&&!TextUtils.isEmpty(Constants.ImageBASE_URL + seedresultBean.getSeedImage()) && seedcontext != null && viewHolder.iv_zhongzipush_image != null && viewHolder.iv_zhongzipush_image.getContext() != null)
//        {   Glide.with(nongjicontext).load(Constants.ImageBASE_URL + nongjiresultBean.getSeedImage())
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.iv_nongjipush_image);
//
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////
        //设置推送时间，用substr函数截取字符串（因为日期固定格式了）
        viewHolder.tv_nongjipush_time.setText(nongjiresultBean.getRecommendTime().substring(5,7)+"月");
        viewHolder.tv_nongjipush_name.setText(nongjiresultBean.getSeedName());
        //viewHolder.tv_zhongzipush_type.setText(nongjiresultBean.getSpecialistId());
        viewHolder.tv_nongji_context.setText(nongjiresultBean.getRecommendContent());
        return convertView;
    }
    static class ViewHolder{
        //种子图片先不要
//        ImageView iv_nongjipush_image;
        TextView tv_nongjipush_name;
        TextView tv_nongjipush_time;
        //种子类型先不要
//        TextView tv_nongjipush_type;
        TextView tv_nongji_context;
    }
}
