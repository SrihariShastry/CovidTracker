package com.example.CovidTracker.services;

import com.example.CovidTracker.Models.CurrentStats;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URL;

@Service
public class CovidDataService {

    private final String VIRUS_DATA_URL="https://api.covidtracking.com/v1/states/ca/current.json";

    //execute after creating the instance of this service
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData(){

        try {
            ObjectMapper mapper = new ObjectMapper();
            CurrentStats currentStat= mapper.readValue(new URL(VIRUS_DATA_URL),CurrentStats.class);
            System.out.println("hello");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

