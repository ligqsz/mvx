package com.pax.mvp;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ligq
 * @date 2018/9/28
 */

public class App extends Application {
    private ThreadPoolExecutor poolExecutor;
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread thread = new Thread(r, "Background in PaxDevice");
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.setDaemon(true);
                return thread;
            }
        };
        poolExecutor = new ThreadPoolExecutor(3, 10, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(128),
                threadFactory);
    }

    public static App getApp() {
        return mApp;
    }

    public void runInBackGround(Runnable runnable) {
        poolExecutor.execute(runnable);
    }
}
