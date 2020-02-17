package com.example.android.moviesplus.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static AppExecutors sInstance;

    private static final int THREAD_COUNT = 5;

    private final Executor diskIO;

    private final Executor mainThread;

    private final Executor networkIO;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public static AppExecutors getInstance(){
        if (sInstance == null){
            synchronized (AppExecutors.class){
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(THREAD_COUNT),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public static class MainThreadExecutor implements Executor{
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
