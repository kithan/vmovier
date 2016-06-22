package com.example.hpb.kunlun.dlna;

/**
 * Created by 0000- on 2016/6/19.
 */
public interface IUpnpView {
    void  onUpnpPause();
    void onUpnpPlay();
    void onUpnpStop();
    void onUpnpUpdateProgress(String currTime,String timeDuration,int total,int currSecs);
    void onUpdateTitle(String title);
}
