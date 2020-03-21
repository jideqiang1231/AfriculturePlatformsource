package com.bigdata.agricultureplatform.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.activity.PushActivity;
import com.bigdata.agricultureplatform.home.bean.SeedinfoBean;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @description：$des$
 **/
public class SeedGridViewAdapter extends BaseAdapter {
//    private final Context seedContext;
    private final List<SeedinfoBean.SeedresultBean> datas;
    private LayoutInflater layoutInflater;

    public SeedGridViewAdapter(Context seedcontext, List<SeedinfoBean.SeedresultBean> seedresultBeanList) {
        //2 得到上下文和数据，并且创建参数
//        this.seedContext = seedcontext;
        //注释原来的context
        layoutInflater = LayoutInflater.from(seedcontext);
        this.datas = seedresultBeanList;
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
        ViewHolder viewHolder=null;
        //4.绑定试图
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_zhongzipush_gridview, null);
            //7.然后把viewholder new出来
            viewHolder = new ViewHolder();
            viewHolder.iv_zhongzipush_image = (ImageView)convertView.findViewById(R.id.iv_zhongzipush_image);
            viewHolder.tv_zhongzipush_name = (TextView) convertView.findViewById(R.id.tv_zhongzipush_name);
            viewHolder.tv_zhongzipush_type = (TextView) convertView.findViewById(R.id.tv_zhongzipush_name);
            viewHolder.tv_zhongzi_context = (TextView) convertView.findViewById(R.id.tv_zhongzi_context);
//            viewHolder.tvZhongzipushType = convertView.findViewById(R.id.tv_zhongzicontext);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //8根据位置得到对应的数据//这里的position是上边的position
        SeedinfoBean.SeedresultBean seedresultBean = datas.get(position);
        //使用Glide加载图片
//        Glide.with(mcontext).load(Constants.ImageBASE_URL+seedresultBean.getSeedImage()).into(viewHolder.ivZhongzipushImage);
        viewHolder.tv_zhongzipush_name.setText(seedresultBean.getSeedName());
        viewHolder.tv_zhongzipush_type.setText(seedresultBean.getSeedType());
        viewHolder.tv_zhongzi_context.setText(seedresultBean.getSeedNote());
        return convertView;

    }


    //注释掉，用下边的butterknife试试
//    5.创建viewhoder实例化子视图
    static class ViewHolder{
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
