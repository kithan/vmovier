package com.example.hpb.kunlun.player.view;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hpb.kunlun.BaseActivity;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.player.model.Comment;
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.player.presenter.PlayerPresenter;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.IjkVideoView;

/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerActivity extends BaseActivity<IPlayerView, PlayerPresenter> implements IPlayerView,
        IMediaPlayer.OnCompletionListener
        , IMediaPlayer.OnInfoListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnErrorListener {


    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.videoView)
    IjkVideoView mVideoView;

    @InjectExtra("url")
    String url;
    @InjectExtra("postId")
    String postId;
    @BindView(R.id.iv_play)
    ImageView playPauseImageView;
    @BindView(R.id.time_current)
    TextView timeCurrent;
    @BindView(R.id.time_total)
    TextView timeTotal;
    @BindView(R.id.iv_full_half)
    ImageView fullImageView;
    @BindView(R.id.media_progress)
    SeekBar playerSeekBar;
    @BindView(R.id.layout_controller)
    RelativeLayout layoutController;
    @BindView(R.id.layout_player)
    RelativeLayout layoutPlayer;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.iv_like)
    ImageView ivLike;
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
        webView.getSettings().setAllowFileAccess(true);
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

        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setmOnBufferingUpdateListener(this);

        presenter.getPostDetail(postId);
        presenter.getComments(1, postId);
    }

    public void onGetPostDetail(PostDetail detail) {
        tvTitle.setText(detail.getTitle());
        mVideoView.setVideoPath(detail.getContent().getVideo().get(0).getQiniu_url());
        mVideoView.start();
    }

    @OnClick(R.id.iv_back)
    public void backClick(View v) {
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showFullTop(true);
        } else {
            showFullTop(false);
        }
    }

    private void showFullTop(boolean fullTop) {
        int visible = fullTop ? View.VISIBLE : View.INVISIBLE;
        tvTitle.setVisibility(visible);
        ivDownload.setVisibility(visible);
        ivShare.setVisibility(visible);
        ivLike.setVisibility(visible);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    public void onGetComments(List<Comment> comments) {

    }

    public void onPlay(String videoSource) {
        mVideoView.setVideoPath(videoSource);
        mVideoView.start();
    }

    @Override
    public PlayerPresenter initPresenter() {
        return new PlayerPresenter();
    }


    @Override
    public void onCompletion(IMediaPlayer mp) {

    }

    @Override
    public boolean onInfo(IMediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer mp, int percent) {

    }

    @Override
    public boolean onError(IMediaPlayer mp, int what, int extra) {
        return false;
    }

}
