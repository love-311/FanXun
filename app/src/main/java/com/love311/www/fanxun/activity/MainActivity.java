package com.love311.www.fanxun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.MainPagerAdapter;
import com.love311.www.fanxun.custom.CustomViewPager;
import com.love311.www.fanxun.fragment.HouseResourceFragment;
import com.love311.www.fanxun.fragment.MyFragment;
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
    private PassengerResourceFragment passengerResourceFragment;
    private MyFragment myFragment;
    private HouseResourceFragment houseResourceFragment;
    //搜索传递数据
    private int type_fragment;
    private int total_numbers;
    private String search_url;
    private int from;
    private int fragment;
    //退出程序，清空房源搜索数据
    private SharedPreferences clearUsedSharedPreferences;
    private SharedPreferences.Editor usedEditor;
    private SharedPreferences clearRentSharedPreferences;
    private SharedPreferences.Editor rentEditor;
    private SharedPreferences clearNewSharedPreferences;
    private SharedPreferences.Editor newEditor;
    //退出程序，清空客源搜索数据
    private SharedPreferences clearUsedPassengerSharedPreferences;
    private SharedPreferences.Editor usedPassengerEditor;
    private SharedPreferences clearRentPassengerSharedPreferences;
    private SharedPreferences.Editor rentPassengerEditor;
    private SharedPreferences clearNewPassengerSharedPreferences;
    private SharedPreferences.Editor newPassengerEditor;
    //退出程序清空房源列表数据
    private SharedPreferences preferences1;
    private SharedPreferences.Editor editor1;
    private SharedPreferences preferences2;
    private SharedPreferences.Editor editor2;
    private SharedPreferences preferences3;
    private SharedPreferences.Editor editor3;
    //退出程序清空客源列表数据
    private SharedPreferences preferences4;
    private SharedPreferences.Editor editor4;
    private SharedPreferences preferences5;
    private SharedPreferences.Editor editor5;
    private SharedPreferences preferences6;
    private SharedPreferences.Editor editor6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //清除房源信息
        clearUsedSharedPreferences = getSharedPreferences("search_used_status", MODE_PRIVATE);
        usedEditor = clearUsedSharedPreferences.edit();
        clearRentSharedPreferences = getSharedPreferences("search_rent_status", MODE_PRIVATE);
        rentEditor = clearRentSharedPreferences.edit();
        clearNewSharedPreferences = getSharedPreferences("search_new_status", MODE_PRIVATE);
        newEditor = clearNewSharedPreferences.edit();
        //清除客源信息
        clearUsedPassengerSharedPreferences = getSharedPreferences("search_passenger_used_status", MODE_PRIVATE);
        usedPassengerEditor = clearUsedPassengerSharedPreferences.edit();
        clearRentPassengerSharedPreferences = getSharedPreferences("search_passenger_rent_status", MODE_PRIVATE);
        rentPassengerEditor = clearRentPassengerSharedPreferences.edit();
        clearNewPassengerSharedPreferences = getSharedPreferences("search_passenger_new_status", MODE_PRIVATE);
        newPassengerEditor = clearNewPassengerSharedPreferences.edit();
        //清除房源列表数据
        preferences1 = getSharedPreferences("used", MODE_PRIVATE);
        editor1 = preferences1.edit();
        preferences2 = getSharedPreferences("rent", MODE_PRIVATE);
        editor2 = preferences2.edit();
        preferences3 = getSharedPreferences("new", MODE_PRIVATE);
        editor3 = preferences3.edit();
        //清除客源列表数据
        preferences4 = getSharedPreferences("passenger_used", MODE_PRIVATE);
        editor4 = preferences4.edit();
        preferences5 = getSharedPreferences("passenger_rent", MODE_PRIVATE);
        editor5 = preferences5.edit();
        preferences6 = getSharedPreferences("passenger_new", MODE_PRIVATE);
        editor6 = preferences6.edit();
        passengerResourceFragment = new PassengerResourceFragment();
        myFragment = new MyFragment();
        houseResourceFragment = new HouseResourceFragment();
        initViews();
        initEvents();
        Log.d("MainActivity","onCreate()..");
    }


    private void initViews() {
        mPager = (CustomViewPager) findViewById(R.id.main_activity_pager);
        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);
        //    mBottomTab.setTabTextColors(R.color.tabUnselected,R.color.tabSelected);
        //mPager.setPagingEnabled(false);
        mPager.setNoScroll(true);
        mPager.setOffscreenPageLimit(3);
//        Bundle bundle = new Bundle();
//        bundle.putInt("type_fragment", type_fragment);
//        bundle.putInt("total_numbers", total_numbers);
//        bundle.putString("search_url", search_url);
//        bundle.putInt("from", from);
//        passengerResourceFragment.setArguments(bundle);
//        houseResourceFragment.setArguments(bundle);
        setTabs();
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
                    Log.d("maindddd", 0000 + "");
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

    private void setTabs() {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addTab(houseResourceFragment, "房源");
        mainPagerAdapter.addTab(passengerResourceFragment, "客源");
        mainPagerAdapter.addTab(myFragment, "我的");
        mPager.setAdapter(mainPagerAdapter);
        mPager.setOffscreenPageLimit(3);
        mPager.setCurrentItem(fragment);
        //把tabLayout和Viewpager关联起来
        mBottomTab.setupWithViewPager(mPager);
        mBottomTab.setTabGravity(TabLayout.GRAVITY_FILL);
        mHouseSource = mBottomTab.getTabAt(0);
        mPassengerSource = mBottomTab.getTabAt(1);
        mMy = mBottomTab.getTabAt(2);
        if (fragment == 1) {
            mHouseSource.setIcon(R.mipmap.house);
            mPassengerSource.setIcon(R.mipmap.red_peoples);
            mMy.setIcon(R.mipmap.people);
        } else {
            mHouseSource.setIcon(R.mipmap.red_house);
            mPassengerSource.setIcon(R.mipmap.peoples);
            mMy.setIcon(R.mipmap.people);
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
            usedEditor.clear().commit();
            rentEditor.clear().commit();
            newEditor.clear().commit();
            usedPassengerEditor.clear().commit();
            rentPassengerEditor.clear().commit();
            newPassengerEditor.clear().commit();
            editor1.clear().commit();
            editor2.clear().commit();
            editor3.clear().commit();
            editor4.clear().commit();
            editor5.clear().commit();
            editor6.clear().commit();
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        fragment = getIntent().getIntExtra("fragment", 0);
        type_fragment = getIntent().getIntExtra("type_fragment", 0);
        total_numbers = getIntent().getIntExtra("total_numbers", 0);
        search_url = getIntent().getStringExtra("search_url");
        from = getIntent().getIntExtra("from", 0);
        Log.d("MainActivity","fragment"+fragment+"type_fragment"+type_fragment+"total_numbers"+total_numbers+"search_url"+search_url
        +"from"+from);
        initViews();
        intent.putExtra("search_url",search_url);
        intent.putExtra("fragment",fragment);
        intent.putExtra("total_numbers",total_numbers);
        intent.putExtra("from",from);
        intent.putExtra("type_fragment",type_fragment);
        Log.d("MainActivity","onNewIntent()..");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy()..");
        usedEditor.clear().commit();
        rentEditor.clear().commit();
        newEditor.clear().commit();
        usedPassengerEditor.clear().commit();
        rentPassengerEditor.clear().commit();
        newPassengerEditor.clear().commit();
        editor1.clear().commit();
        editor2.clear().commit();
        editor3.clear().commit();
        editor4.clear().commit();
        editor5.clear().commit();
        editor6.clear().commit();
    }

}
