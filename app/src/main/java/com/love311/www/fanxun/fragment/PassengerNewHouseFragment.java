package com.love311.www.fanxun.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.PassengerSourceDetailActivity;
import com.love311.www.fanxun.adapter.PassengerNewHouseRecycleViewAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.PassengerNewHouseBean;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/11.
 */
public class PassengerNewHouseFragment extends LazyLoadFragment {

    private LRecyclerView newHouseRecycle;
    private PassengerNewHouseRecycleViewAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private MyApplication my;
    //数据解析
    private String url = "admin/saleCustomer/ajax/myAndCommonSaleCustomer?search.type_eq=newHouses&search.status_eq=noFinish";
    private static String URL;
    private LinkedList<PassengerNewHouseBean.ResBean.ContentBean> bean;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    /**
     * 已经获取到多少条数据了
     */
    private static int mCurrentCounter = 0;
    /**
     * 服务器端一共多少条数据
     */
    private static  int TOTAL_COUNTER = 0;
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    private int oooo;
    private boolean isRefresh = false;
    //搜索传递过来的数据
    private int current_fragment;
    private int total_numbers;
    private String search_url;
    private int from;
    private PassengerNewHouseBean.ResBean bean1;
    //记录列表状态
    private SharedPreferences passengerNewSharedPreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences getPassengerNewSharedPreferences;
    //客源新房房屋界面
    @Override
    public int getLayout() {
        return R.layout.passenger_new_house_fragment;
    }

    @Override
    public void initViews(View view) {
        passengerNewSharedPreferences = getActivity().getSharedPreferences("passenger_new", Context.MODE_PRIVATE);
        editor = passengerNewSharedPreferences.edit();
        getPassengerNewSharedPreferences = getActivity().getSharedPreferences("passenger_rent",Context.MODE_PRIVATE);
        from = 0;
        if (getActivity().getIntent().getIntExtra("from",-1)==6){
            if (getActivity().getIntent().getIntExtra("sort_status", 0) == 6) {
                Log.d("search_url_sort=",search_url);
                search_url = getActivity().getIntent().getStringExtra("search_url_sort");
                current_fragment = getActivity().getIntent().getIntExtra("type_fragment_sort", 0);
                from = getActivity().getIntent().getIntExtra("from_sort", 0);
                total_numbers = getActivity().getIntent().getIntExtra("total_numbers_sort", 0);
                editor.clear().commit();
                editor.putString("search_url", search_url);
                editor.putInt("total_numbers", total_numbers);
                editor.putInt("current_fragment", current_fragment);
                editor.putInt("from", from);
                editor.commit();
            }else {
                search_url = getActivity().getIntent().getStringExtra("search_url");
                total_numbers = getActivity().getIntent().getIntExtra("total_numbers", 0);
                current_fragment = getActivity().getIntent().getIntExtra("type_fragment", 0);
                from = getActivity().getIntent().getIntExtra("from", 0);
                Log.d("loadData", "search_url=" + search_url);
                TOTAL_COUNTER = total_numbers;
                editor.clear().commit();
                editor.putString("search_url", search_url);
                editor.putInt("total_numbers", total_numbers);
                editor.putInt("current_fragment", current_fragment);
                editor.putInt("from", from);
                editor.commit();
            }
        }else {
            search_url = getPassengerNewSharedPreferences.getString("search_url","");
            total_numbers = getPassengerNewSharedPreferences.getInt("total_numbers",0);
            current_fragment = getPassengerNewSharedPreferences.getInt("current_fragment",0);
            from = getPassengerNewSharedPreferences.getInt("from",0);
            TOTAL_COUNTER = total_numbers;
        }
        oooo = 0;
        my = (MyApplication) getActivity().getApplication();
        URL = my.getURL() +url;
        newHouseRecycle = (LRecyclerView) view.findViewById(R.id.passenger_new_house_recycle);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        newHouseRecycle.setLayoutManager(linearLayoutManager);
        myAdapter = new PassengerNewHouseRecycleViewAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(getActivity(), myAdapter);
        newHouseRecycle.setAdapter(mLRecyclerViewAdapter);
        newHouseRecycle.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        newHouseRecycle.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头
//        newHouseRecycle.forceToRefresh();
//        newHouseRecycle.refreshComplete();
//        mLRecyclerViewAdapter.notifyDataSetChanged();
        newHouseRecycle.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(newHouseRecycle, LoadingFooter.State.Normal);
                myAdapter.clear();
                mCurrentCounter = 0;
                isRefresh = true;
                oooo = 0;
                if (getActivity().getIntent().getIntExtra("sort_status", 0) == 6) {
                    Log.d("search_url_sort=",search_url);
                    search_url = getActivity().getIntent().getStringExtra("search_url_sort");
                    current_fragment = getActivity().getIntent().getIntExtra("type_fragment_sort", 0);
                    from = getActivity().getIntent().getIntExtra("from_sort", 0);
                    total_numbers = getActivity().getIntent().getIntExtra("total_numbers_sort", 0);
                    editor.clear().commit();
                    editor.putString("search_url", search_url);
                    editor.putInt("total_numbers", total_numbers);
                    editor.putInt("current_fragment", current_fragment);
                    editor.putInt("from", from);
                    editor.commit();
                    loadSortData();
                }else if (from == 6) {
                    loadSearchData();
                    Log.d("loadSearchData", "loadSearchData()执行了");
                } else {
                    loadNormalData();
                    Log.d("loadData", "loadData()执行了");
                }
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(newHouseRecycle);
                if (state == LoadingFooter.State.Loading) {
                    Log.d("LRecycleView---", "the state is Loading, just wait..");
                    return;
                }
                Log.d("mCurrentCounter==", mCurrentCounter + "");
                Log.d("TOTAL_COUNTER==", TOTAL_COUNTER + "");
                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    RecyclerViewStateUtils.setFooterViewState(getActivity(), newHouseRecycle, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                    if (getActivity().getIntent().getIntExtra("sort_status", 0) == 6) {
                        Log.d("search_url_sort=",search_url);
                        search_url = getActivity().getIntent().getStringExtra("search_url_sort");
                        current_fragment = getActivity().getIntent().getIntExtra("type_fragment_sort", 0);
                        from = getActivity().getIntent().getIntExtra("from_sort", 0);
                        total_numbers = getActivity().getIntent().getIntExtra("total_numbers_sort", 0);
                        editor.clear().commit();
                        editor.putString("search_url", search_url);
                        editor.putInt("total_numbers", total_numbers);
                        editor.putInt("current_fragment", current_fragment);
                        editor.putInt("from", from);
                        editor.commit();
                        loadSortData();
                    }else if (from == 6) {
                        loadSearchData();
                        Log.d("loadSearchData", "loadSearchData()执行了");
                    } else {
                        loadNormalData();
                        Log.d("loadData", "loadData()执行了");
                    }
                } else {
                    //the end
                    RecyclerViewStateUtils.setFooterViewState(getActivity(), newHouseRecycle, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
                }
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
        newHouseRecycle.setRefreshing(true);

        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PassengerSourceDetailActivity.class);
                Log.d("UsedHouseFragment-==", bean.size() + "");
                intent.putExtra("id", myAdapter.getDataList().get(position).getId());
                intent.putExtra("type_fragment", 2);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
    }

    @Override
    public void loadData() {

    }

    public void loadNormalData() {
        oooo = oooo + 1;
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("page.pn", oooo + "")
                .addParams("page.size", 10 + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("UsedHouseFragment", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("UsedHouseFragment", "response");
                        if (isRefresh) {
                            myAdapter.clear();
                            mCurrentCounter = 0;
                        }

                        //int currentSize = myAdapter.getItemCount();
                        Type listType = new TypeToken<LinkedList<PassengerNewHouseBean.ResBean.ContentBean>>() {
                        }.getType();
                        Type listType1 = new TypeToken<PassengerNewHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("jsonElements--------", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            JSONArray jsonArray = jsonObject1.getJSONArray("content");
                            Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(),listType1);
                            bean = gson.fromJson(jsonArray.toString(), listType);
                            TOTAL_COUNTER = bean1.getTotalElements();
                            //myAdapter.addAll(bean, 0);
                            addItems(bean);
                            if (isRefresh) {
                                isRefresh = false;
                                newHouseRecycle.refreshComplete();
                                notifyDataSetChanged();
                            } else {
                                RecyclerViewStateUtils.setFooterViewState(newHouseRecycle, LoadingFooter.State.Normal);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void loadSearchData() {
        oooo = oooo + 1;
        OkHttpUtils
                .get()
                .url(search_url)
                .addParams("page.pn", oooo + "")
                .addParams("page.size", 10 + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("UsedHouseFragment", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("UsedHouseFragment", "response");
                        if (isRefresh) {
                            myAdapter.clear();
                            mCurrentCounter = 0;
                        }

                        //int currentSize = myAdapter.getItemCount();
                        Type listType = new TypeToken<LinkedList<PassengerNewHouseBean.ResBean.ContentBean>>() {
                        }.getType();
                        Type listType1 = new TypeToken<PassengerNewHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("jsonElements--------", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            JSONArray jsonArray = jsonObject1.getJSONArray("content");
                            Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(),listType1);
                            bean = gson.fromJson(jsonArray.toString(), listType);
                            TOTAL_COUNTER = bean1.getTotalElements();
                            //myAdapter.addAll(bean, 0);
                            addItems(bean);
                            if (isRefresh) {
                                isRefresh = false;
                                newHouseRecycle.refreshComplete();
                                notifyDataSetChanged();
                            } else {
                                RecyclerViewStateUtils.setFooterViewState(newHouseRecycle, LoadingFooter.State.Normal);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void loadSortData() {
        oooo = oooo + 1;
        OkHttpUtils
                .get()
                .url(search_url)
                .addParams("page.pn", oooo + "")
                .addParams("page.size", 10 + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("UsedHouseFragment", "http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("UsedHouseFragment", "response");
                        if (isRefresh) {
                            myAdapter.clear();
                            mCurrentCounter = 0;
                        }

                        //int currentSize = myAdapter.getItemCount();
                        Type listType = new TypeToken<LinkedList<PassengerNewHouseBean.ResBean.ContentBean>>() {
                        }.getType();
                        Type listType1 = new TypeToken<PassengerNewHouseBean.ResBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        try {
                            Log.d("jsonElements--------", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            JSONArray jsonArray = jsonObject1.getJSONArray("content");
                            Log.d("jsonElements--------", jsonArray.toString());
                            bean1 = gson.fromJson(jsonObject1.toString(),listType1);
                            bean = gson.fromJson(jsonArray.toString(), listType);
                            TOTAL_COUNTER = bean1.getTotalElements();
                            //myAdapter.addAll(bean, 0);
                            addItems(bean);
                            if (isRefresh) {
                                isRefresh = false;
                                newHouseRecycle.refreshComplete();
                                notifyDataSetChanged();
                            } else {
                                RecyclerViewStateUtils.setFooterViewState(newHouseRecycle, LoadingFooter.State.Normal);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void addItems(LinkedList<PassengerNewHouseBean.ResBean.ContentBean> list) {
        Log.d("addItems----", mCurrentCounter + "");
        myAdapter.addAll(list, mCurrentCounter);
        mCurrentCounter += list.size();

    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }
}
