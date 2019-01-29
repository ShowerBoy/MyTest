package com.annie.wanglei.mytest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.annie.wanglei.mytest1.baselibrary.base.BaseActivity;
import com.annie.wanglei.mytest1.baselibrary.base.BasePresenter;
import com.annie.wanglei.mytest1.presenter.MyPresenter;
import com.annie.wanglei.mytest1.presenter.MyPresenterImp;
import com.annie.wanglei.mytest1.view.LoginView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity implements LoginView {
    private Button button;
    private MyPresenter presenter;

    {
        setRegister(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登陆
                presenter.login("11111111", "111111");
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new MyPresenterImp(this, this);
        presenter.initViewAndData();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Subscribe
    public void onMainEventThread(Object object) {
        /**
         * 接受数据刷新页面
         */
    }

    @Override
    public void showInfo(String info) {
        //
    }

    @Override
    public void showLoad() {
        //显示loading
    }

    @Override
    public void dismissLoad() {
        //隐藏loading
    }
}
