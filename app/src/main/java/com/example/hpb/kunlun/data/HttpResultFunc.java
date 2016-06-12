package com.example.hpb.kunlun.data;


import rx.functions.Func1;

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getStatus() != 0) {
            RxBus.getInstance().send(new HttpError(httpResult.getStatus(), ""));
            return null;
        }
        return httpResult.getData();
    }
}