package com.aircontrol.beta.room;

import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.sensor.Sensor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            throw new IllegalStateException("room with name " + room.getName() + " already exist");
        }
        this.rooms.add(room);
    }

    public void deleteRoom(String roomName) {
        boolean roomExists = this.rooms.stream().anyMatch(r -> r.getName().equals(roomName));
        if (!roomExists){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }
        this.rooms.removeIf(r -> r.getName().equals(roomName));
    }


    public void addNewDevice(String roomName, Device device) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getName().equals(roomName)).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }

        boolean deviceExists = modifyRoom.getDevices().stream().anyMatch(d -> d.getName().equals(device.getName()));
        if (deviceExists){
            throw new IllegalStateException("device with name " + device.getName() + " already exist");
        }
        modifyRoom.getDevices().add(device);
    }

    public void addNewSensor(String roomName, Sensor sensor) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getName().equals(roomName)).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }

        boolean sensorExists = modifyRoom.getSensors().stream().anyMatch(d -> d.getName().equals(sensor.getName()));
        if (sensorExists){
            throw new IllegalStateException("sensor with name " + sensor.getName() + " already exist");
        }
        modifyRoom.getSensors().add(sensor);
    }

    public List<String> getRoomsNames() {
        List<String> roomsNames = new ArrayList<>();
        for (Room r :
             rooms) {
            roomsNames.add(r.getName());
        }
        return roomsNames;
    }

    public void updateRoomInfo(String roomName, Room room) {
        Optional<Room> roomForUpdate = rooms.stream().filter(r -> r.getName().equals(roomName)).findAny();
        if(!roomForUpdate.isPresent()){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }
        roomForUpdate.get().copyMetaData(room);
    }

    public List<Device> getDevices(String roomName) {
        Optional<Room> room = rooms.stream().filter(r -> r.getName().equals(roomName)).findAny();
        if(!room.isPresent()){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }
        return room.get().getDevices();
    }

    public List<Sensor> getSensors(String roomName) {
        Optional<Room> room = rooms.stream().filter(r -> r.getName().equals(roomName)).findAny();
        if(!room.isPresent()){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }
        return room.get().getSensors();
    }

    public void deleteDevice(String roomName, int deviceId) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getName().equals(roomName)).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }

        boolean deletedDevice = modifyRoom.getDevices().removeIf(r -> r.getId() == deviceId);
        if(!deletedDevice){
            throw new IllegalStateException("device with id " + deviceId + " doesn't exist");
        }
    }

    public void deleteSensor(String roomName, int sensorId) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getName().equals(roomName)).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }

        boolean deletedSensor = modifyRoom.getSensors().removeIf(r -> r.getId() == sensorId);
        if(!deletedSensor){
            throw new IllegalStateException("sensor with id " + sensorId + " doesn't exist");
        }
    }

    public void updateRoomName(String roomName, String name) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getName().equals(roomName)).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }

        Room checkRoom = this.rooms.stream().filter(r -> r.getName().equals(name)).findAny().orElse(null);
        if (checkRoom != null){
            throw new IllegalStateException("room with name " + name + " already exist");
        }
        modifyRoom.setName(name);
    }
}
