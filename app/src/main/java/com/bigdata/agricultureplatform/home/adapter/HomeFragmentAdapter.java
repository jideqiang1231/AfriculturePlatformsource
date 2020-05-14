package com.bigdata.agricultureplatform.home.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.home.bean.ResultBeanData;

/**
 * @description：$des$
 **/
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    //设置我们的类型，banner我们不设置了
    public static final int CHANNEl = 0;
    public static final int RECOMMEND = 1;
    private  Context mcontext;//都删掉final
    private  ResultBeanData.LoginresultBean loginresultBean;
    //用它初始化布局了以后
    //方法二，用view.inflater也行，但是最终还是这一个，相当于封装上了踢取出来了
    private  LayoutInflater mLayoutInflater;
    //当前类型设置为0,moren 先为channel（频道）
    private int currentType=CHANNEl;
    private ChannelAdapter adapter;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.LoginresultBean resultBean) {
        //构造方法接受参数
        this.mcontext=mContext;
        this.loginresultBean=resultBean;
        //以后就用layoutinfalter实例化
        //接受参数都用这种prvite的形式，这个东西直接用
        mLayoutInflater=LayoutInflater.from(mContext);
    }


//由要绑定返回来的这几个布局类型，所以还要重写这个方法,去得到我们弄得类型
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case CHANNEl:
                currentType=CHANNEl;
                break;
            case RECOMMEND:
                currentType=RECOMMEND;
                break;
        }
        return currentType;//此时我们就有类型了，此时开始写数据
    }
    @Override
    /*
     * 创建viewholder（部分代码）    与getview方法相比
     * 返回一个父类     返回一个当前类型
     * */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CHANNEl){
            return new ChannelViewHolder(mcontext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }
        return null;
    }

    // 相当于geiview中绑定数据的模块
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==CHANNEl){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            channelViewHolder.setData(loginresultBean.getUserName());
        }

    }
class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mcontext;
        private GridView gvChannel;
    public ChannelViewHolder(final Context mcontext, View itemView) {
        super(itemView);
        this.mcontext=mcontext;
        gvChannel=itemView.findViewById(R.id.gv_channel);
        //设置item的点击事件
        gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //这里还要该变量，i-position，adapterView-parent l-id
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Toast.makeText(mcontext,"position"+position,Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void setData(String userName) {
        //得到数据了
        //设置GridView的适配器
        adapter = new ChannelAdapter(mcontext,userName);
        gvChannel.setAdapter(adapter);

    }
}





//总共有多少个item，这个recycleview有及部分就写几个！
    @Override
    public int getItemCount() {
        //开发过程中从1开始，一个页面增加一个
        return 1;
    }
}
