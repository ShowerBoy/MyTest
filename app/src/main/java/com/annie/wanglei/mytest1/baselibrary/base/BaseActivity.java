package com.annie.wanglei.mytest1.baselibrary.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.annie.wanglei.mytest1.ActivityCollector;
import com.annie.wanglei.mytest1.baselibrary.utils.ToastHelp;

import org.greenrobot.eventbus.EventBus;


/**
 * Activity的基类
 * Created by Mark.Han on 2017/7/10.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
//    private Unbinder mUnbinder;
    private boolean register;
    private Intent intent;

    public void setRegister(boolean register) {
        this.register = register;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityCollector.addActivity(this);
        if (register) {
            EventBus.getDefault().register(this);
        }
        mPresenter = getPresenter();
        if (mPresenter != null) {
            if (this instanceof BaseView)
                mPresenter.attach((BaseView) this);
        }
        initView();
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        mUnbinder = ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P getPresenter();

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if (register) {
            EventBus.getDefault().unregister(this);
        }
//        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    protected void showDialogTip(int id) {
        ToastHelp.show(this, "此时该弹第i个加载框");
    }

    protected void cancelDialogTip(int id) {
        ToastHelp.show(this, "此时该关闭第i个加载框");
    }

    protected void jumpToActivity(Class aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

}
