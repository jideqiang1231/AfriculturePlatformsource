package com.bigdata.agricultureplatform.specialist.activity.nongzi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.MachineBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class MachineviewAdapter extends BaseAdapter {
    private final List<MachineBean.AgrimachineresultBean> datas;
    private final LayoutInflater layoutInflater;
    private final Context nongjicontext;


    public MachineviewAdapter(Context mcontext, List<MachineBean.AgrimachineresultBean> agrimachinesultBeanList) {
        layoutInflater = LayoutInflater.from(mcontext);
        this.datas = agrimachinesultBeanList;
        this.nongjicontext= mcontext;
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
            convertView = layoutInflater.inflate(R.layout.item_nongzi_generalview, null);
            //7.然后把viewholder new出来
            viewHolder = new ViewHolder();
            viewHolder.tvName =  convertView.findViewById(R.id.tv_name);
            viewHolder.tvAdapterCrop =  convertView.findViewById(R.id.tv_adapterCrop);
            viewHolder.tvFunction =  convertView.findViewById(R.id.tv_function);
            viewHolder.tvPushTime =  convertView.findViewById(R.id.tv_push_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //8根据位置得到对应的数据//这里的position是上边的position
        MachineBean.AgrimachineresultBean agrimachineresultBean = datas.get(position);
        //////////////////////////////////////////////////////////////////////////////
        //设置推送时间，用substr函数截取字符串（因为日期固定格式了）
        viewHolder.tvName.setText(agrimachineresultBean.getMachineName());
        viewHolder.tvAdapterCrop.setText(agrimachineresultBean.getMachineManufacturer());
        viewHolder.tvFunction.setText(agrimachineresultBean.getMachineFunction());
        viewHolder.tvPushTime.setText(agrimachineresultBean.getRecommendData());
        return convertView;
    }
    static class ViewHolder{
        TextView tvName;
        TextView tvAdapterCrop;
        TextView tvFunction;
        TextView tvPushTime;
    }
}

