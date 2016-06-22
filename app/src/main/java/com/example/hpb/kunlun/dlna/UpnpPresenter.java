package com.example.hpb.kunlun.dlna;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import com.example.hpb.kunlun.BasePresenter;

import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.support.model.MediaInfo;
import org.fourthline.cling.support.model.PositionInfo;
import org.fourthline.cling.support.model.TransportState;

import com.example.hpb.kunlun.dlna.upnp.*;

import java.util.concurrent.ExecutionException;

/**
 * Created by 0000- on 2016/6/17.
 */
public class UpnpPresenter extends BasePresenter<IUpnpView> {
    private static final String TAG = "UpnpPresenter";
    public static final int PLAY_ACTION = 0xa1;
    public static final int PAUSE_ACTION = 0xa2;
    public static final int STOP_ACTION = 0xa3;
    public static final int GET_MEDIA_INFO_ACTION = 0xa4;
    public static final int GET_POSITION_INFO_ACTION = 0xa5;
    public static final int RESUME_SEEKBAR_ACTION = 0xa6;
    public static final int GET_VOLUME_ACTION = 0xa7;
    public static final int SET_VOLUME_ACTION = 0xa8;

    MediaPlayerController mMediaPlayerController;
    TransportStateBroadcastReceiver mTransportStateBroadcastReceiver;

    Activity activity;

    public UpnpPresenter(Activity activity) {
        mMediaPlayerController = new MediaPlayerController(mHandler);
        mTransportStateBroadcastReceiver = new TransportStateBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intents.ACTION_PLAYING);
        filter.addAction(Intents.ACTION_PAUSED_PLAYBACK);
        filter.addAction(Intents.ACTION_STOPPED);
        filter.addAction(Intents.ACTION_CHANGE_DEVICE);
        filter.addAction(Intents.ACTION_SET_VOLUME);
        filter.addAction(Intents.ACTION_UPDATE_LAST_CHANGE);
        this.activity = activity;
        activity.registerReceiver(mTransportStateBroadcastReceiver, filter);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                switch (msg.what) {
                    case PLAY_ACTION:
                        Log.i(TAG, "Execute PLAY_ACTION");
                        mMediaPlayerController.setCurrentState(TransportState.PLAYING);
                        mView.onUpnpPlay();
                        mMediaPlayerController.startUpdateSeekBar();
                        break;
                    case PAUSE_ACTION:
                        Log.i(TAG, "Execute PAUSE_ACTION");
                        mView.onUpnpPause();
                        mMediaPlayerController.setCurrentState(TransportState.PAUSED_PLAYBACK);
                        mMediaPlayerController.pauseUpdateSeekBar();
                        break;
                    case STOP_ACTION:
                        Log.i(TAG, "Execute STOP_ACTION");
                        mMediaPlayerController.setCurrentState(TransportState.STOPPED);
                        mView.onUpnpStop();
                        mMediaPlayerController.pauseUpdateSeekBar();
                        break;
                    case GET_MEDIA_INFO_ACTION:
                        MediaInfo mediaInfo = (MediaInfo) msg.obj;
                        if (mediaInfo != null) {
                            Log.i(TAG, "Execute GET_MEDIA_INFO_ACTION:" + mediaInfo);
                        }
                        break;
                    case GET_POSITION_INFO_ACTION:
                        PositionInfo positionInfo = (PositionInfo) msg.obj;
                        if (positionInfo != null) {
                            String relTime = positionInfo.getRelTime();
                            String trackDuration = positionInfo.getTrackDuration();


                            int elapsedSeconds = (int) positionInfo.getTrackElapsedSeconds();
                            int durationSeconds = (int) positionInfo.getTrackDurationSeconds();
//                            mRelTimeText.setText(relTime);
//                            mTrackDurationText.setText(trackDuration);
//                            mSeekBar.setProgress(elapsedSeconds);
//                            mSeekBar.setMax(durationSeconds);
                            mView.onUpnpUpdateProgress(relTime, trackDuration, elapsedSeconds, durationSeconds);
                            Log.d(TAG, "elapsedSeconds:" + elapsedSeconds);
                            Log.d(TAG, "durationSeconds:" + durationSeconds);

                            mTrackDurationSeconds = durationSeconds;
                        }
                        break;
                    case RESUME_SEEKBAR_ACTION:
                        mMediaPlayerController.startUpdateSeekBar();
                        break;
                    case GET_VOLUME_ACTION:
                        SystemManager.getInstance().setDeviceVolume(msg.arg1);
                        break;
                    case SET_VOLUME_ACTION:
                        SystemManager.getInstance().setDeviceVolume(msg.arg1);
                        PlaybackCommand.setVolume(msg.arg1);
                        break;
                }
            } catch (InterruptedException e) {
                Log.e(TAG, "SetCurrentStatus InterruptedException:" + e.getMessage());
            } catch (ExecutionException e) {
                Log.e(TAG, "SetCurrentStatus ExecutionException:" + e.getMessage());
            }
        }
    };

    int mTrackDurationSeconds;

    public void playPause() {
        TransportState state = mMediaPlayerController.getCurrentState();
        if (state == TransportState.PLAYING) {
            PlaybackCommand.pause();
        } else if (state == TransportState.PAUSED_PLAYBACK || state == TransportState.STOPPED) {
            PlaybackCommand.play();
            PlaybackCommand.getPositionInfo(mHandler);
        }
    }

    public void startSeek() {
        Log.i(TAG, "Start Seek");
        try {
            mMediaPlayerController.pauseUpdateSeekBar();
        } catch (InterruptedException e) {
            Log.e(TAG, "Interrupt update seekbar thread.");
        }
    }

    public void stopSeek(int progress, int max) {
        float percent = (float) progress / max;
        int seekToSecond = (int) (mTrackDurationSeconds * percent);
        Log.i(TAG, "Seek to second:" + ModelUtil.toTimeString(seekToSecond));
        PlaybackCommand.seek(ModelUtil.toTimeString(seekToSecond), mHandler);
    }

    @Override
    public void dettach() {
        mHandler.removeCallbacksAndMessages(null);
        activity.unregisterReceiver(mTransportStateBroadcastReceiver);
        activity=null;
        mMediaPlayerController.destroy();
        mMediaPlayerController = null;
        super.dettach();
    }

    @Override
    public void onEvent(Object object) {

    }

    private class TransportStateBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Receive playback intent:" + intent.getAction());
            if (Intents.ACTION_PLAYING.equals(intent.getAction())) {
                mHandler.sendEmptyMessage(PLAY_ACTION);
            } else if (Intents.ACTION_PAUSED_PLAYBACK.equals(intent.getAction())) {
                mHandler.sendEmptyMessage(PAUSE_ACTION);
            } else if (Intents.ACTION_STOPPED.equals(intent.getAction())) {
                mHandler.sendEmptyMessage(STOP_ACTION);
            } else if (Intents.ACTION_CHANGE_DEVICE.equals(intent.getAction())) {
                PlaybackCommand.getTransportInfo(mHandler);
                PlaybackCommand.getVolume(mHandler);
            } else if (Intents.ACTION_SET_VOLUME.equals(intent.getAction())) {
                Message msg = Message.obtain(mHandler, SET_VOLUME_ACTION, intent.getIntExtra("currentVolume", 0), 0);
                msg.sendToTarget();
            } else if (Intents.ACTION_UPDATE_LAST_CHANGE.equals(intent.getAction())) {
                mView.onUpdateTitle(intent.getStringExtra("title") + " - " + intent.getStringExtra("creator"));
            }
        }
    }

}
