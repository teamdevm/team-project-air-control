package com.aircontrol.beta.room;

import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.device.DeviceSettings;
import com.aircontrol.beta.sensor.Sensor;
import com.aircontrol.beta.sensor.SensorHeader;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {
    private List<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
        this.rooms.add(new Room(1,"Room_1"));
        //this.rooms.add(new Room("Room 2"));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addNewRoom(Room room) {
        boolean roomExists = this.rooms.stream().anyMatch(r -> r.getName().equals(room.getName()));
        if (roomExists){
            throw new IllegalStateException("room with id " + room.getName() + " already exist");
        }
        this.rooms.add(room);
    }

    public void deleteRoom(int roomId) {
        boolean roomExists = this.rooms.stream().anyMatch(r -> r.getId() == roomId);
        if (!roomExists){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }
        this.rooms.removeIf(r -> r.getId() == roomId);
    }


    public void addNewDevice(int roomId, Device device) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() ==roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        boolean deviceExists = modifyRoom.getDevices().stream().anyMatch(d -> d.getName().equals(device.getName()));
        if (deviceExists){
            throw new IllegalStateException("device with name " + device.getName() + " already exist");
        }
        modifyRoom.getDevices().add(device);
    }

    public void addNewSensor(int roomId, SensorHeader sensor) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        int newSensorId = rooms.size()+1;
        int tmpId = newSensorId;
        Sensor sensorFinder = modifyRoom.getSensors().stream().filter(s -> s.getId() == tmpId).findAny().orElse(null);
        while(sensorFinder != null){
            newSensorId++;
            int ltmpId = newSensorId;
            sensorFinder = modifyRoom.getSensors().stream().filter(s -> s.getId() == ltmpId).findAny().orElse(null);
        }
        modifyRoom.getSensors().add(new Sensor(newSensorId, sensor));
    }

    public List<Map<String, String>> getRoomsNames() {

        List<Map<String, String>> roomsNames = new ArrayList<>();

        for (Room r :
             rooms) {
            Map<String, String> jo = new HashMap<>();
            jo.put("id", ((Integer)r.getId()).toString());
            jo.put("name", r.getName());
            roomsNames.add(jo);
        }
        return roomsNames;
    }

    public void updateRoomInfo(int roomId, Room room) {
        Room roomForUpdate = rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if(roomForUpdate == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }
        roomForUpdate.copyMetaData(room);
    }

    public List<Device> getDevices(int roomId) {
        Optional<Room> room = rooms.stream().filter(r -> r.getId() == roomId).findAny();
        if(!room.isPresent()){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }
        return room.get().getDevices();
    }

    public List<Sensor> getSensors(int roomId) {
        Optional<Room> room = rooms.stream().filter(r -> r.getId() == roomId).findAny();
        if(!room.isPresent()){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }
        return room.get().getSensors();
    }

    public void deleteDevice(int roomId, int deviceId) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        boolean deletedDevice = modifyRoom.getDevices().removeIf(r -> r.getId() == deviceId);
        if(!deletedDevice){
            throw new IllegalStateException("device with id " + deviceId + " does not exist");
        }
    }

    public void deleteSensor(int roomId, int sensorId) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        boolean deletedSensor = modifyRoom.getSensors().removeIf(r -> r.getId() == sensorId);
        if(!deletedSensor){
            throw new IllegalStateException("sensor with id " + sensorId + " doesn't exist");
        }
    }

    public void updateRoomName(int roomId, String name) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        Room checkRoom = this.rooms.stream().filter(r -> r.getName().equals(name)).findAny().orElse(null);
        if (checkRoom != null){
            throw new IllegalStateException("room with name " + name + " already exist");
        }
        modifyRoom.setName(name);
    }

    public void updateRoomOptimalStats(int roomId, Stats optimalStats) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        modifyRoom.setOptimalStats(optimalStats);
    }

    public void updateDeviceSettings(int roomId, int deviceId, DeviceSettings deviceSettings) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        Device modifyDevice = modifyRoom.getDevices().stream().filter(d -> d.getId() == deviceId).findAny().orElse(null);
        if(modifyDevice == null){
            throw new IllegalStateException("device with id " + deviceId + " does not exist");
        }

        modifyDevice.setSettings(deviceSettings);
    }

    public void updateDeviceName(int roomId, int deviceId, String deviceName) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        Device modifyDevice = modifyRoom.getDevices().stream().filter(d -> d.getId() == deviceId).findAny().orElse(null);
        if(modifyDevice == null){
            throw new IllegalStateException("device with id " + deviceId + " does not exist");
        }

        boolean deviceExists = modifyRoom.getDevices().stream().anyMatch(d -> d.getName().equals(deviceName));
        if (deviceExists){
            throw new IllegalStateException("device with name " + deviceName + " already exist");
        }

        modifyDevice.setName(deviceName);
    }

    public void updateSensorName(int roomId, int sensorId, String sensorName) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        Sensor modifySensor = modifyRoom.getSensors().stream().filter(s -> s.getId() == sensorId).findAny().orElse(null);
        if(modifySensor == null){
            throw new IllegalStateException("device with id " + sensorId + " does not exist");
        }

        boolean sensorExists = modifyRoom.getSensors().stream().anyMatch(d -> d.getName().equals(sensorName));
        if (sensorExists){
            throw new IllegalStateException("sensor with name " + sensorName + " already exist");
        }

        modifySensor.setName(sensorName);
    }

    public Stats getCurrentStats(int roomId) {
        Room roomOperation = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (roomOperation == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        return roomOperation.getCurrentStats();
    }

    public Stats getOptimalStats(int roomId) {
        Room roomOperation = this.rooms.stream().filter(r -> r.getId() == roomId).findAny().orElse(null);
        if (roomOperation == null){
            throw new IllegalStateException("room with id " + roomId + " does not exist");
        }

        return roomOperation.getOptimalStats();
    }


    public void addNewRoomWithOptStats(String roomName, Stats optStats) {
        int newRoomId = rooms.size()+1;
        int tmpId = newRoomId;
        Room roomFinder = this.rooms.stream().filter(r -> r.getId() == tmpId).findAny().orElse(null);
        while(roomFinder != null){
            newRoomId++;
            int ltmpId = newRoomId;
            roomFinder = this.rooms.stream().filter(r -> r.getId() == ltmpId).findAny().orElse(null);
        }

        rooms.add(new Room(newRoomId, roomName, optStats.getTemperature(), optStats.getHumidity(), optStats.getCO2content()));
    }
}
