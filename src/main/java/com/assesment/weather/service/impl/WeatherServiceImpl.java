package com.assesment.weather.service.impl;

import com.assesment.weather.model.OpenWeatherMapResponse;
import com.assesment.weather.model.WeatherResponse;
import com.assesment.weather.model.WeatherStackResponse;
import com.assesment.weather.service.WeatherService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
    @Value("${weatherstack.api.key}")
    String weatherStackApiKey;

    @Value("${openweathermap.api.key}")
    String openWeatherMapApiKey;

    RestTemplate restTemplate;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("myCache")
   @CircuitBreaker(name = "weatherService", fallbackMethod = "getFromOpenWeatherMap")
    public WeatherResponse getWeather(String city) {
        try {
            logger.debug("Enter in WeatherStack...");
            return getFromWeatherStack(city);
        } catch (Exception e1) {
            logger.debug("Enter in OpenWeatherMap...");
            return getFromOpenWeatherMap(city);
        }
    }

    public WeatherResponse getFromWeatherStack(String city) {
        logger.debug("Getting weather data from WeatherStack...");
        String url = String.format("http://api.weatherstack.com/current?access_key=%s&query=%s", weatherStackApiKey, city);
        WeatherStackResponse weatherStackResponse = restTemplate.getForObject(url, WeatherStackResponse.class);
        logger.debug("Response data from WeatherStack..."+ weatherStackResponse);
        if (weatherStackResponse != null && weatherStackResponse.getCurrent() != null) {
            return new WeatherResponse(weatherStackResponse.getCurrent().getTemperature(), weatherStackResponse.getCurrent().getWindSpeed());
        }
        throw new RuntimeException("WeatherStack API failed");
    }

    public WeatherResponse getFromOpenWeatherMap(String city) {
        logger.debug("Getting weather data from OpenWeatherMap...");
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, openWeatherMapApiKey);
        OpenWeatherMapResponse openWeatherMapResponse = restTemplate.getForObject(url, OpenWeatherMapResponse.class);
        logger.debug("Response data from OpenWeatherMap..."+ openWeatherMapResponse);
        if (openWeatherMapResponse != null && openWeatherMapResponse.getMain() != null && openWeatherMapResponse.getWind() != null) {
            return new WeatherResponse((Double) openWeatherMapResponse.getMain().getTemp(), openWeatherMapResponse.getWind().getSpeed());
        }
        throw new RuntimeException("OpenWeatherMap API failed");
    }

}