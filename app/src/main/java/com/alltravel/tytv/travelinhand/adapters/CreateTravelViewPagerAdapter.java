package com.alltravel.tytv.travelinhand.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateTravelViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private boolean isAcess = false;
    private final FragmentManager mFragmentManager;
    private Fragment mFragmentAtPos0;

    public boolean isAcess() {
        return isAcess;
    }

    public void setAcess(boolean acess) {
        isAcess = acess;
    }

    public CreateTravelViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {
            return mFragmentList.get(i);

    }

    @Override
    public int getCount() {
//        if(!isAcess){
//            return 1;
//        }else{
            return mFragmentList.size();
//        }
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}

interface FirstPageFragmentListener
{
    void onSwitchToNextFragment();
}