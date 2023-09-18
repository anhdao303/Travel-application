package com.example.travel.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travel.R;
import com.example.travel.adapter.ViewPaperAdapter;
import com.google.android.material.tabs.TabLayout;


public class MeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_me, container, false);
        tabLayout = mView.findViewById(R.id.tabLayout);
        viewPager = mView.findViewById(R.id.viewpaper);
        ViewPaperAdapter adapter = new ViewPaperAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }
}