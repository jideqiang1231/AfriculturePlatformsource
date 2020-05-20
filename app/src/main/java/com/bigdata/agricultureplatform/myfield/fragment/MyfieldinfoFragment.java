package com.bigdata.agricultureplatform.myfield.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

//田的信息模块
public class MyfieldinfoFragment extends BaseFragment {
    @Bind(R.id.b_myfieldinto_update)
    Button bMyfieldintoUpdate;
    @Bind(R.id.myfield_username)
    TextView myfieldUsername;
    @Bind(R.id.et_user_sumacres)
    EditText etUserSumacres;
    @Bind(R.id.et_user_adress)
    EditText etUserAdress;
    @Bind(R.id.et_user_croptyes)
    EditText etUserCroptyes;
    @Bind(R.id.et_user_cropnames)
    EditText etUserCropnames;
    @Bind(R.id.et_user_cropacre)
    EditText etUserCropacre;
    //缓存中的用户名信息
    private String username;
    private SharedPreferences sp;
    private String userfieldsumacres;
    private String userfieldadress;
    private String usercropstype;
    private String usercropsname;
    private String usercropsacre;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_myfieldinfo, null);
        ButterKnife.bind(this, view);
        return view;
    }

    //加载数据，继承了basefragment。
    @Override
    public void initData() {
        super.initData();

        //fragment与avitivity调用sp对象有所区别，必须需要上下文，用到getActivity方法
        //使用sharepreferences，获取对象
        sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        username = sp.getString("username", null);
        if (username == null) {
            Log.e(TAG, "并没有从缓存中获取到用户信息");
        } else {
            myfieldUsername.setText(username);
        }

        //读取缓存中的用户田得信息
        userfieldsumacres=sp.getString("usersumacres",null);
        userfieldadress=sp.getString("useradress",null);
        usercropstype=sp.getString("usercroptypes",null);
        usercropsname=sp.getString("usercropnames",null);
        usercropsacre=sp.getString("usercropacre",null);
        if (userfieldsumacres == null||userfieldadress == null||usercropstype == null||usercropsname == null||usercropsacre == null) {
            Log.e(TAG, "并没有田得信息请输入");
       } else {
            etUserSumacres.setText(userfieldsumacres);
            etUserAdress.setText(userfieldadress);
            etUserCroptyes.setText(usercropstype);
            etUserCropnames.setText(usercropsname);
            etUserCropacre.setText(usercropsacre);
        }



    }




}
