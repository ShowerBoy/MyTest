package com.annie.wanglei.mytest1.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Mark.Han on 2017/7/10.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mView;
    protected static String Tag;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        initView(mView);
        initData();
        return mView;
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getLayoutId();

    public static String getFlagTag() {
        return Tag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
