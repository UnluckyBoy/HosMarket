package com.cloudstudio.hosmarket.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.percentlayout.widget.PercentRelativeLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.cloudstudio.hosmarket.R;
import com.cloudstudio.hosmarket.adpater.fragment.DashboardFragmentPagerAdapter;
import com.cloudstudio.hosmarket.adpater.fragment.ViewPager2Adapter;
import com.cloudstudio.hosmarket.databinding.FragmentDashboardBinding;
import com.cloudstudio.hosmarket.ui.common.CommonFragment;
import com.cloudstudio.hosmarket.util.ElementUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private ViewPager2 viewPager;
    private DashboardFragmentPagerAdapter adapter;
    private TextView _toWareHouse,_outWareHouse,_order;

    private PercentRelativeLayout toWareHouseManagerLay;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //DashboardViewModel dashboardViewModel =new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//防止屏幕被软键盘技压

        initElement(root);
        initDashboardFragment(root);
        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initElement(View root){
        viewPager = binding.viewPager2;
        _toWareHouse=binding.toWareHouse;
        _outWareHouse=binding.outWareHouse;
        _order=binding.order;

        _toWareHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);
            }
        });
        _outWareHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });
        _order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2,true);
            }
        });
    }

    private void initDashboardFragment(View root){
        // 创建 Fragment 列表
        CommonFragment toWareHouseFragment = CommonFragment.newInstance(R.layout.fragment_common,"toWareHouse");
        CommonFragment outWareHouseFragmentFragment = CommonFragment.newInstance(R.layout.fragment_common,"outWareHouse");
        CommonFragment orderFragmentFragment = CommonFragment.newInstance(R.layout.fragment_common,"order");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(toWareHouseFragment);
        fragments.add(outWareHouseFragmentFragment);
        fragments.add(orderFragmentFragment);
        ViewPager2Adapter adapter = new ViewPager2Adapter(getParentFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // 页面滑动时的回调
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 页面选中时的回调
                switch (position){
                    case 0:
                        ElementUtil.textStatusClick(root.getContext(),_toWareHouse);
                        ElementUtil.textStatusNormal(root.getContext(),_outWareHouse);
                        ElementUtil.textStatusNormal(root.getContext(),_order);
                        break;
                    case 1:
                        ElementUtil.textStatusNormal(root.getContext(),_toWareHouse);
                        ElementUtil.textStatusClick(root.getContext(),_outWareHouse);
                        ElementUtil.textStatusNormal(root.getContext(),_order);
                        break;
                    case 2:
                        ElementUtil.textStatusNormal(root.getContext(),_toWareHouse);
                        ElementUtil.textStatusNormal(root.getContext(),_outWareHouse);
                        ElementUtil.textStatusClick(root.getContext(),_order);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                // 页面滚动状态改变时的回调
            }
        });
    }
}