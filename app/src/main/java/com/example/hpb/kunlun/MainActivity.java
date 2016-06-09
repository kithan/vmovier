package com.example.hpb.kunlun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hpb.kunlun.data.RxBus;
import com.example.hpb.kunlun.home.channel.view.ChannelFragment;
import com.example.hpb.kunlun.home.latest.model.Banner;
import com.example.hpb.kunlun.home.latest.view.LatestFragment;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.home.latest.model.PostTab;
import java.util.List;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.tabs) RadioGroup tabs;
  @BindView(R.id.rb_latest) RadioButton latestBtn;
  SectionsPagerAdapter mSectionsPagerAdapter;

  @BindView(R.id.container) ViewPager mViewPager;
  CompositeSubscription _subscriptions;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(mSectionsPagerAdapter);
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        if (position == 0) {
          tabs.check(R.id.rb_latest);
        } else {
          tabs.check(R.id.rb_channel);
        }
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_latest) {
          mViewPager.setCurrentItem(0);
        } else {
          mViewPager.setCurrentItem(1);
        }
      }
    });
    //presenter.loadCateList();

    _subscriptions = new CompositeSubscription();
    _subscriptions.add(RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
      @Override public void call(Object event) {
        if (event instanceof LatestFragment.UpdateTabTitleEvent) {
          latestBtn.setText(((LatestFragment.UpdateTabTitleEvent) event).getTitle());
        }
      }
    }));
  }


  @Override public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
      getWindow().getDecorView()
          .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_FULLSCREEN
              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
  }


  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      if (position == 0) {
        return LatestFragment.newInstance();
      }
      return Fragment.instantiate(getApplicationContext(), ChannelFragment.class.getName());
    }

    @Override public int getCount() {
      return 2;
    }

    @Override public CharSequence getPageTitle(int position) {
      if (position == 0) {
        return "最新";
      }
      return "频道";
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    _subscriptions.unsubscribe();
  }
}
