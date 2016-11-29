package com.android.lala.home;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.bean.VersinBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.home.fragment.CircleFragment;
import com.android.lala.home.fragment.InformationFragment;
import com.android.lala.home.fragment.MarketFragment;
import com.android.lala.home.fragment.MineFragment;
import com.android.lala.home.fragment.NewInformationFragment;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.market.bean.MacBean;
import com.android.lala.utils.ExitAppliation;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.utils.UpdateManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    protected final int CIRCLE_FLAG = 0x100;
    protected final int INFORMATION_FLAG = 0x101;
    protected final int LEADERBOARD_FLAG = 0x102;
    protected final int MINE_FLAG = 0x103;

    private RadioGroup rg_tabs;
    private RadioButton rb_information;
    private RadioButton rb_list;
    private ImageView photo;
    private RadioButton rb_circle;
    private RadioButton rb_mine;

    private CircleFragment circleFragment;
    private InformationFragment informationFragment;
    private MarketFragment marketFragment;
    private MineFragment mineFragment;
    private NewInformationFragment newInformationFragment;
    private HttpListener<String> httpListener;
    private UpdateManager updateManager;
    private FragmentTransaction ft;
    private String macAddress;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        //overridePendingTransition(R.anim.left_out,R.anim.right_in);
        checkReLogin();
        getVerionByVolley();
        rg_tabs = findView(R.id.rg_tabs);
        rb_information = findView(R.id.rb_information);
        rb_list = findView(R.id.rb_list);
        //photo = findView(R.id.rb_photo);
        rb_circle = findView(R.id.rb_circle);
        rb_mine = findView(R.id.rb_mine);
        showContentFragment(INFORMATION_FLAG, null);
        setTitle(getString(R.string.str_information));
        setBackBar(false);
    }

    @Override
    protected void initData() {
        macAddress=getMacAddress();
        commDataDao = new CommDataDaoImpl(this, false, "");
        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                switch (what){
                    case HttpWhatContacts.GETNEW :
                        //检查版本
                        Helper helper = JsonResultUtils.helper(response);
                        String data = helper.getContentByKey("upload");
                        VersinBean bean = FastJsonHelper.getObject(data, VersinBean.class);
                        PreferenceManager manager = PreferenceManager.getInstance(MainActivity.this);
                        manager.putInt("versioncode", bean.getVersion());
                        updateManager = new UpdateManager(MainActivity.this, bean.getVersion(), bean.getVersion_introduce());
                        updateManager.getUpdateInfo();
                        break;
                    case HttpWhatContacts.GETUP :
                        //判断是否多设备登录
                        Helper helper2=JsonResultUtils.helper(response);
                        String data2=helper2.getContentByKey("mac");
                        MacBean macBean=FastJsonHelper.getObject(data2, MacBean.class);
                        if (!macBean.getMac().equals(macAddress)){
                            PreferenceManager.getInstance(MainActivity.this).putBoolean("islogin",false);
                            showMessageDialog("提示","您的账号在其他设备登录，如不是您本人操作请更换密码");
                        }
                        break;
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    @Override
    protected void initListener() {
        rb_information.setOnClickListener(this);
        rb_list.setOnClickListener(this);
        rb_circle.setOnClickListener(this);
        //photo.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
    }

    private void showContentFragment(int index, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case CIRCLE_FLAG:
                if (circleFragment == null) {
                    circleFragment = new CircleFragment();
                    circleFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, circleFragment);
                } else {
                    ft.show(circleFragment);
                }
                break;
            case INFORMATION_FLAG:
                if (newInformationFragment == null) {
                    newInformationFragment = new NewInformationFragment();
                    newInformationFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, newInformationFragment);
                } else {
                    ft.show(newInformationFragment);
                }
                break;
            case LEADERBOARD_FLAG:
                if (marketFragment == null) {
                    marketFragment = new MarketFragment();
                    marketFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, marketFragment);
                } else {
                    ft.show(marketFragment);
                }
                break;
            case MINE_FLAG:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    mineFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (circleFragment != null) {
            ft.hide(circleFragment);
        }
        if (newInformationFragment != null) {
            ft.hide(newInformationFragment);
        }
        if (marketFragment != null) {
            ft.hide(marketFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Bundle bundle = new Bundle();
        if (R.id.rb_circle == viewId) {
            rb_circle.setChecked(true);
            setTitle(getString(R.string.str_circle));
            showContentFragment(CIRCLE_FLAG, bundle);
        } else if (R.id.rb_information == viewId) {
            setTitle(getString(R.string.str_information));
            rb_information.setChecked(true);
            showContentFragment(INFORMATION_FLAG, bundle);
        } else if (R.id.rb_list == viewId) {
            setTitle(getString(R.string.str_leaderboard));
            rb_list.setChecked(true);
            showContentFragment(LEADERBOARD_FLAG, bundle);
        } else if (R.id.rb_mine == viewId) {
            setTitle(getString(R.string.str_mine));
            rb_mine.setChecked(true);
            showContentFragment(MINE_FLAG, bundle);
        }
    }

    public void getVerionByVolley() {
        VolleyHelper.getInstance().add(commDataDao, this, HttpWhatContacts.GETNEW, ApiContacts.GETVERSIONINFO, httpListener, new HashMap<String, String>(), false);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    private String getMacAddress(){
        String result = "";
        WifiManager wifiManager = (WifiManager) getSystemService(this.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        return result;
    }
    public void checkReLogin(){
        boolean islogin;
        PreferenceManager manager=PreferenceManager.getInstance(MainActivity.this);
        manager.putString("mac",macAddress);
        islogin=manager.getBoolean("islogin",false);
        if (islogin){
            String userid=manager.getString("id","");
            HashMap<String,String> paramers=new HashMap<>();
            paramers.put("id",userid);
            VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETUP,ApiContacts.GETMAC,httpListener,paramers,false);
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }


    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;


    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            showToastMsg("再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ExitAppliation.getInstance().exit();

        }
    }

}
