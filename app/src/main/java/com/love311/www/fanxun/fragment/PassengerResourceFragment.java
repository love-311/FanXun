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
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.AddPassengerActivity;
import com.love311.www.fanxun.activity.MainActivity;
import com.love311.www.fanxun.activity.SearchPassengerActivity;
import com.love311.www.fanxun.adapter.PassengerSourcePagerAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.PassengerUsedHouseBean;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Call;

public class PassengerResourceFragment extends LazyLoadFragment {

    private TabLayout topTab;
    private ImageView ivSearch, ivSort, ivAdd;
    private ViewPager mainPager;
    private PassengerUsedHouseFragment passengerUsedHouseFragment;
    private PassengerRentHouseFragment passengerRentHouseFragment;
    private PassengerNewHouseFragment passengerNewHouseFragment;
    private int i;
    //搜索传递过来的数据
    private int current_fragment;
    private int total_numbers;
    private String search_url;
    private int from;
    //private Bundle bundle1;
    private MyApplication my;
    //二手房排序
    private static String URL3;
    private static String URL4;
    private String timeAscUrl = "admin/saleCustomer/ajax/myAndCommonSaleCustomer?search.type_eq=oldHouses&search.status_eq=noFinish&sort.createDate=asc";
    private String timeDescUrl = "admin/saleCustomer/ajax/myAndCommonSaleCustomer?search.type_eq=oldHouses&search.status_eq=noFinish&sort.createDate=desc";
    private PassengerUsedHouseBean.ResBean bean1;
    //租房排序
    private static String URL7;
    private static String URL8;
    private String rentTimeAscUrl = "admin/rentCustomer/ajax/myAndCommonRentCustomer?search.status_eq=noFinish&sort.createDate=asc";
    private String rentTimeDescUrl = "admin/rentCustomer/ajax/myAndCommonRentCustomer?search.status_eq=noFinish&sort.createDate=desc";
    //新房排序
    private static String URL11;
    private static String URL12;
    private String newTimeAscUrl = "admin/saleCustomer/ajax/myAndCommonSaleCustomer?search.type_eq=newHouses&search.status_eq=noFinish&sort.createDate=asc";
    private String newTimeDescUrl = "admin/saleCustomer/ajax/myAndCommonSaleCustomer?search.type_eq=newHouses&search.status_eq=noFinish&sort.createDate=desc";
    @Override
    public int getLayout() {
        return R.layout.passenger_source_fragment;
    }

    @Override
    public void initViews(View view) {
        mainPager = (ViewPager) view.findViewById(R.id.main_passenger_pager);
        topTab = (TabLayout) view.findViewById(R.id.top_passenger_tab);
        ivAdd = (ImageView) view.findViewById(R.id.iv_passenger_add);
        ivSort = (ImageView) view.findViewById(R.id.iv_passenger_sort);
        ivSearch = (ImageView) view.findViewById(R.id.iv_passenger_search);
        passengerUsedHouseFragment = new PassengerUsedHouseFragment();
        passengerRentHouseFragment = new PassengerRentHouseFragment();
        passengerNewHouseFragment = new PassengerNewHouseFragment();
        my = (MyApplication) getActivity().getApplication();
        URL3 = my.getURL() + timeAscUrl;
        URL4 = my.getURL() + timeDescUrl;
        URL7 = my.getURL() + rentTimeAscUrl;
        URL8 = my.getURL() + rentTimeDescUrl;
        URL11 = my.getURL() + newTimeAscUrl;
        URL12 = my.getURL() + newTimeDescUrl;
        search_url=getActivity().getIntent().getStringExtra("search_url");
        total_numbers=getActivity().getIntent().getIntExtra("total_numbers",0);
        current_fragment=getActivity().getIntent().getIntExtra("type_fragment",0);
        from=getActivity().getIntent().getIntExtra("from",0);
        Intent intent = new Intent();
        intent.putExtra("search_url",search_url);
        intent.putExtra("total_numbers",total_numbers);
        intent.putExtra("from",from);
        intent.putExtra("type_fragment",current_fragment);
        ivSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = mainPager.getCurrentItem();
                Intent intent = new Intent(getActivity(), AddPassengerActivity.class);
                intent.putExtra("type", i);
                startActivity(intent);
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = mainPager.getCurrentItem();
                Intent intent = new Intent(getActivity(), SearchPassengerActivity.class);
                intent.putExtra("type", i);
                startActivity(intent);
            }
        });
        setTabs();
    }
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.passenger_sort_pop_window, null);
        // 设置按钮的点击事件
        LinearLayout ll_1 = (LinearLayout) contentView.findViewById(R.id.ll_1);
        LinearLayout ll_2 = (LinearLayout) contentView.findViewById(R.id.ll_2);
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
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view, -view.getWidth() - 60, 10);
        //时间升序
        ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainPager.getCurrentItem() == 0) {
                    usedTimeAscSort();
                } else if (mainPager.getCurrentItem() == 1) {
                    rentTimeAscSort();
                }else if (mainPager.getCurrentItem() == 2) {
                    newTimeAscSort();
                }
                popupWindow.dismiss();
            }
        });
        //时间降序
        ll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainPager.getCurrentItem() == 0) {
                    usedTimeDescSort();
                } else if (mainPager.getCurrentItem() == 1) {
                    rentTimeDescSort();
                }else if (mainPager.getCurrentItem() == 2) {
                    newTimeDescSort();
                }
                popupWindow.dismiss();
            }
        });
    }



    public void usedTimeAscSort() {
        OkHttpUtils
                .get()
                .url(URL3)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<PassengerUsedHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("load_test----", "UsedHouseFragment加载数据");
                            //Log.d("jsonElements------00", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            //Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(), listType1);
                            total_numbers = bean1.getTotalElements();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragment",1);
        intent.putExtra("sort_status",4);
        intent.putExtra("search_url_sort", URL3);
        intent.putExtra("type_fragment_sort", 0);
        intent.putExtra("from_sort", 4);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void usedTimeDescSort() {
        OkHttpUtils
                .get()
                .url(URL4)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<PassengerUsedHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("load_test----", "UsedHouseFragment加载数据");
                            //Log.d("jsonElements------00", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            //Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(), listType1);
                            total_numbers = bean1.getTotalElements();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragment",1);
        intent.putExtra("sort_status",4);
        intent.putExtra("search_url_sort", URL4);
        intent.putExtra("type_fragment_sort", 0);
        intent.putExtra("from_sort", 4);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }


    public void rentTimeAscSort() {
        OkHttpUtils
                .get()
                .url(URL7)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<PassengerUsedHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("load_test----", "UsedHouseFragment加载数据");
                            //Log.d("jsonElements------00", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            //Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(), listType1);
                            total_numbers = bean1.getTotalElements();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragment",1);
        intent.putExtra("sort_status",5);
        intent.putExtra("search_url_sort", URL7);
        intent.putExtra("type_fragment_sort", 1);
        intent.putExtra("from_sort", 5);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void rentTimeDescSort() {
        OkHttpUtils
                .get()
                .url(URL8)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<PassengerUsedHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("load_test----", "UsedHouseFragment加载数据");
                            //Log.d("jsonElements------00", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            //Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(), listType1);
                            total_numbers = bean1.getTotalElements();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragment",1);
        intent.putExtra("sort_status",5);
        intent.putExtra("search_url_sort", URL8);
        intent.putExtra("type_fragment_sort", 1);
        intent.putExtra("from_sort", 5);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void newTimeAscSort() {
        OkHttpUtils
                .get()
                .url(URL11)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<PassengerUsedHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("load_test----", "UsedHouseFragment加载数据");
                            //Log.d("jsonElements------00", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            //Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(), listType1);
                            total_numbers = bean1.getTotalElements();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragment",1);
        intent.putExtra("sort_status",6);
        intent.putExtra("search_url_sort", URL11);
        intent.putExtra("type_fragment_sort", 2);
        intent.putExtra("from_sort", 6);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }
    public void newTimeDescSort() {
        OkHttpUtils
                .get()
                .url(URL12)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<PassengerUsedHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("load_test----", "UsedHouseFragment加载数据");
                            //Log.d("jsonElements------00", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            //Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(), listType1);
                            total_numbers = bean1.getTotalElements();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragment",1);
        intent.putExtra("sort_status",6);
        intent.putExtra("search_url_sort", URL12);
        intent.putExtra("type_fragment_sort", 2);
        intent.putExtra("from_sort", 6);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }
    @Override
    public void loadData() {

    }

    private void setTabs() {
        PassengerSourcePagerAdapter mPagerAdapter = new PassengerSourcePagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTab(passengerUsedHouseFragment, "二手房");
        mPagerAdapter.addTab(passengerRentHouseFragment, "租房");
        mPagerAdapter.addTab(passengerNewHouseFragment, "新房");
        mainPager.setAdapter(mPagerAdapter);
        mainPager.setOffscreenPageLimit(3);
        mainPager.setCurrentItem(current_fragment);
        //把tabLayout和Viewpager关联起来
        topTab.setupWithViewPager(mainPager);
        topTab.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
