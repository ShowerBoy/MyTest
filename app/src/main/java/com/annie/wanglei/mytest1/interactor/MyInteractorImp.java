package com.annie.wanglei.mytest1.interactor;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.annie.wanglei.mytest1.baselibrary.utils.NetUtils.NetWorkImp;
import com.annie.wanglei.mytest1.baselibrary.utils.NetUtils.RequestListener;
import com.annie.wanglei.mytest1.baselibrary.utils.NetUtils.request.FastJsonRequest;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by wanglei on 2019/1/29.
 */

public class MyInteractorImp extends NetWorkImp implements MyInteractor {
    private Context context;
    private RequestListener listener;

    public MyInteractorImp(Context context, RequestListener listener) {
        this.context = context;
        this.listener = listener;
    }


    @Override
    public void login(String phone, String password) {
        FastJsonRequest request = new FastJsonRequest("", RequestMethod.POST);
        request.add("phone", phone);
        request.add("password", password);
        addQueue(1, request);
        startQueue();
    }

    @Override
    protected void onNetWorkStart(int what) {

    }

    @Override
    protected void onNetWorkFinish(int what) {

    }

    @Override
    protected void onSuccess(int what, Object response) {
        String jsonString = response.toString();
        if (jsonString != null) {
            listener.Success(1, jsonString);
        }
    }

    @Override
    protected void onFail(int what, Response response) {
        String error = response.getException().getMessage();
        if (error != null) {
            listener.Error(1, error);
        }
    }
}
