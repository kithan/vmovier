package com.example.hpb.kunlun.data;

import rx.functions.Func1;

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult){
//            if (httpResult.getResultCode() != 0) {
//                throw new ApiException(httpResult.getResultCode());
//            }
            return httpResult.getData();
        }
    }