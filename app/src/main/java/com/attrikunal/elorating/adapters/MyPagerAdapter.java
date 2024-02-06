package com.attrikunal.elorating.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.attrikunal.elorating.fragments.OutcomeFragment;
import com.attrikunal.elorating.fragments.PointsFragment;

public class MyPagerAdapter extends FragmentStateAdapter {
    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1:
                return new PointsFragment();
        }

        return new OutcomeFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
