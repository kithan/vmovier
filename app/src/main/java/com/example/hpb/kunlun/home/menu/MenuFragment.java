package com.example.hpb.kunlun.home.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hpb.kunlun.BaseFragment;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.cache.CacheActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 0000- on 2016/7/1.
 */
public class MenuFragment extends BaseFragment<IMenuView, MenuPresenter> {
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.layout_middle)
    LinearLayout layoutMiddle;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.ll_series)
    LinearLayout llSeries;
    @BindView(R.id.ll_behind)
    LinearLayout llBehind;
    @BindView(R.id.iv_close)
    ImageView close;
    @BindView(R.id.tv_subscribe)
    TextView tvSubscribe;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.tv_like)
    TextView tvLike;

    @Override
    public MenuPresenter initPresenter() {
        return new MenuPresenter(getActivity());
    }

    @Override
    public int getContentView() {
        return R.layout.frag_menu;
    }

    @Override
    public void initViews() {

    }


    @OnClick({R.id.ll_main, R.id.ll_series, R.id.ll_behind, R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main:
                break;
            case R.id.ll_series:
                break;
            case R.id.ll_behind:
                break;
            case R.id.iv_close:
                presenter.hide();
                break;
            case R.id.tv_subscribe:
                break;
            case R.id.tv_download:
                startActivity(new Intent(getActivity(), CacheActivity.class));
                break;
            case R.id.tv_like:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
