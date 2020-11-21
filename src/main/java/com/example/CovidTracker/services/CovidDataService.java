package com.example.CovidTracker.services;

import com.example.CovidTracker.Models.CurrentStats;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {

    private String VIRUS_DATA_URL="https://api.covidtracking.com/v1/states/daily.json";
    private List<CurrentStats> currentStats;

    //execute after creating the instance of this service
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData(){
        currentStats = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(VIRUS_DATA_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CurrentStats currentStat = new CurrentStats();
            System.out.println(response.body());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

