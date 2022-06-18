package com.aircontrol.beta.room;

public class Stats {
    private int temperature;
    private int humidity;
    private int CO2content;

    public Stats() {
        this.temperature = 24;
        this.humidity = 40;
        this.CO2content = 50;
    }

    public Stats(int temperature, int humidity, int CO2content) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.CO2content = CO2content;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCO2content() {
        return CO2content;
    }

    public void setCO2content(int CO2content) {
        this.CO2content = CO2content;
    }

    public Stats copy(){
        return new Stats(this.temperature, this.humidity, this.CO2content);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", CO2content=" + CO2content +
                '}';
    }
}
