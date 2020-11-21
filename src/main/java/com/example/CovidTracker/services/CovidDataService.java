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

    private final String VIRUS_DATA_URL="https://api.covidtracking.com/v1/states/ca/current.json";
    private CurrentStats currentStat;
    private HashMap<String,String> stateList;

    public CurrentStats getCurrentStat() {
        return currentStat;
    }
    public HashMap<String,String> getStateList(){
        return stateList;
    }

    //execute after creating the instance of this service
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData(){
        stateList = new HashMap<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            currentStat= mapper.readValue(new URL(VIRUS_DATA_URL),CurrentStats.class);
            stateList = mapper.readValue(new File("src/main/resources/state-list.json"),HashMap.class);
//            System.out.println("hello");
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}

