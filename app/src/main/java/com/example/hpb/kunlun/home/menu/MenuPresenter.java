package com.example.hpb.kunlun.home.menu;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.example.hpb.kunlun.BasePresenter;

/**
 * Created by 0000- on 2016/7/1.
 */
public class MenuPresenter extends BasePresenter<IMenuView> {
    FragmentActivity activity;

    public MenuPresenter(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onEvent(Object object) {

    }

    public void hide() {
        activity.getSupportFragmentManager()
                .beginTransaction().hide((MenuFragment) mView).commit();
    }

    @Override
    public void dettach() {
        activity = null;
        super.dettach();
    }
}
