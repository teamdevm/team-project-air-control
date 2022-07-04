package com.aircontrol.beta.sensor;

public class SensorHeader {
    private String name;
    private String description;
    private boolean hasTemperature;
    private boolean hasHumidity;
    private boolean hasCO2content;

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

    public boolean isHasHumidity() {
        return hasHumidity;
    }

    public void setHasHumidity(boolean hasHumidity) {
        this.hasHumidity = hasHumidity;
    }

    public boolean isHasCO2content() {
        return hasCO2content;
    }

    public void setHasCO2content(boolean hasCO2content) {
        this.hasCO2content = hasCO2content;
    }

    public SensorHeader(String name, String description, boolean hasTemperature, boolean hasHumidity, boolean hasCO2content) {
        this.name = name;
        this.description = description;
        this.hasTemperature = hasTemperature;
        this.hasHumidity = hasHumidity;
        this.hasCO2content = hasCO2content;
    }

    @Override
    public String toString() {
        return "SensorHeader{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hasTemperature=" + hasTemperature +
                ", hasHumidity=" + hasHumidity +
                ", hasCO2content=" + hasCO2content +
                '}';
    }
}
