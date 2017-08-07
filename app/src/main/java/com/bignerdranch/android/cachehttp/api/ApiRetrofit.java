package com.bignerdranch.android.cachehttp.api;

import com.bignerdranch.android.cachehttp.app.MyApp;
import com.bignerdranch.android.cachehttp.utils.NetConnect;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/7/007.
 */

public class ApiRetrofit {
    private ZhihuApi mZhihuApi;
    private static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/api/4/";

    //重新读取网络数据拦截器
    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //请求：无网络强制使用缓存
            Request request = chain.request();
            if (!NetConnect.isNetWorkConnect(MyApp.sContext)) {
                request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            //给自己应用看的头
            Response response = chain.proceed(request);
            if (NetConnect.isNetWorkConnect(MyApp.sContext)) {
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=0")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cache, max-style=" + maxStale)
                        .build();
            }
        }
    };

    public ApiRetrofit() {
        //设置缓存的大小和url
        File httpCacheFile = new File(MyApp.sContext.getCacheDir(), "responses"); // cache url
        int cacheSize = 50 * 1024 * 1024; // 50 MiB
        Cache cache = new Cache(httpCacheFile, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();

        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ZHIHU_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mZhihuApi = zhihuRetrofit.create(ZhihuApi.class);
    }

    public ZhihuApi getZhihuApi() {
        return mZhihuApi;
    }
}
