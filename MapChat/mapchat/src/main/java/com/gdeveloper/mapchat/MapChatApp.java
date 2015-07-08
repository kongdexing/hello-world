package com.gdeveloper.mapchat;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by kongdexing on 6/30/15.
 */
public class MapChatApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * IMKit SDK调用第一步 初始化
         * context上下文
         */
        RongIM.init(this);
        /**d
         * 融云SDK事件监听处理
         */
        RongCloudEvent.init(this);

        MapChatContext.init(this);

    }
}
