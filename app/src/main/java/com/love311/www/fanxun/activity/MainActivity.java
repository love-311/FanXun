package com.love311.www.fanxun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.widget.Toast;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.custom.CustomViewPager;
import com.love311.www.fanxun.fragment.HouseResourceFragment;
import com.love311.www.fanxun.fragment.MyFragment;
import com.love311.www.fanxun.fragment.PassengerNewHouseFragment;
import com.love311.www.fanxun.fragment.PassengerResourceFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MainActivity extends AutoLayoutActivity {

    private CustomViewPager mPager;
    private TabLayout mBottomTab;
    private TabLayout.Tab mHouseSource;
    private TabLayout.Tab mPassengerSource;
    private TabLayout.Tab mMy;
    private int type_fragment;
    private PassengerResourceFragment passengerResourceFragment;
    private MyFragment myFragment;
    private HouseResourceFragment houseResourceFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        type_fragment = getIntent().getIntExtra("type_fragment",0);
        initViews();
        initEvents();
    }


    private void initViews() {
        mPager = (CustomViewPager) findViewById(R.id.main_activity_pager);
        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);
    //    mBottomTab.setTabTextColors(R.color.tabUnselected,R.color.tabSelected);
        //mPager.setPagingEnabled(false);
        mPager.setNoScroll(true);
        mPager.setOffscreenPageLimit(3);
        passengerResourceFragment =new PassengerResourceFragment();
        myFragment = new MyFragment();
        houseResourceFragment =new HouseResourceFragment();
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"房源", "客源", "我的"};

            @Override
            public Fragment getItem(int position) {
                if (position == 1) {
                    return passengerResourceFragment;
                } else if (position == 2) {
                    return myFragment;
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type_fragment",type_fragment);
                    houseResourceFragment.setArguments(bundle);
                    return houseResourceFragment;
                }
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
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}
