package com.bigdata.agricultureplatform.myfield.fragment;


import android.view.View;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;
//田的管理模块
public class FieldmanageFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_fieldmanage, null);
        return view;
    }

}