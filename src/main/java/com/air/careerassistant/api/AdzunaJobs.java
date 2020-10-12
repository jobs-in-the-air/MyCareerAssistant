package com.air.careerassistant.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdzunaJobs {
    //1. location and title from the front end
    //2. Make the api call here
    //3. return a gson object
    public static void getAdzunaJobs(String title, String location) throws IOException {
        HttpURLConnection connection;
        String description;
        String place;
        URL url = new URL(
                "https://api.adzuna.com/v1/api/jobs/us/search/1?app_id=d64a4bcb&app_key=6fa84eae583e461dbde5a8416fc2adca");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String firstLine = input.readLine();
        StringBuffer entireStringFromResponse = new StringBuffer();
        while (firstLine != null) {
            entireStringFromResponse.append(firstLine);
            firstLine = input.readLine();
        }
        input.close();
        System.out.println("this is the entire string from ADZUNA response  "+entireStringFromResponse);
    }
}