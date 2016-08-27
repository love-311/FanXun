package com.love311.www.fanxun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.AddUsedHouseActivity;
import com.love311.www.fanxun.activity.SearchActivity;
import com.love311.www.fanxun.adapter.HouseSourcePagerAdapter;
import com.love311.www.fanxun.custom.LazyLoadFragment;

public class HouseResourceFragment extends LazyLoadFragment {

    private TabLayout topTab;
    private ImageView ivSearch, ivSort, ivAdd;
    private ViewPager mainPager;
    private UsedHouseFragment usedHouseFragment;
    private RentHouseFragment rentHouseFragment;
    private NewHouseFragment newHouseFragment;
    private int i;
    private int current_fragment;

    @Override
    public int getLayout() {
        return R.layout.house_source_fragment;
    }

    @Override
    public void initViews(View view) {
        mainPager = (ViewPager) view.findViewById(R.id.house_pager);
        topTab = (TabLayout) view.findViewById(R.id.top_house_tab);
        ivSort = (ImageView) view.findViewById(R.id.iv_house_sort);
        ivAdd = (ImageView) view.findViewById(R.id.iv_house_add);
        ivSearch = (ImageView) view.findViewById(R.id.iv_house_search);
        usedHouseFragment = new UsedHouseFragment();
        rentHouseFragment = new RentHouseFragment();
        newHouseFragment = new NewHouseFragment();
        Bundle bundle = getArguments();
        current_fragment=bundle.getInt("type_fragment");
        Log.d("HouseResourceFragment-","type_fragment"+current_fragment);
        ivSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i =mainPager.getCurrentItem();
                Log.d("HouseResourceFragment-",i+"");
                Intent intent = new Intent(getActivity(), AddUsedHouseActivity.class);
                intent.putExtra("type",i);
                startActivity(intent);
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i =mainPager.getCurrentItem();
                Log.d("HouseResourceFragment-",i+"");
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type",i);
                startActivity(intent);
            }
        });
        topTab.setTabGravity(TabLayout.GRAVITY_FILL);
        setTabs();
    }

    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.top_pop_window, null);
        // 设置按钮的点击事件
        //  Button button = (Button) contentView.findViewById(R.id.button1);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "button is pressed",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.color.popwindow));
        popupWindow.showAsDropDown(view,-view.getWidth()-60,10);
        // 设置好参数之后再show
        //popupWindow.showAsDropDown(view);

    }

    private void setTabs() {
        HouseSourcePagerAdapter mPagerAdapter = new HouseSourcePagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTab(usedHouseFragment, "二手房");
        mPagerAdapter.addTab(rentHouseFragment, "租房");
        mPagerAdapter.addTab(newHouseFragment, "新房");
        mainPager.setAdapter(mPagerAdapter);
        mainPager.setOffscreenPageLimit(3);
        mainPager.setCurrentItem(current_fragment);
        //把tabLayout和Viewpager关联起来
        topTab.setupWithViewPager(mainPager);
    }

    //加载数据
    @Override
    public void loadData() {
    }
}
