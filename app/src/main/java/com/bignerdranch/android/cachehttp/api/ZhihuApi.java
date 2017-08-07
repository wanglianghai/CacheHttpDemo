package com.bignerdranch.android.cachehttp.api;

import com.bignerdranch.android.cachehttp.model.NewsTimeLine;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/8/7/007.
 */

public interface ZhihuApi {
    @GET("news/latest")
    Observable<NewsTimeLine> getNews();
}
