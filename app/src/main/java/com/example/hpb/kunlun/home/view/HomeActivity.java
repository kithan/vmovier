package com.example.hpb.kunlun.home.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.home.model.Banner;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.home.model.PostTab;
import com.example.hpb.kunlun.home.presenter.HomePresenterImpl;
import com.example.hpb.kunlun.home.presenter.IHomePresenter;

import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public class HomeActivity extends AppCompatActivity implements  IHomeView {

  IHomePresenter homePresenter;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        homePresenter=new HomePresenterImpl(this);
        homePresenter.loadCateList();

    }

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
}
