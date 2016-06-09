package com.example.hpb.kunlun.home.latest.presenter;

import com.example.hpb.kunlun.data.HttpResultFunc;
import com.example.hpb.kunlun.data.Repository;
import com.example.hpb.kunlun.data.RxHelp;
import com.example.hpb.kunlun.home.latest.model.Banner;
import com.example.hpb.kunlun.home.latest.model.PostTab;
import com.example.hpb.kunlun.home.latest.view.ILatestView;
import java.util.List;
import rx.Observable;

/**
 * Created by hpb on 16/6/8.
 */
public class LatestPresenterImpl implements ILatestPresenter {

  ILatestView latestView;

  public LatestPresenterImpl(ILatestView latestView) {
    this.latestView = latestView;
  }

  @Override public void loadBanner() {
    Observable<List<Banner>> observable = Repository.getInstance()
        .getVmovierApi()
        .getBanner()
        .map(new HttpResultFunc<List<Banner>>());
    new RxHelp<List<Banner>>().toSubscribe(observable, new RxHelp.OnNext<List<Banner>>() {
      @Override public void onNext(List<Banner> banners) {
        latestView.onGetBanner(banners);
      }
    });
  }

  @Override public void loadTabPost(int page) {
    Observable<List<PostTab>> observable = Repository.getInstance()
        .getVmovierApi()
        .getTabPost(page)
        .map(new HttpResultFunc<List<PostTab>>());
    new RxHelp<List<PostTab>>().toSubscribe(observable, new RxHelp.OnNext<List<PostTab>>() {
      @Override public void onNext(List<PostTab> postTabs) {
        latestView.onGetTabPost(postTabs);
      }
    });
  }

}
