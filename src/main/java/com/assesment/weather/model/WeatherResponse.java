package com.assesment.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("temperature_degrees")
    private double temperature;

    public WeatherResponse(double temperature, double windSpeed) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }
}
