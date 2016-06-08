package com.example.hpb.kunlun.home.view;

import com.example.hpb.kunlun.home.model.Banner;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.home.model.PostTab;

import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public interface IHomeView {
    public void onGetTabPost(List<PostTab> postTabs);
    public void onGetBanner(List<Banner> banners);
    public void onGetCateList(List<Cate> cates);
}
