package com.example.hpb.kunlun;

import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadMonitor;

public class GlobalMonitor implements FileDownloadMonitor.IMonitor {
    private volatile int markStart;
    private volatile int markOver;

    private final static class HolderClass {
        private final static GlobalMonitor INSTANCE = new GlobalMonitor();
    }

    public static GlobalMonitor getImpl() {
        return HolderClass.INSTANCE;
    }

    private final static String TAG = "GlobalMonitor";

    @Override
    public void onRequestStart(int count, boolean serial, FileDownloadListener lis) {
        markStart = 0;
        markOver = 0;
        Log.d(TAG, String.format("on request start %d %B", count, serial));
    }

    @Override
    public void onRequestStart(BaseDownloadTask task) {
    }

    @Override
    public void onTaskBegin(BaseDownloadTask task) {
        markStart++;
    }

    @Override
    public void onTaskStarted(BaseDownloadTask task) {

    }

    @Override
    public void onTaskOver(BaseDownloadTask task) {
        markOver++;
    }

    public int getMarkStart() {
        return markStart;
    }

    public int getMarkOver() {
        return markOver;
    }
}