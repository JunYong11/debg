package com.example.main;

import android.app.Application;

public class ip extends Application {
    private String address = "211.108.193.60";

    public String getIp(){
        return address;
    }
}
