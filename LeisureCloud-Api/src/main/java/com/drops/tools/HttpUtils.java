package com.drops.tools;

import com.drops.tools.HttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;


public class HttpUtils {
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Cookie", "cookie的参数");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());

            out.print(param);

            out.flush();


            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {


            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        sendPost("http://localhost:8888/deleteByParem", "tableName=user&paremStr={'id':'2','name':'XXXXX222'}");
    }
}