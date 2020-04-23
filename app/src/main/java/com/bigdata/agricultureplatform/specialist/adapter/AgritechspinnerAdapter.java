package com.bigdata.agricultureplatform.specialist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.SpecialistaddagritechpushActivity;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistseedlistBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class AgritechspinnerAdapter extends BaseAdapter {
    private final Context agritechspinnerContext;
    private final List<SpecialistseedlistBean.SpecialistseedresultBean> datas;

    public AgritechspinnerAdapter(Context mcontext, List<SpecialistseedlistBean.SpecialistseedresultBean> includeseednamelist) {
        this.datas=includeseednamelist;
        this.agritechspinnerContext=mcontext;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(agritechspinnerContext);
        convertView=_LayoutInflater.inflate(R.layout.item_agritech_spinner, null);
        if(convertView!=null) {
            TextView spinner_item_text=convertView.findViewById(R.id.item_agritech_spinner_text);
            spinner_item_text.setText(datas.get(position).getSeedName());
        }
        return convertView;
    }
}
