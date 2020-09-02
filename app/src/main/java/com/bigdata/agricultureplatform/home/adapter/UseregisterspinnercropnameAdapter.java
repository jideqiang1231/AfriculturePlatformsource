package com.bigdata.agricultureplatform.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.activity.RegisterActivity;
import com.bigdata.agricultureplatform.home.bean.seednameBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class UseregisterspinnercropnameAdapter extends BaseAdapter {
    private final List<seednameBean.SeednameresultBean> datas;
    private final Context registerspinnerseedNameContext;

    public UseregisterspinnercropnameAdapter(Context mcontext, List<seednameBean.SeednameresultBean> seednamelist) {
        this.datas = seednamelist;
        this.registerspinnerseedNameContext = mcontext;
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
        LayoutInflater _LayoutInflater = LayoutInflater.from(registerspinnerseedNameContext);
        convertView = _LayoutInflater.inflate(R.layout.item_registercropstype_spinner, null);
        if (convertView != null) {
            TextView spinner_item_text = convertView.findViewById(R.id.item_registercropstype_spinner_text);
            spinner_item_text.setText(datas.get(position).getSeedName());
        }
        return convertView;
    }
}
