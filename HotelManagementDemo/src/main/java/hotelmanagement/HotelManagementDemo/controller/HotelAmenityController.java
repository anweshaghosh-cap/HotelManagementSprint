package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.HotelAmenity;
import hotelmanagement.HotelManagementDemo.payload.HotelAmenityRequest;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.HotelAmenityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotelamenity")
public class HotelAmenityController {

    @Autowired
    private HotelAmenityService hotelAmenityService;
    //Too add new hotel amenity

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> addHotelAmenity(@RequestBody HotelAmenityRequest request) {
        hotelAmenityService.addHotelAmenity(request.getHotelId(), request.getAmenityId());
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Hotelamenity added successfully"));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<HotelAmenity>> getAllHotelAmenities() {
        return ResponseEntity.ok(hotelAmenityService.getAllHotelAmenities());
    }
}