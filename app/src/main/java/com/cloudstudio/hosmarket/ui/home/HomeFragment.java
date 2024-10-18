package com.cloudstudio.hosmarket.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cloudstudio.hosmarket.R;
import com.cloudstudio.hosmarket.adpater.BannerImageAdapter;
import com.cloudstudio.hosmarket.databinding.FragmentHomeBinding;
import com.cloudstudio.hosmarket.entity.BannerDataInfo;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<BannerDataInfo> mBannerDataList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initBannerView(root);
        initRefreshView(root);

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * 加载显示轮播逻辑
     * @param root
     */
    private void initBannerView(View root){
        Banner mBanner = binding.bannerView;//绑定轮播
        mBannerDataList=new ArrayList<>();
        mBannerDataList.add(new BannerDataInfo(R.mipmap.banner02,"测试轮播链接1:https://test.com"));
        mBannerDataList.add(new BannerDataInfo(R.mipmap.banner01,"测试轮播链接2:https://test.com"));
        mBannerDataList.add(new BannerDataInfo(R.mipmap.banner02,"测试轮播链接3:https://test.com"));

        final BannerImageAdapter bannerImageAdapter=new BannerImageAdapter(mBannerDataList);

        mBanner.setAdapter(bannerImageAdapter);//添加进适配器
        mBanner.addBannerLifecycleObserver(this);//添加生命周期
        mBanner.setIndicator(new CircleIndicator(this.getContext()));/*设置圆形计数器*/
        mBanner.setOnBannerListener(new OnBannerListener<BannerDataInfo>() {
            @Override
            public void OnBannerClick(BannerDataInfo data, int position) {
                Toast.makeText(root.getContext(), data.getUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*下拉刷新页面*/
    private void initRefreshView(View root){
        swipeRefreshLayout=(SwipeRefreshLayout)root.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //initMainListView(root);
                        Toast.makeText(root.getContext(), "刷新成功",Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1500);
            }
        });
    }
}