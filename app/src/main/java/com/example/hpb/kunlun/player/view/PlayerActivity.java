package com.example.hpb.kunlun.player.view;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
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
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.player.model.VideoInfo;
import com.example.hpb.kunlun.player.presenter.PlayerPresenter;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerActivity extends BaseActivity<IPlayerView, PlayerPresenter> implements IPlayerView {

    public static final int PLAY_ACTION = 0xa1;
    public static final int PAUSE_ACTION = 0xa2;
    public static final int STOP_ACTION = 0xa3;
    public static final int GET_MEDIA_INFO_ACTION = 0xa4;
    public static final int GET_POSITION_INFO_ACTION = 0xa5;
    public static final int RESUME_SEEKBAR_ACTION = 0xa6;
    public static final int GET_VOLUME_ACTION = 0xa7;
    public static final int SET_VOLUME_ACTION = 0xa8;
    @BindView(R.id.webView)
    WebView webView;


    @InjectExtra("url")
    String url;
    @InjectExtra("postId")
    String postId;
    @InjectExtra
    boolean fromCache = false;


    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard jcVideoPlayerStandard;
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
        fromCache = Dart.get(getIntent().getExtras(), "fromCache");


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

    String title;
    String videoPath;

    public void onGetPostDetail(PostDetail detail) {
        title = detail.getTitle();
        videoPath = detail.getContent().getVideo().get(0).getQiniu_url();
        jcVideoPlayerStandard.setUp(videoPath, title);
        jcVideoPlayerStandard.startButton.performClick();

        if (fromCache) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    @OnClick({R.id.iv_bottom_collect})
    public void collectClick(View view) {

    }

    @OnClick({R.id.iv_bottom_download})
    public void downloadClick(View v) {

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setTitle(title);
        videoInfo.setSource_link(videoPath);
        presenter.addDownload(videoInfo);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        boolean landscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
//        if (landscape) {
//            bottomLayout.setVisibility(View.GONE);
//            JCFullScreenActivity.startActivity(this,
//                    videoPath,
//                    JCVideoPlayerStandard.class,
//                    title);
//        } else {
//            bottomLayout.setVisibility(View.VISIBLE);
//        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    public void onGetComments(List<Comment> comments) {

    }


    @Override
    public PlayerPresenter initPresenter() {
        return new PlayerPresenter();
    }


}
