package com.bigdata.agricultureplatform.recommend.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigdata.agricultureplatform.base.BaseFragment;

import static android.content.ContentValues.TAG;

//推荐的fragment
public class RecommendFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG,"推荐的fragmentui被初始化了");
        textView=new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"推荐的fragment数据被初始化了");
        textView.setText("推荐");
    }
}
