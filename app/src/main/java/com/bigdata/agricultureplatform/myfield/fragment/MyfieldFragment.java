package com.bigdata.agricultureplatform.myfield.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigdata.agricultureplatform.base.BaseFragment;

import static android.content.ContentValues.TAG;

//我的田的fragment
public class MyfieldFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG,"我的田的fragmentui被初始化了");
        textView=new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"我的田的fragment数据被初始化了");
        textView.setText("我的田");
    }
}
