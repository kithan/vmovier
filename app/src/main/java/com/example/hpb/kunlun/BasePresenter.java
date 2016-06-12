package com.example.hpb.kunlun;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.hpb.kunlun.data.HttpError;
import com.example.hpb.kunlun.data.RxBus;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T> {

    CompositeSubscription errorSubscription = new CompositeSubscription();
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
        errorSubscription.add(RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object object) {
                if (object instanceof HttpError) {
                    onEventError((HttpError) object);
                }
            }
        }));
    }

    public abstract void onEventError(HttpError error);

    public void dettach() {
        mView = null;
        errorSubscription.unsubscribe();
    }



}