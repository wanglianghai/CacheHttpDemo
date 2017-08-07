package com.bignerdranch.android.cachehttp.View;

import com.bignerdranch.android.cachehttp.model.NewsTimeLine;

/**
 * Created by Administrator on 2017/8/7/007.
 */

public interface LoadView {
    void loadSuccess(NewsTimeLine s);
    void loadError(String s);
}
