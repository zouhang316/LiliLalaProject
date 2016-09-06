package com.android.lala.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.home.fragment.CircleFragment;
import com.android.lala.home.fragment.InformationFragment;
import com.android.lala.home.fragment.LeaderboardFragment;
import com.android.lala.home.fragment.MineFragment;
import com.android.lala.home.fragment.PhotoFragment;
import com.android.lala.utils.ExitAppliation;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    protected final int CIRCLE_FLAG = 0x100;
    protected final int INFORMATION_FLAG = 0x101;
    protected final int LEADERBOARD_FLAG = 0x102;
    protected final int MINE_FLAG = 0x103;
    protected final int PHOTO_FLAG = 0x104;

    private RadioGroup rg_tabs;
    private RadioButton rb_information;
    private RadioButton rb_list;
    private RadioButton rb_photo;
    private RadioButton rb_circle;
    private RadioButton rb_mine;

    private CircleFragment circleFragment;
    private InformationFragment informationFragment;
    private LeaderboardFragment leaderboardFragment;
    private MineFragment mineFragment;
    private PhotoFragment photoFragment;

    private FragmentTransaction ft;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        rg_tabs = findView(R.id.rg_tabs);
        rb_information = findView(R.id.rb_information);
        rb_list = findView(R.id.rb_list);
        rb_photo = findView(R.id.rb_photo);
        rb_circle = findView(R.id.rb_circle);
        rb_mine = findView(R.id.rb_mine);
        showContentFragment(INFORMATION_FLAG, null);
        setTitle(getString(R.string.str_information));
        setBackBar(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        rb_information.setOnClickListener(this);
        rb_list.setOnClickListener(this);
        rb_circle.setOnClickListener(this);
        rb_photo.setOnClickListener(this);
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
                if (informationFragment == null) {
                    informationFragment = new InformationFragment();
                    informationFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, informationFragment);
                } else {
                    ft.show(informationFragment);
                }
                break;
            case LEADERBOARD_FLAG:
                if (leaderboardFragment == null) {
                    leaderboardFragment = new LeaderboardFragment();
                    leaderboardFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, leaderboardFragment);
                } else {
                    ft.show(leaderboardFragment);
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
            case PHOTO_FLAG:
                if (photoFragment == null) {
                    photoFragment = new PhotoFragment();
                    photoFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, photoFragment);
                } else {
                    ft.show(photoFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (circleFragment != null) {
            ft.hide(circleFragment);
        }
        if (informationFragment != null) {
            ft.hide(informationFragment);
        }
        if (leaderboardFragment != null) {
            ft.hide(leaderboardFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
        if (photoFragment != null) {
            ft.hide(photoFragment);
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
        } else if (R.id.rb_photo == viewId) {
            setTitle(getString(R.string.str_photo));
            rb_photo.setChecked(true);
            showContentFragment(PHOTO_FLAG, bundle);
        }
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
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
