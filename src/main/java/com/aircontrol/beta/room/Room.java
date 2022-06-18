package com.aircontrol.beta.room;

import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private String name;
    private Stats optimalStats;
    private Stats currentStats;
    private List<Device> devices;
    private List<Sensor> sensors;

    public Room(int id, String name, List<Device> devices, List<Sensor> sensors) {
        this.id = id;
        this.name = name;
        this.devices = devices;
        this.sensors = sensors;
        this.optimalStats = new Stats();
        this.currentStats = new Stats(-1, -1, -1);
    }

    public Room() {
    }

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
        devices = new ArrayList<>();
        sensors = new ArrayList<>();
        this.optimalStats = new Stats();
        this.currentStats = new Stats(-1, -1, -1);
    }

    public Room(int id, String name, int optimalTemperature, int optimalHumidity, int optimalCO2content) {
        this.id = id;
        this.name = name;
        this.optimalStats = new Stats(optimalTemperature, optimalHumidity, optimalCO2content);
        this.currentStats = new Stats(-1, -1, -1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDevice(Device device){
        this.devices.add(device);
    }

    public void addSensor(Sensor sensor){
        this.sensors.add(sensor);
    }

    public String getName() {
        return name;
    }

    public int getOptimalTemperature() {
        return this.optimalStats.getTemperature();
    }

    public void setOptimalTemperature(int optimalTemperature) {
        this.optimalStats.setTemperature(optimalTemperature);
    }

    public int getOptimalHumidity() {
        return this.optimalStats.getHumidity();
    }

    public void setOptimalHumidity(int optimalHumidity) {
        this.optimalStats.setHumidity(optimalHumidity);
    }

    public int getOptimalCO2content() {
        return this.optimalStats.getCO2content();
    }

    public void setOptimalCO2content(int optimalCO2content) {
        this.optimalStats.setCO2content(optimalCO2content);
    }

    public int getCurrentTemperature() {
        return this.currentStats.getTemperature();
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentStats.setTemperature(currentTemperature);
    }

    public int getCurrentHumidity() {
        return this.currentStats.getHumidity();
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentStats.setHumidity(currentHumidity);
    }

    public int getCurrentCO2content() {
        return this.currentStats.getCO2content();
    }

    public void setCurrentCO2content(int currentCO2content) {
        this.currentStats.setCO2content(currentCO2content);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", optimalStats=" + optimalStats +
                ", currentStats=" + currentStats +
                ", devices=" + devices +
                ", sensors=" + sensors +
                '}';
    }

    public void copyMetaData(Room room){
        this.id = room.getId();
        this.name = room.getName();
        this.optimalStats = room.optimalStats.copy();
    }
}
