package com.annie.wanglei.mytest1.presenter;

import android.app.Activity;
import android.content.Context;

import com.annie.wanglei.mytest1.baselibrary.base.BasePresenterImp;
import com.annie.wanglei.mytest1.interactor.MyInteractor;
import com.annie.wanglei.mytest1.interactor.MyInteractorImp;
import com.annie.wanglei.mytest1.view.LoginView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wanglei on 2019/1/29.
 */

public class MyPresenterImp extends BasePresenterImp implements MyPresenter {
    private Context context;
    private LoginView loginView;
    private MyInteractor interactor;

    public MyPresenterImp(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    @Override
    public void initViewAndData() {
        interactor = new MyInteractorImp(context, this);
    }

    @Override
    public void login(String phone, String password) {
        loginView.showLoad();
        interactor.login(phone, password);
    }

    @Override
    public void Success(int what, Object result) {
        if (loginView != null) {
            loginView.dismissLoad();
        }
        if (what == 1) {
            /**
             * 请求成功用eventBus更新界面
             */
            EventBus.getDefault().post(result);
        }
    }

    @Override
    public void Error(int what, String error) {
        if (loginView != null) {
            loginView.dismissLoad();
            loginView.showInfo(error);
        }
    }
}
