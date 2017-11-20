package cn.rongcloud.live;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import cn.rongcloud.live.fakeserver.FakeServer;

public class App extends MultiDexApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LiveKit.init(context, FakeServer.getAppKey());
    }

    public static Context getContext() {
        return context;
    }
}