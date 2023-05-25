package com.example.main;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class healthActivity extends AsyncTask<String, Void, String[]> {
    String sendMsg;
    HttpURLConnection conn;
    String str, data[]= new String[11];
    InputStreamReader tmp;
    BufferedReader reader;
    @Override
    protected String[] doInBackground(String... strings) {
        try {

            int i=0;
            // 접속할 서버 주소 (이클립스에서 login.jsp 실행시 웹브라우저 주소)
            URL url = new URL("http://" + new ip().getIp() +":8080/capstoneServer/webapp/health.jsp");
            System.out.println(url);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
            sendMsg = "&dName=" + strings[0];


            bw.write(sendMsg);
            bw.flush();

            //jsp와 통신 성공 시 수행
            if (conn.getResponseCode() == conn.HTTP_OK) {
                tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                reader = new BufferedReader(tmp);


                // jsp에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    System.out.println("i=" + i +"데이터 = " +str);
                    data[i++]=str;
                }
            } else {
                System.out.println("통신실패");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
       }

         catch (IOException e) {
            e.printStackTrace();
        }

        //jsp로부터 받은 리턴 값
        return data;
    }
}
