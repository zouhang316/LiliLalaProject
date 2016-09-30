package com.android.lala.circle.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.circle.fragment.FeaturedFragment;
import com.android.lala.circle.fragment.HotFragment;
import com.android.lala.circle.fragment.NewestFragment;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class CircleActivity extends BaseActivity implements View.OnClickListener{
    protected final int NEWEST_FLAG = 0x100;
    protected final int HOT_FLAG = 0x101;
    protected final int FEATURED_FLAG = 0x102;

    private NewestFragment newestFragment;
    private HotFragment hotFragment;
    private FeaturedFragment featuredFragment;
    private FragmentTransaction ft;

    private RadioButton newest;
    private RadioButton hot;
    private RadioButton featured;

    private Bundle bundle;

    private TextView indicator_new,indicator_hot,indicator_featured;

    @Override
    protected void initData() {
        bundle = new Bundle();
        bundle.putString("sort",getIntent().getStringExtra("sort"));
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_circle);
        newest=findView(R.id.circle_rbt_new);
        hot=findView(R.id.circle_rbt_hot);
        featured=findView(R.id.circle_rbt_featured);
        indicator_new=findView(R.id.indicator_new);
        indicator_hot=findView(R.id.indicator_hot);
        indicator_featured=findView(R.id.indicator_featured);
        showContentFragment(NEWEST_FLAG,bundle);

    }

    @Override
    protected void initListener() {
        newest.setOnClickListener(this);
        hot.setOnClickListener(this);
        featured.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (R.id.circle_rbt_new == viewId) {
            newest.setChecked(true);
            showIndicator();
            showContentFragment(NEWEST_FLAG, bundle);
        } else if (R.id.circle_rbt_hot == viewId) {
            hot.setChecked(true);
            showIndicator();
            showContentFragment(HOT_FLAG, bundle);
        } else if (R.id.circle_rbt_featured == viewId) {
            featured.setChecked(true);
            showIndicator();
            showContentFragment(FEATURED_FLAG, bundle);
        }
    }
    private void showContentFragment(int index, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case NEWEST_FLAG:
                if (newestFragment == null) {
                    newestFragment = new NewestFragment();
                    newestFragment.setArguments(bundle);
                    ft.add(R.id.circle_content, newestFragment);
                } else {
                    ft.show(newestFragment);
                }
                break;
            case HOT_FLAG:
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                    hotFragment.setArguments(bundle);
                    ft.add(R.id.circle_content, hotFragment);
                } else {
                    ft.show(hotFragment);
                }
                break;
            case FEATURED_FLAG:
                if (featuredFragment == null) {
                    featuredFragment = new FeaturedFragment();
                    featuredFragment.setArguments(bundle);
                    ft.add(R.id.circle_content, featuredFragment);
                } else {
                    ft.show(featuredFragment);
                }
                break;
        }
        ft.commit();
    }
    private void hideFragment(FragmentTransaction ft) {
        if (newestFragment != null) {
            ft.hide(newestFragment);
        }
        if (hotFragment != null) {
            ft.hide(hotFragment);
        }
        if (featuredFragment != null) {
            ft.hide(featuredFragment);
        }
    }
    public void showIndicator(){
        if (hot.isChecked()){
            indicator_hot.setVisibility(View.VISIBLE);
            indicator_new.setVisibility(View.INVISIBLE);
            indicator_featured.setVisibility(View.INVISIBLE);
        }else if (newest.isChecked()){
            indicator_hot.setVisibility(View.INVISIBLE);
            indicator_new.setVisibility(View.VISIBLE);
            indicator_featured.setVisibility(View.INVISIBLE);
        }else {
            indicator_hot.setVisibility(View.INVISIBLE);
            indicator_new.setVisibility(View.INVISIBLE);
            indicator_featured.setVisibility(View.VISIBLE);
        }
    }
}
