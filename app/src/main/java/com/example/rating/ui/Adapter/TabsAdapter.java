package com.example.rating.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rating.ui.Top.ByFacultyFragment;
import com.example.rating.ui.Top.ByGroupFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs = 2;

    public TabsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new ByFacultyFragment();
            case 1:
                return new ByGroupFragment();
            default:
                return null;
        }
    }
}
