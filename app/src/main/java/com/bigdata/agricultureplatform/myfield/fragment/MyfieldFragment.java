package com.bigdata.agricultureplatform.myfield.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.app.MainActivity;
import com.bigdata.agricultureplatform.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
//我的田的fragment
public class MyfieldFragment extends BaseFragment {


    private List<Fragment> fragmentList;
    private Fragment tempFragment;
    private FieldmanageFragment fieldmanageFragment;
    public MyfieldinfoFragment myfieldinfoFragment;
    public EstimateFragment estimateFragment;

    @Bind(R.id.s_switch)
    SegmentTabLayout sswitch;
    @Bind(R.id.f_myfieldinfo)
    FrameLayout fMyfieldinfo;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_myfield, null);
        // sswitch = (SegmentTabLayout) view.findViewById(R.id.s_switch);
        //要么上面这种方法，只要butterknife绑定在fragment中，不会自动绑定，声明view即可
       ButterKnife.bind(this,view);

        return view;
    }

//当需要数据时候调用此方法   静态数据也是如此
    @Override
    public void initData() {
        super.initData();
        initFragment();
//增加投入产出估算，但是没有加入新的fragmnt
        String[] titles = {"田的管理", "田间管理","投产估算"};
        sswitch.setTabData(titles);
        sswitch.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(tempFragment, fragmentList.get(position));
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void switchFragment(Fragment fromFragment, Fragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.f_myfieldinfo, nextFragment, "fieldmanageFragment").commit();

                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void initFragment() {
        fragmentList = new ArrayList<Fragment>();
        myfieldinfoFragment = new MyfieldinfoFragment();
        fieldmanageFragment = new FieldmanageFragment();
        estimateFragment=new EstimateFragment();

        fragmentList.add(myfieldinfoFragment);
        fragmentList.add(fieldmanageFragment);
        fragmentList.add(estimateFragment);
        switchFragment(tempFragment, fragmentList.get(0));
    }
}
