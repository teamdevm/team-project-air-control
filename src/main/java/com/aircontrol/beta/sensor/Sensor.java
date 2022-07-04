package com.aircontrol.beta.sensor;

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
        this.temperature = 24 + Integer.parseInt(String.valueOf(random()*10-5));
        this.CO2content = 500 + Integer.parseInt(String.valueOf(random()*10-5));
        this.humidity = 40 + Integer.parseInt(String.valueOf(random()*10-5));
    }

    public Sensor(int id, String name, String description, boolean hasTemperature, boolean hasHumidity, boolean hasCO2content) {
        this.id = id;
        this.sensorHeader = new SensorHeader(name, description, hasTemperature, hasHumidity, hasCO2content);
        this.temperature = 24 + Integer.parseInt(String.valueOf(random()*10-5));
        this.CO2content = 500 + Integer.parseInt(String.valueOf(random()*10-5));
        this.humidity = 40 + Integer.parseInt(String.valueOf(random()*10-5));
    }

    public Sensor(int id, String name, String description, boolean hasTemperature, int temperature, boolean hasHumidity, int humidity, boolean hasCO2content, int CO2content) {
        this.id = id;
        this.sensorHeader = new SensorHeader(name, description, hasTemperature, hasHumidity, hasCO2content);
        this.temperature = temperature;
        this.humidity = humidity;
        this.CO2content = CO2content;
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
        temperature += Integer.parseInt(String.valueOf(random()*10-5));
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
        humidity += Integer.parseInt(String.valueOf(random()*10-5));
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
        CO2content +=  Integer.parseInt(String.valueOf(random()*10-5));
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
