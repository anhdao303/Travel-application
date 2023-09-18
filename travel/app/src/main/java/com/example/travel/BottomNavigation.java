package com.example.travel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.travel.databinding.ActivityBottomNavigationBinding;
import com.example.travel.fragment.ExploreFragment;
import com.example.travel.fragment.HomeFragment;
import com.example.travel.fragment.MeFragment;

public class BottomNavigation extends AppCompatActivity {
    ViewPager viewPager;
    ActivityBottomNavigationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.me:
                    replaceFragment(new MeFragment());
                    break;
                case R.id.explore:
                    replaceFragment(new ExploreFragment());
                    break;
            }
            return true;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            /*@Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        binding.bottomNavigationView.getMenu().findItem(R.id.home);
                        break;
                    case 1:
                        binding.bottomNavigationView.getMenu().findItem(R.id.me);
                        break;
                    case 2:
                        binding.bottomNavigationView.getMenu().findItem(R.id.explore);
                        break;

                }
            }*/

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}