package com.love311.www.fanxun.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.PassengerRentHouseRecycleViewAdapter;
import com.love311.www.fanxun.adapter.RentHouseRecycleViewAdapter;
import com.love311.www.fanxun.custom.LazyLoadFragment;
import com.love311.www.fanxun.custom.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class PassengerRentHouseFragment extends LazyLoadFragment {

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
    //租房房屋界面
    @Override
    public int getLayout() {
        return R.layout.passenger_rent_house_fragment;
    }

    @Override
    public void initViews(View view) {
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
        usedSwipeRefresh
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
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
                        // TODO Auto-generated method stub

                    }

                });
        loadData();
    }

    @Override
    public void loadData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            list.add("item " + i);
        }
        myAdapter.addAll(list, 0);
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
}