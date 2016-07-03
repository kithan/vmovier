package com.example.hpb.kunlun.cache;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.hpb.kunlun.BaseActivity;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.player.model.VideoInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 0000- on 2016/6/22.
 */
public class CacheActivity extends BaseActivity<ICacheView, CachePresenter> implements ICacheView {
    @Override
    public CachePresenter initPresenter() {
        return new CachePresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_cache;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    CacheAdapter cacheAdapter;
    List<VideoInfo> datas;
    View headView;
    TextView downloadingText;

    @Override
    public void initViews() {
        datas = new ArrayList<>();
        cacheAdapter = new CacheAdapter(this, datas);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cacheAdapter);

        headView = LayoutInflater.from(this).inflate(R.layout.view_caching, null);
        downloadingText = (TextView) headView.findViewById(R.id.tv_title);
        cacheAdapter.addHeaderView(headView);

        presenter.initCacheList();

    }

    @Override
    public void updateCachedList(List<VideoInfo> videoInfoList, int cachingCount, String title) {
        downloadingText.setText("正在缓存\n" + title + "(" + cachingCount + ")");
        cacheAdapter.addData(videoInfoList);
    }
}
