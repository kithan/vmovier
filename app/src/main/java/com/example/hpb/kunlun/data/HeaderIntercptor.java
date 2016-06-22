package com.example.hpb.kunlun.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hpb on 16/4/30.
 */
public class HeaderIntercptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request originalRequest = chain.request();
        Response response = null;
        try {
            response=chain.proceed(originalRequest);
        } catch (IOException e) {
            e.printStackTrace();
            RxBus.getInstance().send(new HttpError(400, "time out"));
        }
        return response;
    }
}
