package com.example.hpb.kunlun.dlna;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hpb.kunlun.BaseActivity;
import com.example.hpb.kunlun.R;
import com.f2prateek.dart.InjectExtra;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 0000- on 2016/6/19.
 */
public class UpnpActivity extends BaseActivity<IUpnpView, UpnpPresenter> implements IUpnpView {

    @BindView(R.id.time_current)
    TextView timeCurrent;
    @BindView(R.id.time_total)
    TextView timeTotal;
    @BindView(R.id.media_progress)
    SeekBar mediaProgress;
    @BindView(R.id.layout_controller)
    RelativeLayout layoutController;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_top)
    RelativeLayout layoutTop;
    @BindView(R.id.iv_play)
    ImageView ivPlay;

    @Override
    public UpnpPresenter initPresenter() {
        return new UpnpPresenter(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_upnp;
    }

    @InjectExtra String title;
    @Override
    public void initViews() {
        tvTitle.setText(getIntent().getStringExtra("title"));

        mediaProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                presenter.startSeek();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.stopSeek(seekBar.getProgress(), seekBar.getMax());
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void back(View v) {
        finish();
    }

    @OnClick(R.id.iv_play)
    public void playPause(View v) {
        presenter.playPause();
    }


    @Override
    public void onUpnpPause() {
        ivPlay.setImageResource(R.drawable.video_lan_play);
    }

    @Override
    public void onUpnpPlay() {
        ivPlay.setImageResource(R.drawable.video_lan_pause);
    }

    @Override
    public void onUpnpStop() {
        ivPlay.setImageResource(R.drawable.video_lan_play);
    }

    @Override
    public void onUpnpUpdateProgress(String currTime, String timeDuration, int total, int currSecs) {
        timeCurrent.setText(currTime);
        timeTotal.setText(timeDuration);
        mediaProgress.setMax(total);
        mediaProgress.setProgress(currSecs);
    }

    @Override
    public void onUpdateTitle(String title) {
        tvTitle.setText(title);
    }
}
