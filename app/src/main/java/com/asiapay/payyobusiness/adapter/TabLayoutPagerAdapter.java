package com.asiapay.payyobusiness.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.asiapay.payyobusiness.home.history.CustomHistoryTabFragment;
import com.asiapay.payyobusiness.home.history.OneDayFragment;
import com.asiapay.payyobusiness.home.history.SevenDayFragment;
import com.asiapay.payyobusiness.home.history.TwentyEightDayFragment;

public class TabLayoutPagerAdapter extends FragmentPagerAdapter {
    int tabCount = 0;

    public TabLayoutPagerAdapter(Context mContext, @NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OneDayFragment firstDayFragment = new OneDayFragment();
                return firstDayFragment;
            case 1:
                SevenDayFragment sevenDayFragment = new SevenDayFragment();
                return sevenDayFragment;
            case 2:
                TwentyEightDayFragment twentyEightDayFragment = new TwentyEightDayFragment();
                return twentyEightDayFragment;
            case 3:
                CustomHistoryTabFragment customFragment = new CustomHistoryTabFragment();
                return customFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
