package com.bigdata.agricultureplatform.specialist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.activity.SpeclistagritechpushActivity;
import com.bigdata.agricultureplatform.specialist.bean.SpecialistagritechlistBean;

import java.util.List;

/**
 * @description：$des$
 **/
public class SpecialistAgritechPushListViewAdapter extends BaseAdapter {


    private final List<SpecialistagritechlistBean.SpecialistnongjiresultBean> datas;
    private final SpeclistagritechpushActivity nongjicontext;
    private final LayoutInflater layoutInflater;

    public SpecialistAgritechPushListViewAdapter(SpeclistagritechpushActivity speclistagritechpushActivity, List<SpecialistagritechlistBean.SpecialistnongjiresultBean> specialistnongjiresultBeans) {
        this.datas = specialistnongjiresultBeans;
        this.nongjicontext = speclistagritechpushActivity;
        layoutInflater = LayoutInflater.from(speclistagritechpushActivity);
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
            convertView = layoutInflater.inflate(R.layout.item_specialistpush_nongjilist_listview, null);
            //7.然后把viewholder new出来
            /////////////////////////////////////////////////////////////////
            viewHolder = new ViewHolder();/////纪念再次出错，虽然viewholder已经设置为null了，但是想用还得new出来
            ////////////////////////////////////////////////////////////////
            //viewHolder.ivSpecialistZhongzipushImage = convertView.findViewById(R.id.iv_specialist_zhongzipush_image);
            viewHolder.tvSpecialistNongjipushName = convertView.findViewById(R.id.tv_specialist_nongjipush_name);
            //viewHolder.tvSpecialistZhongzipushType = convertView.findViewById(R.id.tv_specialist_zhongzipush_type);
            viewHolder.tvSpecialistNongjipushContext = convertView.findViewById(R.id.tv_specialist_nongjipush_context);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //view绑定完成后，开始弄数据 根据试图position获取数据
        SpecialistagritechlistBean.SpecialistnongjiresultBean specialistnongjiresultBean = datas.get(position);

        //不再加载图片
//        if(Util.isOnMainThread()&&!TextUtils.isEmpty(Constants.ImageBASE_URL + specialistseedresultBean.getSeedImage()) && seedlistcontext != null && viewHolder.ivSpecialistZhongzipushImage != null && viewHolder.ivSpecialistZhongzipushImage.getContext() != null)
//        {   Glide.with(seedlistcontext).load(Constants.ImageBASE_URL + specialistseedresultBean.getSeedImage())
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.ivSpecialistZhongzipushImage);
//        }
        viewHolder.tvSpecialistNongjipushName.setText(specialistnongjiresultBean.getSeedName());
        //    viewHolder.tvSpecialistZhongzipushType.setText(specialistseedresultBean.getSeedType());
        viewHolder.tvSpecialistNongjipushContext.setText(specialistnongjiresultBean.getRecommendContent());
        return convertView;
    }

    //这里不可以用butterknife
    static class ViewHolder {
        //  ImageView ivSpecialistZhongzipushImage;
        TextView tvSpecialistNongjipushName;
        // TextView tvSpecialistZhongzipushType;
        TextView tvSpecialistNongjipushContext;
    }
}
