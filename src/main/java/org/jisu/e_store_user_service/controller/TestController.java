package org.jisu.e_store_user_service.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping
    public Map<?,?> test(){

 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Get the current time and format it
        String formattedTime = LocalDateTime.now().format(formatter);

        // Return the health status with the formatted time
        return Map.of("Status", "Healthy", "Time", formattedTime);    }
}
