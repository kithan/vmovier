package com.example.hpb.kunlun.player.presenter;

import com.example.hpb.kunlun.BasePresenter;
import com.example.hpb.kunlun.data.HttpError;
import com.example.hpb.kunlun.data.HttpResult;
import com.example.hpb.kunlun.data.HttpResultFunc;
import com.example.hpb.kunlun.data.Repository;
import com.example.hpb.kunlun.data.RxHelp;
import com.example.hpb.kunlun.home.latest.model.PostSection;
import com.example.hpb.kunlun.player.model.Comment;
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.player.view.IPlayerView;

import java.util.List;

import rx.Observable;


/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerPresenter extends BasePresenter<IPlayerView>implements IPlayerPresenter {



    @Override
    public void getPostDetail(String postId) {
        Observable<PostDetail> observable = Repository.getInstance().getVmovierApi()
                .getPostDetail(Integer.parseInt(postId))
                .map(new HttpResultFunc<PostDetail>());
        new RxHelp<PostDetail>().toSubscribe(observable, new RxHelp.OnNext<PostDetail>() {
            @Override
            public void onNext(PostDetail postDetail) {
                mView.onGetPostDetail(postDetail);
            }
        });
    }

    @Override
    public void getComments(int p, String postId) {
        Observable<List<Comment>> observable = Repository.getInstance().getVmovierApi()
                .getComments(p,Integer.parseInt(postId),0)
                .map(new HttpResultFunc<List<Comment>>());
        new RxHelp<List<Comment>>().toSubscribe(observable, new RxHelp.OnNext<List<Comment>>() {
            @Override
            public void onNext(List<Comment> comments) {
                mView.onGetComments(comments);
            }
        });
    }

    @Override
    public void onEventError(HttpError error) {

    }
}
