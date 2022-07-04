package com.aircontrol.beta.sensor;

import com.aircontrol.beta.room.Stats;

import static java.lang.Math.random;

public class Sensor {
    private int id;

    private SensorHeader sensorHeader;
    private int temperature;
    private int humidity;
    private int CO2content;

    public Sensor() {
    }

    public Sensor(int id, SensorHeader sensorHeader) {
        this.id = id;
        this.sensorHeader = sensorHeader;
        this.temperature = 24;
        this.CO2content = 500;
        this.humidity = 40;
    }

    public Sensor(int id, String name, String description, boolean hasTemperature, boolean hasHumidity, boolean hasCO2content) {
        this.id = id;
        this.sensorHeader = new SensorHeader(name, description, hasTemperature, hasHumidity, hasCO2content);
        this.temperature = 24;
        this.CO2content = 500;
        this.humidity = 40;
    }

    public Sensor(int id, String name, String description, boolean hasTemperature, int temperature, boolean hasHumidity, int humidity, boolean hasCO2content, int CO2content) {
        this.id = id;
        this.sensorHeader = new SensorHeader(name, description, hasTemperature, hasHumidity, hasCO2content);
        this.temperature = temperature;
        this.humidity = humidity;
        this.CO2content = CO2content;
    }
    public void getNewStats(Stats optimalStats){
        int min = 0;
        if(optimalStats.getTemperature() > temperature)
           min = -2;
        else
            min = -5;
        temperature += (int)(Math.random()*(5-min)+min);
        if(optimalStats.getHumidity() > humidity)
            min = -2;
        else
            min = -5;
        humidity += (int)(Math.random()*(5-min)+min);
        if(optimalStats.getCO2content() > CO2content)
            min = -2;
        else
            min = -5;
        CO2content += (int)(Math.random()*(5-min)+min);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return sensorHeader.getName();
    }

    public void setName(String name) {
        sensorHeader.setName(name);
    }

    public String getDescription() {
        return sensorHeader.getDescription();
    }

    public void setDescription(String description) {
        sensorHeader.setDescription(description);
    }

    public boolean isHasTemperature() {
        return sensorHeader.isHasTemperature();
    }

    public void setHasTemperature(boolean hasTemperature) {
        sensorHeader.setHasHumidity(hasTemperature);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isHasHumidity() {
        return sensorHeader.isHasHumidity();
    }

    public void setHasHumidity(boolean hasHumidity) {
        this.sensorHeader.setHasHumidity(hasHumidity);
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public boolean isHasCO2content() {

        return sensorHeader.isHasCO2content();
    }

    public void setHasCO2content(boolean hasCO2content) {
        this.sensorHeader.setHasCO2content(hasCO2content);
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
                ", sensorHeader=" + sensorHeader +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", CO2content=" + CO2content +
                '}';
    }
}
