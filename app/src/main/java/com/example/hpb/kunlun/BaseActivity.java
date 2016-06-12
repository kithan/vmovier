package com.example.hpb.kunlun;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by 0000- on 2016/6/11.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public T presenter;

    public abstract T initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
        ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach((V) this);
        }
        initViews();
    }


    public abstract int getContentView();

    public abstract void initViews();

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.dettach();
        }
        super.onDestroy();
    }
}
