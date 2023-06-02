package com.example.casinocardgame.API;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundRunnable implements Runnable {
    public JSONObject resultObject;
    public String urlInput;
    public JSONObject jsonInput;

    public BackgroundRunnable(String url){
        this.urlInput = url;
        this.jsonInput = null;
    }
    public BackgroundRunnable(String url, JSONObject json){
        this.urlInput = url;
        this.jsonInput = json;
    }

    public JSONObject getResult(){
        return resultObject;
    }

    @Override
    public void run() {
        String result = readURL(urlInput);
        System.out.println(result);
        try {
            JSONObject mainObject = new JSONObject(result);
            this.resultObject = mainObject;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private String readURL(String inputUrl){
        String value = "";
        URL url = null;
        try {
            url = new URL(inputUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println(con);
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            InputStream i = con.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                value += inputLine + "\n";
            }
            in.close();
            con.disconnect();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return value;
    }
}
