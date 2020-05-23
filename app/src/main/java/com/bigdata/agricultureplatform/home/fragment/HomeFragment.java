package com.bigdata.agricultureplatform.home.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;
import com.bigdata.agricultureplatform.home.activity.nongji.AgritechpushActivity;
import com.bigdata.agricultureplatform.home.activity.nongshi.AgritimepushActivity;
import com.bigdata.agricultureplatform.home.activity.zhongzi.PushActivity;
import com.bigdata.agricultureplatform.home.activity.SearchActivity;
import com.bigdata.agricultureplatform.home.adapter.HomeFragmentAdapter;
import com.bigdata.agricultureplatform.home.bean.ResultBeanData;

import butterknife.Bind;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;


//主页的fragment
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.tv_search_home)
    TextView tvSearchHome;
    @Bind(R.id.sv_home)
    ScrollView svHome;
    @Bind(R.id.rl_zhongzi)
    RelativeLayout rlZhongzi;
    @Bind(R.id.rl_nongshi)
    RelativeLayout rlNongshi;
    @Bind(R.id.rl_nongji)
    RelativeLayout rlNongji;
    @Bind(R.id.rl_nongzi)
    RelativeLayout rlNongzi;
    @Bind(R.id.rl_zhengce)
    RelativeLayout rlZhengce;
    //    实例化布局啊一个子页面titlebar.xml一个是homefragmentxml
    //把这个东西生成为类的变量，能用，直接写在下边不好把
    private ResultBeanData.LoginresultBean loginresultBean;
    //删除了回到顶部ib_backtop与右上角问答tv_message_home
    //$$$$$$$$$$$$$$$$$$$$$$$$把创建的homefragmentadapter给拉过来
    private HomeFragmentAdapter adapter;//就叫adapter，随便起

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        Log.e(TAG, "主页的fragmentui被初始化了");
        ButterKnife.bind(this, view);
        initListner();
        return view;
    }

    private void initListner() {
        //回到顶部设置
//        rbback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rvHome.scrollToPosition(0);
//            }
//        });
        //顶部搜索
        rlZhongzi.setOnClickListener(this);
        //推送点击事件
        tvSearchHome.setOnClickListener(this);
        rlNongji.setOnClickListener(this);
        rlNongshi.setOnClickListener(this);
    }
    //主页点击事件，搜索，种子，农时，农技，农资，政策
    @Override
    public void onClick(View view) {
        if (view==rlZhongzi){
            Intent intent = new Intent(mContext, PushActivity.class);
            mContext.startActivity(intent);
        }else if (view==tvSearchHome){
            Log.e(TAG, "点击的搜索，跳入SearchActivity");
            Intent intent = new Intent(mContext, SearchActivity.class);
            mContext.startActivity(intent);
        }else if (view==rlNongji){
            Log.e(TAG, "点击的搜索，跳入AgritechpushActivity");
            Intent intent = new Intent(mContext, AgritechpushActivity.class);
            mContext.startActivity(intent);
        }else if (view==rlNongshi) {
            Log.e(TAG, "点击的搜索，跳入AgritimepushActivity");
            Intent intent = new Intent(mContext, AgritimepushActivity.class);
            mContext.startActivity(intent);
        }
    }
    //注意：联网需要加权限
    /////////////////////////////////////////////////////////////////////////////////////////////////
    //（1）取消页面加载的recycleview的布局，换成咱的其它布局，从这里往下，所有的方法没有被调用，注释掉
    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页的fragment数据被初始化了");
        //抽取方法，联网的
        //    getDataFromNet();
    }


//
//    //被抽取的联网okhttputils方法，在initdata里面调用
//    private void getDataFromNet() {
//        //第一步，刚才封装了myapplication的okhttputils，现在直接用了，github上有用法
//        //String url = "http://192.168.1.7:8080/users/login?userName=admin&userPass=123456";
//        //String url = "https://www.baidu.com/s?ie=UTF-8&wd=%E7%99%BE%E5%BA%A6%E4%B8%80%E4%B8%8B";
//
//        //这里的url已经被封装到util的constants下边
//        String url = Constants.LOGIN_URL;
//        OkHttpUtils
//                .get()//由于后端调整，后端的登录和注册全部写成post，没有get所以出错。
//                .url(url)
//                //先干掉
////                .addParams("user", "hyman")
////                .addParams("password", "123")
//                .build()
//                .execute(new StringCallback()
//                {
//                    /*
//                    * 当请求失败的时候回调，打印
//                    * */
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e(TAG,"首页请求数据失败=="+e.getMessage());
//                    }
//                    /*当联网成功回调，这里的
//                    @param response表示成功请求的数据，
//                    @param id 区分http100和https101
//                    */
//                    @Override
//                    public void onResponse(String response, int id) {
//                        //请求成功打印
//                        Log.e(TAG,"首页请求数据成功=="+response);
//                        //22222222222222222222222222请求完数据，需要解析
//                        //抽出出来一个方法，传入response
//                        processData(response);
//                    }
//                    //这是github中的方法，已经过时了，提示的implementmethod生成了上边的这两个
////                    @Override
////                    public void onError(Request request, Exception e)
////                    { }
////                    @Override
////                    public void onResponse(String response)
////                    { }
//                });
//    }
//
//    //这里json是形参，传入的是response是实参，json表示概括实参是json文件
//    private void processData(String json) {
//        //非常爽，直接解析数据给resultBeandata
//        ResultBeanData resultBeanData= JSON.parseObject(json,ResultBeanData.class);
//        //这里有resultBeanData的get字段的方法，直接拿想要的，返回值给resultBean
//        loginresultBean=resultBeanData.getLoginresult();
//
//        if(loginresultBean !=null){
//            //有数据，去弄adapter回来之后，
//            adapter=new HomeFragmentAdapter(mContext,loginresultBean);
//            rvHome.setAdapter(adapter);
//            //弄完数据都放完一个轮回之后，必须设置布局管理者才行。//传入上下文和列数
//            rvHome.setLayoutManager(new GridLayoutManager(mContext,4));
//
//        }else{
//            //没有数据
//        }
////此时已经拿到咱的后端数据的主要部分了，resultbean就是result里边的admin，密码，tell和type，继续拿
//        //获取name打印试试
//        Log.e(TAG,"得到name打印："+loginresultBean.getUserName());
//
//
//    }
}
