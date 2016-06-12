package com.example.hpb.kunlun.home.latest.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.home.latest.model.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0000- on 2016/6/11.
 */
public class BannerAdapter extends PagerAdapter {
    ArrayList<View> views;
    Context context;

    public BannerAdapter(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= views.size();
        if (position < 0) {
            position = views.size() + position;
        }
        View view = views.get(position);
        Banner banner = (Banner) view.getTag();
        Glide.with(container.getContext())
                .load(banner.getImage())
                .into((ImageView) view.findViewById(R.id.banner));
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

}
