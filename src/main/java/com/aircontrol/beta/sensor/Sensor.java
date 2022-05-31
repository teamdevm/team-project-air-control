package com.aircontrol.beta.sensor;

public class Sensor {
    private int id;
    private String name;
    private String description;
    private boolean hasTemperature;
    private int temperature;
    private boolean hasHumidity;
    private int humidity;
    private boolean hasCO2content;
    private int CO2content;

    public Sensor(int id, String name, String description, boolean hasTemperature, boolean hasHumidity, boolean hasCO2content) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hasTemperature = hasTemperature;
        this.hasHumidity = hasHumidity;
        this.hasCO2content = hasCO2content;
        this.temperature = 0;
        this.CO2content = 0;
        this.humidity = 0;
    }

    public Sensor(int id, String name, String description, boolean hasTemperature, int temperature, boolean hasHumidity, int humidity, boolean hasCO2content, int CO2content) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hasTemperature = hasTemperature;
        this.temperature = temperature;
        this.hasHumidity = hasHumidity;
        this.humidity = humidity;
        this.hasCO2content = hasCO2content;
        this.CO2content = CO2content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasTemperature() {
        return hasTemperature;
    }

    public void setHasTemperature(boolean hasTemperature) {
        this.hasTemperature = hasTemperature;
    }

    public int getTemperature() {

        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isHasHumidity() {
        return hasHumidity;
    }

    public void setHasHumidity(boolean hasHumidity) {
        this.hasHumidity = hasHumidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public boolean isHasCO2content() {
        return hasCO2content;
    }

    public void setHasCO2content(boolean hasCO2content) {
        this.hasCO2content = hasCO2content;
    }

    public int getCO2content() {
        return CO2content;
    }

    public void setCO2content(int CO2content) {
        this.CO2content = CO2content;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hasTemperature=" + hasTemperature +
                ", temperature=" + temperature +
                ", hasHumidity=" + hasHumidity +
                ", humidity=" + humidity +
                ", hasCO2content=" + hasCO2content +
                ", CO2content=" + CO2content +
                '}';
    }
}
