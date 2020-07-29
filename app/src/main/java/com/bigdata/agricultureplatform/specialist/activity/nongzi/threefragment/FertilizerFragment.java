package com.bigdata.agricultureplatform.specialist.activity.nongzi.threefragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * @description：$des$ 化肥third
 **/
public class FertilizerFragment extends BaseFragment {
    @Bind(R.id.ib_SpecialistFertilizer_addpush)
    ImageButton ibSpecialistFertilizerAddpush;
    @Bind(R.id.lv_fertilizer)
    ListView lvFertilizer;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_fertilizer, null);
        //如果没用到下边这个句子使用了butterknife则会出现一个非常牛*的错误
        //会在底下adpter引用传入值得时候出现空指针：java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.ListView.setAdapter(android.widget.ListAdapter)' on a null object reference
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "化肥的fragment数据被初始化了");
    }
}
