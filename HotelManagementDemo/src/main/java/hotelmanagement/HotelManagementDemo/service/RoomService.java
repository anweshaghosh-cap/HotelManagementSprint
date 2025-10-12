package hotelmanagement.HotelManagementDemo.service;

import hotelmanagement.HotelManagementDemo.exception.ResourceNotFoundException;
import hotelmanagement.HotelManagementDemo.model.Room;
import hotelmanagement.HotelManagementDemo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public Room saveRoom(Room room) {
        // You can add validation or duplication checks here if needed
        return repository.save(room);
    }

    public List<Room> fetchAllRooms() {
        List<Room> roomList = repository.findAll();
        if (roomList.isEmpty()) {
            throw new ResourceNotFoundException("ROOM_FETCH_ERROR", "No rooms available in the system.");
        }
        return roomList;
    }

    public Room findRoomById(int roomId) {
        return repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("ROOM_NOT_FOUND", "Room with ID " + roomId + " not found."));
    }

    public Room modifyRoom(int roomId, Room newRoomData) {
        Room existingRoom = findRoomById(roomId);

        existingRoom.setRoomNumber(newRoomData.getRoomNumber());
        existingRoom.setAvailable(newRoomData.isAvailable());
        existingRoom.setRoomType(newRoomData.getRoomType());

        return repository.save(existingRoom);
    }

    public void removeRoom(int roomId) {
        Room roomToDelete = findRoomById(roomId);
        repository.delete(roomToDelete);
    }

    public List<Room> fetchAvailableRoomsByType(int typeId) {
        List<Room> availableRooms = repository.findAvailableRoomsByType(typeId);
        if (availableRooms.isEmpty()) {
            throw new ResourceNotFoundException("ROOM_TYPE_EMPTY", "No available rooms found for type ID: " + typeId);
        }
        return availableRooms;
    }

    public List<Room> fetchRoomsByLocation(String location) {
        List<Room> locatedRooms = repository.findRoomsByLocation(location);
        if (locatedRooms.isEmpty()) {
            throw new ResourceNotFoundException("LOCATION_EMPTY", "No rooms found in location: " + location);
        }
        return locatedRooms;
    }

    public List<Room> fetchRoomsByAmenity(int amenityId) {
        List<Room> amenityRooms = repository.findRoomsByAmenity(amenityId);
        if (amenityRooms.isEmpty()) {
            throw new ResourceNotFoundException("AMENITY_EMPTY", "No rooms found with amenity ID: " + amenityId);
        }
        return amenityRooms;
    }
}