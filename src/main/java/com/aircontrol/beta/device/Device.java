package com.aircontrol.beta.device;

public class Device {
    private int id;
    private String name;
    private String description;
    //configurations here in the future;

    private DeviceSettings settings;

    public Device() {
    }

    public Device(int id, String name, String description, DeviceSettings settings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.settings = settings;
    }

    public Device(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.settings = new DeviceSettings();
    }

    public Device(String name, String description) {
        this.name = name;
        this.description = description;
        this.settings = new DeviceSettings();
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

    public DeviceSettings getSettings() {
        return settings;
    }

    public void setSettings(DeviceSettings settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", settings=" + settings +
                '}';
    }
}
