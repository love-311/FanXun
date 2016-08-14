package com.love311.www.fanxun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.custom.CustomViewPager;
import com.love311.www.fanxun.fragment.HouseResourceFragment;
import com.love311.www.fanxun.fragment.MyFragment;
import com.love311.www.fanxun.fragment.PassengerResourceFragment;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MainActivity extends AutoLayoutActivity {

    private CustomViewPager mPager;
    private TabLayout mBottomTab;
    private TabLayout.Tab mHouseSource;
    private TabLayout.Tab mPassengerSource;
    private TabLayout.Tab mMy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initViews();
        initEvents();
    }


    private void initViews() {
        mPager = (CustomViewPager) findViewById(R.id.main_activity_pager);
        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);
    //    mBottomTab.setTabTextColors(R.color.tabUnselected,R.color.tabSelected);
        //mPager.setPagingEnabled(false);
        mPager.setNoScroll(true);
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"房源", "客源", "我的"};

            @Override
            public Fragment getItem(int position) {
                if (position == 1) {
                    return new PassengerResourceFragment();
                } else if (position == 2) {
                    return new MyFragment();
                }
                return new HouseResourceFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mBottomTab.setupWithViewPager(mPager);
        mBottomTab.setTabGravity(TabLayout.GRAVITY_FILL);
        mHouseSource = mBottomTab.getTabAt(0);
        mPassengerSource = mBottomTab.getTabAt(1);
        mMy = mBottomTab.getTabAt(2);
        mHouseSource.setIcon(R.mipmap.red_house);
        mPassengerSource.setIcon(R.mipmap.peoples);
        mMy.setIcon(R.mipmap.people);
    }

    private void initEvents() {
        mBottomTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == mBottomTab.getTabAt(0)) {
                    mHouseSource.setIcon(R.mipmap.red_house);
                    mPassengerSource.setIcon(R.mipmap.peoples);
                    mMy.setIcon(R.mipmap.people);
                    mPager.setCurrentItem(0);
                } else if (tab == mBottomTab.getTabAt(1)) {
                    mPassengerSource.setIcon(R.mipmap.red_peoples);
                    mHouseSource.setIcon(R.mipmap.house);
                    mMy.setIcon(R.mipmap.people);
                    mPager.setCurrentItem(1);
                } else if (tab == mBottomTab.getTabAt(2)) {
                    mMy.setIcon(R.mipmap.red_people);
                    mPassengerSource.setIcon(R.mipmap.peoples);
                    mHouseSource.setIcon(R.mipmap.house);
                    mPager.setCurrentItem(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                if (tab == mBottomTab.getTabAt(0)) {
//                    mHouseSource.setIcon(R.mipmap.house);
//                    //mHouseSource.setText("房源");
//                } else if (tab == mBottomTab.getTabAt(1)) {
//                    mPassengerSource.setIcon(R.mipmap.peoples);
//                   // mPassengerSource.setText("客源");
//                } else if (tab == mBottomTab.getTabAt(2)) {
//                    mMy.setIcon(R.mipmap.people);
//                    //mMy.setText("我的");
//                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
