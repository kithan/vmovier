package com.example.hpb.kunlun.home.presenter;

import com.example.hpb.kunlun.data.RxHelp;
import com.example.hpb.kunlun.data.HttpResultFunc;
import com.example.hpb.kunlun.data.Repository;
import com.example.hpb.kunlun.home.model.Banner;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.home.model.PostTab;
import com.example.hpb.kunlun.home.view.IHomeView;

import java.util.List;

import rx.Observable;

/**
 * Created by hpb on 16/6/8.
 */
public class HomePresenterImpl implements IHomePresenter {

    IHomeView homeView;

    public HomePresenterImpl(IHomeView homeView) {
        this.homeView = homeView;
    }


    @Override
    public void loadCateList() {
        Observable<List<Cate>> observable = Repository.getInstance().getVmovierApi().getCateList().map(new HttpResultFunc<List<Cate>>());
        new RxHelp<List<Cate>>().toSubscribe(observable, new RxHelp.OnNext<List<Cate>>() {
            @Override
            public void onNext(List<Cate> cates) {
                homeView.onGetCateList(cates);
            }
        });
    }

    @Override
    public void loadBanner() {
        Observable<List<Banner>> observable = Repository.getInstance().getVmovierApi().getBanner().map(new HttpResultFunc<List<Banner>>());
        new RxHelp<List<Banner>>().toSubscribe(observable, new RxHelp.OnNext<List<Banner>>() {
            @Override
            public void onNext(List<Banner> banners) {
                homeView.onGetBanner(banners);
            }
        });
    }

    @Override
    public void loadTabPost(int page) {
        Observable<List<PostTab>> observable = Repository.getInstance().getVmovierApi().getTabPost(page).map(new HttpResultFunc<List<PostTab>>());
        new RxHelp<List<PostTab>>().toSubscribe(observable, new RxHelp.OnNext<List<PostTab>>() {
            @Override
            public void onNext(List<PostTab> postTabs) {
                homeView.onGetTabPost(postTabs);
            }
        });


    }
}
