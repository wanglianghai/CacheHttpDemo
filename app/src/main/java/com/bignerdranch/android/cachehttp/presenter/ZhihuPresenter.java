package com.bignerdranch.android.cachehttp.presenter;

import com.bignerdranch.android.cachehttp.View.LoadView;
import com.bignerdranch.android.cachehttp.api.ApiFactory;
import com.bignerdranch.android.cachehttp.model.NewsTimeLine;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/7/007.
 */

public class ZhihuPresenter {
    private LoadView mLoadView;

    public void getNews(LoadView loadView) {
        mLoadView = loadView;
        ApiFactory.getZhiHuApi().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsTimeLine>() {
                    @Override
                    public void accept(NewsTimeLine newsTimeLine) throws Exception {
                        mLoadView.loadSuccess(newsTimeLine);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mLoadView.loadError("http not connect");
                    }
                });
    }
}
