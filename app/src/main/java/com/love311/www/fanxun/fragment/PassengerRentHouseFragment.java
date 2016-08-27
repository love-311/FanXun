package com.love311.www.fanxun.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.love311.www.fanxun.R;
import com.love311.www.fanxun.activity.PassengerSourceDetailActivity;
import com.love311.www.fanxun.adapter.PassengerRentHouseRecycleViewAdapter;
import com.love311.www.fanxun.adapter.RentHouseRecycleViewAdapter;
import com.love311.www.fanxun.application.MyApplication;
import com.love311.www.fanxun.bean.PassengerRentHouseBean;
import com.love311.www.fanxun.bean.PassengerUsedHouseBean;
import com.love311.www.fanxun.bean.RentHouseBean;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.love311.www.fanxun.custom.SuperSwipeRefreshLayout;
import com.love311.www.fanxun.viewholder.MyItemClickListener;
import com.love311.www.fanxun.viewholder.MyItemLongClickListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/11.
 */
public class PassengerRentHouseFragment extends LazyLoadFragment implements MyItemClickListener, MyItemLongClickListener {

    private RecyclerView rentHouseRecycle;
    private SuperSwipeRefreshLayout usedSwipeRefresh;
    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;
    private PassengerRentHouseRecycleViewAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;
    //数据解析
    private String url = "admin/rentCustomer/ajax/myAndCommonRentCustomer?search.status_eq=noFinish&page.pn=1&page.size=10";
    private MyApplication my;
    private static String URL;
    private List<PassengerRentHouseBean.ResBean.ContentBean> bean;
    //租房房屋界面
    @Override
    public int getLayout() {
        return R.layout.passenger_rent_house_fragment;
    }

    @Override
    public void initViews(View view) {
        my = (MyApplication) getActivity().getApplication();
        URL = my.getURL() +url;
        usedSwipeRefresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.passenger_rent_swipe_refresh);
        rentHouseRecycle = (RecyclerView) view.findViewById(R.id.passenger_rent_house_recycle);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rentHouseRecycle.setLayoutManager(linearLayoutManager);
        myAdapter = new PassengerRentHouseRecycleViewAdapter(getActivity());
        rentHouseRecycle.setAdapter(myAdapter);
        usedSwipeRefresh.setHeaderView(createHeaderView());// add headerView
        usedSwipeRefresh.setFooterView(createFooterView());
        usedSwipeRefresh.setHeaderViewBackgroundColor(0xff888888);
        usedSwipeRefresh.setTargetScrollWithLayout(true);
        this.myAdapter.setOnItemClickListener(this);
        this.myAdapter.setOnItemLongClickListener(this);
        usedSwipeRefresh
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
                        loadData();
                        textView.setText("正在刷新");
                        imageView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                usedSwipeRefresh.setRefreshing(false);
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 2000);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        //TODO 下拉距离
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        //TODO 下拉过程中，下拉的距离是否足够出发刷新
                        textView.setText(enable ? "松开刷新" : "下拉刷新");
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setRotation(enable ? 180 : 0);
                    }
                });

        usedSwipeRefresh
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

                    @Override
                    public void onLoadMore() {
                        loadData();
                        footerTextView.setText("正在加载...");
                        footerImageView.setVisibility(View.GONE);
                        footerProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                footerImageView.setVisibility(View.VISIBLE);
                                footerProgressBar.setVisibility(View.GONE);
                                usedSwipeRefresh.setLoadMore(false);
                            }
                        }, 5000);
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
                        footerImageView.setVisibility(View.VISIBLE);
                        footerImageView.setRotation(enable ? 0 : 180);
                    }

                    @Override
                    public void onPushDistance(int distance) {

                    }

                });
        loadData();
    }

    @Override
    public void loadData() {
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("PassengerRentHouse","http error!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("PassengerRentHouse",response);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<LinkedList<PassengerRentHouseBean.ResBean.ContentBean>>(){}.getType();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 =  jsonObject.getJSONObject("res");
                            JSONArray jsonArray = jsonObject1.getJSONArray("content");
                            Log.d("jsonElements--------",jsonArray.toString());
                            while (bean==null){
                                bean = gson.fromJson(jsonArray.toString(), listType);
                                myAdapter.addAll(bean, 0);
                            };
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /**
     * create Header View
     */
    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View headerView = LayoutInflater.from(usedSwipeRefresh.getContext())
                .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.down_arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(usedSwipeRefresh.getContext())
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.drawable.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent(getActivity(), PassengerSourceDetailActivity.class);
        intent.putExtra("id", bean.get(postion).getId());
        intent.putExtra("type_fragment",1);
        startActivity(intent);
        Toast.makeText(getActivity(), "点击了" + postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        Toast.makeText(getActivity(), "长击了" + postion, Toast.LENGTH_SHORT).show();
    }
}
