package com.example.CovidTracker.services;

import com.example.CovidTracker.Models.CurrentStats;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

@Service
public class CovidDataService {

    private CurrentStats currentStat;
    private String URL;

    public void setURL(String URL) {
        this.URL = URL;
    }

    public CurrentStats getCurrentStat() {
        return currentStat;
    }

    //execute after creating the instance of this service
//    @PostConstruct
//    @Scheduled(cron = "* * 1 * * *")
    public void fetchData(String url){
//        stateList = new HashMap<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            this.currentStat= mapper.readValue(new URL(url),CurrentStats.class);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}

