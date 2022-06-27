package com.aircontrol.beta.room;


import com.aircontrol.beta.device.Device;
import com.aircontrol.beta.device.DeviceSettings;
import com.aircontrol.beta.sensor.Sensor;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Map<String, String>> getRoomsNames(){
        return roomService.getRoomsNames();
    }

    @GetMapping(path = "{RoomId}/get/devices")
    public List<Device> getDevices(@PathVariable("RoomId") int roomId){
        return roomService.getDevices(roomId);
    }

    @GetMapping(path = "{RoomId}/get/sensors")
    public List<Sensor> getSensors(@PathVariable("RoomId") int roomId){
        return roomService.getSensors(roomId);
    }

    @PostMapping
    public void addRoom(@RequestBody Room room){
        roomService.addNewRoom(room);
    }

    @PostMapping(path="addroom/{RoomName}")
    public void addRoomWithOptStats(@PathVariable("RoomName") String roomName,
                                    @RequestBody Stats optStats){
        roomService.addNewRoomWithOptStats(roomName, optStats);
    }

    @PostMapping(path = "{RoomId}/add/device")
    public void addDevice(@PathVariable("RoomId") int roomId, @RequestBody Device device){
        roomService.addNewDevice(roomId, device);
    }

    @PostMapping(path = "{RoomId}/add/sensor")
    public void addSensor(@PathVariable("RoomId") int roomId, @RequestBody Sensor sensor){
        roomService.addNewSensor(roomId, sensor);
    }

    @DeleteMapping(path = "{RoomId}")
    public void deleteRoom(@PathVariable("RoomId") int roomId){
        roomService.deleteRoom(roomId);
    }

    @DeleteMapping(path = "{RoomId}/delete/device/{DeviceId}")
    public void deleteDevice(@PathVariable("RoomId") int roomId,
                             @PathVariable("DeviceId") int deviceId){
        roomService.deleteDevice(roomId, deviceId);
    }

    @DeleteMapping(path = "{RoomId}/delete/sensor/{SensorId}")
    public void deleteSensor(@PathVariable("RoomId") int roomId,
                             @PathVariable("SensorId") int sensorId){
        roomService.deleteSensor(roomId, sensorId);
    }

    @PutMapping(path = "{RoomId}/update/info")
    public  void updateRoomInfo(@PathVariable("RoomId") int roomId,
                                @RequestBody Room room){
        roomService.updateRoomInfo(roomId, room);
    }

    @PutMapping(path = "{RoomId}/update/name")
    public  void updateRoomName(@PathVariable("RoomId") int roomId,
                                @RequestBody String name){
        roomService.updateRoomName(roomId, name);
    }

    @PutMapping(path = "{RoomId}/update/optimalStats")
    public  void updateRoomOptStats(@PathVariable("RoomId") int roomId,
                                    @RequestBody Stats optimalStats){
        roomService.updateRoomOptimalStats(roomId, optimalStats);
    }

    @PutMapping(path = "{RoomId}/update/device/{DeviceId}/name")
    public  void updateDevice(@PathVariable("RoomId") int roomId,
                              @PathVariable("DeviceId") int deviceId,
                              @RequestBody String deviceName ){
        roomService.updateDeviceName(roomId, deviceId, deviceName);
    }

    @PutMapping(path = "{RoomId}/update/device/{DeviceId}/settings")
    public  void updateDeviceSettings(@PathVariable("RoomId") int roomId,
                                      @PathVariable("DeviceId") int deviceId,
                                      @RequestBody DeviceSettings deviceSettings ){
        roomService.updateDeviceSettings(roomId, deviceId, deviceSettings);
    }

    @PutMapping(path = "{RoomId}/update/sensor/{SensorId}/settings")
    public  void updateSensorName(@PathVariable("RoomId") int roomId,
                                  @PathVariable("SensorId") int sensorId,
                                  @RequestBody String sensorName ){
        roomService.updateSensorName(roomId, sensorId, sensorName);
    }

    @GetMapping(path = "{RoomId}/get/currentStats")
    public Stats getCurrentStats(@PathVariable("RoomId") int roomId){
        return roomService.getCurrentStats(roomId);
    }
}
