package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.Room;


import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.repository.RoomRepository;
import hotelmanagement.HotelManagementDemo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> createRoom(@RequestBody Room room) {
        roomService.saveRoom(room);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Room added successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.fetchAllRooms());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable int roomId) {
        return ResponseEntity.ok(roomService.findRoomById(roomId));
    }
// updating the room
    @PutMapping("/update/{roomId}")
    public ResponseEntity<SuccessResponse> updateRoom(@PathVariable int roomId, @RequestBody Room room) {
        roomService.modifyRoom(roomId, room);
        return ResponseEntity.ok(new SuccessResponse("UPDATESUCCESS", "Room updated successfully"));
    }
    //To delete the given room

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<SuccessResponse> deleteRoom(@PathVariable int roomId) {
        roomService.removeRoom(roomId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS", "Room deleted successfully"));
    }

    @GetMapping("/available/{roomTypeId}")
    public ResponseEntity<List<Room>> getAvailableRoomsByType(@PathVariable int roomTypeId) {
        return ResponseEntity.ok(roomService.fetchAvailableRoomsByType(roomTypeId));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Room>> getRoomsByLocation(@PathVariable String location) {
        //return ResponseEntity.ok(roomService.fetchRoomsByLocation(location));
        List<Room> rooms = roomRepository.findRoomsByLocation(location); 
        return ResponseEntity.ok(rooms);
       
    }

    @GetMapping("/amenities/{amenityId}")
    public ResponseEntity<List<Room>> getRoomsByAmenity(@PathVariable int amenityId) {
        return ResponseEntity.ok(roomService.fetchRoomsByAmenity(amenityId));
    }
}