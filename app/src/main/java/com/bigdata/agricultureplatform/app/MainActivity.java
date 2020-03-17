package com.bigdata.agricultureplatform.app;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;
import com.bigdata.agricultureplatform.home.fragment.HomeFragment;
import com.bigdata.agricultureplatform.myfield.fragment.MyfieldFragment;
import com.bigdata.agricultureplatform.question.fragment.QuestionFragment;
import com.bigdata.agricultureplatform.recommend.fragment.RecommendFragment;
import com.bigdata.agricultureplatform.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    //装多个fragment的集合
    private ArrayList<BaseFragment> fragments;
    //默认位置是0
    private int position=0;
    //就是当前缓存的fragment，就是上次显示的fragment
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //把fragment都加到主界面集合中初始化
        initFragment();
        //设置videogroup的监听
        initListener();

    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home://主页
                        position=0;
                        break;
                    case R.id.rb_myfield://我的田
                        position=1;
                        break;
                    case R.id.rb_recommend://智能推荐
                        position=2;
                        break;
                    case R.id.rb_question://知识问答
                        position=3;
                        break;
                    case R.id.rb_user://用户中心
                        position=4;
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
        rgMain.check(R.id.rb_home);
    }

    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MyfieldFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new QuestionFragment());
        fragments.add(new UserFragment());
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