package com.air.careerassistant.api;

import com.air.careerassistant.model.job.Job;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
//import java.util.regex.pattern;

public class GitHubJobs {
//    https://jobs.github.com/positions.json?description=python&full_time=true&location=seattle
    //1. location and title from the front end
    //2. Make the api call here
    //3. return a gson object
    public static void getGitHubJobs(String title, String location) throws IOException {
        HttpURLConnection connection;
        String description = "description="+title;
        String place = "&location="+location;
        URL url = new URL("https://jobs.github.com/positions.json?"+description+place);
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
        System.out.println("this is the entire string from response  "+entireStringFromResponse);
        getGsonObject(entireStringFromResponse);

    }

    private static void getGsonObject(StringBuffer response){
        Gson gson = new Gson();
        ArrayList<Job> jobListFromApi = new ArrayList<>();
        jobListFromApi = gson.fromJson(response.toString(), new TypeToken<ArrayList<Job>>(){}.getType());
        System.out.println("jobs list here  "+jobListFromApi);
    }

}
