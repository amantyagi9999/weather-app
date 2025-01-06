package com.assesment.weather.service;

import com.assesment.weather.model.WeatherResponse;

public interface WeatherService {

     WeatherResponse getWeather(String city);

     WeatherResponse getFromWeatherStack(String city);

     WeatherResponse getFromOpenWeatherMap(String city);

}
