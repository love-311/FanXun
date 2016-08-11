package com.love311.www.fanxun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.custom.LazyLoadFragment;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MyFragment extends LazyLoadFragment {

    //我的界面
    @Override
    public int getLayout() {
        return R.layout.my_fragment;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void loadData() {

    }
}
