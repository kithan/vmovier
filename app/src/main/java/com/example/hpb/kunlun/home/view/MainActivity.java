package com.example.hpb.kunlun.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hpb.kunlun.BaseActivity;
import com.example.hpb.kunlun.BasePresenter;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.data.RxBus;
import com.example.hpb.kunlun.home.channel.view.ChannelFragment;
import com.example.hpb.kunlun.home.latest.presenter.LatestPresenter;
import com.example.hpb.kunlun.home.latest.view.LatestFragment;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        if(_subscriptions!=null){
            _subscriptions.unsubscribe();
        }
    }
}
