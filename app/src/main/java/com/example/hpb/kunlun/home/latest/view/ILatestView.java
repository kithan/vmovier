package com.example.hpb.kunlun.home.latest.view;

import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hpb.kunlun.home.latest.model.Banner;
import com.example.hpb.kunlun.home.latest.model.PostSection;
import com.example.hpb.kunlun.home.latest.model.PostTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public interface ILatestView {
    public void onReloadList(ArrayList<PostSection>sections);
    public void onLoadList(ArrayList<PostSection>sections);
    public void onGetBanner(List<Banner> banners);
}
