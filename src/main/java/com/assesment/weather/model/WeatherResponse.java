package com.assesment.weather.model;

public class WeatherResponse {

    private double temperature;
    private double windSpeed;

    public WeatherResponse(double temperature, double windSpeed) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }
}
