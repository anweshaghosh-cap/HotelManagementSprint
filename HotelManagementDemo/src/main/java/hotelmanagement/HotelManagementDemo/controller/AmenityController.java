package hotelmanagement.HotelManagementDemo.controller;

import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.payload.SuccessResponse;
import hotelmanagement.HotelManagementDemo.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenity")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;
    // to post a list of amenities 

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse> handleAmenityCreation(@RequestBody Amenity newAmenity) {
        amenityService.createAmenity(newAmenity);
        return ResponseEntity.ok(new SuccessResponse("POSTSUCCESS", "Amenity added successfully"));
    }

    @PutMapping("/update/{amenityId}")
    public ResponseEntity<SuccessResponse> handleAmenityUpdate(@PathVariable int amenityId, @RequestBody Amenity updatedAmenity) {
        amenityService.updateAmenity(amenityId, updatedAmenity);
        return ResponseEntity.ok(new SuccessResponse("UPDATESUCCESS", "Amenity updated successfully"));
    }
//deleting a specific amenity
    @DeleteMapping("/{amenityId}")
    public ResponseEntity<SuccessResponse> handleAmenityDeletion(@PathVariable int amenityId) {
        amenityService.deleteAmenity(amenityId);
        return ResponseEntity.ok(new SuccessResponse("DELETESUCCESS", "Amenity deleted successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Amenity>> retrieveAllAmenities() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    @GetMapping("/{amenityId}")
    public ResponseEntity<Amenity> retrieveAmenityById(@PathVariable int amenityId) {
        return ResponseEntity.ok(amenityService.getAmenityById(amenityId));
    }
}