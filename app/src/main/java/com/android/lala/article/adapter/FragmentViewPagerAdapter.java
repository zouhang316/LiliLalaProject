package com.android.lala.article.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ZH on 2016/11/10.
 * 497239511@qq.com
 */
public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> flist;
    private String [] titles;

    public FragmentViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> flist,String [] titles) {
        super(fm);
        this.flist=flist;
        this.titles=titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
