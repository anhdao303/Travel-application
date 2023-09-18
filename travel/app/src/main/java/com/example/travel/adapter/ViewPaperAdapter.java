package com.example.travel.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.travel.fragment.JourneyFragment;
import com.example.travel.fragment.ProfileFragment;

public class ViewPaperAdapter extends FragmentStatePagerAdapter{
    public ViewPaperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new JourneyFragment();
            case 1:
                return new ProfileFragment();
            default:
                return new JourneyFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Journey";
            case 1:
                return "Profile";
            default:
                return "Journey";
        }
    }
}

