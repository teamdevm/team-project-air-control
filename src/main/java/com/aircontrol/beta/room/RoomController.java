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

    @GetMapping
    public List<Room> getRooms(){
        return roomService.getRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody Room room){
        roomService.addNewRoom(room);
    }

    @PostMapping(path = "{RoomName}/1")
    public void addDevice(@PathVariable("RoomName") String roomName, @RequestBody Device device){
        roomService.addNewDevice(roomName, device);
    }

    @PostMapping(path = "{RoomName}/2")
    public void addSensor(@PathVariable("RoomName") String roomName, @RequestBody Sensor sensor){
        roomService.addNewSensor(roomName, sensor);
    }

    @DeleteMapping(path = "{RoomName}")
    public void deleteRoom(@PathVariable("RoomName") String roomName){
        roomService.deleteRoom(roomName);
    }

    @PutMapping(path= "{RoomName}")
    public void updateRoom(@PathVariable("RoomName") String roomName,
                           @RequestBody Room room){
        roomService.updateRoom(roomName, room);
    }
}
