package com.annie.wanglei.mytest1.baselibrary.utils.NetUtils;

import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Mark.Han on 2017/8/15.
 */

public interface RequestListener<T> {

    void Success(int what, T result);

    void Error(int what, String error);
}
