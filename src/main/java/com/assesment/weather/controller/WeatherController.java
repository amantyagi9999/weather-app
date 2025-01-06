package com.assesment.weather.controller;

import com.assesment.weather.model.WeatherResponse;
import com.assesment.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/v1/weather")
    public ResponseEntity<?> getWeather(@RequestParam(defaultValue = "melbourne") String city) {
        logger.debug("Enter in WeatherController..");
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        if (weatherResponse != null) {
            return ResponseEntity.ok(weatherResponse);
        } else {
            return ResponseEntity.status(503).body("All weather providers are currently unavailable.");
        }
    }
}
