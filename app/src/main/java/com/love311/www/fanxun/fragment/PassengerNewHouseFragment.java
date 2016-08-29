package com.love311.www.fanxun.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.UsedHouseDetailActivity;
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
    private String url = "admin/saleCustomer/ajax/myAndCommonSaleCustomer?search.type_eq=newHouses";
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
    private static final int TOTAL_COUNTER = 2340;
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    private int oooo;
    private boolean isRefresh = false;
    //客源新房房屋界面
    @Override
    public int getLayout() {
        return R.layout.passenger_new_house_fragment;
    }

    @Override
    public void initViews(View view) {
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
        newHouseRecycle.forceToRefresh();
        newHouseRecycle.refreshComplete();
        mLRecyclerViewAdapter.notifyDataSetChanged();
        newHouseRecycle.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(newHouseRecycle, LoadingFooter.State.Normal);
                myAdapter.clear();
                mCurrentCounter = 0;
                isRefresh = true;
                oooo = 0;
                loadData();
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
                    loadData();
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
                Intent intent = new Intent(getActivity(), UsedHouseDetailActivity.class);
                Log.d("UsedHouseFragment-==", bean.size() + "");
                intent.putExtra("id", myAdapter.getDataList().get(position).getId());
                intent.putExtra("type_fragment", 1);
                startActivity(intent);
                Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(), "长击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loadData() {
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
                        Gson gson = new Gson();
                        try {
                            Log.d("jsonElements--------", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("res");
                            JSONArray jsonArray = jsonObject1.getJSONArray("content");
                            Log.d("jsonElements--------", jsonArray.toString());
                            bean = gson.fromJson(jsonArray.toString(), listType);
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
