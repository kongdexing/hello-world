package com.gdeveloper.mapchat.activity;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.gdeveloper.mapchat.R;

public abstract class BaseActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        getSupportActionBar().setLogo(R.mipmap.de_bar_logo);//actionbar 添加logo

        setContentView(setContentViewResId());
        initView();
        initData();
    }

    /**
     * 加载layout
     *
     * @return
     */
    protected abstract int setContentViewResId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * init data
     */
    protected abstract void initData();
}
