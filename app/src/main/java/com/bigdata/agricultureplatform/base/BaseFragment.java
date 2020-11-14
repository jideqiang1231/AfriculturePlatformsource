package com.bigdata.agricultureplatform.base;

//视频中导入的v4包为了适应更低的版本，但是咱没有，只有这两个随便选了一个
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/*首页HomeFragment
我的田MyfieldFragment
推荐RecommendFragment
问题反馈QuestionFragment
个人中心UserFragment
都要继承该类
*/
public abstract class BaseFragment extends Fragment {
    /*孩子要用，得到上下文*/
    protected Context mContext;

    /*当该类被系统创建的时候回调*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //把抽象类由孩子实现
    //每个页面实现不同的fragment，实现五个不同的布局
    public abstract View initView();

    //当每一个fragment被创建的时候调用这个方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    /*
    当子类需要联网请求数据的时候，可以重写该方法，可以在这里边进行联网请求
    好处是因为这个方法晚于oncreatview执行，先有view后有数据
    * */
    public void initData() {
    }

}
