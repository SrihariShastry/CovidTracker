package com.example.CovidTracker.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CovidDataService {

    public String VIRUS_DATA_URL="https://api.covidtracking.com/v1/us/current.json";

    //execute after creating the instance of this service
    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchData(){

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(VIRUS_DATA_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

