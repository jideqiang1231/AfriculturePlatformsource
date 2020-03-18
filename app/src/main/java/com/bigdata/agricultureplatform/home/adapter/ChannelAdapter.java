package com.bigdata.agricultureplatform.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.bean.ResultBeanData;

/**
 * @description：$des$
 **/
//上边这块
public class ChannelAdapter extends BaseAdapter {
    private final Context mcontext;
    private final String userName;

    public ChannelAdapter(Context mcontext, String userName) {
        this.mcontext=mcontext;
        this.userName=userName;
    }

    @Override
    //这里咱么没有集合所以手动设置把，试试8
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    //这部分参数不对，改了，position为1，view=convertview parent也改了
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建下边的viewholder；
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = View.inflate(mcontext,R.layout.item_channel,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon=convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title=convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        viewHolder.tv_title.setText(userName);
        return convertView;
    }
    //此时做一个静态的viewholder
    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
