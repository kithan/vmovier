package com.example.hpb.kunlun.data;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hpb on 16/6/8.
 */
public class RxHelp<T> {

    private Subscriber subscriber;

    public RxHelp toSubscribe(Observable<T> observable, final OnNext<T> onNext) {
        Subscriber subscriber = new Subscriber<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                RxBus.getInstance().send(new HttpError(1, e.getMessage()));
            }

            @Override
            public void onNext(T t) {
                onNext.onNext(t);
            }

        };
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return this;
    }

    public void unSubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }

    public interface OnNext<T> {
        public void onNext(T t);
    }
}
