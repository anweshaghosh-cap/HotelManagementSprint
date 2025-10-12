package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.RoomAmenity;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Room;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.RoomAmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomAmenity")
public class RoomAmenityController {

    @Autowired
    private RoomAmenityService roomAmenityService;

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> addRoomAmenity(@RequestBody RoomAmenity roomAmenity) {
        roomAmenityService.addRoomAmenity(roomAmenity);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "RoomAmenity added successfully"));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Amenity>> getAmenitiesByRoom(@PathVariable int roomId) {
        return ResponseEntity.ok(roomAmenityService.getAmenitiesByRoomId(roomId));
    }

    @GetMapping("/amenity/{amenityId}")
    public ResponseEntity<List<Room>> getRoomsByAmenity(@PathVariable int amenityId) {
        return ResponseEntity.ok(roomAmenityService.getRoomsByAmenityId(amenityId));
    }
}