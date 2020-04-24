package com.bigdata.agricultureplatform.myfield.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;

import java.util.ResourceBundle;

import butterknife.Bind;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

//田的信息模块
public class MyfieldinfoFragment extends BaseFragment {

    @Bind(R.id.b_myfieldinto_update)
    Button bMyfieldintoUpdate;
    @Bind(R.id.myfield_username)
    TextView myfieldUsername;
    //缓存中的用户名信息
    private String username;
    private SharedPreferences sp;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_myfieldinfo, null);
        ButterKnife.bind(this, view);
//        //得到数据
//        Bundle bundle =MyfieldinfoFragment.this.getArguments();//得到从Activity传来的数据
//        String mess = null;
//        if(bundle!=null){
//            mess = bundle.getString("username");
//        }
//        myfieldUsername.setText(mess);
////        Log.e(TAG,mess);
        return view;
//              接受Mainactivity中从login根目录下传来的用户登录信息
//             Bundle bundle=this.getArguments();
//             username=bundle.getString("username");
//             myfieldUsername.setText(username);

    }


    //加载数据，继承了basefragment。
    @Override
    public void initData() {
        super.initData();
        //fragment与avitivity调用sp对象有所区别，必须需要上下文，用到getActivity方法
        sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        username=sp.getString("username",null);
        if (username==null){
            Log.e(TAG,"并没有从缓存中获取到用户信息");
        }else {
            myfieldUsername.setText(username);
        }
    }
}
