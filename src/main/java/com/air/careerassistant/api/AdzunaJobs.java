package com.air.careerassistant.api;

import com.air.careerassistant.model.AdzunaResult;
import com.air.careerassistant.model.job.Job;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AdzunaJobs {
    //1. location and title from the front end
    //2. Make the api call here
    //3. return a gson object
    public static void getAdzunaJobs(String title, String location) throws IOException {
        HttpURLConnection connection;
        String description;
        String place = "&where=" + location;
        String what = "&what=" + title;
        String stringUrl = "https://api.adzuna.com/v1/api/jobs/us/search/1?app_id=d64a4bcb&app_key=6fa84eae583e461dbde5a8416fc2adca" + place + what;

        URL url = new URL(stringUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String firstLine = input.readLine();
        StringBuffer entireStringFromResponse = new StringBuffer();
        while (firstLine != null) {
            entireStringFromResponse.append(firstLine);
            firstLine = input.readLine();
        }
        input.close();
        System.out.println("this is the entire string from ADZUNA response  "+entireStringFromResponse);

        getGsonObject(entireStringFromResponse);
    }

    private static void getGsonObject(StringBuffer response){
        Gson gson = new Gson();
        ArrayList<Job> jobListFromAdzuna = new ArrayList<>();
        AdzunaResult adzunaResult = gson.fromJson(response.toString(), AdzunaResult.class);
        System.out.println("These are Adzuna results" + adzunaResult);
        jobListFromAdzuna = gson.fromJson(adzunaResult.toString(), new TypeToken<ArrayList<Job>>(){}.getType());
        System.out.println("Adzuna jobs list here  "+jobListFromAdzuna);
    }
}
