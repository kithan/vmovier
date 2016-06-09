package com.example.hpb.kunlun.data;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hpb on 16/6/8.
 */
public class RxHelp<T> {


    public void  toSubscribe(Observable<T>observable, final OnNext<T> onNext){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(T t){
                        onNext.onNext(t);
                    }

                });
    }

    public interface  OnNext<T>{
        public void onNext(T t);
    }
}
