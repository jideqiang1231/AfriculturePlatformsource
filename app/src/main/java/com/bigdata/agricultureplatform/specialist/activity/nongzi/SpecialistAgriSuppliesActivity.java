package com.bigdata.agricultureplatform.specialist.activity.nongzi;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threefragment.FertilizerFragment;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threefragment.MachineFragment;
import com.bigdata.agricultureplatform.specialist.activity.nongzi.threefragment.PesticideFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialistAgriSuppliesActivity extends FragmentActivity {

    @Bind(R.id.rb_nongji)
    RadioButton rbNongji;
    @Bind(R.id.rb_nongyao)
    RadioButton rbNongyao;
    @Bind(R.id.rb_huafei)
    RadioButton rbHuafei;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    //装多个fragment的集合
    private ArrayList<BaseFragment> fragments;
    //默认位置是0
    private int position=0;
    //就是当前缓存的fragment，就是上次显示的fragment
    private Fragment tempFragment;

    //用于接受login的用户名
    // private String userName;
    private static final String TAG ="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_agri_supplies);
        ButterKnife.bind(this);
        initFragment();
        //设置videogroup的监听
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_nongji://农机
                        position=0;
                        break;
                    case R.id.rb_nongyao://农药
                        position=1;
                        //像我的田传递用户信息username
//                        MyfieldinfoFragment myfieldinfoFragment = new MyfieldinfoFragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("username",userName);
//                        myfieldinfoFragment.setArguments(bundle);//数据传递到fragment中
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.frameLayout,myfieldinfoFragment);
//                        fragmentTransaction.commit();
                        break;
                    case R.id.rb_huafei://化肥
                        position=2;
                        break;

                    default:
                        position=0;
                        break;
                }
//                根据位置得到对应的fragment；//这里的fragment激素hi当前点击的fragment
                BaseFragment baseFragment=getFragment(position);
                //第一个参数是上一次显示的fragment，第二个是当前的

                switchFragment(tempFragment,baseFragment);
            }
        });
        rgMain.check(R.id.rb_nongji);
    }

    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new MachineFragment());
        fragments.add(new PesticideFragment());
        fragments.add(new FertilizerFragment());

    }
    private BaseFragment getFragment(int position){
        if (fragments!=null&&fragments.size()>0){
            BaseFragment baseFragment=fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    //切换fragment
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
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
}