package com.example.hpb.kunlun.player.view;

import android.app.Activity;

/**
 * Created by 0000- on 2016/6/12.
 */
public interface IPlayerControlView {
    public void changeLandscape(boolean landscape);

    public void showController();

    public void hideController();

    public int getDuration();

    public  void setBrightSeekBarProgress(float progress);
}
