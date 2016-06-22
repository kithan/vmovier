package com.example.hpb.kunlun.player.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.support.v4.media.session.MediaButtonReceiver;

import com.example.hpb.kunlun.BasePresenter;
import com.example.hpb.kunlun.data.HttpError;
import com.example.hpb.kunlun.player.view.IAudioControlView;

/**
 * Created by 0000- on 2016/6/13.
 */
public class AudioControlPresenter extends BasePresenter<IAudioControlView> implements AudioManager.OnAudioFocusChangeListener {


    AudioManager mAudioManager;
    private int  mVolume = -1;
    private int streamMaxVolume;
    private int streamNowVolume;

    Context context;

    public void initVolManager(Context context) {
        this.context = context;
        mAudioManager = (AudioManager) context.getSystemService(
                Context.AUDIO_SERVICE);
        streamMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        streamNowVolume = mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        int progress = streamNowVolume * 100 / streamMaxVolume;
        mView.setVolSeekBarProgress(progress);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress
                * streamMaxVolume / 100, 0);
        int result = mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mAudioManager.registerMediaButtonEventReceiver(new ComponentName(
                    context.getPackageName(), MediaButtonReceiver.class
                    .getName()));
        }
        mView.setVolSeekBarProgress(progress);
    }

    public void setVolume(int percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

        }
        int index = percent * streamMaxVolume + mVolume;
        if (index > streamMaxVolume)
            index = streamMaxVolume;
        else if (index < 0)
            index = 0;
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        mView.setVolSeekBarProgress((index / streamMaxVolume * 100));
    }

    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            mAudioManager
                    .requestAudioFocus(this,
                            AudioManager.STREAM_MUSIC,
                            AudioManager.AUDIOFOCUS_GAIN);

        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            mAudioManager
                    .unregisterMediaButtonEventReceiver(new ComponentName(
                            context.getPackageName(),
                            MediaButtonReceiver.class.getName()));
            mAudioManager.abandonAudioFocus(this);
        }

    }

    @Override
    public void onEvent(Object object) {

    }

    @Override
    public void dettach() {
        mAudioManager.unregisterMediaButtonEventReceiver(new ComponentName(
               context.getPackageName(), MediaButtonReceiver.class
                .getName()));
        mAudioManager.abandonAudioFocus(this);
        super.dettach();
    }
}
