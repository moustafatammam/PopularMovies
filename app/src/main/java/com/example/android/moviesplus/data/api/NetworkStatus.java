package com.example.android.moviesplus.data.api;

public class NetworkStatus {
    private Status status;
    private String msg;

    public static final NetworkStatus LOADING_SUCCESSFUL = new NetworkStatus(Status.SUCCESS, null);

    public static final NetworkStatus LOADING_IS_RUNNING = new NetworkStatus(Status.LOADING, null);


    public NetworkStatus(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static NetworkStatus error(String msg){
        return new NetworkStatus(Status.ERROR, msg);
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
