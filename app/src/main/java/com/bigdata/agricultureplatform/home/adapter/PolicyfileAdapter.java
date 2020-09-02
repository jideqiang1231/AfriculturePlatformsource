package com.bigdata.agricultureplatform.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.bean.PolicyfileBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class PolicyfileAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final List<PolicyfileBean.PolicyfileresultBean> datas;
    private final Context seedlistcontext;

    public PolicyfileAdapter(Context mcontext, List<PolicyfileBean.PolicyfileresultBean> specialistseedresultBeans) {
        layoutInflater = LayoutInflater.from(mcontext);
        this.datas = specialistseedresultBeans;
        this.seedlistcontext = mcontext;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_general, null);
            viewHolder = new ViewHolder();

            viewHolder.tvcontent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PolicyfileBean.PolicyfileresultBean specialistseedresultBean = datas.get(position);
//        if(Util.isOnMainThread()&&!TextUtils.isEmpty(Constants.ImageBASE_URL + specialistseedresultBean.getSeedImage()) && seedlistcontext != null && viewHolder.ivSpecialistZhongzipushImage != null && viewHolder.ivSpecialistZhongzipushImage.getContext() != null)
//        {   Glide.with(seedlistcontext).load(Constants.ImageBASE_URL + specialistseedresultBean.getSeedImage())
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.ivSpecialistZhongzipushImage);
//
//        }
        viewHolder.tvcontent.setText(specialistseedresultBean.getPolicyfileLocation());
        return convertView;
    }

    static class ViewHolder {

        TextView tvcontent;
    }
}