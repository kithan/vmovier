package com.example.hpb.kunlun.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hpb on 16/4/30.
 */
public class HeaderIntercptor  implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
//            return chain.proceed(originalRequest);
//        }
//        Request authorised = originalRequest.newBuilder()
//                .header("Authorization", Your.sToken)
//                .build();
        return chain.proceed(originalRequest);
    }
}
