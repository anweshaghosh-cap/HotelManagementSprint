package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.RoomAmenity;
import hotelmanagement.HotelManagementDemo.model.Amenity;
import hotelmanagement.HotelManagementDemo.model.Room;
import hotelmanagement.HotelManagementDemo.repository.RoomAmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAmenityService {

    @Autowired
    private RoomAmenityRepository roomAmenityRepository;

    public RoomAmenity addRoomAmenity(RoomAmenity roomAmenity) {
        // Optional: Add duplicate check logic if needed
        return roomAmenityRepository.save(roomAmenity);
    }

    public List<Amenity> getAmenitiesByRoomId(int roomId) {
        List<Amenity> amenities = roomAmenityRepository.findAmenitiesByRoomId(roomId);
        if (amenities.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No amenities found for the given room");
        }
        return amenities;
    }

    public List<Room> getRoomsByAmenityId(int amenityId) {
        List<Room> rooms = roomAmenityRepository.findRoomsByAmenityId(amenityId);
        if (rooms.isEmpty()) {
            throw new ResourceNotFoundException("GETALLFAILS", "No rooms found with the given amenity");
        }
        return rooms;
    }
}