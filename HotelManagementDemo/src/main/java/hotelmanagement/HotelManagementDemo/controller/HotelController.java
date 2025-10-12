package hotelmanagement.HotelManagementDemo.controller;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Hotel;
import hotelmanagement.HotelManagementDemo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    

    @GetMapping("/amenity/{amenityId}")
    public ResponseEntity<List<Hotel>> getHotelsByAmenity(@PathVariable int amenityId) {
        List<Hotel> hotels = hotelService.getHotelsByAmenityId(amenityId);
        return ResponseEntity.ok(hotels);
    }
    
    @GetMapping("/{hotelId}/amenities")
    public ResponseEntity<List<Amenity>> getAmenitiesByHotel(@PathVariable int hotelId) {
        return ResponseEntity.ok(hotelService.getAmenitiesByHotelId(hotelId));
    }
    

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> createHotel(@RequestBody Hotel hotel) {
        hotelService.createHotel(hotel);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Hotel added successfully"));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable int hotelId) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }


    @PutMapping("/update/{hotelId}")
    public ResponseEntity<SuccessResponse> updateHotel(@PathVariable int hotelId, @RequestBody Hotel hotel) {
        hotelService.updateHotel(hotelId, hotel);
        return ResponseEntity.ok(new SuccessResponse("UPDATESUCCESS", "Hotel updated successfully"));
    }


    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<SuccessResponse> deleteHotel(@PathVariable int hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS","Hotel deleted successfully"));
    }
    
}