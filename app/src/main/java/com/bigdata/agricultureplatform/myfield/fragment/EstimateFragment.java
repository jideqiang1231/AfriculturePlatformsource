package com.bigdata.agricultureplatform.myfield.fragment;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.app.MainActivity;
import com.bigdata.agricultureplatform.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

//投产估算
public class EstimateFragment extends BaseFragment {
    @Bind(R.id.rb_ratingbar)
    RatingBar rbRatingbar;
    @Bind(R.id.tv_rating)
    TextView tvRating;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_estimate, null);
        ButterKnife.bind(this,view);
        rbRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(getActivity(), v + "分", Toast.LENGTH_SHORT).show();
                if(v==0.0||v==0.5||v==1.0){
                    tvRating.setText("非常差");
                }else if(v==1.5||v==2.0){
                    tvRating.setText("差");
                }else if(v==2.5||v==3.0){
                    tvRating.setText("中");
                }else if(v==3.5||v==4.0){
                    tvRating.setText("良");
                }else if(v==4.5||v==5.0){
                    tvRating.setText("优秀");
                }
            }
        });


        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}