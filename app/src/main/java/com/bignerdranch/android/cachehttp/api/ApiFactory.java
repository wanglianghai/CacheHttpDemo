package com.bignerdranch.android.cachehttp.api;

/**
 * Created by Administrator on 2017/8/7/007.
 */

public class ApiFactory {
    private static ZhihuApi sZhihuApi;
    public static ZhihuApi getZhiHuApi() {
        if (sZhihuApi == null) {
            sZhihuApi = new ApiRetrofit().getZhihuApi();
        }

        return sZhihuApi;
    }
}
