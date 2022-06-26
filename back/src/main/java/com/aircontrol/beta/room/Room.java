package com.aircontrol.beta.room;

import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private String name;
    private List<Device> devices;
    private List<Sensor> sensors;

    public Room(int id, String name, List<Device> devices, List<Sensor> sensors) {
        this.id = id;
        this.name = name;
        this.devices = devices;
        this.sensors = sensors;
    }

    public Room() {
    }

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
        devices = new ArrayList<>();
        sensors = new ArrayList<>();
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
                ", devices=" + devices +
                ", sensors=" + sensors +
                '}';
    }
}