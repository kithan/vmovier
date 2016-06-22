package com.example.hpb.kunlun.player.model;

/**
 * Created by 0000- on 2016/6/13.
 */
public class LandscapeEvent {
    public boolean isLandscape() {
        return landscape;
    }

    private boolean landscape;

    public LandscapeEvent(boolean landscape) {
        this.landscape = landscape;
    }

}
