package com.habr.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Parser {

    private static String content = "";

    private static String getContentOfHTTPPage(String pageAddress) throws Exception {
        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }

    public static void setContentStringWithHTML(String pageAddress) {
        try {
            content = getContentOfHTTPPage(pageAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
