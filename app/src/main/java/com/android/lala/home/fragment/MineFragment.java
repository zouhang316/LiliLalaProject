package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lala.mine.activity.FeedbackActivity;
import com.android.lala.mine.activity.MoreActivity;
import com.android.lala.R;
import com.android.lala.base.BaseFragment;
import com.android.lala.login.LoginActivity;
import com.android.lala.mine.activity.MyChannelActivity;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.view.CircleImageView;
import com.squareup.picasso.Picasso;

public class MineFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout more;
    private RelativeLayout mychannel;
    private RelativeLayout feedback;
    private LinearLayout islogin,onlogin;
    private CircleImageView mHead;
    private TextView mName;
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view) {
        more= (RelativeLayout) view.findViewById(R.id.more);
        mHead= (CircleImageView) view.findViewById(R.id.my_head);
        mName= (TextView) view.findViewById(R.id.my_name);
        islogin= (LinearLayout) view.findViewById(R.id.islogin);
        onlogin=(LinearLayout) view.findViewById(R.id.onlogin);
        mychannel= (RelativeLayout) view.findViewById(R.id.mychannel);
        feedback= (RelativeLayout) view.findViewById(R.id.feedback);
        initListener();


    }
    private void initListener(){
        more.setOnClickListener(this);
        onlogin.setOnClickListener(this);
        mychannel.setOnClickListener(this);
        feedback.setOnClickListener(this);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager preferenceManager =PreferenceManager.getInstance(getActivity());
        if (preferenceManager.getBoolean("islogin",false)){
            onlogin.setVisibility(View.GONE);
            islogin.setVisibility(View.VISIBLE);
            Picasso.with(getActivity()).load(preferenceManager.getString("photo","")).into(mHead);
            mName.setText(preferenceManager.getString("name",""));
        }else {
            onlogin.setVisibility(View.VISIBLE);
            islogin.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more :
                Intent intent=new Intent(getActivity(),MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.onlogin :
                Intent login =new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                break;
            case R.id.mychannel:
                Intent mychannel=new Intent(getActivity(), MyChannelActivity.class);
                startActivity(mychannel);
                break;
            case R.id.feedback :
                Intent feedback=new Intent(getActivity(), FeedbackActivity.class);
                startActivity(feedback);
        }

    }
}
