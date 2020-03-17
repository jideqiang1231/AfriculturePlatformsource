package com.bigdata.agricultureplatform.question.fragment;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigdata.agricultureplatform.base.BaseFragment;

import static android.content.ContentValues.TAG;

//问题反馈的的fragment
public class QuestionFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG,"问题反馈的的fragmentui被初始化了");
        textView=new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"问题反馈的的fragment数据被初始化了");
        textView.setText("问题反馈");
    }
}
