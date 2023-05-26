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

    public String playerName = "TesterT";
    public double balance = 100;

    public static ApiConnection getInstance(){
        if (instance == null){
            instance = new ApiConnection();
            instance.loadBalance();
        }
        return instance;
    }

    //base url
    //http://192.168.1.227:5001/casino/

    public double getPlayerBalance(){
        Double balance;

        BackgroundRunnable runnable = new BackgroundRunnable("http://192.168.1.227:5001/casino/player/get/"+playerName);
        Thread backgroundThread = new Thread(runnable);
        backgroundThread.start();
        Wait();
        JSONObject playerObject = runnable.getResult();
        try {
            if (playerObject != null){
                balance = playerObject.getDouble("balance");
                return balance;
            } else {
                return 0;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void loadBalance(){
        double balance = getPlayerBalance();
        this.balance = balance;
    }

    private void updateBalance(double amount){
        //request url
        String playerUrl = "https://localhost:5001/casino/player/update/"+playerName;
        //constructing the request body
        JSONObject balanceObject = new JSONObject();
        try {
            balanceObject.put("balance", amount);}
        catch (Exception e){
            System.out.println(e);
        }

        BackgroundRunnable runnable = new BackgroundRunnable(playerUrl, balanceObject);
        Thread backgroundThread = new Thread(runnable);
        backgroundThread.start();
        Wait();
        JSONObject playerObject = runnable.getResult();
        try {
            if (playerObject != null){
                balance = playerObject.getDouble("balance");
                this.balance = balance;
            } else {
                this.balance = 0;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    public void processGameOver(int bet, boolean isWon){
        double betInput = (double) bet;
        double newBalance;
        if (isWon){
            newBalance = this.balance + betInput;
        } else {
            newBalance = this.balance - betInput;
        }
        //updateBalance(newBalance);
    }

    private void Wait() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
