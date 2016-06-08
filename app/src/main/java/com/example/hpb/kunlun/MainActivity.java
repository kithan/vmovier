package com.example.hpb.kunlun;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
   
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hpb.kunlun.data.Contributor;
import com.example.hpb.kunlun.data.Repository;
import com.example.hpb.kunlun.home.model.Banner;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.home.model.PostTab;
import com.example.hpb.kunlun.home.presenter.HomePresenterImpl;
import com.example.hpb.kunlun.home.presenter.IHomePresenter;
import com.example.hpb.kunlun.home.view.IHomeView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  implements IHomeView{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();
          }
      });

        final TabLayout mainTab = (TabLayout) findViewById(R.id.tabLayout);
//
        mainTab.addTab(mainTab.newTab().setCustomView(R.layout.tabview).setText("首页").setIcon(R.mipmap.ic_launcher),true);
        mainTab.addTab(mainTab.newTab().setCustomView(R.layout.tabview).setText("新闻").setIcon(R.mipmap.ic_launcher),false);
        mainTab.addTab(mainTab.newTab().setCustomView(R.layout.tabview).setText("购票").setIcon(R.mipmap.ic_launcher),false);
        mainTab.addTab(mainTab.newTab().setCustomView(R.layout.tabview).setText("商城").setIcon(R.mipmap.ic_launcher),false);
        mainTab.addTab(mainTab.newTab().setCustomView(R.layout.tabview).setText("我的").setIcon(R.mipmap.ic_launcher),false);
        mainTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==1){
                    startActivity(new Intent(getApplicationContext(),NewsActivity.class));
                }
                tab.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                Toast.makeText(getApplicationContext(),tab.getText(),Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary), PorterDuff.Mode.CLEAR);


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mainTab.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_bright));


        presenter=new HomePresenterImpl(this);
        presenter.loadCateList();
    }

    IHomePresenter presenter;

    @Override
    public void onGetTabPost(List<PostTab>tabs) {

    }

    @Override
    public void onGetBanner(List<Banner>banners) {

    }

    @Override
    public void onGetCateList(List<Cate> cates) {
        Toast.makeText(this,""+cates.size(),Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Call<List<Contributor>> call = Repository.getInstance().getmSystemApi().contributors("square", "retrofit");
//
//            call.enqueue(new Callback<List<Contributor>>() {
//                @Override
//                public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//                    if(response.isSuccessful()){
//                        List<Contributor> contributors =  response.body();
//                        for (Contributor contributor : contributors) {
//                            System.out.println(contributor.login + " (" + contributor.contributions + ")");
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<List<Contributor>> call, Throwable t) {
//
//                }
//            });

//        Repository.getInstance().getmSystemApi().rxContributors("square","retrofit").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<List<Contributor>, Observable<Contributor>>() {
//                    @Override
//                    public Observable<Contributor> call(List<Contributor> contributors) {
//
//                        return null;
//                    }
//                })
//        .subscribe(new Subscriber<Contributor>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Contributor contributor) {
////                for (Contributor contributor : contributors) {
//                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
////                }
//
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
  

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            ListView listView=(ListView) rootView.findViewById(R.id.list);
            ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1

                    ,new String[]{"item0","item0","item0","item0","item0","item0","item0","item0","item0","item0",
                    "item0","item0","item0","item0","item0","item0","item0","item0","item0","item0","item0",
                    "item0","item0","item0","item0","item0","item0",});
            listView.setAdapter(stringArrayAdapter);

            return rootView;
        }
    }
}
