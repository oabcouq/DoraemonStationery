package com.example.sellclothesapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sellclothesapp.ui.fragment.AccountFragment;
import com.example.sellclothesapp.ui.fragment.CartFragment;
import com.example.sellclothesapp.ui.fragment.FavouriteFragment;
import com.example.sellclothesapp.ui.fragment.HomeFragment;
import com.example.sellclothesapp.ui.fragment.NotificationFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FavouriteFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
