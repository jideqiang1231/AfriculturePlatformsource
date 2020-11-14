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
import com.bigdata.agricultureplatform.home.bean.SeedinfoBean;
import com.bigdata.agricultureplatform.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;

import java.util.List;

/**
 * @description：$des$
 **/
public class SeedGridViewAdapter extends BaseAdapter {
    //    private final Context seedContext;
    private final List<SeedinfoBean.SeedresultBean> datas;
    private LayoutInflater layoutInflater;
    private Context seedcontext;

    public SeedGridViewAdapter(Context mcontext, List<SeedinfoBean.SeedresultBean> seedresultBeanList) {
        //2 得到上下文和数据，并且创建参数
//        this.seedContext = seedcontext;
        //注释原来的context
        layoutInflater = LayoutInflater.from(mcontext);
        this.datas = seedresultBeanList;
        this.seedcontext = mcontext;
        //mLayoutInflater= LayoutInflater.from(seedContext);
    }

    @Override
    public int getCount() {
        //3.设置固定写法，获取数据的长度
        return datas.size();
    }

    //注意这里的position，它初始的字母设置的是i，记得改，后边获取数据要用的
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    //注意这里的position，它初始的字母设置的是i，记得改，后边获取数据要用的
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //注意这里的position，它初始的字母设置的是i，记得改，后边获取数据要用的
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //6设置完viewholde之后定义出来
        ViewHolder viewHolder = null;
        //4.绑定试图
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_zhongzipush_gridview, null);
            //7.然后把viewholder new出来
            viewHolder = new ViewHolder();
            viewHolder.iv_zhongzipush_image = convertView.findViewById(R.id.iv_zhongzipush_image);
            viewHolder.tv_zhongzipush_name = convertView.findViewById(R.id.tv_zhongzipush_name);
            viewHolder.tv_zhongzipush_type = convertView.findViewById(R.id.tv_zhongzipush_type);
            viewHolder.tv_zhongzi_context = convertView.findViewById(R.id.tv_zhongzi_context);
//            viewHolder.tvZhongzipushType = convertView.findViewById(R.id.tv_zhongzicontext);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //8根据位置得到对应的数据//这里的position是上边的position
        SeedinfoBean.SeedresultBean seedresultBean = datas.get(position);
        //使用Glide加载图片
//*****************************************************************************************
        //  1.  这个pushActivity中传来的mcontext上下文，有两个用处，一个是传值给了layouginflate用于加载下面的布局
        //  2.  为了能够用到上下文，刚才没有this.seedcontext=mcontext,所以报错，用处是给本文中的上下文使用pushactivity中的
//*****************************************************************************************
        // 必须得判是否为空，只要有空值，就不执行就不会崩溃了，玩意数据库请求为空
        // 下边这个imgfebase_url已经到了http:服务器/zhongziinfo（file:G:/nongye_image/zhongzi/）
        if (Util.isOnMainThread() && !TextUtils.isEmpty(Constants.ImageBASE_URL + seedresultBean.getSeedImage()) && seedcontext != null && viewHolder.iv_zhongzipush_image != null && viewHolder.iv_zhongzipush_image.getContext() != null) {
            Glide.with(seedcontext).load(Constants.ImageBASE_URL + seedresultBean.getSeedImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.iv_zhongzipush_image);
            //连续吐司被注释：i详情可调式
//            Toast.makeText(seedcontext,                              //咱这里是为了toast咱请求的种子地址，shuidao/1.jpg 只是为了拼接它前边用/
//                    "http:服务器地址/config拦截地址zhongziinfo（拦截以后让其访问到file:G:/nongye_image/zhongzi/）/"
//                            +seedresultBean.getSeedImage(),Toast.LENGTH_SHORT).show();
            // Glide.with(context).load(Constants.ImageBASE_URL + seedresultBean.getSeedImage()).into(viewHolder.iv_zhongzipush_image);
        }
        viewHolder.tv_zhongzipush_name.setText(seedresultBean.getSeedName());
        viewHolder.tv_zhongzipush_type.setText("(" + seedresultBean.getSeedType() + ")");
        viewHolder.tv_zhongzi_context.setText(seedresultBean.getSeedNote());
        return convertView;

    }


    //注释掉，用下边的butterknife试试
    // 5.创建viewhoder实例化子视图
    static class ViewHolder {
        ImageView iv_zhongzipush_image;
        TextView tv_zhongzipush_name;
        TextView tv_zhongzipush_type;
        TextView tv_zhongzi_context;
    }

//    static class ViewHolder {
//        @Bind(R.id.iv_zhongzipush_image)
//        ImageView ivZhongzipushImage;
//        @Bind(R.id.iv_zhongzipush_name)
//        TextView ivZhongzipushName;
//        @Bind(R.id.iv_zhongzipush_type)
//        TextView ivZhongzipushType;
//        @Bind(R.id.tv_zhongzicontext)
//        TextView tvZhongzicontext;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }

}
