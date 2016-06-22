package com.example.hpb.kunlun.dlna.upnp;


import android.content.Intent;
import android.os.IBinder;

import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.registry.Registry;

public class VmovierUpnpService extends AndroidUpnpServiceImpl {
    private static final String TAG = VmovierUpnpService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new LocalBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    public Registry getRegistry() {
        return upnpService.getRegistry();
    }


    public ControlPoint getControlPoint() {
        return upnpService.getControlPoint();
    }

    public class LocalBinder extends Binder {
        public VmovierUpnpService getService() {
            return VmovierUpnpService.this;
        }
    }
}