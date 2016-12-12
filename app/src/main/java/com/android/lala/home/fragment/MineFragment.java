package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lala.LaLaAppaction;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.mine.activity.CollectActivity;
import com.android.lala.mine.activity.FeedbackActivity;
import com.android.lala.mine.activity.MoreActivity;
import com.android.lala.R;
import com.android.lala.base.BaseFragment;
import com.android.lala.login.LoginActivity;
import com.android.lala.mine.activity.MyChannelActivity;
import com.android.lala.mine.bean.CollectBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MineFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout more;
    private RelativeLayout mychannel;
    private RelativeLayout feedback;
    private RelativeLayout collect;
    private RelativeLayout invite;
    private LinearLayout islogin,onlogin;
    private CircleImageView mHead;
    private TextView mName;
    private HttpListener<String> httpListener;
    private PreferenceManager preferenceManager;
    private List<CollectBean> beanArrayList;
    @Override
    public void initData(Bundle savedInstanceState) {

        commDataDao=new CommDataDaoImpl(getActivity(),false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                LalaLog.i("collct",response);
                Helper helper= JsonResultUtils.helper(response);
                String data=helper.getContentByKey("article");
                beanArrayList=new ArrayList<>();
                beanArrayList= FastJsonHelper.getObjects(data,CollectBean.class);
                LalaLog.i("size",beanArrayList.size()+"");
                Intent intent=new Intent(getActivity(), CollectActivity.class);
                intent.putParcelableArrayListExtra("collectlist", (ArrayList<? extends Parcelable>) beanArrayList);
                startActivity(intent);
            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    @Override
    public void initView(View view) {
        more= (RelativeLayout) view.findViewById(R.id.more);
        mHead= (CircleImageView) view.findViewById(R.id.my_head);
        mName= (TextView) view.findViewById(R.id.my_name);
        islogin= (LinearLayout) view.findViewById(R.id.islogin);
        onlogin=(LinearLayout) view.findViewById(R.id.onlogin);
        collect= (RelativeLayout) view.findViewById(R.id.collect);
        mychannel= (RelativeLayout) view.findViewById(R.id.mychannel);
        feedback= (RelativeLayout) view.findViewById(R.id.feedback);
        invite= (RelativeLayout) view.findViewById(R.id.invite);
        initListener();
    }
    private void initListener(){
        more.setOnClickListener(this);
        onlogin.setOnClickListener(this);
        mychannel.setOnClickListener(this);
        collect.setOnClickListener(this);
        feedback.setOnClickListener(this);
        invite.setOnClickListener(this);
    }
    private void getCollect(){
        if (!preferenceManager.getBoolean("islogin",false)){
            showToast("登录查看收藏");
            return;
        }
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("userId",preferenceManager.getString("id",""));
        VolleyHelper.getInstance().add(commDataDao,getActivity(), HttpWhatContacts.GETUP, ApiContacts.GETCOLLECT,httpListener,paramers,false);
    }
    private void inviteFriends(){

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("蚂蚁新空");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(ApiContacts.APKDOWNLOAD);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("让创新无处不在");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(ApiContacts.APKICON);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(ApiContacts.APKDOWNLOAD);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这里有好多新鲜好玩的东西，赶快来吧");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(ApiContacts.APKDOWNLOAD);
        // 启动分享GUI
        oks.show(getActivity());
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        preferenceManager =PreferenceManager.getInstance(getActivity());
        if (preferenceManager.getBoolean("islogin",false)){
            onlogin.setVisibility(View.GONE);
            islogin.setVisibility(View.VISIBLE);
            Picasso.with(getActivity()).load(preferenceManager.getString("photo","")).placeholder(R.drawable.ic_defult_head).into(mHead);
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
                break;
            case R.id.collect :
                getCollect();
                break;
            case R.id.invite :
                inviteFriends();
                break;
        }

    }
}
