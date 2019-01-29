package com.annie.wanglei.mytest1;

import android.app.Application;
import android.content.Context;

/**
 * Created by wanglei on 2019/1/29.
 */

public class MyApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
