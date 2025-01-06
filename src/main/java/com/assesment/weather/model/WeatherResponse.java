package com.assesment.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    @JsonProperty(" temperature_degrees")
    private double temperature;
    @JsonProperty("wind_speed")
    private double windSpeed;

    public WeatherResponse(double temperature, double windSpeed) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }
}
