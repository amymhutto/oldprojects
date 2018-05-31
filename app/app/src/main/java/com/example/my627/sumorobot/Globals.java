package com.example.my627.sumorobot;

import android.app.Application;

public class Globals extends Application {
    private String ipAddress="10.108.71.11:80";

    public String getIpAddress(){return this.ipAddress;
    }
}
