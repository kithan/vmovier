package com.example.hpb.kunlun;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.hpb.kunlun.data.HttpError;
import com.example.hpb.kunlun.data.RxBus;
import com.example.hpb.kunlun.data.RxHelp;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T> {

    CompositeSubscription errorSubscription = new CompositeSubscription();
    public T mView;
     List<RxHelp> rxHelpList = new ArrayList<>();

    public void attach(T mView) {
        this.mView = mView;
        errorSubscription.add(RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object object) {
                if (object instanceof HttpError) {
                    HttpError error = (HttpError) object;
                    Log.e("presenter error:", error.getErrMsg());
                } else {
                    onEvent(object);
                }
            }
        }));
    }

    protected  void addRxHelp(RxHelp help){
        rxHelpList.add(help);
    }
    public abstract void onEvent(Object object);

    public void dettach() {
        mView = null;
        for (RxHelp rxHelp : rxHelpList) {
            rxHelp.unSubscribe();
        }
        errorSubscription.unsubscribe();
    }


}