package com.example.casinocardgame.API;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiConnection {
    private static ApiConnection instance;

    public static ApiConnection getInstance(){
        if (instance == null){
            instance = new ApiConnection();
        }
        return instance;
    }

    //http://192.168.1.227:5001/casino/game/get/3

    public String getGameName(){
        String name;

        BackgroundRunnable runnable = new BackgroundRunnable("http://192.168.1.227:5001/casino/game/get/3");
        Thread backgroundThread = new Thread(runnable);
        backgroundThread.start();
        Wait();
        JSONObject gameObject = runnable.getResult();
        try {
            if (gameObject != null){
                name = gameObject.getString("name");
                return name;
            } else {
                return null;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private void Wait() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
