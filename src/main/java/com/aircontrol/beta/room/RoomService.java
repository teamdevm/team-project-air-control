package com.aircontrol.beta.room;

import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.sensor.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private List<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
        this.rooms.add(new Room("Room 1"));
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

        boolean deviceExists = modifyRoom.getDevices().stream().anyMatch(d -> d.getId() == device.getId());
        if (deviceExists){
            throw new IllegalStateException("device with id " + device.getId() + " already exist");
        }
        modifyRoom.getDevices().add(device);
    }

    public void addNewSensor(String roomName, Sensor sensor) {
        Room modifyRoom = this.rooms.stream().filter(r -> r.getName().equals(roomName)).findAny().orElse(null);
        if (modifyRoom == null){
            throw new IllegalStateException("room with name " + roomName + " does  not exist");
        }

        boolean sensorExists = modifyRoom.getDevices().stream().anyMatch(d -> d.getId() == sensor.getId());
        if (sensorExists){
            throw new IllegalStateException("device with id " + sensor.getId() + " already exist");
        }
        modifyRoom.getSensors().add(sensor);
    }


    public void updateRoom(String roomName, Room room) {
        
    }
}
