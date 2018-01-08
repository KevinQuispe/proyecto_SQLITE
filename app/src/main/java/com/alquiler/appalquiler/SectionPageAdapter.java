package com.alquiler.appalquiler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Quispe on 03/11/2017.
 */

public class SectionPageAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentlist=new ArrayList<>();
    private final List<String> mFragmentTitlelist=new ArrayList<>();
    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public void  maddFragment(Fragment fragment,String title){

    mFragmentlist.add(fragment);
        mFragmentTitlelist.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentlist.size();
    }
}
