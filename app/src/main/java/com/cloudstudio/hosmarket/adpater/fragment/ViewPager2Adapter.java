package com.cloudstudio.hosmarket.adpater.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName ViewPager2Adapter
 * @Author Create By matrix
 * @Date 2024/10/16 22:01
 */
public class ViewPager2Adapter extends FragmentStateAdapter {
    private final List<Fragment> fragments;

    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, List<Fragment> fragments) {
        super(Objects.requireNonNull(fragmentManager.getPrimaryNavigationFragment()));
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
