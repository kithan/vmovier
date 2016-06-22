package com.example.hpb.kunlun.player.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TimeUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hpb.kunlun.BaseFragment;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.Utils;
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.player.presenter.PlayerControlPresenter;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import butterknife.BindView;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.IjkVideoView;

/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerFragment extends BaseFragment<IPlayerControlView, PlayerControlPresenter> implements IPlayerControlView,
        IAudioControlView,
        IMediaPlayer.OnCompletionListener, IMediaPlayer.OnPreparedListener,
        IMediaPlayer.OnErrorListener, View.OnTouchListener {


    public static final int PLAY_ACTION = 0xa1;
    public static final int PAUSE_ACTION = 0xa2;
    public static final int STOP_ACTION = 0xa3;
    public static final int GET_MEDIA_INFO_ACTION = 0xa4;
    public static final int GET_POSITION_INFO_ACTION = 0xa5;
    public static final int RESUME_SEEKBAR_ACTION = 0xa6;
    public static final int GET_VOLUME_ACTION = 0xa7;
    public static final int SET_VOLUME_ACTION = 0xa8;

    @BindView(R.id.videoView)
    IjkVideoView mVideoView;
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
    @BindView(R.id.iv_upnp)
    ImageView ivUpnp;

    @BindView(R.id.layout_top)
    View layoutTop;



    String title;


    private FragmentCallback callback;

    @Override
    public PlayerControlPresenter initPresenter() {
        return new PlayerControlPresenter(getActivity(),this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        videoSource = bundle.getString("videoPath");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            callback = (FragmentCallback) context;
        }

    }

    @Override
    public int getDuration() {
        return mVideoView.getDuration();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_player;
    }

    String videoSource;

    private boolean mShowing;
    private static final int EVENT_PLAY = 200;
    private static final int FADE_OUT = 100;
    private static final int FADE_IN = 150;
    private static final int UPDATE_PROGRESS = 400;
    private static final int HIDE_GESTURE_LAYOUT = 500;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case FADE_OUT:
                    presenter.hideOverLay();
                    break;
                case FADE_IN:
                    presenter.showOverLay();
                    break;
                case UPDATE_PROGRESS:
                    int pos = mVideoView.getCurrentPosition();
                    playerSeekBar.setProgress(pos);
                    timeCurrent.setText(Utils.format(pos));
                    msg = mHandler.obtainMessage(UPDATE_PROGRESS);
                    mHandler.sendMessageDelayed(msg, 1000);
                    break;
                case EVENT_PLAY:
                    mVideoView.setVideoPath(videoSource);
                    mVideoView.start();
                    break;
                case HIDE_GESTURE_LAYOUT:
//                    volLayout.setVisibility(View.GONE);
//                    brightLayout.setVisibility(View.GONE);
                    break;
            }
            return true;
        }
    });

    @Override
    public void showController() {
        mShowing = true;
        layoutController.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom));
        layoutController.setVisibility(View.VISIBLE);
        playPauseImageView.setVisibility(View.VISIBLE);
        ivUpnp.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessageDelayed(FADE_OUT, 5000);
    }

    @Override
    public void hideController() {
        mShowing = false;
        layoutController.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom));

        layoutController.setVisibility(View.GONE);
        ivUpnp.setVisibility(View.GONE);
        playPauseImageView.setVisibility(View.GONE);
    }


    @Override
    public void initViews() {

        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnPreparedListener(this);

        rootView.setOnTouchListener(this);

        playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeCurrent.setText(Utils.format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeMessages(FADE_OUT);
                mHandler.removeMessages(UPDATE_PROGRESS);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mVideoView.seekTo(seekBar.getProgress());
                if (!mVideoView.isPlaying()) {
                    playPauseImageView.setImageResource(R.drawable.video_lan_pause);
                    mVideoView.start();
                }
                mHandler.sendEmptyMessage(UPDATE_PROGRESS);
                mHandler.sendEmptyMessage(FADE_OUT);
            }
        });
        tvTitle.setText(title);

        mHandler.sendEmptyMessage(EVENT_PLAY);
//        brightSeekBar.setMax(100);
        mBrightness = getActivity().getWindow().getAttributes().screenBrightness;
//        brightSeekBar.setProgress((int)(mBrightness*100));
//        volSeekBar.setBackgroundColor(getResources().getColor(R.color.light_gray));
    }

    @OnClick(R.id.iv_back)
    public void backClick(View v) {
        callback.finishActivity();
    }
    @OnClick(R.id.iv_upnp)
    public void upnpClick(View v) {
        presenter.startUpnp();
    }


    @Override
    public void changeLandscape(boolean landscape) {
        int visible = landscape ? View.VISIBLE : View.INVISIBLE;
        tvTitle.setVisibility(visible);
        ivDownload.setVisibility(visible);
        ivShare.setVisibility(visible);
        ivLike.setVisibility(visible);
    }

    @OnClick({R.id.iv_like})
    public void collectClick(View view) {

    }

    @OnClick({R.id.iv_download})
    public void downloadClick(View v) {

    }


    @Override
    public void setVolSeekBarProgress(int progress) {
//        volLayout.setVisibility(View.VISIBLE);
//        volSeekBar.setProgress(progress);

    }

    private float mBrightness;

    @Override
    public void setBrightSeekBarProgress(float precent) {
        if (mBrightness < 0) {
            mBrightness = getActivity().getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.00f;

        }
        WindowManager.LayoutParams lpa = getActivity().getWindow()
                .getAttributes();
        lpa.screenBrightness = mBrightness + precent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getActivity().getWindow().setAttributes(lpa);
//        brightLayout.setVisibility(View.VISIBLE);
//        brightSeekBar.setProgress((int)(lpa.screenBrightness * 100));
    }

    @OnClick(R.id.iv_share)
    public void shareClick(View v) {

    }

    @OnClick(R.id.iv_play)
    public void playPause(View v) {
        if (mVideoView.getPlayState() == IjkVideoView.STATE_IDLE) {
            mVideoView.resume();
        } else if (mVideoView.getPlayState() == IjkVideoView.STATE_PLAYING) {
            mVideoView.pause();
            playPauseImageView.setImageResource(R.drawable.video_lan_play);
        } else if (mVideoView.getPlayState() == IjkVideoView.STATE_PAUSED) {
            mVideoView.start();
            playPauseImageView.setImageResource(R.drawable.video_lan_pause);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (presenter.onTouch(v, event)) {
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mHandler.sendEmptyMessageDelayed(HIDE_GESTURE_LAYOUT, 3000);
            if (!mShowing) {
                showController();
            } else {
                hideController();
            }
        }
        return true;
    }

    private PowerManager.WakeLock mWakeLock = null;

    @Override
    public void onResume() {
        super.onResume();
        if (null != mWakeLock && (!mWakeLock.isHeld())) {
            mWakeLock.acquire();
        }
        if (mVideoView != null) {
            mVideoView.start();
            playPauseImageView.setImageResource(R.drawable.video_lan_pause);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
        if (mVideoView != null) {
            mVideoView.pause();
            playPauseImageView.setImageResource(R.drawable.video_lan_play);
        }
    }

    @Override
    public void onPrepared(IMediaPlayer mp) {
        mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 600);
        timeTotal.setText(Utils.format(mVideoView.getDuration()));
        playerSeekBar.setMax(mVideoView.getDuration());
    }

    @Override
    public void onCompletion(IMediaPlayer mp) {

        playPauseImageView.setImageResource(R.drawable.video_lan_play);
        playPauseImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onError(IMediaPlayer mp, int what, int extra) {
        if(callback!=null){
            callback.finishActivity();
        }
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
