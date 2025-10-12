package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.RoomType;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/RoomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> createRoomType(@RequestBody RoomType roomType) {
        roomTypeService.createRoomType(roomType);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "RoomType added successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeService.getAllRoomTypes());
    }

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable int roomTypeId) {
        return ResponseEntity.ok(roomTypeService.getRoomTypeById(roomTypeId));
    }

    @PutMapping("/update/{roomTypeId}")
    public ResponseEntity<SuccessResponse> updateRoomType(@PathVariable int roomTypeId, @RequestBody RoomType roomType) {
        roomTypeService.updateRoomType(roomTypeId, roomType);
        return ResponseEntity.ok(new SuccessResponse("UPDATESUCCESS", "RoomType updated successfully"));
    }

    @DeleteMapping("/delete/{roomTypeId}")
    public ResponseEntity<SuccessResponse> deleteRoomType(@PathVariable int roomTypeId) {
        roomTypeService.deleteRoomType(roomTypeId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS", "RoomType deleted successfully"));
    }
}