package com.bigdata.agricultureplatform.specialist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.adapter.ZhengceGridViewAdapter;
import com.bigdata.agricultureplatform.specialist.activity.zhengce.SpecialistpolicypushActivity;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistpolicylistBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class SpecialistPolicyPushListViewAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private final List<SpecialistpolicylistBean.SpecialistseedresultBean> datas;
    private final Context policylist;

    public SpecialistPolicyPushListViewAdapter(Context mcontext, List<SpecialistpolicylistBean.SpecialistseedresultBean> specialistpolivy_resultBeans) {
        layoutInflater = LayoutInflater.from(mcontext);
        this.datas=specialistpolivy_resultBeans;
        this.policylist=mcontext;
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
        /////////////////////////////////////////////////////////////////////
        //这里绑定的视图都是农技的一个xml然后和ZhengceGridViewAdapter一样的
        /////////////////////////////////////////////////////////////////////
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_nongjipush_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_nongjipush_name = convertView.findViewById(R.id.tv_nongjipush_name);
            viewHolder.tv_nongjipush_time = convertView.findViewById(R.id.tv_nongjipush_time);
            viewHolder.tv_nongji_context = convertView.findViewById(R.id.tv_nongji_context);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SpecialistpolicylistBean.SpecialistseedresultBean specialistseedresultBean = datas.get(position);
        viewHolder.tv_nongjipush_time.setText(specialistseedresultBean.getPolicyTime());
        viewHolder.tv_nongjipush_name.setText(specialistseedresultBean.getPolicyTitle());
        viewHolder.tv_nongji_context.setText(specialistseedresultBean.getPolicyContent());
        return convertView;
    }
    //这里不可以用butterknife
    static class ViewHolder {
        TextView tv_nongjipush_name;
        TextView tv_nongjipush_time;
        TextView tv_nongji_context;
    }
}
