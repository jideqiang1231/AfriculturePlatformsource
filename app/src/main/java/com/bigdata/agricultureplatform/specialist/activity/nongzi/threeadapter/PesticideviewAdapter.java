package com.bigdata.agricultureplatform.specialist.activity.nongzi.threeadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.bean.PesticideBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class PesticideviewAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private final List<PesticideBean.PesticidesresultBean> datas;
    private final Context nongyaocontext;

    public PesticideviewAdapter(Context mcontext, List<PesticideBean.PesticidesresultBean> pesticideresultBeanList) {
        layoutInflater = LayoutInflater.from(mcontext);
        this.datas = pesticideresultBeanList;
        this.nongyaocontext= mcontext;
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
        ViewHolder viewHolder=null;
        if (convertView == null) {
            //三个fragment共用的item_nongzi_generaliview
            convertView = layoutInflater.inflate(R.layout.item_nongzi_generalview, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName =  convertView.findViewById(R.id.tv_name);
            viewHolder.tvAdapterCrop =  convertView.findViewById(R.id.tv_adapterCrop);
            viewHolder.tvFunction =  convertView.findViewById(R.id.tv_function);
            viewHolder.tvPushTime =  convertView.findViewById(R.id.tv_push_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PesticideBean.PesticidesresultBean pesticidesresultBean = datas.get(position);
        viewHolder.tvName.setText(pesticidesresultBean.getPesticideName());
        viewHolder.tvAdapterCrop.setText(pesticidesresultBean.getPesticideModeratecrop());
        viewHolder.tvFunction.setText(pesticidesresultBean.getPesticideInstructions());
        viewHolder.tvPushTime.setText(pesticidesresultBean.getRecommendData());
        return convertView;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvAdapterCrop;
        TextView tvFunction;
        TextView tvPushTime;
    }
}