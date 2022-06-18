package com.aircontrol.beta.room;


import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.sensor.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/roomsinfo")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(path = "all")
    public List<Room> getRooms(){
        return roomService.getRooms();
    }

    @GetMapping(path = "roomList")
    public List<String> getRoomsNames(){
        return roomService.getRoomsNames();
    }

    @GetMapping(path = "{RoomName}/get/devices")
    public List<Device> getDevices(@PathVariable("RoomName") String roomName){
        return roomService.getDevices(roomName);
    }

    @GetMapping(path = "{RoomName}/get/sensors")
    public List<Sensor> getSensors(@PathVariable("RoomName") String roomName){
        return roomService.getSensors(roomName);
    }

    @PostMapping
    public void addRoom(@RequestBody Room room){
        roomService.addNewRoom(room);
    }

    @PostMapping(path = "{RoomName}/add/device")
    public void addDevice(@PathVariable("RoomName") String roomName, @RequestBody Device device){
        roomService.addNewDevice(roomName, device);
    }

    @PostMapping(path = "{RoomName}/add/sensor")
    public void addSensor(@PathVariable("RoomName") String roomName, @RequestBody Sensor sensor){
        roomService.addNewSensor(roomName, sensor);
    }

    @DeleteMapping(path = "{RoomName}")
    public void deleteRoom(@PathVariable("RoomName") String roomName){
        roomService.deleteRoom(roomName);
    }

    @DeleteMapping(path = "{RoomName}/delete/device/{DeviceId}")
    public void deleteDevice(@PathVariable("RoomName") String roomName,
                             @PathVariable("DeviceId") int deviceId){
        roomService.deleteDevice(roomName, deviceId);
    }

    @DeleteMapping(path = "{RoomName}/delete/sensor/{SensorId}")
    public void deleteSensor(@PathVariable("RoomName") String roomName,
                             @PathVariable("SensorId") int sensorId){
        roomService.deleteSensor(roomName, sensorId);
    }

    @PutMapping(path = "{RoomName}/update/info")
    public  void updateRoomInfo(@PathVariable("RoomName") String roomName,
                                @RequestBody Room room){
        roomService.updateRoomInfo(roomName, room);
    }

    @PutMapping(path = "{RoomName}/update/name")
    public  void updateRoomName(@PathVariable("RoomName") String roomName,
                                @RequestBody String name){
        roomService.updateRoomName(roomName, name);
    }
}
