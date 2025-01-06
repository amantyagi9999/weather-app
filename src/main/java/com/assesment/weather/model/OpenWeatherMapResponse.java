package com.assesment.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public class OpenWeatherMapResponse {

    // Nested class to represent "Main" section
    public static class Main {
        @JsonProperty("temp")
        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    '}';
        }
    }

    // Nested class to represent "wind" section
    public static class Wind {
        @JsonProperty("speed")
        private double speed;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    '}';
        }
    }

    @JsonProperty("main")
    private Main main;

    @JsonProperty("wind")
    private Wind wind;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    // Methods to directly get temp and speed
    public double getTemp() {
        return main != null ? main.getTemp() : 0;
    }

    public double getSpeed() {
        return wind != null ? wind.getSpeed() : 0;
    }

    @Override
    public String toString() {
        return "OpenWeatherMapResponse{" +
                "main=" + main +
                ", wind=" + wind +
                '}';
    }
}

