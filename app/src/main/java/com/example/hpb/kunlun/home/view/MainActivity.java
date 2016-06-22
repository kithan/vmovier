package com.example.hpb.kunlun.home.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hpb.kunlun.BaseActivity;
import com.example.hpb.kunlun.BasePresenter;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.data.RxBus;
import com.example.hpb.kunlun.home.channel.view.ChannelFragment;
import com.example.hpb.kunlun.home.latest.presenter.LatestPresenter;
import com.example.hpb.kunlun.home.latest.view.LatestFragment;
import com.example.hpb.kunlun.dlna.upnp.VmovierUpnpService;
import com.example.hpb.kunlun.dlna.upnp.SystemManager;
import com.example.hpb.kunlun.dlna.upnp.SystemService;
import com.example.hpb.kunlun.player.view.DeviceListDialogFragment;

import butterknife.BindView;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tabs)
    RadioGroup tabs;
    @BindView(R.id.rb_latest)
    RadioButton latestBtn;
    SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;
    CompositeSubscription _subscriptions;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tabs.check(R.id.rb_latest);
                } else {
                    tabs.check(R.id.rb_channel);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_latest) {
                    mViewPager.setCurrentItem(0);
                } else {
                    mViewPager.setCurrentItem(1);
                }
            }
        });

        _subscriptions = new CompositeSubscription();
        _subscriptions.add(RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof LatestPresenter.UpdateTabTitleEvent) {
                    latestBtn.setText(((LatestPresenter.UpdateTabTitleEvent) event).getTitle());
                }
            }
        }));


        Intent upnpServiceIntent = new Intent(this, VmovierUpnpService.class);
        bindService(upnpServiceIntent, mUpnpServiceConnection, Context.BIND_AUTO_CREATE);
        Intent systemServiceIntent = new Intent(this, SystemService.class);
        bindService(systemServiceIntent, mSystemServiceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return LatestFragment.newInstance();
            }
            return Fragment.instantiate(getApplicationContext(), ChannelFragment.class.getName());
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "最新";
            }
            return "频道";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (_subscriptions != null) {
            _subscriptions.unsubscribe();
        }
        unbindService(mUpnpServiceConnection);
        unbindService(mSystemServiceConnection);
        SystemManager.getInstance().destroy();
    }

    private ServiceConnection mUpnpServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            VmovierUpnpService.LocalBinder binder = (VmovierUpnpService.LocalBinder) service;
            VmovierUpnpService beyondUpnpService = binder.getService();

            SystemManager systemManager = SystemManager.getInstance();
            systemManager.setUpnpService(beyondUpnpService);

            systemManager.searchAllDevices();


        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            SystemManager.getInstance().setUpnpService(null);
        }
    };

    private ServiceConnection mSystemServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            SystemService.LocalBinder systemServiceBinder = (SystemService.LocalBinder) service;
            SystemManager systemManager = SystemManager.getInstance();
            systemManager.setSystemService(systemServiceBinder.getService());
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            SystemManager.getInstance().setSystemService(null);
        }
    };
}
