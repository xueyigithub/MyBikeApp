package com.bikeapp.xueyi.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.bikeapp.xueyi.config.Consts;
import com.bikeapp.xueyi.fragment.FindFragment;
import com.bikeapp.xueyi.fragment.MineFragment;
import com.bikeapp.xueyi.fragment.WeatherFragment;


public class MyFragmentHomeAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private FindFragment findFragment = null;
    private WeatherFragment weatherFragment = null;
    private MineFragment mineFragment = null;


    public MyFragmentHomeAdapter(FragmentManager fm) {
        super(fm);
        findFragment = new FindFragment();
        weatherFragment = new WeatherFragment();
        mineFragment = new MineFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Consts.PAGE_ONE:
                fragment = findFragment;
                break;
            case Consts.PAGE_TWO:
                fragment = weatherFragment;
                break;
            case Consts.PAGE_THREE:
                fragment = mineFragment;
                break;
        }
        return fragment;
    }
}