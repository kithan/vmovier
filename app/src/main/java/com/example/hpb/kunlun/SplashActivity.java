package com.example.hpb.kunlun;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.hpb.kunlun.home.view.MainActivity;

import butterknife.BindView;

/**
 * Created by 0000- on 2016/6/21.
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash)
    ImageView splash;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    public static final String PREF_KEY_FIRST_START = "com.example.hpb.PREF_KEY_START";
    public static final int REQUEST_CODE_INTRO = 1;

    @Override
    public void initViews() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1, 1.3f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(5000);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash.startAnimation(scaleAnimation);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INTRO) {
            if (resultCode == RESULT_CANCELED) {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean(PREF_KEY_FIRST_START, true)
                        .apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }
    }

}
