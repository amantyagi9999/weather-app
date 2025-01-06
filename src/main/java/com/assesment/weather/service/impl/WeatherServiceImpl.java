package com.assesment.weather.service.impl;

import com.assesment.weather.model.OpenWeatherMapResponse;
import com.assesment.weather.model.WeatherResponse;
import com.assesment.weather.model.WeatherStackResponse;
import com.assesment.weather.service.WeatherService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weatherstack.api.key}")
    String weatherStackApiKey;

    @Value("${openweathermap.api.key}")
    String openWeatherMapApiKey;

    RestTemplate restTemplate;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Cacheable(value = "weather", key = "#city", unless = "#result == null")
    @CircuitBreaker(name = "weatherService", fallbackMethod = "getFromOpenWeatherMap")
    public WeatherResponse getWeather(String city) {
        try {
            return getFromWeatherStack(city);
        } catch (Exception e1) {
            return getFromOpenWeatherMap(city);
        }
    }

    public WeatherResponse getFromWeatherStack(String city) {
        String url = String.format("http://api.weatherstack.com/curren?access_key=%s&query=%s", weatherStackApiKey, city);
        WeatherStackResponse response = restTemplate.getForObject(url, WeatherStackResponse.class);
        if (response != null && response.getCurrent() != null) {
            return new WeatherResponse(response.getCurrent().getTemperature(), response.getCurrent().getWindSpeed());
        }
        throw new RuntimeException("WeatherStack API failed");
    }

    public WeatherResponse getFromOpenWeatherMap(String city) {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, openWeatherMapApiKey);
        OpenWeatherMapResponse response = restTemplate.getForObject(url, OpenWeatherMapResponse.class);

        if (response != null && response.getMain() != null && response.getWind() != null) {
            return new WeatherResponse((Double) response.getMain().getTemp(), response.getWind().getSpeed());
        }
        throw new RuntimeException("OpenWeatherMap API failed");
    }

}