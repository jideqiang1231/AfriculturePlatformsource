package com.bigdata.agricultureplatform.user.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

//个人中心的fragment
public class UserFragment extends BaseFragment {

    @Bind(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;

    @Bind(R.id.tv_all_order)
    TextView tvAllOrder;
    @Bind(R.id.tv_user_pay)
    TextView tvUserPay;
    @Bind(R.id.tv_user_receive)
    TextView tvUserReceive;
    @Bind(R.id.tv_user_finish)
    TextView tvUserFinish;
    @Bind(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @Bind(R.id.tv_user_history)
    TextView tvUserHistory;
    @Bind(R.id.tv_user_collect)
    TextView tvUserCollect;
    @Bind(R.id.tv_user_alter)
    TextView tvUserAlter;
    @Bind(R.id.tv_user_variety)
    TextView tvUserVariety;
    @Bind(R.id.tv_user_prize)
    TextView tvUserPrize;
    @Bind(R.id.tv_user_expert)
    TextView tvUserExpert;
    @Bind(R.id.tv_user_help)
    TextView tvUserHelp;
    @Bind(R.id.tv_user_feedback)
    TextView tvUserFeedback;
    @Bind(R.id.scrollview)
    ScrollView scrollview;
    @Bind(R.id.tv_usercenter)
    TextView tvUsercenter;
    @Bind(R.id.ib_user_setting)
    ImageButton ibUserSetting;
    @Bind(R.id.ib_user_message)
    ImageButton ibUserMessage;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    //接受缓存
    private SharedPreferences sp;
    private String username;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        //不加这一句，呵呵，必须报错，
        //fragment中使用butterKnife必须哟啊绑定到这个视图中
        ButterKnife.bind(this, view);
         // 慎用alpha崩掉了
        //tvUsercenter.setAlpha(0);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        //设置用户头像
        ibUserIconAvator.setImageResource(R.drawable.usericon_man);
        //读取缓存中的用户信息
        sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        username = sp.getString("username", null);
        if (username == null) {
            Log.e(TAG, "并没有从缓存中获取到用户信息");
        } else {
            tvUsername.setText(username);
        }
    }
}
