package com.example.ivandimitrov.recepiestask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ivan Dimitrov on 2/16/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {
    public static final String BEER_TAB        = "Beer";
    public static final String BREWERY_TAB     = "Brewery";
    public static final String PAGE_PAGE_ERROR = "PAGE ERROR";

    private List<Fragment> fragments;

    public PageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }


    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case MainActivity.BEER_PAGE:
                return BEER_TAB;
            case MainActivity.BREWERY_PAGE:
                return BREWERY_TAB;
            default:
                return PAGE_PAGE_ERROR;
        }
    }
}
