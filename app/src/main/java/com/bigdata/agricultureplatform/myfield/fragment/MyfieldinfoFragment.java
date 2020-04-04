package com.bigdata.agricultureplatform.myfield.fragment;

import android.view.View;
import android.widget.Button;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

//田的信息模块
public class MyfieldinfoFragment extends BaseFragment {

    @Bind(R.id.b_myfieldinto_update)
    Button bMyfieldintoUpdate;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_myfieldinfo, null);
        ButterKnife.bind(this,view);

        return view;
    }



    //加载数据，继承了basefragment。
    @Override
    public void initData() {
        super.initData();
    }

}
