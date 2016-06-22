package com.example.hpb.kunlun.player.view;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hpb.kunlun.BaseActivity;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.Utils;
import com.example.hpb.kunlun.data.RxBus;
import com.example.hpb.kunlun.player.model.Comment;
import com.example.hpb.kunlun.player.model.LandscapeEvent;
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.player.presenter.PlayerPresenter;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerActivity extends BaseActivity<IPlayerView, PlayerPresenter> implements IPlayerView, FragmentCallback {


    @BindView(R.id.webView)
    WebView webView;


    @InjectExtra("url")
    String url;
    @InjectExtra("postId")
    String postId;


    @BindView(R.id.layout_player)
    View playerLayout;
    @BindView(R.id.layout_bottom)
    View bottomLayout;
    @BindView(R.id.layout_comment)
    RelativeLayout layoutComment;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.iv_bottom_download)
    ImageView ivBottomDownload;
    @BindView(R.id.iv_bottom_collect)
    ImageView ivBottomCollect;
    @BindView(R.id.text_comment)
    EditText textComment;

    @Override
    public int getContentView() {
        return R.layout.activity_player;
    }

    @TargetApi(17)
    @Override
    public void initViews() {
        Dart.inject(this);

        postId = Dart.get(getIntent().getExtras(), "postId");
        url = Dart.get(getIntent().getExtras(), "url");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.contains("redirect")) {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl(url);


        presenter.getPostDetail(postId);
        presenter.getComments(1, postId);

    }

    public void onGetPostDetail(PostDetail detail) {
        Bundle bundle = new Bundle();
        bundle.putString("title", detail.getTitle());
        bundle.putString("videoPath", detail.getContent().getVideo().get(0).getQiniu_url());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_player, Fragment.instantiate(this, PlayerFragment.class.getName(), bundle))
                .commit();
    }


    @OnClick({R.id.iv_bottom_collect})
    public void collectClick(View view) {

    }

    @OnClick({R.id.iv_bottom_download})
    public void downloadClick(View v) {

    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        boolean landscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (landscape) {
            bottomLayout.setVisibility(View.GONE);
            playerLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            bottomLayout.setVisibility(View.VISIBLE);
            playerLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(this, 200)));
        }
        RxBus.getInstance().send(new LandscapeEvent(landscape));
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onGetComments(List<Comment> comments) {

    }


    @Override
    public PlayerPresenter initPresenter() {
        return new PlayerPresenter();
    }


    @Override
    public void finishActivity() {
        finish();
    }







}
