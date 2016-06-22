package com.example.hpb.kunlun.player.presenter;

import android.app.Activity;
import android.media.AudioManager;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.hpb.kunlun.BasePresenter;
import com.example.hpb.kunlun.Henson;
import com.example.hpb.kunlun.data.HttpError;
import com.example.hpb.kunlun.data.HttpResultFunc;
import com.example.hpb.kunlun.data.Repository;
import com.example.hpb.kunlun.data.RxHelp;
import com.example.hpb.kunlun.player.model.Comment;
import com.example.hpb.kunlun.player.model.LandscapeEvent;
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.player.view.DeviceListDialogFragment;
import com.example.hpb.kunlun.player.view.IAudioControlView;
import com.example.hpb.kunlun.player.view.IPlayerControlView;
import com.example.hpb.kunlun.player.view.IPlayerView;

import java.util.List;

import rx.Observable;


/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerControlPresenter extends BasePresenter<IPlayerControlView> implements GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private boolean firstScroll = false, seeking;
    private int GESTURE_FLAG = 0;
    public int seekpos;
    private static final int GESTURE_MODIFY_PROGRESS = 1;
    private static final int GESTURE_MODIFY_VOLUME_BRIGHRT = 2;
    private boolean mIsShowing;
    private int windowWidth, windowHeight;
    private int mVolume = -1;
    AudioControlPresenter audioControlPresenter;

    FragmentActivity activity;

    public PlayerControlPresenter(FragmentActivity activity, IAudioControlView audioControlView) {
        this.activity = activity;
        mGestureDetector = new GestureDetector(activity, this);
        mGestureDetector.setIsLongpressEnabled(false);
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        windowWidth = metrics.widthPixels;
        windowHeight = metrics.heightPixels;
        audioControlPresenter = new AudioControlPresenter();
        audioControlPresenter.attach(audioControlView);
        audioControlPresenter.initVolManager(activity);
    }


    DeviceListDialogFragment deviceListDialogFragment;
    public void startUpnp(){
        if(deviceListDialogFragment==null){
            deviceListDialogFragment=DeviceListDialogFragment.newInstance();
        }
        deviceListDialogFragment.show(activity.getSupportFragmentManager(),"device");
    }
    @Override
    public void onEvent(Object object) {
        if (object instanceof LandscapeEvent) {
            LandscapeEvent event = (LandscapeEvent) object;
            mView.changeLandscape(event.isLandscape());
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        return false;
    }

    public void showOverLay() {
        mIsShowing = true;
        mView.showController();
    }

    public void hideOverLay() {
        mIsShowing = false;
        mView.hideController();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        firstScroll = true;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float mOldX = e1.getX(), mOldY = e1.getY();
        int y = (int) e2.getRawY();
        int x = (int) e2.getRawX();

        if (firstScroll) {
            if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                GESTURE_FLAG = GESTURE_MODIFY_PROGRESS;
            } else {
                GESTURE_FLAG = GESTURE_MODIFY_VOLUME_BRIGHRT;
            }
        }
        if (GESTURE_FLAG == GESTURE_MODIFY_PROGRESS
                && seekpos <= mView.getDuration()) {// 控制进度条
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                seeking = true;
//                gesturelayout.setVisibility(View.VISIBLE);
//                if ((current - x) < 0) {
//                    current = x;
//                    gesturelayoutBg
//                            .setBackgroundResource(R.drawable.gesture_middle_next);
//                    changeSeekPos(mOldX, x);
//                    mediaSeekBar.setProgress(seekpos);
//                } else {
//                    current = x;
//                    gesturelayoutBg
//                            .setBackgroundResource(R.drawable.gesture_middle_back);
//                    changeSeekPos(mOldX, x);
//                    mediaSeekBar.setProgress(seekpos);
//                }
//                mUIHandler.sendEmptyMessage(GESTURE_UPDATE_TXT);
            }
        } else if (GESTURE_FLAG == GESTURE_MODIFY_VOLUME_BRIGHRT) {
            if (mOldX > windowWidth / 2)
                audioControlPresenter.setVolume((int) (mOldY - y) / windowHeight);
            else if (mOldX < windowWidth / 4)
                mView.setBrightSeekBarProgress((mOldY - y) / windowHeight);
        }
        firstScroll = false;
        return false;
    }




    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void dettach() {
        super.dettach();
        activity = null;
        audioControlPresenter.dettach();
    }
}
