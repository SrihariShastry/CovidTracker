package com.example.CovidTracker.Controller;

import com.example.CovidTracker.services.CovidDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CovidDataService service;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("stateList", service.getStateList());
        return "home";
    }
}
