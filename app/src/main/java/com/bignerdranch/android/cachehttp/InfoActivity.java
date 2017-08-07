package com.bignerdranch.android.cachehttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.cachehttp.View.LoadView;
import com.bignerdranch.android.cachehttp.model.NewsTimeLine;
import com.bignerdranch.android.cachehttp.presenter.ZhihuPresenter;

public class InfoActivity extends AppCompatActivity implements LoadView {
    private TextView mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        mContext = (TextView) findViewById(R.id.context);
        ZhihuPresenter presenter = new ZhihuPresenter();
        presenter.getNews(this);
    }

    @Override
    public void loadSuccess(NewsTimeLine s) {
        mContext.setText(s.toString());
        Toast.makeText(this, "context: " + s.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
