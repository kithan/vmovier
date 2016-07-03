package com.example.hpb.kunlun;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;

/**
 * Created by 0000- on 2016/6/22.
 */
public class VmovierApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FileDownloader.init(getApplicationContext(),
                new FileDownloadHelper.OkHttpClientCustomMaker() {
                    @Override
                    public OkHttpClient customMake() {
                        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        builder.connectTimeout(15_000, TimeUnit.MILLISECONDS);
                        builder.proxy(Proxy.NO_PROXY);
                        return builder.build();
                    }
                });


        //Realm init
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
