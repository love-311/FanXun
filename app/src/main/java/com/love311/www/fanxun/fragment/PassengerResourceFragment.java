package com.love311.www.fanxun.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.AddPassengerActivity;
import com.love311.www.fanxun.activity.SearchActivity;
import com.love311.www.fanxun.activity.SearchPassengerActivity;
import com.love311.www.fanxun.adapter.PassengerSourcePagerAdapter;
import com.love311.www.fanxun.custom.LazyLoadFragment;

public class PassengerResourceFragment extends LazyLoadFragment {

    private TabLayout topTab;
    private ImageView ivSearch,ivSort,ivAdd;
    private ViewPager mainPager;


    @Override
    public int getLayout() {
        return R.layout.passenger_source_fragment;
    }

    @Override
    public void initViews(View view) {
        mainPager= (ViewPager) view.findViewById(R.id.main_passenger_pager);
        topTab = (TabLayout) view.findViewById(R.id.top_passenger_tab);
        ivAdd = (ImageView) view.findViewById(R.id.iv_passenger_add);
        ivSearch = (ImageView) view.findViewById(R.id.iv_passenger_search);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i =mainPager.getCurrentItem();
                Intent intent = new Intent(getActivity(), AddPassengerActivity.class);
                intent.putExtra("type",i);
                startActivity(intent);
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i =mainPager.getCurrentItem();
                Intent intent = new Intent(getActivity(), SearchPassengerActivity.class);
                intent.putExtra("type",i);
                startActivity(intent);
            }
        });
        topTab.setTabGravity(TabLayout.GRAVITY_FILL);
        setTabs();
        topTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == topTab.getTabAt(1)) {
                    mainPager.setCurrentItem(1);
                } else if (tab == topTab.getTabAt(2)) {
                    mainPager.setCurrentItem(2);
                }else {
                    mainPager.setCurrentItem(0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void loadData() {

    }
    private void setTabs() {
        PassengerSourcePagerAdapter mPagerAdapter = new PassengerSourcePagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTab(new PassengerUsedHouseFragment(), "二手房");
        mPagerAdapter.addTab(new PassengerRentHouseFragment(), "租房");
        mPagerAdapter.addTab(new PassengerNewHouseFragment(), "新房");
        mainPager.setAdapter(mPagerAdapter);
        mainPager.setOffscreenPageLimit(3);
        //把tabLayout和Viewpager关联起来
        topTab.setupWithViewPager(mainPager);
    }
}
