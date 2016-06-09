package com.example.hpb.kunlun.home.latest.view;

import com.example.hpb.kunlun.home.latest.model.Banner;
import com.example.hpb.kunlun.home.latest.model.PostTab;
import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public interface ILatestView {
    public void onGetTabPost(List<PostTab> postTabs);
    public void onGetBanner(List<Banner> banners);
}
