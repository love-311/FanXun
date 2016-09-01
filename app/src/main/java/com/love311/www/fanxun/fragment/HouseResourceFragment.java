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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.AddUsedHouseActivity;
import com.love311.www.fanxun.activity.MainActivity;
import com.love311.www.fanxun.activity.SearchActivity;
import com.love311.www.fanxun.adapter.HouseSourcePagerAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.UsedHouseBean;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Call;

public class HouseResourceFragment extends LazyLoadFragment {

    private TabLayout topTab;
    private ImageView ivSearch, ivSort, ivAdd;
    private ViewPager mainPager;
    private UsedHouseFragment usedHouseFragment;
    private RentHouseFragment rentHouseFragment;
    private NewHouseFragment newHouseFragment;
    private int i;
    //搜索传递过来的数据
    private int current_fragment;
    private int total_numbers;
    private String search_url;
    private int from;
    private MyApplication my;
    //二手房排序
    private static String URL1;
    private static String URL2;
    private static String URL3;
    private static String URL4;
    private String priceAscUrl = "admin/houses/getOldHouses?search.status_eq=pass&sort.salePrice=asc";
    private String priceDescUrl = "admin/houses/getOldHouses?search.status_eq=pass&sort.salePrice=desc";
    private String timeAscUrl = "admin/houses/getOldHouses?search.status_eq=pass&sort.createDate=asc";
    private String timeDescUrl = "admin/houses/getOldHouses?search.status_eq=pass&sort.createDate=desc";
    private UsedHouseBean.ResBean bean1;
    //租房排序
    private static String URL5;
    private static String URL6;
    private static String URL7;
    private static String URL8;
    private String rentPriceAscUrl = "admin/houses/getRentHouses?search.status_eq=pass&sort.salePrice=asc";
    private String rentPriceDescUrl = "admin/houses/getRentHouses?search.status_eq=pass&sort.salePrice=desc";
    private String rentTimeAscUrl = "admin/houses/getRentHouses?search.status_eq=pass&sort.createDate=asc";
    private String rentTimeDescUrl = "admin/houses/getRentHouses?search.status_eq=pass&sort.createDate=desc";
    //新房排序
    private static String URL9;
    private static String URL10;
    private static String URL11;
    private static String URL12;
    private String newPriceAscUrl = "admin/houses/getNewHouses?search.status_eq=pass&sort.salePrice=asc";
    private String newPriceDescUrl = "admin/houses/getNewHouses?search.status_eq=pass&sort.salePrice=desc";
    private String newTimeAscUrl = "admin/houses/getNewHouses?search.status_eq=pass&sort.createDate=asc";
    private String newTimeDescUrl = "admin/houses/getNewHouses?search.status_eq=pass&sort.createDate=desc";
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
        my = (MyApplication) getActivity().getApplication();
        URL1 = my.getURL() + priceAscUrl;
        URL2 = my.getURL() + priceDescUrl;
        URL3 = my.getURL() + timeAscUrl;
        URL4 = my.getURL() + timeDescUrl;
        URL5 = my.getURL() + rentPriceAscUrl;
        URL6 = my.getURL() + rentPriceDescUrl;
        URL7 = my.getURL() + rentTimeAscUrl;
        URL8 = my.getURL() + rentTimeDescUrl;
        URL9 = my.getURL() + newPriceAscUrl;
        URL10 = my.getURL() + newPriceDescUrl;
        URL11 = my.getURL() + newTimeAscUrl;
        URL12 = my.getURL() + newTimeDescUrl;
        search_url = getActivity().getIntent().getStringExtra("search_url");
        total_numbers = getActivity().getIntent().getIntExtra("total_numbers", 0);
        current_fragment = getActivity().getIntent().getIntExtra("type_fragment", 0);
        from = getActivity().getIntent().getIntExtra("from", 0);
        Log.d("MainActivity--", "type_fragment" + current_fragment + "total_numbers" + total_numbers + "search_url" + search_url
                + "from" + from);
        Intent intent = new Intent();
        intent.putExtra("search_url", search_url);
        intent.putExtra("total_numbers", total_numbers);
        intent.putExtra("from", from);
        intent.putExtra("type_fragment", current_fragment);
//        bundle1 = new Bundle();
//        bundle1.putInt("type_fragment", current_fragment);
//        bundle1.putInt("total_numbers", total_numbers);
//        bundle1.putString("search_url", search_url);
//        bundle1.putInt("from", from);
//        usedHouseFragment.setArguments(bundle1);
//        rentHouseFragment.setArguments(bundle1);
//        newHouseFragment.setArguments(bundle1);
        Log.d("HouseResourceFragment-", "type_fragment" + current_fragment);
        ivSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = mainPager.getCurrentItem();
                Log.d("HouseResourceFragment-", i + "");
                Intent intent = new Intent(getActivity(), AddUsedHouseActivity.class);
                intent.putExtra("type", i);
                startActivity(intent);
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = mainPager.getCurrentItem();
                Log.d("HouseResourceFragment-", i + "");
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type", i);
                startActivity(intent);
            }
        });
        setTabs();
    }

    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.house_search_pop_window, null);
        // 设置按钮的点击事件
        LinearLayout ll_1 = (LinearLayout) contentView.findViewById(R.id.ll_1);
        LinearLayout ll_2 = (LinearLayout) contentView.findViewById(R.id.ll_2);
        LinearLayout ll_3 = (LinearLayout) contentView.findViewById(R.id.ll_3);
        LinearLayout ll_4 = (LinearLayout) contentView.findViewById(R.id.ll_4);
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

        //价格升序
        ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainPager.getCurrentItem() == 0) {
                    usedPriceAscSort();
                } else if (mainPager.getCurrentItem() == 1) {
                    rentPriceAscSort();
                }else if (mainPager.getCurrentItem() == 2) {
                    newPriceAscSort();
                }
                popupWindow.dismiss();
            }
        });
        //价格降序
        ll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainPager.getCurrentItem() == 0) {
                    usedPriceDescSort();
                } else if (mainPager.getCurrentItem() == 1) {
                    rentPriceDescSort();
                }else if (mainPager.getCurrentItem() == 2) {
                    newPriceDescSort();
                }
                popupWindow.dismiss();
            }
        });
        //时间升序
        ll_3.setOnClickListener(new View.OnClickListener() {
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
        ll_4.setOnClickListener(new View.OnClickListener() {
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


    public void usedPriceAscSort() {
        OkHttpUtils
                .get()
                .url(URL1)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",1);
        intent.putExtra("search_url_sort", URL1);
        intent.putExtra("type_fragment_sort", 0);
        intent.putExtra("from_sort", 1);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void usedPriceDescSort() {
        OkHttpUtils
                .get()
                .url(URL2)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",1);
        intent.putExtra("search_url_sort", URL2);
        intent.putExtra("type_fragment_sort", 0);
        intent.putExtra("from_sort", 1);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
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
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",1);
        intent.putExtra("search_url_sort", URL3);
        intent.putExtra("type_fragment_sort", 0);
        intent.putExtra("from_sort", 1);
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
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",1);
        intent.putExtra("search_url_sort", URL4);
        intent.putExtra("type_fragment_sort", 0);
        intent.putExtra("from_sort", 1);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void rentPriceAscSort() {
        OkHttpUtils
                .get()
                .url(URL5)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",2);
        intent.putExtra("search_url_sort", URL5);
        intent.putExtra("type_fragment_sort", 1);
        intent.putExtra("from_sort", 2);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void rentPriceDescSort() {
        OkHttpUtils
                .get()
                .url(URL6)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",2);
        intent.putExtra("search_url_sort", URL6);
        intent.putExtra("type_fragment_sort", 1);
        intent.putExtra("from_sort", 2);
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
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",2);
        intent.putExtra("search_url_sort", URL7);
        intent.putExtra("type_fragment_sort", 1);
        intent.putExtra("from_sort", 2);
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
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",2);
        intent.putExtra("search_url_sort", URL8);
        intent.putExtra("type_fragment_sort", 1);
        intent.putExtra("from_sort", 2);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }

    public void newPriceAscSort() {
        OkHttpUtils
                .get()
                .url(URL9)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",3);
        intent.putExtra("search_url_sort", URL9);
        intent.putExtra("type_fragment_sort", 2);
        intent.putExtra("from_sort", 3);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }
    public void newPriceDescSort() {
        OkHttpUtils
                .get()
                .url(URL10)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",3);
        intent.putExtra("search_url_sort", URL10);
        intent.putExtra("type_fragment_sort", 2);
        intent.putExtra("from_sort", 3);
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
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",3);
        intent.putExtra("search_url_sort", URL11);
        intent.putExtra("type_fragment_sort", 2);
        intent.putExtra("from_sort", 3);
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
                        Type listType1 = new TypeToken<UsedHouseBean.ResBean>() {
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
        intent.putExtra("sort_status",3);
        intent.putExtra("search_url_sort", URL12);
        intent.putExtra("type_fragment_sort", 2);
        intent.putExtra("from_sort", 3);
        intent.putExtra("total_numbers_sort", total_numbers);
        startActivity(intent);
    }
    private void setTabs() {
        HouseSourcePagerAdapter mPagerAdapter = new HouseSourcePagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTab(usedHouseFragment, "二手房");
        mPagerAdapter.addTab(rentHouseFragment, "租房");
        mPagerAdapter.addTab(newHouseFragment, "新房");
        mainPager.setAdapter(mPagerAdapter);
        mainPager.setOffscreenPageLimit(3);
        mainPager.setCurrentItem(current_fragment);
        Log.d("sdsddd", current_fragment + "");
        //把tabLayout和Viewpager关联起来
        topTab.setupWithViewPager(mainPager);
        topTab.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    //加载数据
    @Override
    public void loadData() {
    }

}
